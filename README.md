The goal is to write a parser in Java that parses web server access log file, loads the log to MySQL and checks if a 
given IP makes more than a certain number of requests for the given duration. 

First Steps

Run the `createDatabase.sql` file into the MySQL server to create de database schema for the parser.
Obs: All tables will be created automaticaly

Configure persistence.xml replacing the user and password tags by the user and password of your database respectively


In the goal: also load them to another MySQL table with comments on why it's blocked
 the message was not defined, so was not included on the database.    


used technologies: 
    Maven (https://maven.apache.org/)
    Eclipse (http://www.eclipse.org)
    Hibernate (Java Persistence API by Oracle)
    Java (https://www.java.com)

Author: Túlio Martins
        tuliomartins@gmail.com