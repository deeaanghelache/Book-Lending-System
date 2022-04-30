package entity.user;

import entity.loan.Loan;
import service.AuditService;

import java.util.Scanner;
import java.util.Set;

public class Customer extends User{
    private static int customerNumber = 2; // Admin has id = 1
    private Set<Loan> loans;
    private String address;
    protected int companyId;

    public Customer(String firstName, String lastName, String emailAddress, String password, int companyId, Set<Loan> loans, String address) {
        super(customerNumber, firstName, lastName, emailAddress, password);
        customerNumber++;
        this.loans = loans;
        this.address = address;
        this.companyId = companyId;
    }

    public Set<Loan> getLoans() {
        return loans;
    }

//    public void setLoan(Loan loan) {
//        this.loan = loan;
//    }

    public String getUserType(){
        return "Customer";
    }

    public void addLoan(Loan currentLoan){
        this.loans.add(currentLoan);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    @Override
    public String toString() {
        StringBuilder customer = new StringBuilder("\n\t\t --------- " + emailAddress + "'s PROFILE ---------" +
                "\n\t * User id: " + userId +
                "\n\t * First Name: " + firstName +
                "\n\t * Last Name: " + lastName +
                "\n\t * Email Address: " + emailAddress +
                "\n\t * Password: " + password +
                "\n\t * Address: " + address +
                "\n\t * Company id: " + companyId);

        if (loans.size() != 0) {
            customer.append("\n\t * Loans: ");
            for (Loan loan : loans){
                customer.append(loan);
            }
        }
        else {
            customer.append("\nThis customer has no loans!");
        }
        return customer.toString();
    }


    public String changeUsername(Scanner scanner, AuditService audit){
        scanner.nextLine();
        System.out.println("Enter the new username: ");
        String newUsername = scanner.nextLine();

        this.setEmailAddress(newUsername);
        return newUsername;
    }

    public void changePassword(Scanner scanner, AuditService audit){
        scanner.nextLine();
        System.out.println("Enter the new password: ");
        String newPassword = scanner.nextLine();
        System.out.println("Enter the new password again: ");
        String duplicate = scanner.nextLine();

        if (newPassword.equals(duplicate))
        {
            this.setPassword(newPassword);
            System.out.println("Your password has been successfully changed! ");
        }
        else {
            System.out.println("The passwords aren't identical!");
        }
    }
}

