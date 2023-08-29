######### WEEK 2 ##############

# What are the benefits of using a RESTful API:
  - scalability
  - clear separation of concerns (persistence, logic and presentation)

# What is JSON, and why does JSON fit so well with REST?:
  - JSON: Javascript Object Notation, det er en tekstbaseret måde at beskrive data på. konkret i form af key-value par,

# How to design simple CRUD endpoints using spring boot and DTOs to separate api from data structure  -> Focus on your use of DTO's:
  - 
  
#  What is the advantage of using using DTOs to separate api from data structure when designing rest endpoints:

# Explain shortly the concept mocking in relation to software testing:

# How did you mock database features, either using an in-memory database and/or mockito:

# Explain the concept Build Server and the role Github Actions play here:

# Explain maven, relevant parts in maven, and how maven is used in our CI setup. Explain where maven is used by your GitHub Actions Script(s):

#  Understand and chose cloud service models (IaaS, PaaS, SaaS, DBaaS)for your projects -> Just explain what you have used for this handin


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
