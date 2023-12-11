# Tree-Cutting Management System

## Description
  A website for managing tree-cutting for a contractor David Smith. This will allow users to create accounts and log in with their credentials. This website will allow the client and the contractors to communicate through quotes. They can negotiate the price. Clients can post their information as it helps the contractors understand how much they have to do.

## Table of Contents
- [Contributors](#contributors)
- [Installation](#installation)
- [Configuration](#configuration)
- [Features](#features)

## Contributors
1. Ahmed Minhaj (hi4718)
   Email: hi4718@wayne.edu
   Project 1: Login and Signup Configuration.
2. David Pogosian
   Email: davidpogosian@wayne.edu
   Project 1: Database initialization
Hours we worked together: 5 hours.
   

## Installation
Please follow the instructions I've included below to set up this project.
1. Download and install Java JDK from Java SE Development Kit 15 - Downloads (oracle.com) (Links to an external site.)
   Watch this tutorial: https://rumble.com/ve6719-how-to-download-oracle-java-jdk-se-and-install-it-in-windows.html (Links to an external site.)
2. Download and install Eclipse IDE for Enterprise Java Developers from https://www.eclipse.org/downloads/packages/release/2021-12/r/eclipse-ide-enterprise-java-and-web-    
   developers (Links to an external site.) (Links to an external site.)
   Watch this tutorial: https://rumble.com/ve67kb-how-to-download-eclipse-ide-and-install-it-for-windows.html (Links to an external site.)
3. Download and install Apache Tomcat 9.0 from https://tomcat.apache.org/download-90.cgi (Links to an external site.)
   Watch this tutorial: https://rumble.com/ve68gn-how-to-download-and-install-tomcat-for-windows-and-use-it-in-eclipse-ide.html (Links to an external site.)
4. Download MySQL Server on https://dev.mysql.com/downloads/mysql/ (Links to an external site.)
   Watch this tutorial: https://rumble.com/ve6eun-how-to-download-and-install-mysql-server-for-windows.html
5. Clone this repository and import it into Eclipse IDE. 
   Watch this tutorial to understand how to import: https://rumble.com/ve7cvv-how-to-import-a-github-project-into-eclipse-ide.html

## Configuration
1. After importing the repository you need to set up the server in the Eclipses. You need to set up the Apache Tomcat 9.0 and then add the project.
2. Connect to the MySQL server using your root confidential (using either MySQL shell or MySQL workbench) and then create a MySQL database local user. Example: User "john" with the password "pass1234" on MySQL Workbench and open it, run the following code(Example):
    CREATE USER 'john'@'localhost' IDENTIFIED BY 'pass1234';
    GRANT ALL PRIVILEGES ON *.* TO 'john'@'localhost';
3. You also need to change the connection in the code if you change the
4. Please run the register.jsp file and use the server you just created.

## Features
1. User registration and Login
2. Dashboard for clients, David Smith, and the root administrator
3. Create quotes with the help of the data provided by the user.
4. Quote negotiation and order creation
5. Finalize and process the billing
