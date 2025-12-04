FROM eclipse-temurin:17-jre-alpine AS runtime

# Keep base image minimal and time-zone predictable
ENV TZ=UTC \
    JAVA_OPTS="-XX:MaxRAMPercentage=75 -XX:+UseContainerSupport -Dserver.port=8081"
    
# Create a non-root user and group for security
RUN addgroup -S appuser \
    && adduser -S -G appuser -H -s /sbin/nologin appuser

# Set working directory for the application
WORKDIR /app

# Copy the Spring Boot fat JAR built by Maven
COPY target/insurance-registration-service.jar /app/insurance-registration-service.jar

# Secure permissions:
# - /app directory: only appuser can access (700)
# - JAR file: read & execute only by owner (500)
RUN chown -R appuser:appuser /app \
    && chmod 700 /app \
    && chmod 500 /app/insurance-registration-service.jar

# Expose application port
EXPOSE 8081

# Run as non-root user
USER appuser

# Entry point: start Spring Boot with container-aware JVM options
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app/insurance-registration-service.jar"]
