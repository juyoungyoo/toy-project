FROM java:openjdk-8-jre

COPY build/libs/demo-1.1.jar /usr/home/
#RUN chmod +x /usr/home/demo-1.1jar
WORKDIR /usr/home

EXPOSE 8080
CMD ["java", "-Dspring.profiles.active=dev", "-jar", "demo-1.1.jar"]
