package service;

import entity.bookType.*;
import entity.category.Category;
import entity.category.Subcategory;
import entity.company.Company;
import entity.loan.Loan;
import entity.user.Admin;
import entity.user.Customer;
import entity.user.User;
import exception.NumberOfPagesException;

import java.io.IOException;
import java.util.*;
import java.util.function.Predicate;

public class AdminService implements Service {
    private List<Book> books = new ArrayList<>();
    private List<Audiobook> audiobooks = new ArrayList<>();
    private List<EBook> ebooks = new ArrayList<>();
    private List<Company> companies = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();

    private WriteToCsvFileService csvFileWriter = WriteToCsvFileService.getInstance();

    public AdminService() {}

    public AdminService(List<Book> books, List<Audiobook> audiobooks, List<EBook> ebooks, List<Company> companies, List<Customer> customers) {
        this.books = books;
        this.audiobooks = audiobooks;
        this.ebooks = ebooks;
        this.companies = companies;
        this.customers = customers;
    }

    public void login(Scanner scanner, AdminService adminService, String username, AuditService audit) throws IOException {
        audit.writeActionToFile("AdminLogin");

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
            menu(scanner, adminService, "admin", audit);
            break;
        }
    }

    private void addBook(Scanner scanner, AuditService audit) throws IOException {
        audit.writeActionToFile("AddBook");
        scanner.nextLine();

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

        int numberOfPages;

        while(true){
            System.out.println("Number of pages: ");

            numberOfPages = scanner.nextInt();
            try {
                if (numberOfPages <= 0){
                    throw new NumberOfPagesException("");
                }
                break;
            }
            catch (NumberOfPagesException exception){
                System.out.println("The number of pages shouldn't be a negative number!");
            }
        }

        Book currentBook = new Book(name, author, description, categoryOfTheBook, subcategoryOfTheBook, availability, numberOfBooksAvailable, numberOfPages, coverType, publishingHouse);
        books.add(currentBook);
        csvFileWriter.writeItemToCsv(currentBook, Book.class);
//        System.out.println(name + " " + description);
    }

    private void addAudioBook(Scanner scanner, AuditService audit) throws IOException {
        audit.writeActionToFile("AddAudioBook");
        scanner.nextLine();

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
        csvFileWriter.writeItemToCsv(currentAudioBook, Audiobook.class);
    }

    private void addEBook(Scanner scanner, AuditService audit) throws IOException {
        audit.writeActionToFile("AddEBook");
        scanner.nextLine();

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

        int numberOfPages;

        while(true){
            System.out.println("Number of pages: ");

            numberOfPages = scanner.nextInt();
            try {
                if (numberOfPages <= 0){
                    throw new NumberOfPagesException("");
                }
                break;
            }
            catch (NumberOfPagesException exception){
                System.out.println("The number of pages shouldn't be a negative number!");
            }
        }

        EBook currentEBook = new EBook(name, author, description, categoryOfTheBook, subcategoryOfTheBook, availability, numberOfPages, format);
        ebooks.add(currentEBook);
        csvFileWriter.writeItemToCsv(currentEBook, EBook.class);
    }

    private void addCompany(Scanner scanner, AuditService audit) throws IOException {
        audit.writeActionToFile("AddCompany");
        scanner.nextLine();

        System.out.println("Name: ");
        String name = scanner.nextLine();

        System.out.println("Address: ");
        String address = scanner.nextLine();

        System.out.println("Telephone Number: ");
        String telephoneNumber = scanner.nextLine();

        Company company = new Company(name, address, telephoneNumber);
        companies.add(company);
        csvFileWriter.writeToCompanyCsv(company);
    }

    // register
    public void addCustomer(Scanner scanner, AuditService audit) throws IOException {
        audit.writeActionToFile("Register");
//        scanner.nextLine();

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
            csvFileWriter.writeToCustomerCsv(currentCustomer);
        }
        else{
            System.out.println("There is no company with the name provided!");
        }
    }

    private void removeBookFromList(int idToRemove, AuditService audit) throws IOException {
        audit.writeActionToFile("RemoveBook");
        books.removeIf(x -> x.getId() == idToRemove);
    }

    private void removeAudioBookFromList(int idToRemove, AuditService audit) throws IOException {
        audit.writeActionToFile("RemoveAudioBook");
        audiobooks.removeIf(x -> x.getId() == idToRemove);
    }

    private void removeEBookFromList(int idToRemove, AuditService audit) throws IOException {
        audit.writeActionToFile("RemoveEBook");
        ebooks.removeIf(x -> x.getId() == idToRemove);
    }

    private void removeCompany(int idToRemove, AuditService audit) throws IOException {
        audit.writeActionToFile("RemoveCompany");
        companies.removeIf(x -> x.getCompanyId() == idToRemove);
    }

    private void displayBooks(AuditService audit) throws IOException {
        audit.writeActionToFile("DisplayBooks");

        BookNameComparator bookNameComparator = new BookNameComparator();
        books.sort(bookNameComparator);

        for (Book book : books){
            System.out.println(book);
        }
    }

    private void displayAudiobooks(AuditService audit) throws IOException {
        audit.writeActionToFile("DisplayAudiobooks");

        for (Audiobook audiobook : audiobooks){
            System.out.println(audiobook);
        }
    }

    private void displayEBooks(AuditService audit) throws IOException {
        audit.writeActionToFile("DisplayEBooks");

        for (EBook ebook : ebooks){
            System.out.println(ebook);
        }
    }

    private void displayCompanies(AuditService audit) throws IOException {
        audit.writeActionToFile("DisplayCompanies");

        for (Company company : companies){
            System.out.println(company);
        }
    }

    public void displayCustomers(AuditService audit) throws IOException {
        audit.writeActionToFile("DisplayCustomers");

        for (Customer customer : customers){
            System.out.println(customer);
        }
    }

    @Override
    public void menu(Scanner scanner, AdminService admin, String username, AuditService audit) throws IOException {
        audit.writeActionToFile("AdminMenu");

        System.out.println("\n\t\t\t------------- ADMIN MENU -------------");

        while(true){

            int option1;

            while (true){
                System.out.println("\t Please choose what you want to do: ");
                System.out.println("\t -> Option 1 - Add items in the database ");
                System.out.println("\t -> Option 2 - Remove items in the database ");
                System.out.println("\t -> Option 3 - Display existing items ");
                System.out.println("\t -> Option 4 - View available books ");
                System.out.println("\t -> Option 5 - List all customers that have loans ");
                System.out.println("\t -> Option 6 - List all books from a given publishing house ");
                System.out.println("\t -> Option 7 - EXIT ");

                try {
                    option1 = scanner.nextInt();
                    break;
                }
                catch (Exception exception) {
                    scanner.nextLine();
                    System.out.println("\t\tYou should enter a number 1-7");
                    System.out.println("\t !! Please Try Again");
                }
            }

            switch (option1) {
                case (1):
                    // Add

                    int option2;

                    while (true){
                        System.out.println("\t What type of item do you want to add?");
                        System.out.println("\t -> Option 1 - Book ");
                        System.out.println("\t -> Option 2 - AudioBook ");
                        System.out.println("\t -> Option 3 - EBook ");
                        System.out.println("\t -> Option 4 - Company ");

                        try {
                            option2 = scanner.nextInt();
                            break;
                        }
                        catch (Exception exception) {
                            scanner.nextLine();
                            System.out.println("\t\tYou should enter a number 1-4");
                            System.out.println("\t !! Please Try Again");
                        }
                    }

                    switch (option2) {
                        case (1) -> addBook(scanner, audit);
                        case (2) -> addAudioBook(scanner, audit);
                        case (3) -> addEBook(scanner, audit);
                        case (4) -> addCompany(scanner, audit);
                    }
                    break;
                case (2):
                    // Remove

                    int option3;

                    while (true){
                        System.out.println("\t What type of item do you want to remove?");
                        System.out.println("\t -> Option 1 - Book ");
                        System.out.println("\t -> Option 2 - AudioBook ");
                        System.out.println("\t -> Option 3 - EBook ");
                        System.out.println("\t -> Option 4 - Company ");

                        try {
                            option3 = scanner.nextInt();
                            break;
                        }
                        catch (Exception exception) {
                            scanner.nextLine();
                            System.out.println("\t\tYou should enter a number 1-4");
                            System.out.println("\t !! Please Try Again");
                        }
                    }

                    System.out.println("\t Please, enter the id of the item you want to remove: ");
                    int idToRemove = scanner.nextInt();

                    switch (option3) {
                        case (1) -> removeBookFromList(idToRemove, audit);
                        case (2) -> removeAudioBookFromList(idToRemove, audit);
                        case (3) -> removeEBookFromList(idToRemove, audit);
                        case (4) -> removeCompany(idToRemove, audit);
                    }
                    break;
                case (3):
                    // Display

                    int option4;

                    while (true){
                        System.out.println("\t What type of item do you want to display?");
                        System.out.println("\t -> Option 1 - Books ");
                        System.out.println("\t -> Option 2 - AudioBooks ");
                        System.out.println("\t -> Option 3 - EBooks ");
                        System.out.println("\t -> Option 4 - Companies ");
                        System.out.println("\t -> Option 5 - Customers ");

                        try {
                            option4 = scanner.nextInt();
                            break;
                        }
                        catch (NumberFormatException exception) {
                            scanner.nextLine();
                            System.out.println("\t\tYou should enter a number 1-5");
                            System.out.println("\t !! Please Try Again");
                        }
                    }

                    switch (option4) {
                        case (1) -> displayBooks(audit);
                        case (2) -> displayAudiobooks(audit);
                        case (3) -> displayEBooks(audit);
                        case (4) -> displayCompanies(audit);
                        case (5) -> displayCustomers(audit);
                    }
                    System.out.println("\n");
                    break;
                case (4):
                    viewAvailableBooks(audit);
                    break;
                case (5):
                    viewCustomersThatHaveLoans(audit);
                    break;
                case (6):
                    scanner.nextLine();
                    System.out.println("Please type the name of the publishing house: ");
                    String publishingHouse = scanner.nextLine();
                    viewBooksFromGivenPublishingHouse(publishingHouse, audit);
                    break;
                case (7):
                    // Exit
                    System.out.println("Goodbye, " + username);
                    return;
            }
        }
    }

    public void viewAvailableBooks(AuditService audit) throws IOException {
        audit.writeActionToFile("ViewAvailableBooks");
        List<String> availableBooks = new ArrayList<>();

        Predicate<Book> bookPredicate = book -> book.getNumberOfBooksAvailable() > 0;
        for (Book book : books){
            if (bookPredicate.test(book)){
                availableBooks.add(book.getName());
            }
        }

        System.out.println("\tAvailable Books:\n");
        for (String name : availableBooks){
            System.out.println("\t\t - " + name);
        }
    }

    public void viewCustomersThatHaveLoans(AuditService audit) throws IOException {
        audit.writeActionToFile("ViewCustomersThatHaveLoans");

        var customersWithLoans = customers;
        customersWithLoans
                .stream()
                .filter(x -> !x.getLoans().isEmpty())
                .map(User::getEmailAddress)
                .forEach(System.out::println);
    }

    public void viewBooksFromGivenPublishingHouse(String publishingHouse, AuditService audit) throws IOException {
        audit.writeActionToFile("ViewBooksFromGivenPublishingHouse");

        var booksStream = books;
        booksStream
                .stream()
                .filter(x -> x.getPublishingHouse().equalsIgnoreCase(publishingHouse))
                .map(BookType::getName)
                .forEach(System.out::println);
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
