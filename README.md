# Integration Test
## Master Branch
* PoC to separate unit test execution from  integration test execution
* Use Data Spring projection (use postman projection request utils/API-THIRDPARTY-CURRENCY.postman_collection.json)

### Configuration
 * Use Maven surefire plug in to exclude integration test from the basic build lifecycle
   * The Unit Test must ended with Test 
   * To run the unit test  ``./mvnw clean install``

* Use Maven failsafe plugin creating a new profile 'failsafe' and runs during the integration-test and verify phases of the Maven build lifecycle.  
* By default, Failsafe looks for test classes matching patterns like **/IT*.java, **/*IT.java, or **/*ITCase.java. 
  *  To run the integration test  ``./mvnw clean verify -Pfailsafe``


## Reactive Branch
* PoC to test Mono and Flux from reactive spring boot webflux
* Integrated with liquibase and no test classes
* only run the application

# Postman
utils/API-THIRDPARTY-CURRENCY.postman_collection.json


