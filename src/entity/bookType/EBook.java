package entity.bookType;

import entity.category.Category;
import entity.category.Subcategory;

public class EBook extends BookType {
    private static int ebooksNumber = 1;
    private int numberOfPages;
    private String format; // pdf, kindle

    public EBook(String name, String author, String description, Category category, Subcategory subcategory, String availability, int numberOfPages, String format) {
        super(ebooksNumber, name, author, description, category, subcategory, availability);
        ebooksNumber++;
        this.numberOfPages = numberOfPages;
        this.format = format;
    }


    @Override
    public void setAvailability(String availability) {
        this.availability = "available"; // EBooks are always available
    }

    @Override
    public String toString() {
        return "\t* EBook: " +
                "\n\t\t -> id = " + id +
                "\n\t\t -> name = " + name +
                "\n\t\t -> author = " + author +
                "\n\t\t -> description = " + description +
                "\n\t\t -> availability = " + availability +
                "\n\t\t -> category = " + category +
                "\n\t\t -> subcategory = " + subcategory +
                "\n\t\t -> numberOfPages = " + numberOfPages +
                "\n\t\t -> format = " + format +
                "\n\t\t -> reviews: " + reviews;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
