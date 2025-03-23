Restaurant Management System
A Spring Boot REST API for managing restaurant operations, including menu items, orders, and analytics.

Features
Menu Management: Add, update, delete, and retrieve menu items.

Order Management: Place and retrieve orders.

Analytics: Get insights into revenue, popular items, peak hours, and average preparation time.

Prerequisites
Before running the project, ensure you have the following installed:

Java Development Kit (JDK): Version 21 or higher.

MySQL: A running MySQL server.

Maven: For building the project.

Postman : For testing the API endpoints.

Setup Instructions
1. Clone the Repository
Clone the repository to your local machine: https://github.com/vikrammore85/Restaurent-Management.git

 
cd restaurant-management-api
2. Configure MySQL Database
Create a new MySQL database: restaurant_management

CREATE DATABASE-- restaurant_management;
Update the application.properties file with your MySQL credentials:

properties
 
spring.datasource.url=jdbc:mysql://localhost:3306/restaurant_management

spring.datasource.username=root

spring.datasource.password=yourpassword

spring.jpa.hibernate.ddl-auto=update

3. Build the Project
   
Use Maven to build the project:

 
mvn clean install

4. Run the Application
5. 
Start the Spring Boot application:


mvn spring-boot:run
The application will start on http://localhost:8080.

 
API Endpoints

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
Testing the API
Use Postman or any API testing tool.

Send requests to the endpoints mentioned above.

Analytic-endpoint 

1) GetDaily-Revenue =GET METHOD=        http://localhost:8080/api/analytics/daily-revenue

2) Get-weekly-revenue =GET METHOD=      http://localhost:8080/api/analytics/weekly-revenue

3) Get-Monthly-revenue =GET METHOD=     http://localhost:8080/api/analytics/monthly-revenue

4) Get-populat-item =GET METHOD=        http://localhost:8080/api/analytics/popular-item

5) Peak-hours =GET METHOD=              http://localhost:8080/api/analytics/peak-hours

6) Average-prepration-Time =GET METHOD= http://localhost:8080/api/analytics/average-preparation-time


* Menu-Item Endpoint

1) get-all-menu-item =GET METHOD =   http://localhost:8080/api/menu-items

2) gey-menu-item-byId = GET METHOD = http://localhost:8080/api/menu-items/{id}

3) Create-menu-item = POST METHOD=   http://localhost:8080/api/menu-items

4) Update-menu-item = PUT METHOD=    http://localhost:8080/api/menu-items/{id}

5) Delete-menu-item =DELETE METHOD = http://localhost:8080/api/menu-items/{id}


* Order EndPoint *

1) get-incomming-orders = GET =   http://localhost:8080/orders/incoming

2) Accept-order = POST =          http://localhost:8080/{orderId}/accept

3) Cancel-order =POST =           http://localhost:8080/{orderId}/cancel

4) Update-order-status = PUT=     http://localhost:8080/{orderId}/status


* Review EndPoint *

1) Get-review-byRating =GET= PUT=   http://localhost:8080/reviews/filter/rating

2) Get-Review-By-Date-Range = GET=  http://localhost:8080/reviews//filter/date

3) Get-AggregateRating = GET =      http://localhost:8080/reviews/aggregate-rating










 
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
Logging
Logs are written to restaurant-management.log in the project root directory.

Logging level can be configured in application.properties:

properties
Copy
logging.level.com.example.restaurantmanagement=DEBUG
Exception Handling
Global exception handling is implemented using @ControllerAdvice.

All exceptions are logged and return a user-friendly error message.

Contributing
Fork the repository.




License
This project is licensed under the MIT License. See the LICENSE file for details.

Contact
For questions or feedback, please contact:

Your Name

Email: vikrammore2511@gmail.com

GitHub: your-username

Let me know if you need further assistance!

