package entity.bookType;

import entity.category.Category;
import entity.category.Subcategory;

public class Book extends BookType{
    private static int booksNumber = 1;
    private int numberOfBooksAvailable;
    private int numberOfPages;
    private String coverType;
    private String publishingHouse;


    public Book(String name, String author, String description, Category category, Subcategory subcategory, String availability, int numberOfBooksAvailable, int numberOfPages, String coverType, String publishingHouse) {
        super(booksNumber, name, author, description, category, subcategory, availability);
        booksNumber++;
        this.numberOfBooksAvailable = numberOfBooksAvailable;
        this.numberOfPages = numberOfPages;
        this.coverType = coverType;
        this.publishingHouse = publishingHouse;
    }

//    public Book() {
//    }

    @Override
    public void setAvailability(String availability){
        this.availability = availability; // physical books can be available or not
    }

    @Override
    public String toString() {
        return "\t* Book: " +
                "\n\t\t -> id = " + id +
                "\n\t\t -> name = " + name +
                "\n\t\t -> author = " + author +
                "\n\t\t -> description = " + description +
                "\n\t\t -> availability = " + availability +
                "\n\t\t -> category = " + category +
                "\n\t\t -> subcategory = " + subcategory +
                "\n\t\t -> numberOfBooksAvailable = " + numberOfBooksAvailable +
                "\n\t\t -> numberOfPages = " + numberOfPages +
                "\n\t\t -> coverType = " + coverType +
                "\n\t\t -> publishingHouse = " + publishingHouse +
                "\n\t\t -> reviews: " + reviews + "\n";
    }

    public int getNumberOfBooksAvailable() {
        return numberOfBooksAvailable;
    }

    public void setNumberOfBooksAvailable(int numberOfBooksAvailable) {
        this.numberOfBooksAvailable = numberOfBooksAvailable;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public String getCoverType() {
        return coverType;
    }

    public void setCoverType(String coverType) {
        this.coverType = coverType;
    }

    public String getPublishingHouse() {
        return publishingHouse;
    }

    public void setPublishingHouse(String publishingHouse) {
        this.publishingHouse = publishingHouse;
    }
}
