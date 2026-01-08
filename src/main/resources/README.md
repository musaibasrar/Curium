# Configuration Guide for Curium Service

This document explains the configuration settings for the Curium Service. These parameters allow the user to control how the application connects to the database, handles file uploads, and manages its web interface.

## 1. General Application Settings
These settings define how the application identifies itself and how it is accessed through a web browser.

*   **Application Name**: The internal name used by the system to identify this service.
    *   `spring.application.name`: Defaults to `curium_service`.
*   **Web Context Path**: This is the "base" URL of the application. 
    *   `server.servlet.context-path`: Defaults to `/school`. This means the website will be found at `http://server-IP:port/school`.

## 2. Database Connections
The service is designed to work with a **MariaDB** database. It uses environment variables so that the database details can be changed easily without editing the code.

*   **Database URL**: DB connection URL - Set value via environment variable: `SPRING_DATASOURCE_JDBC_URL` (Default: `jdbc:mariadb://localhost:3306/`)
*   **Database Username**: DB Username - Set value via environment variable: `SPRING_DATASOURCE_USERNAME` (Default: `root`)
*   **Database Password**: DB Password - Set value via environment variable: `SPRING_DATASOURCE_PASSWORD` (Default: `root`)
*   **Database driver**: DB JDBC Driver - Set value via environment variable: `SPRING_DATASOURCE_DRIVERCLASSNAME` (Default: `org.mariadb.jdbc.Driver`)
*   **Connection Pooling**: To stay fast, the application keeps a "pool" of database connections ready.
    *   Allows up to Maximum DB connection pool-size simultaneous connections: Set value via environment variable: `MAXIMUM_POOL_SIZE` (Default: `10`)
    *   Keeps at least Minimu, DB connection pool-size connections ready at all times: Set value via environment variable: `MINIMUM_POOL_SIZE` (Default: `2`)

## 3. Multi-Tenancy Features
The application is built to support multiple "tenants" using a single installation. 

*   **Strategy**: It uses a `SCHEMA` approach, meaning data for different users is kept in separate database folders (schemas).
*   **Routing**: The application automatically decides which data schema to use based on the user's request.
*   **Default**: The default tenant is `school`, this is the default database name

## 4. File Upload Limits
These settings control the size of files (like documents or images) that users can upload to the system.

*   **Max File Size**: `spring.servlet.multipart.max-file-size` is set to `1MB`. 
*   **Header Size**: `server.max-http-header-size` is set to `20000` bytes to allow for complex web requests.


## 5. Maintenance and Monitoring
The application includes special "Actuator" tools that allow administrators to check the health of the system.

*   **Management Port**: These tools run on port `9090` (separate from the main application port for better security).
*   **Monitoring Features**: The system exposes `health` (to see if the server is running), `info` (for version details), and `loggers` (to troubleshoot errors).
*   **To list all the supported loggers**: `http://localhost:9090/actuator/loggers/`

<u>Note</u>: Avoid <i>spring.jpa.show-sql=true</i>: This property prints directly to System.out (the console), bypassing Logback configuration and appenders.

**Binding Parameters**: To see the values substituted for the ? placeholders, set the following logger to TRACE:

* Hibernate 6+: org.hibernate.orm.jdbc.bind
* Hibernate 5: org.hibernate.type.descriptor.sql.BasicBinder

To dynamically change the ROOT logging configuration

```
curl -X POST http://localhost:8080/actuator/loggers/ROOT \
     -H 'Content-Type: application/json' \
     -d '{"configuredLevel": "DEBUG"}'
```

To dynamically change the custom logging configuration

~~~
curl -i -X POST http://localhost:9090/actuator/loggers/org.ideoholic.service \
     -H "Content-Type: application/json" \
     -d '{"configuredLevel": "TRACE"}'
~~~

## 6. Safe Shutdown
To ensure no data is lost when the server is turned off or restarted, the application uses a **Graceful Shutdown** process.

*   **Graceful Exit**: `server.shutdown` is enabled.
*   **Waiting Period**: The system will wait for up to `60 seconds` for active tasks to finish before it fully closes.
