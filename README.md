# Book Lending System :books:

This is a book lending project. Customers can borrow books, audiobooks or ebooks and review the ones they liked.

## Functionalities
|                           Admin                           |     Customer      |
|:---------------------------------------------------------:|:-----------------:|
|                           Menu                            |       Menu        |
|                           Login                           |       Login       |
|        Add (Books, Ebooks, Audiobooks, Companies)         |     Register      |
|       Remove (Books, Ebooks, Audiobooks, Companies)       |    Make a loan    |
| Display (Books, Ebooks, Audiobooks, Companies, Customers) |   View profile    |
|             viewBooksFromGivenPublishingHouse             | Display all loans |
|                viewCustomersThatHaveLoans                 |  Change password  |
|                    viewAvailableBooks                     |  Change username  |
|                                                           | Review a product  |

## Objects:

- Book Type
    - Book
    - AudioBook
    - eBook
- Company
- Loan
- User
    - Admin
    - Customer
- Review
- Category (enum)
- Subcategory (enum)
- Main
- Admin Service
- Customer Service

## Functions:
- **Admin**
    - Login
    - Menu (from Service Interface)
    - *Add Functions* (book, audiobook, ebook, company, customer)
    - *Remove Functions* (book, audiobook, ebook, company)
    - *Display Functions* (book, audiobook, ebook, company, customer)
---------------------
- **Customer**
    - Login
    - Menu (from Service Interface)
    - Check If Book Available
    - Check If AudioBook Exists
    - Check If EBook Exists
    - Add Loan
    - Display Loans For A Given Customer
    - Display All Loans
    - Review (book, audiobook, ebook)
    - Change password
    - Change Username
    

# Cerințe

## Etapa I - *Deadline 29 martie 2022*

### 1) Definirea sistemului
- [x] Să se creeze o lista pe baza temei alese cu cel puțin 10 acțiuni/interogări care se pot face în cadrul sistemului și o lista cu cel puțin 8 tipuri de obiecte.

### 2) Implementare
Sa se implementeze în limbajul Java o aplicație pe baza celor definite la primul punct.
Aplicația va conține:

- [x] clase simple cu atribute private / protected și metode de acces
- [x] cel puțin 2 colecții diferite capabile să gestioneze obiectele definite anterior (eg: List, Set, Map, etc.) dintre care cel puțin una sa fie sortata – se vor folosi array-uri uni- /bidimensionale în cazul în care nu se parcurg colectiile pana la data checkpoint-ului.
- [x] utilizare moștenire pentru crearea de clase adiționale și utilizarea lor încadrul colecțiilor;
- [x] cel puțin o clasă serviciu care sa expună operațiile sistemului
- [x] o clasa Main din care sunt făcute apeluri către servicii

## Etapa II - *Deadline 3 mai 2022*

### 1) Extindeți proiectul din prima etapa prin realizarea persistentei utilizând fișiere:


- [x] Se vor realiza fișiere de tip CSV pentru cel puțin 4 dintre clasele definite în prima etapa. Fiecare coloana din fișier este separata de virgula. Exemplu:nume,prenume,varsta
- [x] Se vor realiza servicii singleton generice pentru scrierea și citirea din fișiere;
- [x] La pornirea programului se vor încărca datele din fișiere utilizând serviciile create;

### 2) Realizarea unui serviciu de audit

- [x] Se va realiza un serviciu care sa scrie într-un fișier de tip CSV de fiecare data când este executată una dintre acțiunile descrise în prima etapa. Structura fișierului: nume_actiune, timestamp

### 3) Din Barem:

- [x] Streams
- [x] Lambda Expresii
- [x] Exceptii

## Etapa III - *Deadline 31 mai 2022*


Înlocuiți serviciile realizate în etapa a II-a cu servicii care sa asigure persistenta utilizând baza de date folosind JDBC.

- [ ] Să se realizeze servicii care sa expună operații de tip create, read, update si delete pentru cel puțin 4 dintre clasele definite.