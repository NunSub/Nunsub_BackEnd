package dmu.noonsub_backend.global.auth.config;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.tomcat.jni.SSL;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;

import javax.net.ssl.SSLContext;
import javax.swing.*;
import java.io.InputStream;
import java.security.KeyStore;

@Configuration
public class HttpClientConfig {
    @Value("${openbanking.mtls.cert-path}")
    private Resource certResource;

    @Value("${openbanking.mtls.cert-password}")
    private String certPassword;

    @Profile("prod")
    @Bean
    public CloseableHttpClient openBankingHttpClient(
            @Value("${openbanking.mtls.cert-path}") Resource certResource,
            @Value("${openbanking.mtls.cert-password}") String certPassword
    ) throws Exception {
        // 1. PKCS12 타입의 KeyStore 인스턴스 생성
        KeyStore keyStore = KeyStore.getInstance("PKCS12");

        // 2. 클래스패스에 있는 인증서 파일을 읽어옴
        try (InputStream inputStream = certResource.getInputStream()) {
            keyStore.load(inputStream, certPassword.toCharArray());
        }

        // 3. KeyStore를 사용하여 mTLS를 위한 SSLContext 설정
        SSLContext sslContext = SSLContexts.custom()
                .loadKeyMaterial(keyStore, certPassword.toCharArray())
                .build();

        // 4. 생성된 SSLContext를 사용하여 CloseableHttpClient 생성
        SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(sslContext);

        return HttpClients.custom()
                .setSSLSocketFactory(sslSocketFactory)
                .build();
    }

    @Profile("!prod")
    @Bean
    public CloseableHttpClient openBankingHttpClientDefault() {
        return HttpClients.createDefault();
    }
}
