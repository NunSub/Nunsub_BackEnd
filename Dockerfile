# --- 1. 빌드(Build) 스테이지 ---
# JDK 17 Temurin 이미지를 빌드 환경으로 사용합니다.
FROM eclipse-temurin:21-jdk-jammy as builder

# 작업 디렉터리를 생성합니다.
WORKDIR /workspace/app

# Gradle 래퍼와 빌드 스크립트를 먼저 복사합니다.
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .

# gradlew를 실행하기 전에 실행 권한을 부여하는 단계를 추가합니다.
RUN chmod +x ./gradlew

# Gradle 의존성을 다운로드하여 캐싱 효과를 극대화합니다.
# 소스 코드가 변경되지 않으면 이 단계는 캐시를 사용해 빠르게 넘어갑니다.
RUN ./gradlew build -x test --parallel --build-cache || ./gradlew dependencies

# 애플리케이션 소스 코드를 복사합니다.
COPY src src

# 애플리케이션을 빌드하고 실행 가능한 jar 파일을 생성합니다.
RUN ./gradlew build -x test --parallel


# --- 2. 실행(Runtime) 스테이지 ---
# 더 작고 안전한 JRE(Java Runtime Environment) 이미지를 최종 실행 환경으로 사용합니다.
FROM eclipse-temurin:21-jre-jammy

# 작업 디렉터리를 생성합니다.
WORKDIR /workspace/app

ENV TZ=Asia/Seoul

RUN wget https://storage.googleapis.com/cloud-sql-connectors/cloud-sql-proxy/v2.8.2/cloud-sql-proxy.linux.amd64 -O cloud-sql-proxy && \
    chmod +x cloud-sql-proxy

COPY --chown=root:root start.sh .

# 빌드 스테이지에서 생성된 jar 파일을 실행 스테이지로 복사합니다.
# build/libs/*.jar 패턴으로 생성된 jar 파일을 app.jar 라는 이름으로 복사합니다.
COPY --from=builder /workspace/app/build/libs/*.jar app.jar

# 컨테이너가 시작될 때 실행될 명령어입니다.
# java -jar app.jar를 실행하여 Spring Boot 애플리케이션을 시작합니다.
ENTRYPOINT ["./start.sh"]