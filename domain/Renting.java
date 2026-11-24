public class Renting {
    private StoreMovie storeMovie;
    private Movie movie;
    private int idAlquiler;
    private ModelRent rent;
    private Customer cliente;
    private int startDate;
    private int endDate;
    private boolean state;

    public Renting(StoreMovie storeMovie, Movie movie, int idAlquiler, ModelRent rent, Customer cliente, int startDate,
    int endDate, boolean state) {
        this.storeMovie = storeMovie;
        this.movie = movie;
        this.idAlquiler = idAlquiler;
        this.rent = rent;
        this.cliente = cliente;
        this.startDate = startDate;
        this.endDate = endDate;
        this.state = state;
    }
    public StoreMovie getStoreMovie() {
        return storeMovie;
    }
    public void setStoreMovie(StoreMovie storeMovie) {
        this.storeMovie = storeMovie;
    }
    public Movie getMovie() {
        return movie;
    }
    public void setMovie(Movie movie) {
        this.movie = movie;
    }
    public int getIdAlquiler() {
        return idAlquiler;
    }
    public void setIdAlquiler(int idAlquiler) {
        this.idAlquiler = idAlquiler;
    }
    public ModelRent getRent() {
        return rent;
    }
    public void setRent(ModelRent rent) {
        this.rent = rent;
    }
    public Customer getCliente() {
        return cliente;
    }
    public void setCliente(Customer cliente) {
        this.cliente = cliente;
    }
    public int getStartDate() {
        return startDate;
    }
    public void setStartDate(int startDate) {
        this.startDate = startDate;
    }
    public int getEndDate() {
        return endDate;
    }
    public void setEndDate(int endDate) {
        this.endDate = endDate;
    }
    public boolean isState() {
        return state;
    }
    public void setState(boolean state) {
        this.state = state;
    }

    public static boolean parentalControl(Customer customer, Movie movie){
        if (customer.getAge() >= movie.getPegi()){
            return true;
        }
        return false;
    }
}
