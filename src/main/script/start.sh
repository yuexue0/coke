#!/bin/bash

BASEDIR=$(cd `dirname $0`; pwd)
SERVICE_NAME=coke
JAR_NAME=coke-@activatedProperties@.jar
SHUTDOWN_MAX_WAIT_SECS=5

LOG_DIR=./

PID=0
EXIT_CODE=0

getpid() {
   PID=`ps aux |grep $JAR_NAME |grep -v grep |awk '{print $2}'`
   # 无服务进程id，使用默认值0
   if [ -z "$PID" ]; then
        PID=0
   fi
}
#########################start####################
start() {

#export JAVA_OPTS="-Xms2048m -Xmx2048m -Xss256k -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=384m -XX:NewSize=2048m -XX:MaxNewSize=2048m -XX:SurvivorRatio=8"
export JAVA_OPTS="-Xmx512m -XX:SurvivorRatio=8"
export JAVA_OPTS="$JAVA_OPTS -XX:+UseParNewGC -XX:ParallelGCThreads=4 -XX:MaxTenuringThreshold=9 -XX:+UseConcMarkSweepGC -XX:+DisableExplicitGC -XX:+UseCMSInitiatingOccupancyOnly -XX:+ScavengeBeforeFullGC -XX:+UseCMSCompactAtFullCollection -XX:+CMSParallelRemarkEnabled -XX:CMSFullGCsBeforeCompaction=9 -XX:CMSInitiatingOccupancyFraction=60 -XX:+CMSClassUnloadingEnabled -XX:SoftRefLRUPolicyMSPerMB=0 -XX:+CMSPermGenSweepingEnabled -XX:CMSInitiatingPermOccupancyFraction=70 -XX:+ExplicitGCInvokesConcurrent -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintGCApplicationConcurrentTime -XX:+PrintHeapAtGC -XX:+UseGCLogFileRotation -XX:+HeapDumpOnOutOfMemoryError -XX:-OmitStackTraceInFastThrow -Duser.timezone=Asia/Shanghai -Dclient.encoding.override=UTF-8 -Dfile.encoding=UTF-8 -Djava.security.egd=file:/dev/./urandom"

export JAVA_OPTS="$JAVA_OPTS -Xloggc:$LOG_DIR/gc.log -XX:NumberOfGCLogFiles=5 -XX:GCLogFileSize=5M -XX:HeapDumpPath=$LOG_DIR/HeapDumpOnOutOfMemoryError/"

  getpid

  if [ 0 -ne $PID ]
  then
      echo "$SERVICE_NAME process is already running, pid=$PID"
      EXIT_CODE=201
  else
      cd $BASEDIR
      cd ..
      echo "starting $SERVICE_NAME"
      java $JAVA_OPTS -jar $JAR_NAME

      getpid

      if [ 0 -eq $PID ]
      then
        echo "$SERVICE_NAME startup failed"
        EXIT_CODE=202
      else
        echo "$SERVICE_NAME startup pid=$PID"
      fi
  fi
}

start
exit $EXIT_CODE