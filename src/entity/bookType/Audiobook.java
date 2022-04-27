package entity.bookType;

import entity.category.Category;
import entity.category.Subcategory;

public class Audiobook extends BookType{
    private static int audiobookNumber = 1;
    private int duration;

    public Audiobook(String name, String author, String description, Category category, Subcategory subcategory, String availability, int duration) {
        super(audiobookNumber, name, author, description, category, subcategory, availability);
        audiobookNumber++;
        this.duration = duration;
    }

    @Override
    public void setAvailability(String availability) {
        this.availability = "available"; // Audiobooks are always available
    }

    @Override
    public String toString() {
        return "\t* Audiobook: " +
                "\n\t\t -> id = " + id +
                "\n\t\t -> name = " + name +
                "\n\t\t -> author = " + author +
                "\n\t\t -> description = " + description +
                "\n\t\t -> availability = " + availability +
                "\n\t\t -> category = " + category +
                "\n\t\t -> subcategory = " + subcategory +
                "\n\t\t -> duration = " + duration +
                "\n\t\t -> reviews: " + reviews;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
