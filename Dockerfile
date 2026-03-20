FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn -f ezz/pom.xml clean package -DskipTests

FROM eclipse-temurin:17-jdk-alpine
COPY --from=build /ezz/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]