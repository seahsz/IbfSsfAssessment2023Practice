FROM eclipse-temurin:23.0.1_11-jdk AS builder
# AS builder only for multi stage docker

WORKDIR /compiledDir

COPY src src
COPY .mvn .mvn
COPY pom.xml .
COPY mvnw .

RUN chmod a+x ./mvnw && ./mvnw package -Dmaven.test.skip=true

FROM eclipse-temurin:23.0.1_11-jdk

WORKDIR /myapp

# COPY --from=builder /compiledDir/target/<jar file name of compiled jar> <jar name that you want it to be>
COPY --from=builder /compiledDir/target/ibfAssessment2023-0.0.1-SNAPSHOT.jar ibfAssessment2023.jar

# Set the environment variables

ENV SPRING_DATA_REDIS_HOST=localhost
ENV SPRING_DATA_REDIS_PORT=6379
ENV SPRING_DATA_REIDS_USERNAME=
ENV SPRING_DATA_REDIS_PASSWORD=

ENV PORT=4000

EXPOSE ${PORT}

# or EXPOSE ${SERVER_PORT}

ENTRYPOINT java -jar ibfAssessment2023.jar