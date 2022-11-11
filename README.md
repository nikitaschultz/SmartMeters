# SP Smart Meter Service

A Spring Boot application to simulate a Smart Meter service. 

## Requirements

For building and running the application you need: 
- [JDK 17](http://www.oracle.com/technetwork/java/javase/downloads)
- [Maven 4.0](https://maven.apache.org)

## Running the application locally

To run the application on your local machine, execute the `main` method in the `com/example/SPTest/SpTestApplication.java` class from your IDE.

Running the main file will execute the DataLoader in `com/example/SPTest/components/DataLoader.java` which will insert some test data into the H2 database. 

## Using H2 Console 

To browse the H2 database, visit [http://localhost:8081/api/smart/h2](http://localhost:8081/api/smart/h2), enter the relevant credentials (username is initially set to `sa` with an empty password) and click `Connect`.
The H2 Console settings and other configuration properties for the application are defined in `application.properties`, including:
- `spring.datasource.username` - the username for the H2 console
- `spring.datasource.password` - the password for the H2 console
- `spring.jpa.hibernate.ddl-auto` - the db initialisation strategy
- `server.servlet.context-path` - the root path of the application 
- `server.port` - the default port for the application 

## Justification and Thoughts on the Solution
### Data Models
A different approach that I considered to model the data was to have two separate classes for gas and electric meters that would inherit from an abstract meter class.
I ultimately rejected that approach as the currently known properties for either meter are the same and, even if this would be the eventual model that's decided upon, a simpler approach is more suitable due to the high level of uncertainty.

### Assumptions
I made some assumptions on the business logic through my personal experience and a small amount of research.  One such assumption was that an account could have multiple gas or electricity meters associated with it.  
Upon reflection, based on the last requirement in particular (a single comparison point between accounts for either energy type) I think this assumption was incorrect.  If I were to approach the problem again, I might model the Meter:Account relationship differently as a result.

### Performance and Coding Practices
There are areas where the solution is inefficient and contains repeated code that could be factored out to improve performance and readability.
I would like to reconsider my approach on the storage of the averageDailyUsage given the last requirement for a comparison between accounts.  Although storing this value in the database would break normalisation rules, given all readings from all accounts are required to compute this value, I think it would improve performance to store it in the database.  
The comparison value could then be retrieved by a count on the entries in the joined tables that are above or below the value for a given customer.
The field validation on the POST endpoint could be improved to include comprehensive type testing. 
I also think that I could have utilised custom queries in the repositories instead of filtering the data after getting it back from the database.
I also could add more unit tests.  After writing unit tests for the models, I decided to prioritise including at least one test for each of the other types to demonstrate a variety of test frameworks instead of exhaustive tests.   
The data loader could have been tidied and simplified but I consider this to be a low priority in the presented challenge. 

### General Approach and Learnings
The general approach I would take in this situation, given the high degree of ambiguity, is to focus on writing comprehensive test scenarios, including integration and cucumber tests, that can be used to assert there are no breaking changes when the final db design is agreed and the work is refactored. 
