package dao.repository;

import dao.configuration.DatabaseConfiguration;
import entity.category.Category;
import entity.category.Subcategory;

import java.sql.*;

public class EbookRepository {
    // CRUD
    // Name,Author,Description,Category,Subcategory,Availability,Number of Pages,Format

    private static EbookRepository ebookRepository;

    private EbookRepository() {}

    public static EbookRepository getEbookRepository(){
        if(ebookRepository == null){
            ebookRepository = new EbookRepository();
        }
        return ebookRepository;
    }

    public void createTable() {
        String createTableSql = "CREATE TABLE IF NOT EXISTS ebooks" +
                "(id int PRIMARY KEY AUTO_INCREMENT, name varchar(100), author varchar(100), " +
                "description varchar(500), category enum ('FICTION', 'NONFICTION'), " +
                "subcategory enum ('HISTORY', 'BIOGRAPHY', 'CLASSICS', 'FANTASY', 'HISTORICALFICTION', 'MEMOIR', 'POETRY', 'ROMANCE', 'SCIENCE', 'SELFHELP', 'TRAVEL', 'THRILLER', 'SCIENCEFICTION'), " +
                "availability varchar(50), numberOfPages int, format varchar(20) )";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            Statement stmt = connection.createStatement();
            stmt.execute(createTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // CREATE
    public void insertEBook(String name, String author, String description, Category category, Subcategory subcategory, String availability, int numberOfPages, String format) {
        String insertEbook = "INSERT INTO ebooks(name, author, description, category, subcategory" +
                ", availability, numberOfPages, format) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertEbook);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, author);
            preparedStatement.setString(3, description);
            preparedStatement.setString(4, category.name());
            preparedStatement.setString(5, subcategory.name());
            preparedStatement.setString(6, availability);
            preparedStatement.setInt(7, numberOfPages);
            preparedStatement.setString(8, format);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ
    public void selectAllEbook() {
        String selectBooks = "SELECT * FROM ebooks";

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
                System.out.println("\tNumber of Pages: " + resultSet.getInt(8));
                System.out.println("\tFormat: " + resultSet.getString(9));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // UPDATE
    public void updateFormatEbook(String format, int id) {
        String updateString = "UPDATE ebooks SET format=? WHERE id=?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateString);
            preparedStatement.setString(1, format);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public void deleteEBook(int id) {
        String deleteString = "DELETE FROM ebooks WHERE id=?";

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
