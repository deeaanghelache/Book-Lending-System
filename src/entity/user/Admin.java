package entity.user;

import entity.bookType.Audiobook;
import entity.bookType.Book;
import entity.bookType.EBook;
import entity.category.Category;
import entity.category.Subcategory;
import entity.company.Company;
import entity.loan.Loan;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

public class Admin extends User{
    private static Admin admin;

    private Admin() {
        super(1, "Cristina", "Ionita", "admin", "adminpa55");
    }

    public static Admin getAdminInstance(){
        if(admin == null){
            admin = new Admin();
        }
        return admin;
    }

    public String getUserType(){
        return "Admin";
    }

    @Override
    public String toString() {
        return "The admin is " + firstName + " " + lastName + "\n Email address of the admin: " + emailAddress;
    }

    @Override
    public String getPassword(){
        return "adminpa55";
    }

    @Override
    public String getEmailAddress() {
        return "admin";
    }
}
