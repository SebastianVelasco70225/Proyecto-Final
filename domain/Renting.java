public class Renting {
    private StoreMovie storeMovie;
    private Movie movie;
    private int idRenting;
    private ModelRent rent;
    private Customer customer;
    private int rentalDays;
    private boolean state;
    private float totalCost;
    private boolean returnLate;

    public Renting(StoreMovie storeMovie, Movie movie, int idAlquiler, ModelRent modelRent,Customer customer, int days) {
        if (movie == null || customer == null ){
            throw new IllegalArgumentException("Error: el cliente y la pelicula son obligatorios");
        }

        if(days <= 0 ){
            throw new IllegalArgumentException("Error: el numero de dias debe de ser mayor de 0 ");
        }

        if(parentalControl(customer, movie)){
            throw new IllegalArgumentException(
                "Control Parental: " + customer.getName() + " tiene: " + customer.getAge() +
                " años y la pelicula requiere mínimo " + movie.getPegi()
            );
        }

        setStoreMovie(storeMovie);
        setMovie(movie);
        setIdRenting(idAlquiler);
        setCustomer(customer);
        this.rent = modelRent;
        setRentalDays(days);
        this.state = true;
        this.returnLate = false;

        this.totalCost =this.rent.calculateTotalPrice(movie, days);
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
    public int getIdRenting() {
        return idRenting;
    }
    public void setIdRenting(int idAlquiler) {
        this.idRenting = idAlquiler;
    }
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer cliente) {
        this.customer = cliente;
    }
    public boolean isState() {
        return state;
    }
    public void setState(boolean state) {
        this.state = state;
    }
    public void setRentalDays(int days){
        this.rentalDays = days;
    }
    public int getRentalDays(){
        return this.rentalDays;
    }

    public float getFinalPrice(){
        return this.totalCost;
    }

    public float getTotalCost(){
        return this.totalCost;
    }
    public void setTotalCost(int totalCost){
        this.totalCost = totalCost;
    }

    public void setReturnLate(boolean returnLate){
        this.returnLate = returnLate;
    }

    public boolean getReturnLate(){
        return this.returnLate;
    }

    public boolean parentalControl(Customer customer, Movie movie){
        if (customer.getAge() < movie.getPegi()){
            return true;
        }
        return false;
    }

    public float calculatelafee(int days){
        return this.rent.calculateLateFee(this, days);
    }

    public void addLateFee(float lateFee){
        this.totalCost += lateFee;
    }
}
