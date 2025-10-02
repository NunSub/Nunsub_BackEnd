#!/bin/sh

# 스크립트 실행 중 오류 발생 시 즉시 중단
set -e

docker compose pull redis nginx
docker compose up -d redis nginx

# Cloud SQL 인증 프록시를 백그라운드에서 실행
# INSTANCE_CONNECTION_NAME 환경 변수는 docker-compose.yml을 통해 전달받음
./cloud-sql-proxy --port 5432 $INSTANCE_CONNECTION_NAME &

# 프록시가 시작될 시간을 잠시 (3초) 기다려줌
sleep 3

ACTIVE_CONTAINER=$(docker ps --filter "name=nunsub-backend-" --format "{{.Names}}")
if [[ -n "$ACTIVE_CONTAINER" ]]; then
  echo ">> Container $ACTIVE_CONTAINER is already running."
  exit 0
fi

EXISTING_CONTAINER=$(docker ps -a --filter "name=nunsub-backend-" --format "{{.Names}}" | head -n 1)

if [[ "$EXISTING_CONTAINER" == *"blue" ]]; then
    PROFILE="blue"
elif [[ "$EXISTING_CONTAINER" == *"green" ]]; then
    PROFILE="green"
else
    PROFILE="blue"
fi

echo ">> Restarting container with profile: $PROFILE"
docker compose up -d nunsub-backend-"$PROFILE"

# Spring Boot 애플리케이션 실행 (java -jar 명령어)
# exec java -jar app.jar
java -Duser.timezone=Asia/Seoul -jar app.jar