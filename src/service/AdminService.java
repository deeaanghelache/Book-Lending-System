package service;


import entity.bookType.*;
import entity.category.Category;
import entity.category.Subcategory;
import entity.company.Company;
import entity.loan.Loan;
import entity.review.Review;
import entity.user.Admin;
import entity.user.Customer;

import java.util.*;

public class AdminService implements Service {
    private List<Book> books = new ArrayList<>();
    private List<Audiobook> audiobooks = new ArrayList<>();
    private List<EBook> ebooks = new ArrayList<>();
    private List<Company> companies = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();

    public AdminService() {}

    public AdminService(List<Book> books, List<Audiobook> audiobooks, List<EBook> ebooks, List<Company> companies, List<Customer> customers) {
        this.books = books;
        this.audiobooks = audiobooks;
        this.ebooks = ebooks;
        this.companies = companies;
        this.customers = customers;
    }

    public void login(Scanner scanner, AdminService adminService, String username){
        scanner.nextLine();
        System.out.println("\n\t\t\t------------- ADMIN LOGIN -------------");
        Admin admin = Admin.getAdminInstance();
        int attempts = 3;

        while (attempts != 0) {
            System.out.println("\t Please enter your username: ");
            String adminUsername = scanner.nextLine();
            if (!Objects.equals(adminUsername, admin.getEmailAddress())) {
                attempts--;
                System.out.println(" Wrong username! You have " + attempts + " attempts left.");
                continue;
            }
            System.out.println("\t Please enter your password: ");
            String adminPassword = scanner.nextLine();
            if (!Objects.equals(adminPassword, admin.getPassword())) {
                attempts--;
                System.out.println(" Wrong password! You have " + attempts + " attempts left.");
                continue;
            }
            System.out.println("\t - You are now logged in as admin -");
            menu(scanner, adminService, "admin");
            break;
        }
    }

    private void addBook(Scanner scanner){
        System.out.println("Give the following information about a book: ");

        System.out.println("Name: ");
        String name = scanner.nextLine();

        System.out.println("Author: ");
        String author = scanner.nextLine();

        System.out.println("Description: ");
        String description = scanner.nextLine();

        System.out.println("Category: ");
        String category = scanner.nextLine();

        Category categoryOfTheBook = Category.NONFICTION;
        if(category.toUpperCase().equals("FICTION")){
            categoryOfTheBook = Category.FICTION;
        } else{
            if (!category.toUpperCase().equals("NONFICTION")){
                System.out.println("There is not such category");
                return;
            }
        }

        System.out.println("Subcategory: ");
        String subcategory = scanner.nextLine();

        Subcategory subcategoryOfTheBook = Subcategory.HISTORY;;
        switch (subcategory.toUpperCase()){
            case ("HISTORY"):
                break;
            case ("BIOGRAPHY"):
                subcategoryOfTheBook = Subcategory.BIOGRAPHY;
                break;
            case ("CLASSICS"):
                subcategoryOfTheBook = Subcategory.CLASSICS;
                break;
            case ("FANTASY"):
                subcategoryOfTheBook = Subcategory.FANTASY;
                break;
            case ("HISTORICALFICTION"):
                subcategoryOfTheBook = Subcategory.HISTORICALFICTION;
                break;
            case ("MEMOIR"):
                subcategoryOfTheBook = Subcategory.MEMOIR;
                break;
            case ("POETRY"):
                subcategoryOfTheBook = Subcategory.POETRY;
                break;
            case ("ROMANCE"):
                subcategoryOfTheBook = Subcategory.ROMANCE;
                break;
            case ("SCIENCE"):
                subcategoryOfTheBook = Subcategory.SCIENCE;
                break;
            case ("SELFHELP"):
                subcategoryOfTheBook = Subcategory.SELFHELP;
                break;
            case ("TRAVEL"):
                subcategoryOfTheBook = Subcategory.TRAVEL;
                break;
            case ("THRILLER"):
                subcategoryOfTheBook = Subcategory.THRILLER;
                break;
            case ("SCIENCEFICTION"):
                subcategoryOfTheBook = Subcategory.SCIENCEFICTION;
                break;
            default:
                System.out.println("There is no such subcategory!");
                return;
        }

        String availability = "available";

        System.out.println("Cover Type: ");
        String coverType = scanner.nextLine();

        System.out.println("Publishing House: ");
        String publishingHouse = scanner.nextLine();

        System.out.println("Number of books available: ");
        int numberOfBooksAvailable = scanner.nextInt();

        System.out.println("Number of pages: ");
        int numberOfPages = scanner.nextInt();

        Book currentBook = new Book(name, author, description, categoryOfTheBook, subcategoryOfTheBook, availability, numberOfBooksAvailable, numberOfPages, coverType, publishingHouse);
        books.add(currentBook);

//        System.out.println(name + " " + description);
    }

