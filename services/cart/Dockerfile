
FROM eclipse-temurin:19-jdk-alpine as build
WORKDIR /helidon
COPY . .
RUN ./mvnw package -DskipTests
RUN echo "done!"

FROM eclipse-temurin:19-jre-alpine
WORKDIR /helidon
COPY --from=build /helidon/target/cart.jar ./
COPY --from=build /helidon/target/libs ./libs
CMD ["java", "--enable-preview", "-jar", "cart.jar"]
EXPOSE 8080
