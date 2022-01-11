# jobsearchapplication
simple job searching platform

This application was created with Spring Boot and it is a Maven project.
Java edition: jdk 11.

For running this application, download and install said jdk version.

Next, install Apache Maven and add the bin folder to your PATH
https://maven.apache.org/install.html

Dependencies used in this project:
  Spring boot starter web as it is a web application
  Spring boot starter data jpa and h2 for creating an in memory database and implement queries
  validation api and hibernate validator for user input validations
  spring fox dependencies to enable swagger and its ui surface 
  
For detail, check the pom.xml file in the project.

The application enables the registration of client-applications for creating job opportunities. After registration the client gets an Api-key for accessing further endpoints.
The already posted jobs are searchable by title and location.

Future development ideas:


