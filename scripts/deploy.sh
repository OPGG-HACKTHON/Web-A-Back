#!/usr/bin/env bash

REPOSITORY=/home/ubuntu/jampick

cd $REPOSITORY

JAR_NAME=$(ls $REPOSITORY/build/libs/ | grep '.jar' | tail -n 1)
JAR_PATH=$REPOSITORY/build/libs/$JAR_NAME

echo "> JAR Path: $JAR_PATH"
chmod +x $JAR_PATH

echo "> 8080 에서 구동중인 애플리케이션 pid 확인"
IDLE_PID=$(lsof -ti tcp:8080)

if [ -z ${IDLE_PID} ]
then
  echo "> 현재 구동중인 애플리케이션이 없으므로 종료하지 않습니다."
else
  echo "> kill -15 $IDLE_PID"
  kill -15 ${IDLE_PID}
  sleep 5
fi

echo "> $JAR_NAME 배포"
nohup java -jar $JAR_PATH > /var/log/jampick/nohup.out 2>&1 &