    private void addAudioBook(Scanner scanner){
        System.out.println("Give the following information about an audiobook: ");

        System.out.println("Name: ");
        String name = scanner.nextLine();

        System.out.println("Author: ");
        String author = scanner.nextLine();

        System.out.println("Description: ");
        String description = scanner.nextLine();

        System.out.println("Category: ");
        String category = scanner.nextLine();

        Category categoryOfTheBook = Category.NONFICTION;
        if(category.toUpperCase().equals("FICTION")){
            categoryOfTheBook = Category.FICTION;
        } else{
            if (!category.toUpperCase().equals("NONFICTION")){
                System.out.println("There is not such category");
                return;
            }
        }

        System.out.println("Subcategory: ");
        String subcategory = scanner.nextLine();

        Subcategory subcategoryOfTheBook = Subcategory.HISTORY;;
        switch (subcategory.toUpperCase()){
            case ("HISTORY"):
                break;
            case ("BIOGRAPHY"):
                subcategoryOfTheBook = Subcategory.BIOGRAPHY;
                break;
            case ("CLASSICS"):
                subcategoryOfTheBook = Subcategory.CLASSICS;
                break;
            case ("FANTASY"):
                subcategoryOfTheBook = Subcategory.FANTASY;
                break;
            case ("HISTORICALFICTION"):
                subcategoryOfTheBook = Subcategory.HISTORICALFICTION;
                break;
            case ("MEMOIR"):
                subcategoryOfTheBook = Subcategory.MEMOIR;
                break;
            case ("POETRY"):
                subcategoryOfTheBook = Subcategory.POETRY;
                break;
            case ("ROMANCE"):
                subcategoryOfTheBook = Subcategory.ROMANCE;
                break;
            case ("SCIENCE"):
                subcategoryOfTheBook = Subcategory.SCIENCE;
                break;
            case ("SELFHELP"):
                subcategoryOfTheBook = Subcategory.SELFHELP;
                break;
            case ("TRAVEL"):
                subcategoryOfTheBook = Subcategory.TRAVEL;
                break;
            case ("THRILLER"):
                subcategoryOfTheBook = Subcategory.THRILLER;
                break;
            case ("SCIENCEFICTION"):
                subcategoryOfTheBook = Subcategory.SCIENCEFICTION;
                break;
            default:
                System.out.println("There is no such subcategory!");
                return;
        }

        String availability = "available";

        System.out.println("Duration: ");
        int duration = scanner.nextInt();

        Audiobook currentAudioBook = new Audiobook(name, author, description, categoryOfTheBook, subcategoryOfTheBook, availability, duration);
        audiobooks.add(currentAudioBook);
    }

