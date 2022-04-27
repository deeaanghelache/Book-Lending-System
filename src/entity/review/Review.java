package entity.review;

public class Review {
    public int stars;
    public String description;

    public Review(int stars, String description) {
        this.stars = stars;
        this.description = description;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "\n\t\t\t - stars: " + stars +
                ", description='" + description;
    }
}
