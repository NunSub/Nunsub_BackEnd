#!/bin/sh

# 스크립트 실행 중 오류 발생 시 즉시 중단
set -e

# Cloud SQL 인증 프록시를 백그라운드에서 실행
# INSTANCE_CONNECTION_NAME 환경 변수는 docker-compose.yml을 통해 전달받음
./cloud-sql-proxy --port 5432 $INSTANCE_CONNECTION_NAME &

# 프록시가 시작될 시간을 잠시 (3초) 기다려줌
sleep 3

# Spring Boot 애플리케이션 실행 (java -jar 명령어)
# exec java -jar app.jar
java -jar app.jar