- Where and why you have used a @OneToMany annotation
  - 
    - I Car og Member klasserne er der en OneToMany relation :En Car og en Member kan have mange reservationer
- Where and why you have used a @ManyToOne annotation
  - 
    - I Reservation er der en ManyToOne relation til Car og Member: Mange Reservationer kan have den samme og/eller Member 
    - (Reservationsidentiten kan forståes som en kombination af Car, Member og Date id'er, dennekombination skal være unik)
- The purpose of the CascadeType, FetchType and mappedBy attributes you can use with one-to-many
  - 
    - CascadeType definerer hvilken handling udføres på associerede entities når en given handling udføres på den 
    - "ejersiden". CascadeType.PERSIST gemmer en relateret entitet når "ejer-entiteten". MappedBy angiver den associerede attribut FetchType angiver 
    - om den associerede entitet skal hentes sammen med ejersiden (FetchType.EAGER) eller når der forespørges på den(FetchType.LAZY)
    - default er LAZY
- How/where you have (if done) added user defined queries to you repositories
  - 
    - jeg tilføjede nye queries i CarRepository (findAllByBrandAndModel, findAllByReservationsIsNull),MemberRepository(findAllByReservationsIsNotNull)
    - og ReservationRepository(existsByCarIdAndRentalDate, existsByCar_IdAndRentalDate). disse Queries kaldes fra de respektive services
    - 
- a few words, explaining what you had to do on your Azure App Service in order to make your Spring Boot App connect to your Azure MySqlDatabase
  - 
    - under configuration i appService tilføjede jeg application settings: de environment variables som angiver url, brugernavn og password til databasen
    - (JDBC_DATABASE_URL, JDBC_USERNAME, JDBC_PASSWORD)
    - 
- a few words about where you have used inheritance in your project, and how it's reflected in your database
  - 
    - jeg brugte arv i memberklassen med @Inheritance(strategy = InheritanceType.SINGLE_TABLE)
      @DiscriminatorColumn(name = "USER_TYPE") Member klassen Extends UserWithRoles og i tabellen er der nu en kolonne 'USER_TYPE' som indeholde
    - klasse navnet (i vores tilfælde UserWithRoles eller Member)
- What are the pros & cons of using the Single Table Strategy for inheritance?
  - 
    - fordele: simplere at sætte op, performance ved at alle data er i samme tabel og der ikke skal laves komplicerede queries, det er 
    - muligt at query på subklasser(polymorfi) og da alle data er i samme tabel er der god dataintegritet.
    - ulemper: begrænset mulighed for at sikre datatype på databaseniveau, udfordringer i forhold til indeksering fordi nogle kolonner kun er relevante
    - for nogle subklasser. det kan kræve omfattende ændringer i tabellen ved ændringer i inheritance-hieraki og klasserne. tabellen bliver hurtig kompleks hvis der er mange subklasser
    - da ikke alle felter er relevante for alle klasser vil der være mange NUll-værdier og der kan være en stor mængde redundant data - fordi klasserne arver attributter,
- how are passwords stored in the database with the changes suggested in part-6 of the exercise
  - 
    - Passwords er gemt i hashed and Salted format. Bcrypt tager sig af denne del.
