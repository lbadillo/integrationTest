# Integration Test
## 1. Master Branch
* PoC to separate unit test execution from  integration test execution
* Use Data Spring projection (use postman projection request utils/API-THIRDPARTY-CURRENCY.postman_collection.json)
* Kafka Example to send and receive messages
    * endpoint /kafka/send/ (body: KafkaDTO)
* Redis as cache

### 1.1 Run
To run the application it needs:
* Mysql db server:
    * db name: liquibase
    * user: root
    * password: admin
* kafka server
    * settings default
* Redis
    * settings default


### 1.1.1 Kafka
#### 1.1.1.1  Configuration
* docker pull apache/kafka:4.0.0
* docker run --name kafka-server -p 9092:9092 apache/kafka:4.0.0
* Binary directory in docker container: /opt/kafka/bin
    * create topic: ```./kafka-topics.sh --bootstrap-server localhost:9092 --create --topic test-topic```
    * send message: ```./kafka-console-producer.sh --bootstrap-server localhost:9092 --topic test-topic ```
    * receive message:  ```./kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test-topic --from-beginning ```

### 1.1.2 Redis
#### 1.1.2.1 Configuration
* docker run -d --name redis-stack -p 6379:6379 -p 8001:8001 redis/redis-stack:latest
* To viw redis: http://localhost:8001/redis-stack/browser

### 1.1.3 Configuration Unit Test - Integration Test
* Use Maven surefire plug in to exclude integration test from the basic build lifecycle
    * The Unit Test must ended with Test
    * To run the unit test  ``./mvnw clean install``

* Use Maven failsafe plugin creating a new profile 'failsafe' and runs during the integration-test and verify phases of the Maven build lifecycle.
* By default, Failsafe looks for test classes matching patterns like **/IT*.java, **/*IT.java, or **/*ITCase.java.
    *  To run the integration test  ``./mvnw clean verify -Pfailsafe``


## 2. Reactive Branch
* PoC to test Mono and Flux from reactive spring boot webflux
* Integrated with liquibase and no test classes
* only run the application

# 3. Postman
utils/API-THIRDPARTY-CURRENCY.postman_collection.json


