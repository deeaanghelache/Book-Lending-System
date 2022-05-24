package dao.repository;

import dao.configuration.DatabaseConfiguration;
import entity.user.Admin;

import java.sql.*;

public class CompanyRepository {
    // CRUD
    // Nume Companie,Adresa,Telefon

    private static CompanyRepository companyRepository;

    private CompanyRepository() {}

    public static CompanyRepository getCompanyRepository(){
        if(companyRepository == null){
            companyRepository = new CompanyRepository();
        }
        return companyRepository;
    }

    public void createTable() {
        String createTableSql = "CREATE TABLE IF NOT EXISTS companies" +
                "(id int PRIMARY KEY AUTO_INCREMENT, companyName varchar(100), address varchar(100), telephoneNumber varchar(20))";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            Statement statement = connection.createStatement();
            statement.execute(createTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // CREATE
    public void insertCompany(String name, String companyAddress, String telephoneNumber) {
        String insertCompanyString = "INSERT INTO companies(companyName, address, telephoneNumber) VALUES(?, ?, ?)";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertCompanyString);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, companyAddress);
            preparedStatement.setString(3, telephoneNumber);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ
    public void selectAllCompanies() {
        String selectCompanies = "SELECT * FROM companies";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectCompanies);
            while (resultSet.next()) {
                System.out.println("Company Id: " + resultSet.getInt(1));
                System.out.println("\tName: " + resultSet.getString(2));
                System.out.println("\tAddress: " + resultSet.getString(3));
                System.out.println("\tTelephone Number: " + resultSet.getString(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int selectCompanyByName(String name) {
        String selectCompany = "SELECT id FROM companies WHERE companyName=?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectCompany);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                return resultSet.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    // UPDATE
    public void updateTelephoneNumber(String telephoneNumber, int id) {
        String updateString = "UPDATE companies SET telephoneNumber=? WHERE id=?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateString);
            preparedStatement.setString(1, telephoneNumber);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public void deleteCompany(int id) {
        String deleteString = "DELETE FROM companies WHERE id=?";

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
