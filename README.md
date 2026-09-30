# Library Web Application

Architecture:

JSP / HTML / CSS
        |
     Servlet
        |
     Service
        |
       DAO
        |
      JDBC
        |
      MySQL

## Requirements
- JDK 17+
- Maven
- MySQL 8+
- Apache Tomcat 10.1+ (Jakarta Servlet 6)

## Setup
1. Run `database/schema.sql` in MySQL.
2. Edit `src/main/java/com/library/util/DBConnection.java` and set your MySQL password.
3. Run `mvn clean package`.
4. Deploy `target/library.war` to Tomcat 10.1+.
5. Open `http://localhost:8080/library/`.
6. Login with username `admin` and password `admin123`.

## Included
- JSP web pages
- Servlets
- Service layer
- DAO layer
- JDBC/MySQL connection
- Login/logout
- Book add/view/delete
- Member add/view
- Issue book
- Return book
- Transaction handling for issue/return
- Fine calculation: ₹5 per late day
- Dashboard

## Important
The demo login stores a simple password for learning only. For a real application, hash passwords with a strong password hashing algorithm and add authorization/session filters.
