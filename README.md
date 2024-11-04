# Apartment System
Simple system for storing tenant and maintenance records.

# Getting Started
## Dependencies
Java, Java IDE, Tomcat, XAMPP, search engine required.
## Installing
- Download latest version of Java and a Java IDE (I used eclipse).
- Download XAMPP in order to utilize the MySQL database (https://www.apachefriends.org/download.html).
- Download Apache Tomcat (I am using v. 10.0.27). Can be done from within Eclipse (https://www.youtube.com/watch?v=0CsWW1Ni8jA).
- Download the SQL file to use for the database.

# Executing Program
1. Open XAMPP (manager-osx) and start MySQL Database and Apache Web Server. *Note that if MySQL Database will not start, open your terminal and type sudo killall mysqld, then try again.*
2. Confirm everything started properly by visiting http://localhost/phpmyadmin/ on your search engine. Make sure to implement the SQL file provided.
3. In your IDE, start Tomcat 10.0.27. 

# Notes:
1. I plan to continue updating this to have more functionality, where the servlets interconnect better.
2. The database is not implemented well, so this will likely be updated in the future.
