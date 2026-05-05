# Stage 1: build
FROM maven:3.9.11-amazoncorretto-21-alpine AS build

WORKDIR /app

# аргумент — какой модуль собираем
ARG MODULE

# 1. Копируем POM'ы
COPY pom.xml .
COPY stat-support/pom.xml stat-support/pom.xml
COPY stat-support/stat-contract/pom.xml stat-support/stat-contract/pom.xml
COPY stat-support/stat-model/pom.xml stat-support/stat-model/pom.xml
COPY stat-support/stat-client/pom.xml stat-support/stat-client/pom.xml
COPY stat-support/stat-server/pom.xml stat-support/stat-server/pom.xml
COPY order-db-migration/pom.xml order-db-migration/pom.xml
COPY order-service/pom.xml order-service/pom.xml
COPY mock-service/pom.xml mock-service/pom.xml

# 2. Качаем зависимости
RUN mvn -e -C dependency:go-offline

# 3. Копируем исходники
COPY . .

# 4. Собираем нужный модуль
RUN mvn -B clean package -DskipTests -pl ${MODULE} -am

# Stage 2: runtime
FROM amazoncorretto:21-alpine

WORKDIR /app

RUN apk add curl

ARG MODULE

COPY --from=build /app/${MODULE}/target/${MODULE}-*.jar app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]