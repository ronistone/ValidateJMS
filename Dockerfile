FROM maven:3.6.0-jdk-7-alpine

ENV USER_ID 1002

RUN adduser -D -u $USER_ID -s /bin/bash messageUser
USER messageUser

EXPOSE 7399

ENV MAVEN_OPTS="-Djava.net.preferIPv4Stack=true  -Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.port=7399 -Dcom.sun.management.jmxremote.rmi.port=7399 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.local.only=false -Dcom.sun.management.jmxremote.authenticate=false -agentlib:jdwp=transport=dt_socket,address=8000,server=y,suspend=n "

ENTRYPOINT mvn exec:java -Dexec.mainClass=br.com.rgrj.Main