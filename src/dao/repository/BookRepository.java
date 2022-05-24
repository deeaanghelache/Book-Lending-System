package dao.repository;

import dao.configuration.DatabaseConfiguration;
import entity.category.Category;
import entity.category.Subcategory;

import java.sql.*;

public class BookRepository {
    // CRUD
    // Name,Author,Description,Category,Subcategory,Availability,Number of Books Available,Number of Pages,CoverType,Publishing House
    private static BookRepository bookRepository;

    private BookRepository() {}

    public static BookRepository getBookRepository(){
        if(bookRepository == null){
            bookRepository = new BookRepository();
        }
        return bookRepository;
    }
    public void createTable() {
        String createTableSql = "CREATE TABLE IF NOT EXISTS books" +
                "(id int PRIMARY KEY AUTO_INCREMENT, name varchar(100), author varchar(100), " +
                "description varchar(500), category enum ('FICTION', 'NONFICTION'), " +
                "subcategory enum ('HISTORY', 'BIOGRAPHY', 'CLASSICS', 'FANTASY', 'HISTORICALFICTION', 'MEMOIR', 'POETRY', 'ROMANCE', 'SCIENCE', 'SELFHELP', 'TRAVEL', 'THRILLER', 'SCIENCEFICTION'), " +
                "availability varchar(50), numberOfBooksAvailable int, numberOfPages int, coverType varchar(20), publishingHouse varchar(30) )";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            Statement stmt = connection.createStatement();
            stmt.execute(createTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // CREATE
    public void insertBook(String name, String author, String description, Category category, Subcategory subcategory, String availability, int numberOfBooksAvailable, int numberOfPages, String coverType, String publishingHouse) {
        String insertBook = "INSERT INTO books(name, author, description, category, subcategory" +
                ", availability, numberOfBooksAvailable, numberOfPages, coverType, publishingHouse) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertBook);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, author);
            preparedStatement.setString(3, description);
            preparedStatement.setString(4, category.name());
            preparedStatement.setString(5, subcategory.name());
            preparedStatement.setString(6, availability);
            preparedStatement.setInt(7, numberOfBooksAvailable);
            preparedStatement.setInt(8, numberOfPages);
            preparedStatement.setString(9, coverType);
            preparedStatement.setString(10, publishingHouse);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ
    public void selectAllBooks() {
        String selectBooks = "SELECT * FROM books";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectBooks);
            while (resultSet.next()) {
                System.out.println("Book Id: " + resultSet.getInt(1));
                System.out.println("\tName: " + resultSet.getString(2));
                System.out.println("\tAuthor: " + resultSet.getString(3));
                System.out.println("\tDescription: " + resultSet.getString(4));
                System.out.println("\tCategory: " + resultSet.getString(5));
                System.out.println("\tSubcategory: " + resultSet.getString(6));
                System.out.println("\tAvailability: " + resultSet.getString(7));
                System.out.println("\tNumber of Books Available: " + resultSet.getInt(8));
                System.out.println("\tNumber of Pages: " + resultSet.getInt(9));
                System.out.println("\tCover Type: " + resultSet.getString(10));
                System.out.println("\tPublishing House: " + resultSet.getString(11));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // UPDATE
    public void updateNumberOfBooksAvailable(int numberOfBooksAvailable, int id) {
        String updateString = "UPDATE books SET numberOfBooksAvailable=? WHERE id=?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateString);
            preparedStatement.setInt(1, numberOfBooksAvailable);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public void deleteBook(int id) {
        String deleteString = "DELETE FROM books WHERE id=?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteString);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
