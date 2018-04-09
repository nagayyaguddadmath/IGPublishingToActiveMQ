Assignment developed by Nagayya:

Spring Boot Web Application that will allow a user to upload a file (example attached),
de-serialize each line into an Order object to be posted as a message onto an ActiveMQ destination as JSON.

Junits are still in progress

Below is the command to run the project:

mvn  spring-boot:run

Below is the link to test locally:
http://localhost:8080/

Below is the command to run all testcases
mvn test

Please Note that this code is not production ready because of following reasons:
1) Code is not Transactional
2) Error handling should be improved
3) performance can be improved by adding multi-threading publishing
4) Logging can be added
5) Integration tests