    private void addEBook(Scanner scanner){
        System.out.println("Give the following information about an eBook: ");

        System.out.println("Name: ");
        String name = scanner.nextLine();

        System.out.println("Author: ");
        String author = scanner.nextLine();

        System.out.println("Description: ");
        String description = scanner.nextLine();

        System.out.println("Category: ");
        String category = scanner.nextLine();

        Category categoryOfTheBook = Category.NONFICTION;
        if(category.toUpperCase().equals("FICTION")){
            categoryOfTheBook = Category.FICTION;
        } else{
            if (!category.toUpperCase().equals("NONFICTION")){
                System.out.println("There is not such category");
                return;
            }
        }

        System.out.println("Subcategory: ");
        String subcategory = scanner.nextLine();

        Subcategory subcategoryOfTheBook = Subcategory.HISTORY;;
        switch (subcategory.toUpperCase()){
            case ("HISTORY"):
                break;
            case ("BIOGRAPHY"):
                subcategoryOfTheBook = Subcategory.BIOGRAPHY;
                break;
            case ("CLASSICS"):
                subcategoryOfTheBook = Subcategory.CLASSICS;
                break;
            case ("FANTASY"):
                subcategoryOfTheBook = Subcategory.FANTASY;
                break;
            case ("HISTORICALFICTION"):
                subcategoryOfTheBook = Subcategory.HISTORICALFICTION;
                break;
            case ("MEMOIR"):
                subcategoryOfTheBook = Subcategory.MEMOIR;
                break;
            case ("POETRY"):
                subcategoryOfTheBook = Subcategory.POETRY;
                break;
            case ("ROMANCE"):
                subcategoryOfTheBook = Subcategory.ROMANCE;
                break;
            case ("SCIENCE"):
                subcategoryOfTheBook = Subcategory.SCIENCE;
                break;
            case ("SELFHELP"):
                subcategoryOfTheBook = Subcategory.SELFHELP;
                break;
            case ("TRAVEL"):
                subcategoryOfTheBook = Subcategory.TRAVEL;
                break;
            case ("THRILLER"):
                subcategoryOfTheBook = Subcategory.THRILLER;
                break;
            case ("SCIENCEFICTION"):
                subcategoryOfTheBook = Subcategory.SCIENCEFICTION;
                break;
            default:
                System.out.println("There is no such subcategory!");
                return;
        }

        String availability = "available";

        System.out.println("Format: ");
        String format = scanner.nextLine();

        System.out.println("Number of pages: ");
        int numberOfPages = scanner.nextInt();

        EBook currentEBook = new EBook(name, author, description, categoryOfTheBook, subcategoryOfTheBook, availability, numberOfPages, format);
        ebooks.add(currentEBook);
    }

    private void addCompany(Scanner scanner){
        System.out.println("Name: ");
        String name = scanner.nextLine();

        System.out.println("Address: ");
        String address = scanner.nextLine();

        System.out.println("Telephone Number: ");
        String telephoneNumber = scanner.nextLine();

        Company company = new Company(name, address, telephoneNumber);
        companies.add(company);
    }

    // register
    public void addCustomer(Scanner scanner){
        System.out.println();
        System.out.println("-------REGISTER--------");

        System.out.println("Hello, please complete the following information in order to register to our site!");

        System.out.println("First Name: ");
        String firstName = scanner.nextLine();

        System.out.println("Last Name: ");
        String lastName = scanner.nextLine();

        System.out.println("Email: ");
        String email = scanner.nextLine();

        System.out.println("Password: ");
        String password = scanner.nextLine();

        System.out.println("Address: ");
        String address = scanner.nextLine();

        System.out.println("Company name: ");
        String companyName = scanner.nextLine();

        int companyId = 0;
        for (Company company : companies) {
            if (company.getName().toUpperCase().equals(companyName.toUpperCase())){
                companyId = company.getCompanyId();
            }
        }

        Set<Loan> emptyList = new HashSet<>();

        if (companyId != 0){
            Customer currentCustomer = new Customer(firstName, lastName, email, password, companyId, emptyList, address);
            customers.add(currentCustomer);
//            System.out.println(currentCustomer);
        }
        else{
            System.out.println("There is no company with the name provided!");
        }
    }

    private void removeBookFromList(int idToRemove){
        books.removeIf(x -> x.getId() == idToRemove);
    }

    private void removeAudioBookFromList(int idToRemove){
        audiobooks.removeIf(x -> x.getId() == idToRemove);
    }

    private void removeEBookFromList(int idToRemove){
        ebooks.removeIf(x -> x.getId() == idToRemove);
    }

