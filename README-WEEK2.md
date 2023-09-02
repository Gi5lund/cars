######### WEEK 2 ##############

# What are the benefits of using a RESTful API:
  - scalability
  - clear separation of concerns (persistence, logic and presentation)

# What is JSON, and why does JSON fit so well with REST?:
  - JSON: Javascript Object Notation, det er en tekstbaseret måde at beskrive data på. konkret i form af key-value par,

# How to design simple CRUD endpoints using spring boot and DTOs to separate api from data structure  -> Focus on your use of DTO's:
  - ved at benytte en serviceklasse som "snakker" med Repository og benytter DTO klasser som parametre. Repository er et interface
   som extender JpaRepository og derved har jeg adgang til simple CRUD-metoder via framework. DTO-klasserne mapper response/request
    til Entity-klassen, Serviceklassen håndterer CRUD, Controller klassen modtager/returnerer et DTO-klasse objekt som serviceklassen
    persisterer eller henter fra repository.


#  What is the advantage of using DTOs to separate api from data structure when designing rest endpoints:
ved at bruge DTO klasser kan vi sikre at vi kun viser de attributter fra Entitets-klasserne som er relevante for brugeren og skjule
de attributter vi ønsker.

# Explain shortly the concept mocking in relation to software testing:
at “mocke” er et lave en slags kopi eller efterligning af det som IKKE skal testes. Det er en metode som sikrer at man
kender bestemte variabler, betingelser og tilstande forud for en test som derved kan teste på
hvordan koden forholder sig til dette kendte input.

# How did you mock database features, either using an in-memory database and/or mockito:
jeg brugte annotationen @JPAtest og in-memory database. jeg satte et repository op i testklassen og Autowired serviceklassen

# Explain the concept Build Server and the role Github Actions play here:
Github fungerer som buildserver: Github Actions automatiserer build vha et YAML script som udnytter Maven Lifecycle.
build scriptet starter når et push-event indtræffer.

# Explain maven, relevant parts in maven, and how maven is used in our CI setup. Explain where maven is used by your GitHub Actions Script(s):
dette step (i master_carsrus.yml) er ansvarligt for build:

      - name: Build with Maven
        run: mvn clean install
fra Maven Lifecycle køres først clean og derefter trinnene op til og med install (validate,compile,test,package,verify)
#  Understand and chose cloud service models (IaaS, PaaS, SaaS, DBaaS)for your projects -> Just explain what you have used for this handin
i denne opgave har jeg brugt Platfor as a Service med database: Azure har håndteret alt andet.
