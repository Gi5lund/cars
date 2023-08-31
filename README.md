
######### WEEK 1 ##############
##The idea with, and reasons for why to use, a ORM-mapper: ##
Reason for using an ORM includes:
- reduction of code
- avoiding low-level jdbc and SQL 
- independence of database type and scheme
- keeping it Object Oriented (abstraction, encapsulation,...)

##The meaning of the terms JPA, Hibernate and Spring Data JPA and how they are connected:##
- JPA: Java Persistence API, interface handling persistence, just a specification
- Hibernate: an implementation of JPA
- Spring Data JPA: an abstraction that uses a JPA provider, thus reducing boilerplate code required for implementing data
  acces layers for various persistence stores
- 
##How to create simple Java entities and map them to a database via the Spring Data API:##
By annotating the class with @Entity, @Id and have a NoArg-constructor
- Annotating each attribute to be persisted with @Column (name="<name of column in database>", lenght= <Size of field>)
  - eg: @Column(name="car_model", length=60)
  - 
##How to control the mapping between individual fields in an Entity class and their matching columns in the database:##
by Annotating with @Column(name=" <field-name in table>")

##How to auto generate IDs, and how to ensure we are using  a specific database's preferred way of doing it (Auto Increment in our case for  MySQL):##
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
##How to use and define repositories and relevant query methods using Spring Data JPAs repository pattern:##

##How to write simple "integration" tests, using H2 as a mock-database instead of MySQL:##

##How to add (dev) connection details for you local MySQL database:##
setting connection details as environment variables in application.properties and configure the values of those 
variables under Run- Edit Configurations...  
