import entity.bookType.Audiobook;
import entity.bookType.Book;
import entity.bookType.EBook;
import entity.category.Category;
import entity.category.Subcategory;
import entity.company.Company;
import entity.loan.Loan;
import entity.user.Customer;
import service.AdminService;
import service.AuditService;
import service.CustomerService;

import java.io.IOException;
import java.util.*;

public class Main {

    static List<Book> booksDatabase(){
        Book book1 = new Book("Iernile Sufletului", "Katherine May", "Descriere", Category.NONFICTION, Subcategory.SELFHELP, "available", 30, 300, "Paperback", "Nemira");
        Book book2 = new Book("Cascada Ingerilor", "Kristin Hannah", "Descriere", Category.FICTION, Subcategory.ROMANCE, "available", 0, 300, "Paperback", "Litera");
        Book book3 = new Book("Calatoria Cilkai", "Heather Morris", "Descriere", Category.FICTION, Subcategory.HISTORICALFICTION, "available", 0, 300, "Paperback", "Humanitas");
        Book book4 = new Book("Modelul din Paris", "Alexandra Joel", "Descriere", Category.FICTION, Subcategory.HISTORICALFICTION, "available", 0, 400, "Paperback", "Nemira");
        Book book5 = new Book("Troia", "Stephen Fry", "Descriere", Category.NONFICTION, Subcategory.HISTORY, "available", 11, 200, "Paperback", "Trei");
        Book book6 = new Book("Ganduri catre sine insusi", "Marcus Aurelius", "Descriere", Category.NONFICTION, Subcategory.BIOGRAPHY, "not available", 0, 180, "Paperback", "Humanitas");
        Book book7 = new Book("Nepovestitele iubiri", "Tatiana Niculescu", "Descriere", Category.FICTION, Subcategory.ROMANCE, "available", 12, 210, "Paperback", "Humanitas");
        Book book8 = new Book("O scurta istorie ilustrata a romanilor", "Neagu Djuvara", "Descriere", Category.NONFICTION, Subcategory.HISTORY, "available", 30, 300, "Paperback", "Humanitas");
        Book book9 = new Book("Privighetoarea", "Kristin Hannah", "Descriere", Category.FICTION, Subcategory.ROMANCE, "available", 1, 500, "Paperback", "Litera");
        Book book10 = new Book("Hopeless", "Colleen Hoover", "Descriere", Category.FICTION, Subcategory.ROMANCE, "available", 10, 400, "Paperback", "Epica");

        List<Book> books = new ArrayList<>();

        books.add(book1); books.add(book2); books.add(book3); books.add(book4); books.add(book5);
        books.add(book6); books.add(book7); books.add(book8); books.add(book9); books.add(book10);

        return books;
    }

    static List<Audiobook> audiobooksDatabase(){
        Audiobook audiobook1 = new Audiobook("Iernile Sufletului", "Katherine May", "Descriere", Category.NONFICTION, Subcategory.SELFHELP, "available", 400);
        Audiobook audiobook3 = new Audiobook("Calatoria Cilkai", "Heather Morris", "Descriere", Category.FICTION, Subcategory.HISTORICALFICTION, "available", 1000);
        Audiobook audiobook5 = new Audiobook("Troia", "Stephen Fry", "Descriere", Category.NONFICTION, Subcategory.HISTORY, "available", 1200);
        Audiobook audiobook7 = new Audiobook("Nepovestitele iubiri", "Tatiana Niculescu", "Descriere", Category.FICTION, Subcategory.ROMANCE, "available", 500);
        Audiobook audiobook9 = new Audiobook("Privighetoarea", "Kristin Hannah", "Descriere", Category.FICTION, Subcategory.ROMANCE, "available", 450);

        List<Audiobook> audiobooks = new ArrayList<>();

        audiobooks.add(audiobook1); audiobooks.add(audiobook3); audiobooks.add(audiobook5); audiobooks.add(audiobook7); audiobooks.add(audiobook9);

        return audiobooks;
    }

