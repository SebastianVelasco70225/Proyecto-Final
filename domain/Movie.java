public class Movie {
    private String title;
    private int pegi;
    private boolean available;
    private String category;
    private float rentPrice;
    private MovieStore movieStore; 
    private int id;
    private int copyNumber;

    public Movie (String title, int pegi, String category, float rentPrice, MovieStore movieStore, int id, int copyNumber){
        setAvailable(true);
        setTitle(title);
        setPegi(pegi);
        setCategory(category);
        setRentPrice(rentPrice);
        setMovieStore(movieStore);
        setId(id);
        setCopyNumber(copyNumber);
    }

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

    public MovieStore getMovieStore() {
        return movieStore;
    }

    public void setMovieStore(MovieStore movieStore) {
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

    public float getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(float rentPrice) {
        this.rentPrice = rentPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCopyNumber() {
        return copyNumber;
    }

    public void setCopyNumber(int copyNumber) {
        this.copyNumber = copyNumber;
    }
    
}
