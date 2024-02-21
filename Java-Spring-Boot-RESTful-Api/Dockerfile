FROM tomcat:10-jdk11
WORKDIR /usr/local/tomcat
RUN rm -rf webapps/*
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} webapps/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "webapps/app.jar", "--spring.profiles.active=docker"]
