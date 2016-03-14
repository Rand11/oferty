# oferty

## Funkcjonalności
* Rejestracja, logowanie, autoryzacja użytkowników
* Dodawanie, edycja, usuwanie przez użytkownika 1 oferty widocznej na stronie powitalnej
* Wysyłanie wiadomości do innych użytkowników
* Odczytywanie otrzymanych wiadomości
* Użytkownicy - administratorzy ("ROLE_ADMIN")  mają dostęp do panelu administracyjnego, który zawiera listę wszystkich użytkowników

##Baza danych
Strona wymaga do działania bazy danych, którą można zaimportować z pliku [BazaDanychWlasciwa.sql](BazaDanychWlasciwa.sql).
Testy JUnit operują na bazie danych o identycznej strukturze, ale innej nazwie: [BazaDanychDoJUnit.sql](BazaDanychDoJUnit.sql)

##TODO: 
* przeprowadzać testy na oryginalnej bazie danych (rollback? @Transactional?)
* umożliwić administratorom deaktywowanie kont użytkowników z panelu admina

