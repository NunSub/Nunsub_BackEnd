package dmu.noonsub_backend.domain.openbanking;

import com.fasterxml.jackson.databind.ObjectMapper;
import dmu.noonsub_backend.domain.openbanking.dto.OpenBankingUserInfoResponseDto;
import dmu.noonsub_backend.domain.openbanking.dto.OpenBankingTokenResponseDto;
import dmu.noonsub_backend.domain.openbanking.dto.TokenExchangeRequestDto;
import dmu.noonsub_backend.domain.openbanking.dto.TransactionDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@Component
@RequiredArgsConstructor
public class OpenBankingClient {

    private final CloseableHttpClient openBankingHttpClient;
    private final OpenBankingProperties properties;
    private final ObjectMapper objectMapper;
    private final CloseableHttpClient defaultHttpClient = HttpClients.createDefault(); // Plain client for mock

    private CloseableHttpClient getClient() {
        // properties.getBaseUrl()가 mock 주소를 포함하는지 확인
        if (properties.getBaseUrl() != null && properties.getBaseUrl().contains("localhost")) {
            return defaultHttpClient;
        }
        return openBankingHttpClient;
    }

    public OpenBankingTokenResponseDto exchangeToken(TokenExchangeRequestDto requestDto) {
        // 1. 토큰 발급 요청을 위한 POST 객체 생성
        HttpPost post = new HttpPost(properties.getTokenUrl());

        // 2. 요청 파라미터 설정
        List<BasicNameValuePair> params = Arrays.asList(
                new BasicNameValuePair("grant_type", "authorization_code"),
                new BasicNameValuePair("code", requestDto.getCode()),
                new BasicNameValuePair("client_id", properties.getClientId()),
                new BasicNameValuePair("client_secret", properties.getClientSecret()),
                new BasicNameValuePair("redirect_uri", properties.getRedirectUri())
        );
        post.setEntity(new UrlEncodedFormEntity(params, StandardCharsets.UTF_8));

        // 3. mTLS가 적용된 HttpClient로 요청 실행
        try (CloseableHttpResponse response = getClient().execute(post)){
            String responseBody = EntityUtils.toString(response.getEntity());
            int statusCode = response.getStatusLine().getStatusCode();

            if (statusCode != 200) {
                throw new RuntimeException("Failed to exchange open banking token. Status: " + statusCode + ", Body: " + responseBody);
            }
            if(responseBody == null || responseBody.trim().isEmpty()) {
                throw new RuntimeException("오픈뱅킹 유저 정보를 가져오지 못했습니다 info : 응답 Body가 비었습니다.");
            }
            return objectMapper.readValue(responseBody, OpenBankingTokenResponseDto.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public OpenBankingUserInfoResponseDto getUserInfo(String accessToken, String userSeqNo) {
        // 1. 사용자 정보 조회 API를 위한 GET 객체 생성
        String url = UriComponentsBuilder.fromHttpUrl(properties.getBaseUrl() + "/v2.0/user/me")
                .queryParam("user_seq_no", userSeqNo)
                .encode()
                .toUriString();
        HttpGet get = new HttpGet(url);
        log.info("url : {}", url);

        // 2. 헤더에 Access Token 설정
        get.setHeader("Authorization", "Bearer " + accessToken);
        log.info("accessToken : {}", accessToken);
        log.info(">> [CALL] OpenBankingClient.getUserInfo - URL: {}, UserSeqNo: {}", url, userSeqNo);

        // 3. mTLS가 적용된 HttpClient로 요청 실행
        try (CloseableHttpResponse response = getClient().execute(get)){
            String responseBodies = response.getEntity() != null
                    ? EntityUtils.toString(response.getEntity())
                    : null;
            int statusCode = response.getStatusLine().getStatusCode();


            log.info("<< [RECV] OpenBanking API Response: status={}, body={}", statusCode, responseBodies);

            if (statusCode != 200) {
                throw new RuntimeException("Failed to fetch open banking user info. Status: " + statusCode + ", Body: " + responseBodies);
            }
            if (responseBodies == null || responseBodies.trim().isEmpty()) {
                // 최초 에러가 발생한 지점
                log.error("!! Response body is empty, cannot map to DTO.");
                throw new RuntimeException("오픈뱅킹 유저 정보를 가져오지 못했습니다: 응답 Body가 비었습니다.");
            }

            return objectMapper.readValue(responseBodies, OpenBankingUserInfoResponseDto.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public TransactionDto.OpenBankingResponse getTransactionHistory(
            String accessToken, String fintechUseNum, String fromDate, String toDate,
            String sortOrder, String inquiryType, String beforeInquiryTraceInfo) {
        String tranDtime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));

        UriComponentsBuilder urlBuilder = UriComponentsBuilder.fromHttpUrl(properties.getBaseUrl() + "/v2.0/account/transaction_list/fin_num")
                .queryParam("bank_tran_id", generateBankTranId())
                .queryParam("fintech_use_num", fintechUseNum)
                .queryParam("inquiry_type", inquiryType)
                .queryParam("inquiry_base", "D")
                .queryParam("from_date", fromDate)
                .queryParam("to_date", toDate)
                .queryParam("sort_order", sortOrder)
                .queryParam("tran_dtime", tranDtime);

        if (beforeInquiryTraceInfo != null && !beforeInquiryTraceInfo.isEmpty()){
            urlBuilder.queryParam("before_inquiry_trace_info", beforeInquiryTraceInfo);
        }

        String url = urlBuilder.encode().toUriString();

        HttpGet get = new HttpGet(url);
        get.setHeader("Authorization", "Bearer " + accessToken);
        try (CloseableHttpResponse response = getClient().execute(get)){
            String responseBody = EntityUtils.toString(response.getEntity());
            int statusCode = response.getStatusLine().getStatusCode();

            if (statusCode != 200) {
                throw new RuntimeException("Failed to get transaction history. Status: " + statusCode + ", Body: " + responseBody);
            }
            return objectMapper.readValue(responseBody, TransactionDto.OpenBankingResponse.class);
        } catch (Exception e) {
            throw new RuntimeException("Error during getting transaction history.", e);
        }
    }

    private String generateBankTranId() {
        String clientUseCode = properties.getClientUseCode();
        String randomNum = String.format("%09d", ThreadLocalRandom.current().nextInt(1_000_000_000));
        return clientUseCode + "U" + randomNum;
    }

    public OpenBankingTokenResponseDto refreshToken(String refreshToken) {
        HttpPost post = new HttpPost(properties.getTokenUrl());

        List<BasicNameValuePair> params = Arrays.asList(
                new BasicNameValuePair("grant_type", "refresh_token"),
                new BasicNameValuePair("client_id", properties.getClientId()),
                new BasicNameValuePair("client_secret", properties.getClientSecret()),
                new BasicNameValuePair("refresh_token", refreshToken),
                new BasicNameValuePair("scope", properties.getScope())
        );
        post.setEntity(new UrlEncodedFormEntity(params, StandardCharsets.UTF_8));

        try (CloseableHttpResponse response = getClient().execute(post)) {
            String responseBody = EntityUtils.toString(response.getEntity());
            int statusCode = response.getStatusLine().getStatusCode();

            if (statusCode != 200) {
                throw new RuntimeException("Failed to refresh open banking token. Status: " + statusCode + ", Body: " + responseBody);
            }
            return objectMapper.readValue(responseBody, OpenBankingTokenResponseDto.class);
        } catch (Exception e) {
            throw new RuntimeException("Error during open banking token refresh.", e);
        }
    }
}
