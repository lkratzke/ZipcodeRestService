# ZipcodeRestService
Demo project that exposes US zipcodes as a REST service

This project demonstrates the following technologies:
* SpringBoot
* Spring MVC
* Spring HATEOAS
* MySQL/JDBC

Data for the MySQL database that this project uses can be obtained from https://github.com/lkratzke/ZipcodeDBImportUtil

Use that application to either generate a SQL file for MySQL or use the program to import the data directly into MySQL. 
That project also contains a CSV file containing the source data if you wish to do something else.

The only required configuration is in the `application.properties` file where you configure your database connection.

Compile using `mvn clean install` and access the application at http://localhost:8080/rest

Enjoy, learn, and have fun!
