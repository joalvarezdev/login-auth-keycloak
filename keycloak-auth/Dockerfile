FROM joalvarezdev/springboot-alpine-17:latest

MAINTAINER joalvarez

COPY target/Keycloak Auth.jar .

EXPOSE 8090

ENTRYPOINT ["java","-jar","Keycloak Auth.jar"]
