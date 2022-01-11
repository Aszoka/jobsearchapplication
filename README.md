# jobsearchapplication
simple job searching platform

This application was created with Spring Boot and it is a Maven project.
Java edition: jdk 11.

For running this application, download and install said jdk version.
</br>
Next, install Apache Maven and add the bin folder to your PATH
https://maven.apache.org/install.html
</br>
</br>
Dependencies used in this project: </br>
</br>
  Spring boot starter web as it is a web application </br>
  Spring boot starter data jpa and h2 for creating an in memory database and implement queries </br>
  validation api and hibernate validator for user input validations </br>
  spring fox dependencies to enable swagger and its ui surface </br>
  </br>
For detail, check the pom.xml file in the project.
</br>
Configure the environment variables for using the in memory database, check the application.properties file (DB_USER, DB_PASSWORD)</br>
</br>
The application is running on port 8081</br>
Accessing Swagger-ui, go to: http://localhost:8081/swagger-ui/</br>
</br>
When running the application, the CommandlineRunner uploads some test data. For testing the endpoints, use these datas (for the Apikey, which is generated over with every run, 
please check for the actual valid apikey)</br>
</br>
The application enables the registration of client-applications for creating job opportunities. After registration the client gets an Api-key for accessing further endpoints.
The already posted jobs are searchable by title and location.
</br>
</br>
Future development ideas:</br>
</br>
  correct the existing code (for.ex validation error while searching doesn't provide the right message like with other requests)</br>
  implementing test cases for business logic, endpoints, database connection</br>
  adding security, users can login and verify themselves with an accesstoken for example JWT</br>
  authorization for distinct admins, job searchers and posters</br>
  option for generating new apikeys, give expiration time, edit access (apikey with full access or partial)</br>
  users can save and apply for jobs, upload files, for example CV</br>
  messaging service</br>


