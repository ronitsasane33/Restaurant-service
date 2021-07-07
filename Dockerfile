FROM openjdk:8
ADD target/hamburger-company.jar hamburger-company.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar","hamburger-company.jar"]