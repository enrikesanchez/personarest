FROM maven:3-openjdk-11-slim as build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src src
RUN mvn package -DskipTests

FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
COPY scripts/wait-for-it.sh .
RUN chmod +x wait-for-it.sh
EXPOSE 8080
ENTRYPOINT ["./wait-for-it.sh", "db:3306", "--strict", "--timeout=300", "--", "java", "-jar", "app.jar"]