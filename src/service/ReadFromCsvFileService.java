package service;

import dao.repository.*;
import entity.bookType.Audiobook;
import entity.bookType.Book;
import entity.bookType.BookType;
import entity.bookType.EBook;
import entity.category.Category;
import entity.category.Subcategory;
import entity.company.Company;
import entity.loan.Loan;
import entity.user.Customer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class ReadFromCsvFileService {
    public static ReadFromCsvFileService reader;
    private static final CustomerRepository customerRepository = CustomerRepository.getCustomerRepository();
    private static final CompanyRepository companyRepository = CompanyRepository.getCompanyRepository();
    private static final AudiobookRepository audiobookRepository = AudiobookRepository.getAudiobookRepository();
    private static final BookRepository bookRepository = BookRepository.getBookRepository();
    private static final EbookRepository ebookRepository = EbookRepository.getEbookRepository();

    public static ReadFromCsvFileService getInstance(){
        if (reader == null){
            reader = new ReadFromCsvFileService();
        }
        return reader;
    }

    private ReadFromCsvFileService() {}

    public Category getCategoryFromString(String categoryString) {
        Category categoryOfTheBook = Category.NONFICTION;
        if (categoryString.toUpperCase().equals("FICTION")) {
            categoryOfTheBook = Category.FICTION;
        }
        return categoryOfTheBook;
    }

    public Subcategory getSubcategoryFromString(String subcategoryString){
        Subcategory subcategoryOfTheBook = Subcategory.HISTORY;

        switch (subcategoryString.toUpperCase()) {
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
        }
        return subcategoryOfTheBook;
    }

    public List<Customer> readCustomersFromCsv(){
        List<Customer> customers = new ArrayList<>();

        try {
            FileReader fileReader = new FileReader("src/csvFiles/Customers.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String currentLine = bufferedReader.readLine(); // First Name,Last Name,Email,Password,CompanyId,Address
            currentLine = bufferedReader.readLine();

            while (currentLine != null){
                var splitLine = currentLine.split(",");

                Set<Loan> emptyLoans = new HashSet<>();

                String firstName = splitLine[0];
                String lastName = splitLine[1];
                String email = splitLine[2];
                String password = splitLine[3];
                int companyId = Integer.parseInt(splitLine[4]);
                String address = splitLine[5];

                Customer currentCustomer = new Customer(firstName, lastName, email, password, companyId, emptyLoans, address);

                customers.add(currentCustomer);
//                customerRepository.insertCustomer(firstName, lastName, email, password, companyId, address);  // am inserat datele din csv in baza de date, se executa o singura data linia asta!

                currentLine = bufferedReader.readLine();
            }
        }
        catch (Exception exception) {
            System.out.println("Exception: " + exception.getMessage());
        }

        return customers;
    }

    public List<Company> readCompaniesFromCsv(){
        List<Company> companies = new ArrayList<>();

        try {
            FileReader fileReader = new FileReader("src/csvFiles/Companies.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String currentLine = bufferedReader.readLine(); // Nume Companie,Adresa,Telefon
            currentLine = bufferedReader.readLine();

            while (currentLine != null){
                var splitLine = currentLine.split(",");

               String companyName = splitLine[0];
               String address = splitLine[1];
               String telephoneNumber = splitLine[2];

               Company currentCompany = new Company(companyName, address, telephoneNumber);
               companies.add(currentCompany);
//               companyRepository.insertCompany(companyName, address, telephoneNumber);  // se executa o singura data

               currentLine = bufferedReader.readLine();
            }
        }
        catch (Exception exception) {
            System.out.println("Exception: " + exception.getMessage());
        }

        return companies;
    }

    public <T extends BookType> List<T> readItems(Class<T> itemClass){

        List<T> items = new ArrayList<>();

        if (itemClass.toString().equalsIgnoreCase("class entity.bookType.Audiobook")) {
            try {
                FileReader fileReader = new FileReader("src/csvFiles/Audiobooks.csv");
                BufferedReader bufferedReader = new BufferedReader(fileReader);

                String currentLine = bufferedReader.readLine(); // Name,Author,Description,Category,Subcategory,Availability,Duration
                currentLine = bufferedReader.readLine();

                while (currentLine != null) {
                    var splitLine = currentLine.split(",");

                    String name = splitLine[0];
                    String author = splitLine[1];
                    String description = splitLine[2];
                    Category category = getCategoryFromString(splitLine[3]);
                    Subcategory subcategory = getSubcategoryFromString(splitLine[4]);
                    String availability = splitLine[5];
                    int duration = Integer.parseInt(splitLine[6]);

                    var currentAudiobook = new Audiobook(name, author, description, category, subcategory, availability, duration);
                    items.add(itemClass.cast(currentAudiobook));
//                    audiobookRepository.insertAudioBook(name, author, description, category, subcategory, availability, duration);   // se executa o singura data

                    currentLine = bufferedReader.readLine();
                }
            } catch (Exception exception) {
                System.out.println("Exception: " + exception.getMessage());
            }
        } else if (itemClass.toString().equalsIgnoreCase("class entity.bookType.Book")) {
            try {
                FileReader fileReader = new FileReader("src/csvFiles/Books.csv");
                BufferedReader bufferedReader = new BufferedReader(fileReader);

                String currentLine = bufferedReader.readLine(); // Name,Author,Description,Category,Subcategory,Availability,Number of Books Available,Number of Pages,CoverType,Publishing House
                currentLine = bufferedReader.readLine();

                while (currentLine != null) {
                    var splitLine = currentLine.split(",");

                    String name = splitLine[0];
                    String author = splitLine[1];
                    String description = splitLine[2];
                    Category category = getCategoryFromString(splitLine[3]);
                    Subcategory subcategory = getSubcategoryFromString(splitLine[4]);
                    String availability = splitLine[5];
                    int numberOfBooksAvailable = Integer.parseInt(splitLine[6]);
                    int numberOfPages = Integer.parseInt(splitLine[7]);
                    String coverType = splitLine[8];
                    String publishingHouse = splitLine[9];

                    var currentBook = new Book(name, author, description, category, subcategory, availability, numberOfBooksAvailable, numberOfPages, coverType, publishingHouse);
                    items.add(itemClass.cast(currentBook));
//                    bookRepository.insertBook(name, author, description, category, subcategory, availability, numberOfBooksAvailable, numberOfPages, coverType, publishingHouse);   // se executa o singura data

                    currentLine = bufferedReader.readLine();
                }
            } catch (Exception exception) {
                System.out.println("Exception: " + exception.getMessage());
            }
        } else if (itemClass.toString().equalsIgnoreCase("class entity.bookType.EBook")) {
            try {
                FileReader fileReader = new FileReader("src/csvFiles/Ebooks.csv");
                BufferedReader bufferedReader = new BufferedReader(fileReader);

                String currentLine = bufferedReader.readLine(); // Name,Author,Description,Category,Subcategory,Availability,Number of Pages,Format
                currentLine = bufferedReader.readLine();

                while (currentLine != null) {
                    var splitLine = currentLine.split(",");

                    String name = splitLine[0];
                    String author = splitLine[1];
                    String description = splitLine[2];
                    Category category = getCategoryFromString(splitLine[3]);
                    Subcategory subcategory = getSubcategoryFromString(splitLine[4]);
                    String availability = splitLine[5];

                    int numberOfPages = Integer.parseInt(splitLine[6]);
                    String format = splitLine[7];

                    var currentEbook = new EBook(name, author, description, category, subcategory, availability, numberOfPages, format);
                    items.add(itemClass.cast(currentEbook));
//                    ebookRepository.insertEBook(name, author, description, category, subcategory, availability, numberOfPages, format);   // se executa o singura data

                    currentLine = bufferedReader.readLine();
                }
            } catch (Exception exception) {
                System.out.println("Exception: " + exception.getMessage());
            }
        }

        return items;
    }

    /*
    public static void main(String[] args) {
        var c = readCustomersFromCsv();
        System.out.println(c);

        var c = readCompaniesFromCsv();
        System.out.println(c);

        var a = readItems(Audiobook.class);
        a.stream().map(BookType::getName).forEach(System.out::println);

        System.out.println("-----------------------------------------------");

        var b = readItems(Book.class);
        b.stream().map(BookType::getName).forEach(System.out::println);

        System.out.println("-----------------------------------------------");

        var c = readItems(EBook.class);
        c.stream().map(BookType::getName).forEach(System.out::println);
    }
     */
}
