package service;

import entity.bookType.Audiobook;
import entity.bookType.Book;
import entity.bookType.EBook;
import entity.loan.Loan;
import entity.review.Review;
import entity.user.Customer;

import java.time.LocalDate;
import java.util.*;

public class CustomerService implements Service {
    //    private TreeMap<Integer, List<Loan>> customerLoans = new TreeMap<>(); // customerId, loans
    private List<Loan> loans = new ArrayList<>();

    public void login(Scanner scanner, AdminService admin){
        System.out.println("\n\t\t\t------------- CUSTOMER LOGIN -------------");
        int attempts = 3;

        while (attempts != 0){
            System.out.println("\t Please enter your username: ");
            String customerUsername = scanner.nextLine();
            System.out.println("\t Please enter your password: ");
            String customerPassword = scanner.nextLine();

            var customers = admin.getCustomers();

            boolean ok = false;
            for (Customer customer : customers){
                if (customer.getEmailAddress().equals(customerUsername) && customer.getPassword().equals(customerPassword)){
                    ok = true;
                    break;
                }
            }

            if (!ok){
                attempts--;
                System.out.println(" Wrong password or username! You have " + attempts + " attempts left.");
                continue;
            }

            System.out.println("\t - You are now logged in as customer -");
            menu(scanner, admin, customerUsername);
            break;
        }
    }

    public boolean checkIfBookAvailable(String name, AdminService as){
        var books = as.getBooks();
        boolean available = false;

        for(Book book : books){
            if (Objects.equals(book.getName().toLowerCase(), name.toLowerCase())){
                if (book.getNumberOfBooksAvailable() > 0){
                    available = true;
                    int numberAvailable = book.getNumberOfBooksAvailable();
                    numberAvailable--;
                    book.setNumberOfBooksAvailable(numberAvailable);
                    break;
                }
            }
        }
        return available;
    }

//    private boolean checkIfBookExists(String name, AdminService as){
//        var books = as.getBooks();
//
//        for(Book book : books){
//            if (Objects.equals(book.getName().toLowerCase(), name.toLowerCase())){
//                if (book.getNumberOfBooksAvailable() > 0){
//                    return true;
//                }
//            }
//        }
//        return false;
//    }

