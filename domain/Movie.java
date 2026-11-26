public class Movie {
    private String title;
    private int pegi;
    private int release;
    private boolean available;
    private String category;
    private StoreMovie movieStore; 
    private int id;
    private int copyNumber;

    public Movie (String title, int pegi,int release, String category, StoreMovie movieStore, int id, int copyNumber){
        setAvailable(true);
        setTitle(title);
        setPegi(pegi);
        setRelease(release);
        setCategory(category);
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

    public int getCopyNumber() {
        return copyNumber;
    }

    public void setCopyNumber(int copyNumber) {
        this.copyNumber = copyNumber;
    }
    
}
