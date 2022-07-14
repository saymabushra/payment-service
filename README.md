## The project is using 
* Java 17
* Gradle 7.4

## About default setup
* You can start Spring application via your favourite ide or using `gradle bootjar` and then executing previously created jar
* It uses h2 for db so no need to setup any external database. To access running application db http://localhost:8080/h2-console/login.jsp (login parameters are in application.properties file)
* /db/migration/V1__create_table.sql will generate sql tables when Spring application starts

## Assignment
* Create account with the endpoint, POST `/accounts` with json body 
    * Request json example
       ```
          {
              "name": "Account 1",
              "balance": "100.00"
          }
      ```
* Make payment with the endpoint POST `/payments` with json body 
    * Request json example:
        ```
        {
            "senderAccountId": "1",
            "receiverAccountId: "2",
            "amount": "100.00"
        }
      ```
* Requirements
    * Amount must be a number > 0 and can have two decimal places
    * senderAccountId, receiverAccountId are account table id-s 
    * Account balance cannot go negative.
    * Money is withdrawn from sender account and deposited into receiver account 
    * And everything else what you think that is important
* Example
    * Before payment
        * Sender account balance 100.00
        * Receiver account balance 100.00
    * Do payment
        *  POST `/payments`
            ```
            {
                "senderAccountId": "1",
                "receiverAccountId: "2",
                "amount": "100.00"
            }
            ```
    * After payment
        * Sender account balance 0.00
        * Receiver account balance 200.00
    
* Swagger documentation can be found at: http://localhost:8080/swagger-ui.html
* The application can be dockerized
# r-payment-service
