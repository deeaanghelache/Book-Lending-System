# Book Lending System

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
- Category (enum)
- Subcategory (enum)
- Main
- Admin Service
- Customer Service

## Functions:
- **Admin Service:**
    - Login
    - Menu (from Service Interface)
    - *Add Functions:*
        - Add Book
        - Add AudioBook
        - Add EBook
        - Add Company
        - Add Customer
    - *Remove Functions:*
        - Remove Book
        - Remove AudioBook
        - Remove EBook
        - Remove Company
    - *Display Functions:*
        - Display Books
        - Display Audiobooks
        - Display EBooks
        - Display Companies
        - Display Customers
---------------------
- **Customer Service**
    - Login
    - Menu (from Service Interface)
    - Check If Book Available
    - Check If AudioBook Exists
    - Check If EBook Exists
    - Add Loan
    - Display Loans For A Given Customer
    - Display All Loans

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


- [ ] Se vor realiza fișiere de tip CSV pentru cel puțin 4 dintre clasele definite în prima etapa. Fiecare coloana din fișier este separata de virgula. Exemplu:nume,prenume,varsta
- [ ] Se vor realiza servicii singleton generice pentru scrierea și citirea din fișiere;
- [ ] La pornirea programului se vor încărca datele din fișiere utilizând serviciile create;

### 2) Realizarea unui serviciu de audit

- [ ] Se va realiza un serviciu care sa scrie într-un fișier de tip CSV de fiecare data când este executată una dintre acțiunile descrise în prima etapa. Structura fișierului: nume_actiune, timestamp

## Etapa III - *Deadline 31 mai 2022*


Înlocuiți serviciile realizate în etapa a II-a cu servicii care sa asigure persistenta utilizând baza de date folosind JDBC.

- [ ] Să se realizeze servicii care sa expună operații de tip create, read, update si delete pentru cel puțin 4 dintre clasele definite.