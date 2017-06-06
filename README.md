# Adserver

Overview
--------

You will be building a simple web application that allows a user to create ad campaigns. You should demonstrate that your code meets the functional requirements described below via unit and integration tests. There should be instructions for deploying and running the application, ideally expressed via code/configuration and not prose.

Use the language with which you feel most comfortable.

Share your project via a Git repository in GitHub.

Be prepared to walk through your code, discuss your thought process, and talk through how you might monitor and scale this application. You should also be able to demo a running instance of the host.

 
Functional Requirements
-----------------------

== Create Ad Campaign via HTTP POST

A user should be able to create an ad campaign by sending a POST request to the ad server at http://<host>/ad.  The body of the POST request must be a JSON object containing the following data:

{

"partner_id": "unique_string_representing_partner',

"duration": "int_representing_campaign_duration_in_seconds_from_now"

"ad_content": "string_of_content_to_display_as_ad"

}


The server should enforce the following invariant upon receiving a request to create a new campaign.


* Only one active campaign can exist for a given partner.

If an error is encountered, the ad server must return an appropriate response and indicate the problem to the user.  The response format is left up to the discretion of the implementer.

Storing campaign data in memory or a cookie is totally fine.

POST URL : http://localhost:8080/ad
 

== Fetch Ad Campaign for a Partner

A partner should be able to get their ad data by sending a GET request to the ad server at http://<host>/ad/<partner_id>.  Response can be delivered as a JSON object representing the active ad

If the current time is greater than a campaign's creation time + duration, then the server's response should be an error indicating that no active ad campaigns exist for the specified partner.

GET URL: http://localhost:8080/ad/11
 
Bonus
-----

The following are not required but might be nice additions to the exercise.

* Describe a fault tolerant deployment topology for your application, and the types of failures it would and would not be resilient to.

Build an executable JAR

You can run the application from the command line with Gradle or Maven. Or you can build a single executable JAR file that contains all the necessary dependencies, classes, and resources, and run that. This makes it easy to ship, version, and deploy the service as an application throughout the development lifecycle, across different environments, and so forth.

If you are using Gradle, you can run the application using ./gradlew bootRun. Or you can build the JAR file using ./gradlew build. Then you can run the JAR file:

java -jar build/libs/adServer-0.1.0.jar

If you are using Maven, you can run the application using ./mvnw spring-boot:run. Or you can build the JAR file with ./mvnw clean package. Then you can run the JAR file:

java -jar target/adServer-0.1.0.jar

* Discuss the advantages and disadvantages of your persistence mechanism.

I used h2 embeded DB for campgain storage it started when the server start.  
Advantage is it will start with as Spring Boot server started spring container take care of the rest. 
No need to create, drop or alter schema like terditional RDBMS. light weight and work as cache.
Disadvantage data flush on every restart. work as cache not sauitable for persistance of data for long term. 

* Add a URL to return a list of all campaigns as JSON.

localhost:8080/campaign

* Add support for multiple ad campaigns per partner.

added the schema for one to many relation in the domain model. we can extend that to add the partner and then their multiple campaigns as 
in Campaign domain model partner id is used as forigen Key. 

E2E Test cases :

initail level of E2E test cases were implemented which will be verified with ./gradlew build 

CURL : curl -i -X POST -d "{  \"partner_id\" : \"10\",  \"duration\" : \"20\",  \"ad_content\" : \"babajee\"  }" http://localhost:8080/ad