    public boolean checkIfAudioBookExists(String name, AdminService as){
        var audiobooks = as.getAudiobooks();

        for (Audiobook audio : audiobooks){
//            System.out.println(audio.getName());
//            System.out.println(name);
            if (audio.getName().equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
    }

    public boolean checkIfEBookExists(String name, AdminService as){
        var ebooks = as.getEbooks();

        for (EBook ebook : ebooks){
            if (ebook.getName().equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
    }

    // adding a loan using the username(email) of the client
    public void addLoan(String email, AdminService as){
        // sa adaugi un loan (cu username)
        System.out.println("-------Loan a book-------");
        System.out.println("Please enter the name of the book/audiobook/ebook you want");
        System.out.println("If you are done, please enter DONE");

        Scanner scanner = new Scanner(System.in);
        List<Book> initialbooks = new ArrayList<>();
        List<Audiobook> initialaudiobooks = new ArrayList<>();
        List<EBook> initialebooks = new ArrayList<>();

        LocalDate date = LocalDate.now();

        Loan currentLoan = new Loan(initialbooks, initialebooks, initialaudiobooks, date, 0);
        boolean empty = true;

//        as.displayAudiobooks();
//        as.displayEBooks();
//        as.displayBooks();

        while(true){
            System.out.println("Type the format of the book you want or DONE if you are done!");
            String command = scanner.nextLine();

            if (command.toUpperCase().equals("AUDIOBOOK")){
                System.out.println("Enter the name of the audiobook");
                String name = scanner.nextLine();

//                System.out.print("----------" + name);

                if(checkIfAudioBookExists(name, as)){
                    var audiobooks = as.getAudiobooks();

                    for (Audiobook audio : audiobooks){
                        String audiobookName = audio.getName();
//                        System.out.println(audiobookName);
//                        System.out.println(name);
                        if (audiobookName.equalsIgnoreCase(name)){
                            List<Audiobook> currentAudiobooks = currentLoan.getAudioBooks();
                            currentAudiobooks.add(audio);
                            currentLoan.setAudioBooks(currentAudiobooks);
                            empty = false;
                        }
                    }
                }
                else{
                    System.out.println("Sorry, we couldn't find the audiobook you were looking for!");
                }
            }

            if (command.toUpperCase().equals("BOOK")){
                System.out.println("Enter the name of the book");
                String name = scanner.nextLine();

                if (checkIfBookAvailable(name, as)){
                    // Book is available
                    var books = as.getBooks();
                    for(Book book : books){
                        if (book.getName().equalsIgnoreCase(name)){
                            List<Book> currentBooks = currentLoan.getBooks();
                            currentBooks.add(book);
                            currentLoan.setBooks(currentBooks);
                            empty = false;
                        }
                    }
                }
                else{
                    System.out.println("Sorry, we couldn't find the book you were looking for!");
                }
            }

            if (command.toUpperCase().equals("EBOOK")){
                System.out.println("Enter the name of the ebook");
                String name = scanner.nextLine();

                if(checkIfEBookExists(name, as)){
                    var ebooks = as.getEbooks();

                    for (EBook ebook : ebooks){
                        if (ebook.getName().equalsIgnoreCase(name)){
                            List<EBook> currentEbooks = currentLoan.getEBooks();
                            currentEbooks.add(ebook);
                            currentLoan.setEBooks(currentEbooks);
                            empty = false;
                        }
                    }
                }
                else{
                    System.out.println("Sorry, we couldn't find the audiobook you were looking for!");
                }

            }
            if (command.toUpperCase().equals("DONE")){
                break;
            }
        }
        if (!empty){
            List<Customer> customers = as.getCustomers();
            for (Customer customer : customers){
                if (customer.getEmailAddress().equals(email)){
                    int id = customer.getUserId();
                    currentLoan.setCustomerId(id);
                    customer.addLoan(currentLoan);

                    var currentloans = getLoans();
                    currentloans.add(currentLoan);
                    setLoans(currentloans);
//                    System.out.println(getLoans());
                }
            }
        }
    }

    public void viewProfile(AdminService admin, String username){
        var customers = admin.getCustomers();
//        System.out.println(customers);

        for (Customer customer : customers){
            if (customer.getEmailAddress().equals(username)){
                System.out.println(customer);
                System.out.println("\n");
                return;
            }
        }
    }

    public void reviewBook(Book book, Scanner scanner){
        // un review este anonim, iar un user poate oferi un review
        System.out.println("\t------- BOOK REVIEW " + book.getName() + " -------");
        System.out.println("Give a rating 1-5: ");

        int rating = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Do you want to write something about this book (yes/no)?");
        String answer = scanner.nextLine();

        switch (answer){
            case ("yes") -> {
                System.out.println("Write your review here and press ENTER when you're done: ");
                String reviewDescription = scanner.nextLine();
                Review review = new Review(rating, reviewDescription);
                book.addReview(review);
            }
            case ("no") -> {
                Review review = new Review(rating, "");
                book.addReview(review);
            }
        }

        System.out.println("Your review has been saved! Thank you!");
    }

    public void reviewEBook(EBook ebook, Scanner scanner){
        System.out.println("\t------- EBOOK REVIEW " + ebook.getName() + " -------");
        System.out.println("Give a rating 1-5: ");

        int rating = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Do you want to write something about this ebook (yes/no)?");
        String answer = scanner.nextLine();

        switch (answer){
            case ("yes") -> {
                System.out.println("Write your review here and press ENTER when you're done: ");
                String reviewDescription = scanner.nextLine();
                Review review = new Review(rating, reviewDescription);
                ebook.addReview(review);
            }
            case ("no") -> {
                Review review = new Review(rating, "");
                ebook.addReview(review);
            }
        }

        System.out.println("Your review has been saved! Thank you!");
    }

    public void reviewAudioBook(Audiobook audiobook, Scanner scanner){
        System.out.println("\t------- AUDIOBOOK REVIEW " + audiobook.getName() + " -------");
        System.out.println("Give a rating 1-5: ");

        int rating = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Do you want to write something about this audiobook (yes/no)?");
        String answer = scanner.nextLine();

        switch (answer){
            case ("yes") -> {
                System.out.println("Write your review here and press ENTER when you're done: ");
                String reviewDescription = scanner.nextLine();
                Review review = new Review(rating, reviewDescription);
                audiobook.addReview(review);
            }
            case ("no") -> {
                Review review = new Review(rating, "");
                audiobook.addReview(review);
            }
        }

        System.out.println("Your review has been saved! Thank you!");
    }

    @Override
    public void menu(Scanner scanner, AdminService admin, String username) {
        System.out.println("\n\t\t\t------------- CUSTOMER MENU -------------");

        var books = admin.getBooks();
        var audiobooks = admin.getAudiobooks();
        var ebooks = admin.getEbooks();
        var customers = admin.getCustomers();

        while(true){
            System.out.println("\t Please choose what you want to do: ");
            System.out.println("\t -> Option 1 - Loan some books ");
            System.out.println("\t -> Option 2 - Display your loans ");
            System.out.println("\t -> Option 3 - View your profile ");
            System.out.println("\t -> Option 4 - Review something you read ");
            System.out.println("\t -> Option 5 - Change your username ");
            System.out.println("\t -> Option 6 - Change your password ");
            System.out.println("\t -> Option 7 - EXIT ");

            int option1 = scanner.nextInt();

            switch (option1) {
                case (1) -> addLoan(username, admin);
                case (2) -> displayLoansCustomer(username, admin);
                case (3) -> viewProfile(admin, username);
                case (4) -> {
                    System.out.println("\t Please choose what you want to review: ");
                    System.out.println("\t -> Option 1 - Book ");
                    System.out.println("\t -> Option 2 - EBook ");
                    System.out.println("\t -> Option 3 - AudioBook ");

                    scanner.nextLine();
                    int answer = scanner.nextInt();

                    switch (answer){
                        case (1) -> {
                            scanner.nextLine();
                            System.out.println("\t Enter the name of the book: ");
                            String name = scanner.nextLine();
                            boolean found = false;

                            for(Book book : books){
                                if (book.getName().equalsIgnoreCase(name)){
                                    reviewBook(book, scanner);
                                    found = true;
                                }
                            }

                            if (!found){
                                System.out.println("Sorry, we don't have the book you wanted to review in our database.");
                            }
                        }
                        case (2) -> {
                            scanner.nextLine();
                            System.out.println("\t Enter the name of the ebook: ");
                            String name = scanner.nextLine();
                            boolean found = false;

                            for(EBook ebook : ebooks){
                                if (ebook.getName().equalsIgnoreCase(name)){
                                    reviewEBook(ebook, scanner);
                                    found = true;
                                }
                            }

                            if (!found){
                                System.out.println("Sorry, we don't have the ebook you wanted to review in our database.");
                            }
                        }
                        case (3) -> {
                            scanner.nextLine();
                            System.out.println("\t Enter the name of the audiobook: ");
                            String name = scanner.nextLine();
                            boolean found = false;

                            for(Audiobook audiobook : audiobooks){
                                if (audiobook.getName().equalsIgnoreCase(name)){
                                    reviewAudioBook(audiobook, scanner);
                                    found = true;
                                }
                            }

                            if (!found){
                                System.out.println("Sorry, we don't have the audiobook you wanted to review in our database.");
                            }
                        }
                    }
                }
                case (5) -> {
                    for (var customer : customers) {
                        if (customer.getEmailAddress().equals(username)) {
                            username = customer.changeUsername(scanner);
                        }
                    }
                }
                case (6) -> {
                    for (var customer : customers){
                        if (customer.getEmailAddress().equals(username)){
                            customer.changePassword(scanner);
                        }
                    }
                }
                case (7) -> {
                    System.out.println("Goodbye, " + username);
                    return;
                }
            }
        }
    }

    public void displayLoansCustomer(String username, AdminService admin){
        // sa afisezi toate loans pentru un customer dat

        var customers = admin.getCustomers();
        int customerId = 0;

        for (Customer customer : customers){
            if (customer.getEmailAddress().equals(username)){
                customerId = customer.getUserId();
            }
        }

        boolean hasLoans = false;
        if (customerId != 0) {
            System.out.println("\t\t -----" + username + "'s LOANS -----");
            for (Loan loan : loans) {
                if (loan.getCustomerId() == customerId) {
                    System.out.println(loan);
                    hasLoans = true;
                }
            }
            if (!hasLoans){
                System.out.println("I'm sorry, " + username + ", but you have no loans :(");
            }
            System.out.println("\n");
        }
    }

    private void displayAllLoans(){
        // sa afisezi toate loans din aplicatie
        for (Loan loan : loans){
            System.out.println(loan);
        }
    }

//    public TreeMap<Integer, List<Loan>> getCustomerLoans() {
//        return customerLoans;
//    }
//
//    public void setCustomerLoans(TreeMap<Integer, List<Loan>> customerLoans) {
//        this.customerLoans = customerLoans;
//    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }
}

