FROM eclipse-temurin:21-jdk-jammy AS build

WORKDIR /workspace

COPY app/mvnw app/mvnw
COPY app/.mvn app/.mvn
COPY app/pom.xml app/pom.xml

RUN chmod +x app/mvnw
RUN ./app/mvnw -f app/pom.xml dependency:go-offline

COPY app/src app/src

RUN ./app/mvnw -f app/pom.xml clean package -DskipTests

FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

RUN useradd --system --uid 1001 spring
COPY --from=build /workspace/app/target/*.jar app.jar

USER spring

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
