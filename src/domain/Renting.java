package src.domain;
import java.io.Serializable;

public class Renting implements Serializable {

    private static final long serialVersionUID = 1L;

    // Atributes
    private StoreMovie storeMovie;
    private Movie movie;
    private int idRenting;
    private ModelRent rent;
    private Customer customer;
    private int rentalDays;
    private boolean state;
    private float totalCost;
    private boolean returnLate;

    // Constructor
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
                " años y la pelicula requiere mínimo " + movie.getPegi()+" años."
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

    // Getters and Setters
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
        if(idAlquiler<0){
            throw new IllegalArgumentException("El id no puede ser negativo");
        }
        idRenting = idAlquiler;
    }
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public boolean isState() {
        return state;
    }
    public void setState(boolean state) {
        this.state = state;
    }
    public void setRentalDays(int days){
        if(days<=0){
            throw new IllegalArgumentException("Los días deben ser positivos");
        }
        rentalDays = days;
    }
    
    public int getRentalDays(){
        return rentalDays;
    }

    public float getTotalCost(){
        return totalCost;
    }
    public void setTotalCost(int totalCost){
        this.totalCost = totalCost;
    }

    public void setReturnLate(boolean returnLate){
        this.returnLate = returnLate;
    }

    public boolean getReturnLate(){
        return returnLate;
    }

    // Methods 

    // Method for comprobing if a customer is able to rent a movie based on it´s age and movie´s pegi
    public boolean parentalControl(Customer customer, Movie movie){
        if (customer.getAge() < movie.getPegi()){
            return true;
        }
        return false;
    }

    // Method for extra price
    public float calculateLateFee(int days){
        return this.rent.calculateLateFee(this, days);
    }

    // Method for adding extra price
    public void addLateFee(float lateFee){
        this.totalCost += lateFee;
    }
}
