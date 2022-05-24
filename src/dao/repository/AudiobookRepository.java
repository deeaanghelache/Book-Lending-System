package dao.repository;

import dao.configuration.DatabaseConfiguration;
import entity.category.Category;
import entity.category.Subcategory;

import java.sql.*;

public class AudiobookRepository {
    // CRUD
    // Name,Author,Description,Category,Subcategory,Availability,Duration

    private static AudiobookRepository audiobookRepository;

    private AudiobookRepository() {}

    public static AudiobookRepository getAudiobookRepository(){
        if(audiobookRepository == null){
            audiobookRepository = new AudiobookRepository();
        }
        return audiobookRepository;
    }

    public void createTable() {
        String createTableSql = "CREATE TABLE IF NOT EXISTS audiobooks" +
                "(id int PRIMARY KEY AUTO_INCREMENT, name varchar(100), author varchar(100), " +
                "description varchar(500), category enum ('FICTION', 'NONFICTION'), " +
                "subcategory enum ('HISTORY', 'BIOGRAPHY', 'CLASSICS', 'FANTASY', 'HISTORICALFICTION', 'MEMOIR', 'POETRY', 'ROMANCE', 'SCIENCE', 'SELFHELP', 'TRAVEL', 'THRILLER', 'SCIENCEFICTION'), " +
                "availability varchar(50), duration int)";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            Statement stmt = connection.createStatement();
            stmt.execute(createTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // CREATE
    public void insertAudioBook(String name, String author, String description, Category category, Subcategory subcategory, String availability, int duration) {
        String insertAudiobook = "INSERT INTO audiobooks(name, author, description, category, subcategory" +
                ", availability, duration) VALUES(?, ?, ?, ?, ?, ?, ?)";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertAudiobook);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, author);
            preparedStatement.setString(3, description);
            preparedStatement.setString(4, category.name());
            preparedStatement.setString(5, subcategory.name());
            preparedStatement.setString(6, availability);
            preparedStatement.setInt(7, duration);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ
    public void selectAllAudioBooks() {
        String selectBooks = "SELECT * FROM audiobooks";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectBooks);
            while (resultSet.next()) {
                System.out.println("Audiobook Id: " + resultSet.getInt(1));
                System.out.println("\tName: " + resultSet.getString(2));
                System.out.println("\tAuthor: " + resultSet.getString(3));
                System.out.println("\tDescription: " + resultSet.getString(4));
                System.out.println("\tCategory: " + resultSet.getString(5));
                System.out.println("\tSubcategory: " + resultSet.getString(6));
                System.out.println("\tAvailability: " + resultSet.getString(7));
                System.out.println("\tDuration: " + resultSet.getInt(8));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // UPDATE
    public void updateDuration(int duration, int id) {
        String updateString = "UPDATE audiobooks SET duration=? WHERE id=?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateString);
            preparedStatement.setInt(1, duration);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public void deleteAudiobook(int id) {
        String deleteString = "DELETE FROM audiobooks WHERE id=?";

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
