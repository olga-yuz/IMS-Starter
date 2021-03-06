Coverage: 82.3%
# IMS Fundamental Project

An Inventory Management application using MySQL

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

Install Java jdk1.8.0_271, which you can get from [Oracle Java](https://www.oracle.com/uk/java/technologies/javase/javase-jdk8-downloads.html)

Install Maven 3.6.3 for project dependencies from [Apache Maven](http://maven.apache.org/)

Install MySQL Workbench [MySQL](https://dev.mysql.com/downloads/mysql/5.7.html)


### Installing

Add a JAVA_HOME Environment System variable and add it to PATH variable

```
Create JAVA_HOME: C:\Program Files\Java\jdk1.8.0_271
Append to PATH: %JAVA_HOME%\bin;  
```

Add MAVEN_HOME and M2_HOME Environment System variables and add MAVEN_HOME to PATH variable

```
Create MAVEN_HOME: C:\Program Files\apache-maven-3.6.3
Create M2_HOME: C:\Program Files\apache-maven-3.6.3
Append to PATH: %MAVEN_HOME%\bin;
```

Compile the code using Command Line:

```
mvn clean package
```
A .jar with dependencies should appear in \target

You may like to install Eclipse 2020-06 for Java [Eclipse](https://www.eclipse.org/downloads/packages/release/2020-06/r).

Create a local instance of the Database in MySQL using the [schema](https://github.com/olga-yuz/IMS-Starter/tree/master/src/main/resources) provided.

You can now use the interface to view, create, update and delete Items, Customers and Orders.

## Running the tests

Automated tests will run when the project is compiling.

### Unit Tests 

These test the DAOs (data access objects), you can run them by running [DAO tests](https://github.com/olga-yuz/IMS-Starter/tree/master/src/test/java/com/qa/ims/persistence/dao) from an IDE. These confirm that each DAO object can sucessfully connect, retrieve and write to the database .


### Integration Tests 

These test the controllers for the customer, item and order objects, you can run them by running [controller tests](https://github.com/olga-yuz/IMS-Starter/tree/master/src/test/java/com/qa/ims/controller) from an IDE. These confirm that the objects can work together.


## Deployment

In Command Line:

```
java -jar ims-0.0.1-jar-with-dependencies.jar
```

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

We use [GitHub](https://github.com/) for versioning.

## Authors

* **Chris Perrins** - *Initial work* - [christophperrins](https://github.com/christophperrins)
* **Olga Yuzenkova** - *Continung work* - [olga-yuz](https://github.com/olga-yuz/IMS-Starter)

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

*For help in [Choosing a license](https://choosealicense.com/)*

## Acknowledgments

* Big thanks to Piers Barber, Aswene Sivaraj and Savannah Vaithilingam for all the help!

