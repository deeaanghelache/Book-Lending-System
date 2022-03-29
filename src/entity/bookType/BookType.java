package entity.bookType;

import entity.category.Category;
import entity.category.Subcategory;

import java.util.Comparator;

public abstract class BookType{
    protected int id;
    protected String name, author, description, availability;
    protected Category category;
    protected Subcategory subcategory;

    public BookType(int id, String name, String author, String description, Category category, Subcategory subcategory, String availability) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.description = description;
        this.category = category;
        this.subcategory = subcategory;
        this.availability = availability;
    }

//    public BookType() {}

    abstract public void setAvailability(String availability);

//    @Override
//    public int compare(Book book1, Book book2){
//        return book1.getName().compareTo(book2.getName());
//    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Subcategory getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(Subcategory subcategory) {
        this.subcategory = subcategory;
    }

    public String getAvailability() {
        return availability;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
