
FROM eclipse-temurin:21.0.2_13-jdk-alpine as build
WORKDIR /helidon
COPY . .
RUN ./mvnw package -DskipTests
RUN echo "done!"

FROM eclipse-temurin:21.0.2_13-jre-alpine
WORKDIR /helidon
COPY --from=build /helidon/target/order.jar ./
COPY --from=build /helidon/target/libs ./libs
CMD ["java", "--enable-preview", "-jar", "order.jar"]
EXPOSE 8080
