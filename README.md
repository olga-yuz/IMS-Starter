Coverage: 82.4%
# IMS Fundamental Project

An Inventory Management application using MySQL

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

Need to install Java jdk1.8.0_271, which you can get from [Oracle Java](https://www.oracle.com/uk/java/technologies/javase/javase-jdk8-downloads.html)

Need to install Maven 3.6.3 for project dependencies from [Apache Maven](http://maven.apache.org/)

```
Give examples
```

### Installing

A step by step series of examples that tell you how to get a development env running

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

End with an example of getting some data out of the system or using it for a little demo

## Running the tests

Automated tests will run when the project is compiling

### Unit Tests 

Explain what these tests test, why and how to run them

```
Give an example
```

### Integration Tests 
Explain what these tests test, why and how to run them

```
Give an example
```

### And coding style tests

Explain what these tests test and why

```
Give an example
```

## Deployment

In Command Line:

```
java -jar ims-0.0.1-jar-with-dependencies.jar
```

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

We use [SemVer](http://semver.org/) for versioning.

## Authors

* **Chris Perrins** - *Initial work* - [christophperrins](https://github.com/christophperrins)
* **Olga Yuzenkova** - *Continung work* - [olga-yuz](https://github.com/olga-yuz/IMS-Starter)

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

*For help in [Choosing a license](https://choosealicense.com/)*

## Acknowledgments

* Hat tip to anyone whose code was used
* Inspiration
* etc
