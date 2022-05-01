package service;

import entity.bookType.Audiobook;
import entity.bookType.Book;
import entity.bookType.EBook;
import entity.company.Company;
import entity.user.Customer;

import java.io.FileWriter;
import java.util.ArrayList;

public class WriteToCsvFileService {
    public static WriteToCsvFileService writer;

    public static WriteToCsvFileService getInstance(){
        if (writer == null){
            writer = new WriteToCsvFileService();
        }
        return writer;
    }

    private WriteToCsvFileService() {}

    public void writeToCustomerCsv(Customer currentCustomer){
        String fileName = "src/csvFiles/Customers.csv";
        try (FileWriter fileWriter = new FileWriter(fileName, true)){

            // First Name,Last Name,Email,Password,CompanyId,Address
            fileWriter.append("\n");
            fileWriter.append(currentCustomer.getFirstName());
            fileWriter.append(",");
            fileWriter.append(currentCustomer.getLastName());
            fileWriter.append(",");
            fileWriter.append(currentCustomer.getEmailAddress());
            fileWriter.append(",");
            fileWriter.append(currentCustomer.getPassword());
            fileWriter.append(",");
            fileWriter.append(String.valueOf(currentCustomer.getCompanyId()));
            fileWriter.append(",");
            fileWriter.append(currentCustomer.getAddress());

            fileWriter.flush();
        }
        catch (Exception exception) {
            System.out.printf("\n\t\tException: " + exception.getMessage() + "\n");
        }
    }

    public void writeToCompanyCsv(Company currentCompany){
        String fileName = "src/csvFiles/Companies.csv";
        try (FileWriter fileWriter = new FileWriter(fileName, true)){
            // Nume Companie,Adresa,Telefon

            fileWriter.append("\n");
            fileWriter.append(currentCompany.getName());
            fileWriter.append(",");
            fileWriter.append(currentCompany.getAddress());
            fileWriter.append(",");
            fileWriter.append(currentCompany.getTelephoneNumber());

            fileWriter.flush();
        }
        catch (Exception exception){
            System.out.printf("\n\t\tException: " + exception.getMessage() + "\n");
        }
    }

    public <T> void writeItemToCsv(T item, Class<T> itemClass){
        if (itemClass.toString().equalsIgnoreCase("class entity.bookType.Audiobook")){
            String fileName = "src/csvFiles/Audiobooks.csv";

            try (FileWriter fileWriter = new FileWriter(fileName, true)){
//                item = itemClass.cast(item);
                Audiobook audiobookItem = (Audiobook) item;

                // Name,Author,Description,Category,Subcategory,Availability,Duration
                fileWriter.append("\n");
                fileWriter.append(audiobookItem.getName());
                fileWriter.append(",");
                fileWriter.append(audiobookItem.getAuthor());
                fileWriter.append(",");
                fileWriter.append(audiobookItem.getDescription());
                fileWriter.append(",");
                fileWriter.append(audiobookItem.getCategory().toString());
                fileWriter.append(",");
                fileWriter.append(audiobookItem.getSubcategory().toString());
                fileWriter.append(",");
                fileWriter.append(audiobookItem.getAvailability());
                fileWriter.append(",");
                fileWriter.append(String.valueOf(audiobookItem.getDuration()));

                fileWriter.flush();
            }
            catch (Exception exception) {
                System.out.printf("\n\t\tException: " + exception.getMessage() + "\n");
            }

        } else if (itemClass.toString().equalsIgnoreCase("class entity.bookType.Book")) {
            String fileName = "src/csvFiles/Books.csv";

            try (FileWriter fileWriter = new FileWriter(fileName, true)){
                Book bookItem = (Book) item;

                // Name,Author,Description,Category,Subcategory,Availability,Number of Books Available,Number of Pages,CoverType,Publishing House
                fileWriter.append("\n");
                fileWriter.append(bookItem.getName());
                fileWriter.append(",");
                fileWriter.append(bookItem.getAuthor());
                fileWriter.append(",");
                fileWriter.append(bookItem.getDescription());
                fileWriter.append(",");
                fileWriter.append(bookItem.getCategory().toString());
                fileWriter.append(",");
                fileWriter.append(bookItem.getSubcategory().toString());
                fileWriter.append(",");
                fileWriter.append(bookItem.getAvailability());
                fileWriter.append(",");
                fileWriter.append(String.valueOf(bookItem.getNumberOfBooksAvailable()));
                fileWriter.append(",");
                fileWriter.append(String.valueOf(bookItem.getNumberOfPages()));
                fileWriter.append(",");
                fileWriter.append(bookItem.getCoverType());
                fileWriter.append(",");
                fileWriter.append(bookItem.getPublishingHouse());
                fileWriter.append(",");

                fileWriter.flush();
            }
            catch (Exception exception) {
                System.out.printf("\n\t\tException: " + exception.getMessage() + "\n");
            }

        } else if (itemClass.toString().equalsIgnoreCase("class entity.bookType.EBook")) {
            String fileName = "src/csvFiles/Ebooks.csv";

            try (FileWriter fileWriter = new FileWriter(fileName, true)){
                EBook ebookItem = (EBook) item;

                // Name,Author,Description,Category,Subcategory,Availability,Number of Pages,Format
                fileWriter.append("\n");
                fileWriter.append(ebookItem.getName());
                fileWriter.append(",");
                fileWriter.append(ebookItem.getAuthor());
                fileWriter.append(",");
                fileWriter.append(ebookItem.getDescription());
                fileWriter.append(",");
                fileWriter.append(ebookItem.getCategory().toString());
                fileWriter.append(",");
                fileWriter.append(ebookItem.getSubcategory().toString());
                fileWriter.append(",");
                fileWriter.append(ebookItem.getAvailability());
                fileWriter.append(",");
                fileWriter.append(String.valueOf(ebookItem.getNumberOfPages()));
                fileWriter.append(",");
                fileWriter.append(ebookItem.getFormat());

                fileWriter.flush();
            }
            catch (Exception exception) {
                System.out.printf("\n\t\tException: " + exception.getMessage() + "\n");
            }
        }
    }

}