    static List<EBook> ebooksDatabase(){
        EBook ebook2 = new EBook("Cascada Ingerilor", "Kristin Hannah", "Descriere", Category.FICTION, Subcategory.ROMANCE, "available", 1, "pdf");
        EBook ebook4 = new EBook("Modelul din Paris", "Alexandra Joel", "Descriere", Category.FICTION, Subcategory.HISTORICALFICTION, "available", 10, "kindle");
        EBook ebook6 = new EBook("Ganduri catre sine insusi", "Marcus Aurelius", "Descriere", Category.NONFICTION, Subcategory.BIOGRAPHY, "not available", 0, "kindle");
        EBook ebook8 = new EBook("O scurta istorie ilustrata a romanilor", "Neagu Djuvara", "Descriere", Category.NONFICTION, Subcategory.HISTORY, "available", 30, "kindle");
        EBook ebook10 = new EBook("Hopeless", "Colleen Hoover", "Descriere", Category.FICTION, Subcategory.ROMANCE, "available", 10, "pdf");

        List<EBook> ebooks = new ArrayList<>();

        ebooks.add(ebook2); ebooks.add(ebook4); ebooks.add(ebook6); ebooks.add(ebook8); ebooks.add(ebook10);

        return ebooks;
    }

    static List<Company> companiesDatabase(){
        Company company1 = new Company("SoftWeb", "Strada Soarelui", "07xxxxxxxxx");
        Company company2 = new Company("WebDesigners", "Strada Muncii", "07xxxxxxxxx");
        Company company3 = new Company("HelloWorld", "Strada Muzicii", "07xxxxxxxxx");

        List<Company> companies = new ArrayList<>();

        companies.add(company1); companies.add(company2); companies.add(company3);

        return companies;
    }

    static List<Customer> customersDatabase(){
        Set<Loan> loans1 = new HashSet<>();
        Customer customer1 = new Customer("Maria", "Ionescu", "maria.ionescu@gmail.com", "mariapa55", 1, loans1, "Adresa 1");
        Customer customer2 = new Customer("Ion", "cristea", "ic", "pa55", 1, loans1, "Adresa 3");

        List<Customer> customers = new ArrayList<>();
        customers.add(customer1);
        customers.add(customer2);

        return customers;
    }

    static void initialDisplay(Scanner scanner, AdminService admin, CustomerService customerService, AuditService audit) throws IOException {
        {
            while(true)
            {
                System.out.println("********** WELCOME TO MY BOOK LENDING PROJECT **********");
                System.out.println("\tPlease tell us if you are an admin, a customer or you want to exit by typing one of the 3 options:");
                System.out.println("\t * Option 1 - Admin");
                System.out.println("\t * Option 2 - Customer");
                System.out.println("\t * Option 3 - Exit");

                int option = scanner.nextInt();

                if (option == 1) {
                    System.out.println("\t\t Welcome, admin! Please login in order to continue: ");
                    admin.login(scanner, admin, "admin", audit);
                } else if (option == 2){
                    System.out.println("\t\t Welcome, customer! Do you have an account?");
                    System.out.println("\t * Option 1 - Yes");
                    System.out.println("\t * Option 2 - No");

                    int response = scanner.nextInt();
                    if (response == 1) {
                        scanner.nextLine();
                        System.out.println("\t Please login in order to continue: ");
                        customerService.login(scanner, admin, audit);
                    } else {
                        if (response == 2) {
                            scanner.nextLine();
                            admin.addCustomer(scanner, audit);

                            System.out.println("Please wait, we're REDIRECTING you to LOGIN");
                            customerService.login(scanner, admin, audit);
                        }
                    }
                }
                else {
                    break;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);

        CustomerService customerService = new CustomerService();

        var books = booksDatabase();
        var audiobooks = audiobooksDatabase();
        var ebooks = ebooksDatabase();
        var companies = companiesDatabase();
        var customers = customersDatabase();

        AdminService admin = new AdminService(books, audiobooks, ebooks, companies, customers);
        AuditService audit = new AuditService();

        initialDisplay(scanner, admin, customerService, audit);
    }
}
