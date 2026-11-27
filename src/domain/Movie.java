package src.domain;
import java.io.Serializable;

public class Movie implements Serializable {

    private static final long serialVersionUID = 1L;

    // Atribute
    private String title;
    private int pegi;
    private int release;
    private boolean available;
    private String category;
    private StoreMovie movieStore; 
    private int id;
    private boolean active;

    // Constructor
    public Movie (String title, int pegi,int release, String category, StoreMovie movieStore){
        setAvailable(true);
        setTitle(title);
        setPegi(pegi);
        setRelease(release);
        setCategory(category);
        setMovieStore(movieStore);
        this.active = true;
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPegi() {
        return pegi;
    }

    public void setPegi(int pegi) {
        this.pegi = pegi;
    }
    
    public int getRelease() {
        return release;
    }

    public void setRelease(int release) {
        this.release = release;
    }
    

    public StoreMovie getMovieStore() {
        return movieStore;
    }

    public void setMovieStore(StoreMovie movieStore) {
        this.movieStore = movieStore;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public boolean isActive() {
        return active;
    }

    // Methods

    // Atribute for switching from active to inactive
    public void switchActive() {
        this.active = !(active);
    }

    @Override
    public String toString() {
        return "Movie [title=" + title + ", pegi=" + pegi + ", release=" + release + ", available=" + available
                + ", category=" + category + ", movieStore=" + movieStore + ", id=" + id+ ", getTitle()=" + getTitle() + "]";
    }
    
    
}