    private void removeCompany(int idToRemove){
        companies.removeIf(x -> x.getCompanyId() == idToRemove);
    }

    private void displayBooks(){
        BookNameComparator bookNameComparator = new BookNameComparator();
        books.sort(bookNameComparator);

        for (Book book : books){
            System.out.println(book);
        }
    }

    private void displayAudiobooks(){
        for (Audiobook audiobook : audiobooks){
            System.out.println(audiobook);
        }
    }

    private void displayEBooks(){
        for (EBook ebook : ebooks){
            System.out.println(ebook);
        }
    }

    private void displayCompanies(){
        for (Company company : companies){
            System.out.println(company);
        }
    }

    public void displayCustomers(){
        for (Customer customer : customers){
            System.out.println(customer);
        }
    }

    @Override
    public void menu(Scanner scanner, AdminService admin, String username) {
        System.out.println("\n\t\t\t------------- ADMIN MENU -------------");

        while(true){
            System.out.println("\t Please choose what you want to do: ");
            System.out.println("\t -> Option 1 - Add items in the database ");
            System.out.println("\t -> Option 2 - Remove items in the database ");
            System.out.println("\t -> Option 3 - Display existing items ");
            System.out.println("\t -> Option 4 - EXIT ");

            int option1 = scanner.nextInt();

            switch (option1) {
                case (1):
                    // Add

                    System.out.println("\t What type of item do you want to add?");
                    System.out.println("\t -> Option 1 - Book ");
                    System.out.println("\t -> Option 2 - AudioBook ");
                    System.out.println("\t -> Option 3 - EBook ");
                    System.out.println("\t -> Option 4 - Company ");

                    int option2 = scanner.nextInt();
                    scanner.nextLine();

                    switch (option2) {
                        case (1) -> addBook(scanner);
                        case (2) -> addAudioBook(scanner);
                        case (3) -> addEBook(scanner);
                        case (4) -> addCompany(scanner);
                    }
                    break;
                case (2):
                    // Remove

                    System.out.println("\t What type of item do you want to remove?");
                    System.out.println("\t -> Option 1 - Book ");
                    System.out.println("\t -> Option 2 - AudioBook ");
                    System.out.println("\t -> Option 3 - EBook ");
                    System.out.println("\t -> Option 4 - Company ");

                    int option3 = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("\t Please, enter the id of the item you want to remove: ");
                    int idToRemove = scanner.nextInt();

                    switch (option3) {
                        case (1) -> removeBookFromList(idToRemove);
                        case (2) -> removeAudioBookFromList(idToRemove);
                        case (3) -> removeEBookFromList(idToRemove);
                        case (4) -> removeCompany(idToRemove);
                    }
                    break;
                case (3):
                    // Display

                    System.out.println("\t What type of item do you want to display?");
                    System.out.println("\t -> Option 1 - Books ");
                    System.out.println("\t -> Option 2 - AudioBooks ");
                    System.out.println("\t -> Option 3 - EBooks ");
                    System.out.println("\t -> Option 4 - Companies ");
                    System.out.println("\t -> Option 5 - Customers ");

                    int option4 = scanner.nextInt();
                    scanner.nextLine();

                    switch (option4) {
                        case (1) -> displayBooks();
                        case (2) -> displayAudiobooks();
                        case (3) -> displayEBooks();
                        case (4) -> displayCompanies();
                        case (5) -> displayCustomers();
                    }
                    System.out.println("\n");
                    break;
                case (4):
                    // Exit
                    System.out.println("Goodbye, " + username);
                    return;
            }
        }
    }

    public List<Book> getBooks() {
        return books;
    }

    public List<Audiobook> getAudiobooks() {
        return audiobooks;
    }

    public List<EBook> getEbooks() {
        return ebooks;
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public void setAudiobooks(List<Audiobook> audiobooks) {
        this.audiobooks = audiobooks;
    }

    public void setEbooks(List<EBook> ebooks) {
        this.ebooks = ebooks;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
}
