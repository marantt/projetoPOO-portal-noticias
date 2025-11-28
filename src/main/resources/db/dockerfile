# ====== STAGE 1: BUILD ======
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copia o pom.xml e baixa dependências antes do código (cache inteligente)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copia o código fonte e compila
COPY src ./src
RUN mvn clean package -DskipTests

# ====== STAGE 2: RUNTIME ======
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copia o JAR gerado no stage de build
COPY --from=build /app/target/*.jar app.jar

# Porta padrão do Spring Boot
EXPOSE 8080

# Comando final
ENTRYPOINT ["java", "-jar", "app.jar"]
