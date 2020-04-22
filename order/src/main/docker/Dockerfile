FROM java:8
VOLUME /tmp
ADD order-1.0-SNAPSHOT.jar app.jar
RUN  bash -c 'touch /app.jar'
EXPOSE 2001
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]