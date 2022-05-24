package dao.repository;

import dao.configuration.DatabaseConfiguration;

import java.sql.*;

public class CustomerRepository {
    // CRUD
    // First Name,Last Name,Email,Password,CompanyId,Address

    private static CustomerRepository customerRepository;

    private CustomerRepository() {}

    public static CustomerRepository getCustomerRepository(){
        if(customerRepository == null){
            customerRepository = new CustomerRepository();
        }
        return customerRepository;
    }

    public void createTable() {
        String createTableSql = "CREATE TABLE IF NOT EXISTS customers" +
                "(id int PRIMARY KEY AUTO_INCREMENT, firstName varchar(100), lastName varchar(100), emailAddress varchar(100), password varchar(100), companyId int, address varchar(100))";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            Statement stmt = connection.createStatement();
            stmt.execute(createTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // CREATE
    public void insertCustomer(String firstName, String lastName, String email, String password, int companyId, String address) {
        String insertCustomerString = "INSERT INTO customers(firstName, lastName, emailAddress, password, companyId, address) VALUES(?, ?, ?, ?, ?, ?)";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertCustomerString);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, password);
            preparedStatement.setInt(5, companyId);
            preparedStatement.setString(6, address);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ
    public void selectAllCustomers() {
        String selectCustomers = "SELECT * FROM customers";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectCustomers);
            while (resultSet.next()) {
                System.out.println("Customer Id: " + resultSet.getInt(1));
                System.out.println("\tFirst Name: " + resultSet.getString(2));
                System.out.println("\tLast Name: " + resultSet.getString(3));
                System.out.println("\tEmail: " + resultSet.getString(4));
                System.out.println("\tPassword: " + resultSet.getString(5));
                System.out.println("\tCompany Id: " + resultSet.getInt(6));
                System.out.println("\tAddress: " + resultSet.getString(7));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // UPDATE
    public void updatePassword(String newPassword, String username) {
        String updateString = "UPDATE customers SET password=? WHERE emailAddress=?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateString);
            preparedStatement.setString(1, newPassword);
            preparedStatement.setString(2, username);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUsername(String newUsername, String username) {
        String updateString = "UPDATE customers SET emailAddress=? WHERE emailAddress=?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateString);
            preparedStatement.setString(1, newUsername);
            preparedStatement.setString(2, username);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public void deleteCustomer(int id) {
        String deleteString = "DELETE FROM customers WHERE id=?";

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


