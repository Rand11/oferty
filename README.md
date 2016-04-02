## Funkcjonalności
* Rejestracja, logowanie, autoryzacja użytkowników
* Dodawanie, edycja, usuwanie przez użytkownika 1 oferty widocznej na stronie powitalnej
* Wysyłanie wiadomości do innych użytkowników
* Odczytywanie otrzymanych wiadomości
* Użytkownicy - administratorzy ("ROLE_ADMIN")  mają dostęp do panelu administracyjnego, który zawiera listę wszystkich użytkowników

##Baza danych
Strona wymaga do działania bazy danych, którą można zaimportować z pliku [BazaDanychWlasciwa.sql](BazaDanychWlasciwa.sql).
Testy JUnit operują na bazie danych o identycznej strukturze, ale innej nazwie: [BazaDanychDoJUnit.sql](BazaDanychDoJUnit.sql)

##Konfiguracja serwera Tomcat
Do pliku context.xml należy dodać poniższe Resource
```
<Context>
...
<Resource name="jdbc/spring" auth="Container" type="javax.sql.DataSource"
               maxActive="100" maxIdle="30" maxWait="10000"
               username="root" password="" driverClassName="com.mysql.jdbc.Driver"
               url="jdbc:mysql://localhost:3306/springtutorial"/>
...
</Context>
```
##TODO: 
* przeprowadzać testy na oryginalnej bazie danych (rollback? @Transactional?)
* umożliwić administratorom deaktywowanie kont użytkowników z panelu admina

