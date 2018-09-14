# ifesoft
## About
Ifesoft is an enterprise congress and meetings desktop java organizer made for the subject Software Engineer of Software Engineer Bachelor's Degree at Universidad Complutense Madrid.
The documentation has been written in spanish because of a language constraint in the assignment for the subject as some classes at the code.
## How to use it
ifesoft uses [mariadb 10.2](https://downloads.mariadb.org/mariadb/10.2.14/), [Java 7](http://www.oracle.com/technetwork/java/javase/downloads/java-archive-downloads-javase7-521261.html) and [JUnit 4](https://junit.org/junit4/); these tools are necessary to run the application.
### Running mariadb
Once you've installed [mariadb 10.2](https://downloads.mariadb.org/mariadb/10.2.14/), launch MySQL client, create a user with _manager_ as username and _manager-if_ as password.
After that, run the following command:

`CREATE DATABASE ifesoft`

ifesoft is the name of the database.

Create a new user:

`CREATE USER 'manager'@'localhost' IDENTIFIED BY 'manager_if';`

Set privileges for the user created:

`GRANT ALL PRIVILEGES ON * . * TO 'manager'@'localhost';`

Go to /bin directory:

`{INSTALLATION PATH}/bin`

If you are in MariaDb terminal: (keys) Ctrl + C

`mysql -u manager -p ifesoft < {PATH}/ifesoft-create-db.sql`

Insert the password `manager-if`.

this imports an existing database with a valid structure for ifesoft.

## Authors
The development team consists in a class-group of seven students:
  - [Alberto Pastor Moreno](https://github.com/albertopastormr/) @albertopastormr
  - [Iván Fernandez Mena](https://github.com/ivanfermena) @ivanf3rmena
  - [Rubén García](https://github.com/RubenGarciaMateos) @RubenGarciaMateos
  - [Pablo López](https://github.com/pablolop002) @pablolop002 
  - [Gerardo Parra](https://github.com/gprossignoli) @gprossignoli
  - [Marco Adinolfi](https://github.com/MarcoAdinolfi) @MarcoAdinolfi
 
