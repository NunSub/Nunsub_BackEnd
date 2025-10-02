#!/bin/sh

# 스크립트 실행 중 오류 발생 시 즉시 중단
set -e

# Cloud SQL 인증 프록시를 백그라운드에서 실행
# INSTANCE_CONNECTION_NAME 환경 변수는 .env 파일을 통해 전달받음
./cloud-sql-proxy --port 5432 $INSTANCE_CONNECTION_NAME &

# 프록시가 시작될 시간을 잠시 (3초) 기다려줌
echo "Waiting for Cloud SQL Proxy to start..."
sleep 3
echo "Cloud SQL Proxy started."

# Spring Boot 애플리케이션 실행
# exec를 사용하면 현재 셸 프로세스가 java 프로세스로 대체되어 불필요한 셸 래퍼를 줄이고,
# 컨테이너의 기본 프로세스(PID 1)가 되므로 시그널 처리에 더 유리합니다.
echo "Starting Spring Boot application..."
exec java -Duser.timezone=Asia/Seoul -jar app.jar