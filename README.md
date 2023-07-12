Lab - Java Persistence
==========

Before you start
----------
The purpose of this lab is to reinforce and build upon the lecture material concerning Hibernate.

Begin by cloning this repository, then import the project onto your local.


The Concert database application
----------
For this exercise, complete the `lab-web-data` project. This project helps to build the database to store concert information.

The project is a simple Maven project using JPA / Hibernate to enable the persistence of `Concert`s and `Performer`s. It contains the following classes:


The project is a simple Maven project that includes the following key artifacts:

- Domain classes `Concert` and `Performer`. `Concert` in (package `lab.concert.app.domain`) has a unique ID, a title, a date and one `Performer`. `Performer` has a unique ID, a name, image file and `Genre`. A `Performer` can feature in many `Concert`s, hence there's a one-to-many relationship between `Performer` and `Concert`. The relationship is unidirectional, from `Concert` to `Performer`.

![relationship](lab-data-relationship.png)


- Class `ConcertRepositoryTest`, a unit test that tests the `ConcertReposity` to access the database.
 
- `schema.sql` and `data.sql` (in `src\main\resources`) are database initialisation scripts that includes SQL DDL and DML statements to create tables for `Concert` and `Performer`, and to populate the tables with data. This script is run automatically at the beginning of each unit test.

- `application.properties` is a configuration file that specifies the database connection details. The database is an in-memory **H2 database**, which is a lightweight database that runs in the same JVM as the application. The database is created and populated with data when the application starts up, and is destroyed when the application shuts down. The database is configured to use the `schema.sql` script to initialise the database with `spring.jpa.defer-datasource-initialization` property.

#### (a) Annotate the domain classes
For this task, annotate the `Concert` and `Performer` classes using `@Entity` `@Id` and `@GeneratedValue`. 

Important things to consider:

- The relationship between the two entities is as follows. Each `Concert` only has one performer. Each `Performer` may perform in any number of concerts. The only field in the Java classes describing this relationship is the `performer` field in `Concert`. Please place proper annotation  on this field to describe the relationship.

- For Id of both entity The pre-defined database in `db-init.sql` uses `AUTO_INCREMENT` to automatically generate and assign valid IDs to newly persisted entities. To force Hibernate to utilize the `AUTO_INCREMENT` functionality rather than its own generation strategy, we can set the `strategy` property of the `@GeneratedValue` annotation to `GenerationType.IDENTITY`.

#### (b) Run the unit tests
Once you've annotated your classes, simply run the unit tests from your IDE (there is no need to run a Maven goal for this project, as we are not running integration tests which require an active server). 

The unit test `ConcertRepositryTests` is complete, so you do not need to modify it.:smile_cat::smile_cat::smile_cat:  

The unit tests should pass. If they do not, modify the annotations from task (a) until they do. You should not need to modify anything in the project, other than adding JPA annotations.


#### Do not forget to Commit and Push code to github

#### Resources

Useful resources for H2  include the H2 website:

<http://www.h2database.com/html/main.html>

From here, you can download the H2 Console for your own machines. The website also has useful information, e.g. the SQL grammar for H2.

Challenges
----------
If you have time left, please try to add more relationship to the concert.
- one concert can have many seats
 - one seat can only belong to one person (who will book to see the concert)

![relationship](lab-data-challenge.png)