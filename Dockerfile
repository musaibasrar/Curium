# Stage 1: Build
FROM maven:3.9.6-eclipse-temurin-17 as builder

# Set working directory
WORKDIR /build

# Increase Maven heap (optional, helps large builds)
ENV MAVEN_OPTS="-Xmx1024m"

# Copy only the files needed for dependency resolution first to leverage docker cache
COPY pom.xml .
COPY .mvn .mvn
COPY mvnw .

# Copy source code
COPY src ./src

# Build the WAR inside the image (build happens here, not on the host)
RUN mvn -B clean package -DskipTests

# Final image: Tomcat to run the WAR
FROM tomcat:9.0-jdk17-openjdk

# Installing libraries required for reports
# RUN apt-get update && apt-get install -y fontconfig libfreetype6 && rm -rf /var/lib/apt/lists/*

# Remove default webapps so our app is served as ROOT
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy the generated WAR from the builder stage; rename to ROOT.war so it's served at /
COPY --from=builder /build/target/*.war /usr/local/tomcat/webapps/school.war

# Expose default Tomcat port
EXPOSE 8080

# Run Tomcat in foreground
CMD ["catalina.sh", "run"]


# Command to build
# docker build --no-cache --progress=plain -t curium:latest -f Dockerfile .