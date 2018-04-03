# ifesoft
## What is ifesoft?
Ifesoft is an enterprise congress and meetings desktop java organizer made for the subject Software Engineer of Software Engineer Bachelor's Degree at Universidad Complutense Madrid.
The documentation has been written in spanish because of a language constraint in the assignment for the subject as some classes at the code.
## How to use it
ifesoft uses [mariadb 10.2](https://downloads.mariadb.org/mariadb/10.2.14/), [Java 7](http://www.oracle.com/technetwork/java/javase/downloads/java-archive-downloads-javase7-521261.html) and [JUnit 4](https://junit.org/junit4/); these tools are necessary to run the application.
### Running mariadb
Once you've installed [mariadb 10.2](https://downloads.mariadb.org/mariadb/10.2.14/), launch MySQL client and run the following command:

`CREATE DATABASE ifesoft`

ifesoft is the name of the database.

`mysql -u manager -p ifesoft < db/dump-ifesoft-201804032101.sql`

this imports an existing database with a valid structure for ifesoft.
## Authors
The development team consists in a class-group of seven students:
  . Alberto Pastor Moreno @albertopastormr
  . Iván Fernandez Mena
  . Rubén García
  . Pablo López 
  . Arthur Amon
  . Gerardo Parra
  . Marco Adinolfi
