# Project Curium
Curium Project is an opensource software for School Management

## Requrement
1. Java 1.8
2. Tomcat 7

## For development environment
1. Import the project in eclipse as existing maven project
2. Right-Click on project view and select Run As -> Run on server
3. Configure Tomcat server and the project should run

Application can be accessed via browser on: http://localhost:8080/SchoolManagement/

### In case of errors
If error is seen while running the project as Run on server with the error as:
The server does not support version 4.0 of the J2EE Web module specification.

Then we have to downgrade the Dynamic Web Module version to version 3.0 from 4.0.

Follow the steps given [here](https://stackoverflow.com/questions/6922391/the-server-does-not-support-version-3-0-of-the-j2ee-web-module-specification) to achieve that. 


If the project runs but the webpage is not opening then need to add webapps to the list of deployment artifacts
Follow the steps given [here](https://stackoverflow.com/questions/2641379/eclipse-maven-web-application-can-not-run-on-server-anymore) to achieve that.

