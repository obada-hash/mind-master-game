FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn -f ezz/pom.xml clean package -DskipTests

FROM openjdk:17-alpine
COPY --from=build /ezz/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]