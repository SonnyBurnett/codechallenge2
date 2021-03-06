# Project Object Oriented Programing

Object Oriented Programing using Java.

## Table of contents
* [Getting Started](#Getting-Started)
    * [Prerequisites](#Prerequisites)
    * [Installing](#Installing)
* [Running the README](#Running-the-README)
* [Expert problems](#Expert-problems)
* [Deployment](#Deployment)
* [Build With](#Build-With)
* [Tests](#Tests)
* [Contributing](#Contributing)
* [Versioning](#Versioning)
* [Authors](#Authors)
* [License](#License)
* [Acknowledgments](#Acknowledgments)

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for demonstrating 
Object Oriented Programing OOP principles.

### Prerequisites

In the [pom.xml](gep2/pom.xml) all specific dependencies have been listed as code, the maven framework will install those.

### Installing

For a local install, install the following:
   * An IDE; I used IntelliJ IDEA 2020.2.3 (Community Edition).
   * Maven 4.0.0, the framework will configure itself based on the [pom.xml](gep2/pom.xml).
   * Java 11 SDK; the default version of IntelliJ IDEA  2020.2.3 (Community Edition)


Set-up the code by either clone the repertory or download it as a whole and extract into a directory of your own choosing.

### Running the README

The readme files of the root modules contain the explanation for the expert assignments.

#### Expert problems
1. [Expert Assignment 1](gep2/sre-gep2-ex01/README.md): 'gep2/sre-gep2-ex01/README.md'
    is guiding you through the solution for the Object Oriented setup for assignment 1.
2. [Expert Assignment 2](gep2/sre-gep2-ex02/README.md): 'gep2/sre-gep2-ex02/README.md'
    is guiding you through the solution for the Object Oriented setup for assignment 2.
2. [Expert Assignment 3](gep2/sre-gep2-ex03/README.md): 'gep2/sre-gep2-ex03/README.md'
    is guiding you through the solution for the Object Oriented setup for assignment 3.

## Deployment

No additional notes about how to deploy this on a live system, follow the installation instruction above.
GitHub directly support the README for a preview in the browser of your choosing supported by GitHub.
The project structure is structured as displayed here:
```
sre14-oop
├── README.md               This top-level README for developers using this project.
├── pom.xml
├── azure-pipeline.yml
├── gep2
    |
    ├──src
    |   ├──main
    |   |   ├──java
    |   |   └──resources
    |   |   
    |   └──test
    |       └──java
    ├──sre-gep2-ex01
    |   ├── README.md               This README for explaining the fist assignment.
    |   ├── pom.xml
    |   └──src
    |       ├──main
    |       |   ├──java
    |       |   |   └──ex01
    |       |   |       ├── Currency.java
    |       |   |       ├── Expert01.java
    |       |   |       ├── Product.java
    |       |   |       ├── ProductDetail.java
    |       |   |       ├── Products.java
    |       |   |       └── ProductsHandler.java
    |       |   └──resources
    |       |       ├── 001-experts-inputs.csv
    |       |       └── 001-experts-outputs.csv
    |       └──test
    |           └──java
    |           |    └──ex01
    |           |       ├── CurrencyTest.java
    |           |       ├── ProductTest.java
    |           |       └── ProductsTest.java
    |           └──resources
    |               ├── 001-test-inputs.csv
    |               └── 001-test-outputs.csv
    |
    ├──sre-gep2-ex02
    |   ├── README.md               This README for explaining the second assignment.
    |   ├── pom.xml
    |   └──src
    |       ├──main
    |       |   ├──java
    |       |   |   └──ex02
    |       |   |       ├── Board.java
    |       |   |       ├── Cell.java
    |       |   |       ├── fileLoader.java
    |       |   |       ├── fileWriter.java
    |       |   |       ├── Game.java
    |       |   |       ├── GameStates.java
    |       |   |       ├── IllegalGameSetup.java
    |       |   |       ├── IllegalMove.java
    |       |   |       ├── Player.java
    |       |   |       ├── PlayTicTacToe.java
    |       |   |       └── TicTacToe.java
    |       |   └──resources
    |       |       └── inputs.txt
    |       └──test
    |           └──java
    |           |    └──ex02
    |           |       ├── BoardTest.java
    |           |       ├── CellTest.java
    |           |       ├── GameStatesTest.java
    |           |       ├── PlayerTest.java
    |           |       └── TicTacToeTest.java
    |           └──resources
    |               ├── 002-experts.txt
    |               └── 002-experts-example.txt
    |
    └──sre-gep2-ex02
        ├── README.md               This README for explaining the second assignment.
        ├── pom.xml
        └──src
            ├──main
            |   ├──java
            |   |   └──ex02
            |   |       ├── Customer.java
            |   |       ├── Customers.java
            |   |       ├── Order.java
            |   |       ├── Orders.java
            |   |       ├── Package.java
            |   |       ├── Packages.java
            |   |       ├── ParcelEvening.java
            |   |       ├── ReadInterface.java
            |   |       ├── Shipping.java
            |   |       ├── ShipingInformation.java
            |   |       ├── ShippingRule.java
            |   |       ├── ShippingRules.java
            |   |       └── WriteFileInterface.java
            |   └──resources
            |       └── 003-exports-inputs.csv
            └──test
                └──java
                |    └──ex02
                |       ├── CustomersTest.java
                |       ├── CustomerTest.java
                |       ├── OrdersTest.java
                |       ├── OrderTest.java
                |       ├── PackagesTest.java
                |       ├── PackageTest.java
                |       ├── ParcelEveningTest.java 
                |       ├── ShipingInformationTest.java
                |       ├── ShippingRulesTest.java
                |       ├── ShippingRuleTest.java
                |       └── ShippingTest.java
                └──resources
                    ├── 003-experts-example.txt
                    └── 003-experts-inputs.txt
```
## Build With

Anything that was used to write, compile, execute and stored for this project:
* [IntelliJ IDEA](https://www.jetbrains.com/idea/) - IntelliJ IDEA - Capable and Ergonomic IDE for JVM.
* [Maven](http://maven.apache.org/) - Maven - Apache Maven is a software project management and comprehension tool.
* [GitHub](https://github.com/) - A Git repository hosting service.
* [Azure Pipelines](https://azure.microsoft.com/en-us/services/devops/pipelines/) - Continuously build, test,
 and deploy to any platform and cloud.
* [Junit5](https://junit.org/junit5/) - The new major version of the programmer-friendly testing framework for Java.

## Tests

The tests where executed with IntelliJ and Maven:
  * All tests are separated into a different directory from the main project files.

The used test framework is JUnit5 (dependencies have been managed by Maven):
 

The CI on the unittests is executed by using Azure Pipelines:
[Pipeline sre14-oop](https://dev.azure.com/SreckoSuznjevic0449/sre14-oop)  

## Contributing

Anybody who has access can contribute to this git repository, please contact me when you have contributions.

## Versioning

Current versions and additions are directly added to the GitHub repository.

## Authors

* **Srećko Sužnjević** - *Sre14* 

## License

No license applies for my build code.

## Acknowledgments

Building this framework to demonstrate the solutions was a challenge on its own, next to solving the assignments. I would like to acknowledge the following internet resources for inspiration and guidance:

* https://www.jetbrains.com/help/idea/2020.2
* https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html
* https://dzone.com/articles/never-use-float-and-double-for-monetary-calculation
* https://www.journaldev.com/20834
* https://www.markdownguide.org/
* https://guides.github.com/pdfs/markdown-cheatsheet-online.pdf
* https://www.baeldung.com/
    * https://www.baeldung.com/java-collections
    * https://www.baeldung.com/java-collection-stream-foreach
    * https://www.baeldung.com/java-lists-difference
    * https://www.baeldung.com/java-maps-streams
* https://www.geeksforgeeks.org/map-interface-java-examples/
* https://www.vogella.com/tutorials/JavaCollections/article.html

Special attention to the advices given in the following pages:
* https://www.geeksforgeeks.org/difference-between-abstract-class-and-interface-in-java/
And the simplified explanation by this page:
* https://medium.com/@alifabdullah/easiest-explanation-of-abstract-class-and-interface-280741bc2daf

Collection of frequently used Java Design Patterns:
* https://www.journaldev.com/1827/java-design-patterns-example-tutorial
* https://www.tutorialspoint.com/design_pattern/index.htm