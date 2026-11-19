ECO – Full-Stack E-Commerce Platform

A full-stack online shopping web application built with Spring Boot MVC and Thymeleaf. It has two roles, Admin and User, each with its own login, dashboard and permissions. It includes a paginated product catalog, a stock-aware shopping cart, Razorpay payment integration and email confirmation after payment.

Features
Admin
Secure session-based login
Add, edit and delete products, with image upload (unique timestamped file names)
Inventory control: add stock and track quantity in real time
Paginated product grid (6 products per page)
View all customer orders, payment status and customer contact details
User
Registration and login with session-based authentication
Browse the product catalog with pagination (6 products per page)
Shopping cart:
Detects duplicate products and increases quantity instead of adding a new row
Blocks adding out-of-stock products
Reduces product stock when an item is added to the cart
One-click item removal
Checkout and payment through Razorpay (UPI, cards, netbanking, wallets)
Order history ("My Orders")
Confirmation email sent to the registered address after a successful payment
Security
Role-based route protection (Admin and User pages)
Redirect to login for unauthorized access
Session invalidation and cookie clearing on logout
Cache-control headers on protected routes
Tech Stack
Layer	Technology
Language	Java
Framework	Spring Boot, Spring MVC
View	Thymeleaf, HTML, CSS, JavaScript
Persistence	Spring Data JPA, Hibernate
Database	MySQL
Payments	Razorpay Java SDK (test mode)
Email	JavaMail (SMTP)
Build tool	Maven
Architecture
Browser → Thymeleaf Templates → Controller Layer → Service Layer → Repository Layer (Spring Data JPA) → MySQL

Controllers: AdminController, UserController, ProductController, CartController, PaymentController, MailController

Screenshots

Add your screenshots to a screenshots/ folder in the repo and update the file names below.

Home	Admin Login
Home	Admin Login
Admin Dashboard	Add Product
Admin Dashboard	Add Product
User Dashboard	Cart
User Dashboard	Cart
Razorpay Payment	My Orders
Payment	Orders
Getting Started
Prerequisites
JDK 17 or later
Maven
MySQL
A Razorpay account (test mode keys)
An SMTP account for sending email (for example, a Gmail app password)
Setup
Clone the repository
bash
git clone https://github.com/Sultan8474/<repo-name>.git
cd <repo-name>
Create the database
sql
CREATE DATABASE <your_database_name>;
Update src/main/resources/application.properties with your own values
properties
server.port=8081

spring.datasource.url=jdbc:mysql://localhost:3306/<your_database_name>
spring.datasource.username=<your_db_username>
spring.datasource.password=<your_db_password>
spring.jpa.hibernate.ddl-auto=update

razorpay.key.id=<your_razorpay_test_key_id>
razorpay.key.secret=<your_razorpay_test_key_secret>

spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=<your_email>
spring.mail.password=<your_app_password>

Never commit real keys or passwords. Keep them in a local file or in environment variables.

Run the application
bash
mvn spring-boot:run
Open http://localhost:8081 in your browser.
Payment Testing

The project uses Razorpay in test mode. Use Razorpay's test card or UPI details from their documentation. No real money is charged.

Learning Outcomes
Building a layered Spring MVC application (Controller, Service, Repository)
Session-based authentication and role-based access
Payment gateway integration with server-side order creation
Pagination with Spring Data JPA
Sending transactional email with JavaMail
Future Improvements
Password hashing with Spring Security
Product search and category filters
Order cancellation and refunds
Docker support and deployment
Author

Sultan Khan Java Backend Developer | Hyderabad LinkedIn • sultankhan35536@gmail.com
