package entity.bookType;

import entity.category.Category;
import entity.category.Subcategory;
import entity.review.Review;

import java.util.ArrayList;
import java.util.List;

public abstract class BookType{
    protected int id;
    protected String name;
    protected String author;
    protected String description;
    protected String availability;
    protected Category category;
    protected Subcategory subcategory;
    protected List<Review> reviews = new ArrayList<>();

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

    public void addReview(Review review){
        this.reviews.add(review);
    }

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

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}
