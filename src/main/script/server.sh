#!/bin/bash

BASEDIR=./
SERVICE_NAME=auth-service
JAR_NAME=auth-service-@activatedProperties@.jar
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

export JAVA_OPTS="-Xms6144m -Xmx6144m -Xss256k -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=384m -XX:NewSize=4096m -XX:MaxNewSize=4096m -XX:SurvivorRatio=8"
export JAVA_OPTS="$JAVA_OPTS -XX:+UseParNewGC -XX:ParallelGCThreads=4 -XX:MaxTenuringThreshold=9 -XX:+UseConcMarkSweepGC -XX:+DisableExplicitGC -XX:+UseCMSInitiatingOccupancyOnly -XX:+ScavengeBeforeFullGC -XX:+UseCMSCompactAtFullCollection -XX:+CMSParallelRemarkEnabled -XX:CMSFullGCsBeforeCompaction=9 -XX:CMSInitiatingOccupancyFraction=60 -XX:+CMSClassUnloadingEnabled -XX:SoftRefLRUPolicyMSPerMB=0 -XX:+CMSPermGenSweepingEnabled -XX:CMSInitiatingPermOccupancyFraction=70 -XX:+ExplicitGCInvokesConcurrent -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintGCApplicationConcurrentTime -XX:+PrintHeapAtGC -XX:+UseGCLogFileRotation -XX:+HeapDumpOnOutOfMemoryError -XX:-OmitStackTraceInFastThrow -Duser.timezone=Asia/Shanghai -Dclient.encoding.override=UTF-8 -Dfile.encoding=UTF-8 -Djava.security.egd=file:/dev/./urandom"

export JAVA_OPTS="$JAVA_OPTS -Xloggc:$LOG_DIR/gc.log -XX:NumberOfGCLogFiles=5 -XX:GCLogFileSize=5M -XX:HeapDumpPath=$LOG_DIR/HeapDumpOnOutOfMemoryError/"

  getpid

  if [ 0 -ne $PID ]
  then
      echo "$SERVICE_NAME process is already running, pid=$PID"
      EXIT_CODE=201
  else
      cd $BASEDIR
      echo "starting $SERVICE_NAME"
      nohup java $JAVA_OPTS -jar $JAR_NAME > /dev/null 2>&1 &

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
#########################stop####################
stop() {
  getpid

  if [ 0 -eq $PID ]
  then
      echo "$SERVICE_NAME process is not running"
      EXIT_CODE=203
  else
      kill $PID
      echo "kill $PID"

      getpid

      SHUTDOWN_WAIT_SECS=0
      while (( $PID > 0 ))
      do
          sleep 1
          SHUTDOWN_WAIT_SECS=`expr $SHUTDOWN_WAIT_SECS + 1`

          getpid

          if [ $SHUTDOWN_WAIT_SECS -eq $SHUTDOWN_MAX_WAIT_SECS ]
          then
              break
          fi
      done

      if [ 0 -ne $PID ]
      then
          kill -9 $PID
          echo "kill -9 $PID"
          EXIT_CODE=204
      fi
  fi
}
#########################restart####################
restart(){
  echo restart
    stop
    start
}

case "$1" in
  start)
        start
        exit $EXIT_CODE
        ;;
  stop)
        stop
        exit $EXIT_CODE
        ;;
  restart)
        restart
        exit $EXIT_CODE
        ;;
  ?|help)
        echo $"Usage: $0 {start|stop|restart|help|?}"
        ;;
  *)
        echo $"Usage: $0 {help|?}"
esac