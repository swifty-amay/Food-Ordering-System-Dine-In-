# Food Ordering System (Dine-In)

A restaurant dine-in food ordering system developed using **Java** and **MySQL**. The project allows customers to place food orders, generate bills, and provides administrative functionality for managing orders.

This project implements a **Soft Delete** mechanism for completed orders, preserving historical data while keeping active order records clean and manageable.

## Features

* User Registration and Authentication
* Food Menu Management
* Order Placement
* Bill Generation
* Order History Management
* Admin Dashboard
* Soft Delete Implementation for Completed Orders

## Technologies Used

* Java
* MySQL
* JDBC
* Object-Oriented Programming (OOP)

## Project Structure

```
food/
services/
utils/
main.java
```

## Soft Delete Implementation

Instead of permanently deleting completed orders from the database, this project uses a soft delete approach.

A dedicated flag is used to mark completed orders as deleted. These orders are hidden from active order listings while still remaining stored in the database.

### Benefits

* Preserves order history
* Prevents accidental data loss
* Simplifies auditing and reporting
* Allows future recovery of records if required

## How to Run

### Prerequisites

* Java JDK 8 or later
* MySQL Server
* MySQL Connector/J JDBC Driver
* Eclipse IDE (or any Java IDE)

### Setup Instructions

1. Clone or download this repository.

2. Open the project in Eclipse or any Java IDE.

3. Create a MySQL database.

4. Create the required tables for:

   * Users
   * Orders
   * Admin

## Database Schema

### Users Table

```sql
CREATE TABLE users (
    uid INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL
);
```

### Orders Table

```sql
CREATE TABLE orders (
    order_id INT PRIMARY KEY AUTO_INCREMENT,
    uid INT NOT NULL,
    item_names VARCHAR(500) NOT NULL,
    total DOUBLE NOT NULL,
    ordered_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    deleted TINYINT DEFAULT 0,
    FOREIGN KEY (uid) REFERENCES users(uid)
);
```

### Admin Table

```sql
CREATE TABLE admin (
    aid INT PRIMARY KEY AUTO_INCREMENT,
    admin_name VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL
);
```

5. Add MySQL Connector/J to the project build path.

### Adding JDBC Driver in Eclipse

1. Right-click the project.
2. Click **Build Path**.
3. Select **Configure Build Path**.
4. Open the **Libraries** tab.
5. Click **Add External JARs**.
6. Select the MySQL Connector/J `.jar` file.
7. Click **Apply and Close**.

### Database Configuration

Update the database credentials inside:

```
utils/DBConnection.java
```

Example:

```java
String url = "jdbc:mysql://localhost:3306/food_ordering_system";
String user = "root";
String password = "your_password";
```

Ensure MySQL Server is running before starting the application.

### Run the Project

Run:

```
main.java
```

## Team Members

* Amay Raj Srivastav
* Anjali Singh
* Aakriti Gupta
* Abhishek Goyal
* Anushka Rai

## Future Improvements

* Table Management System
* Inventory Tracking
* Online Payment Integration
* Multiple User Roles
* Order Status Tracking
* Sales Reports and Analytics

## Images

## Users Table

<img width="293" height="53" alt="image" src="https://github.com/user-attachments/assets/6a9ec1e1-791d-4ee3-8126-634e107985c2" />





## Orders Table

<img width="948" height="39" alt="image" src="https://github.com/user-attachments/assets/872f9815-b2f5-405f-9840-430df74c368c" />





## Users Dashboard

<img width="1392" height="428" alt="image" src="https://github.com/user-attachments/assets/af70321c-e78b-444c-a0c9-3d8392fc1d04" />





## Admin Dashboard

<img width="1400" height="582" alt="image" src="https://github.com/user-attachments/assets/f7336cd3-8aa0-4b72-a87e-50864ed7f338" />



## Team Bug_Buster
If you have any queries or you face any difficluty in cloning the repo and running the project then feel free to ask. Our team is always there to respond you asap. 
