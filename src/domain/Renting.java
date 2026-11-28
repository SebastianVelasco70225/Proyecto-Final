package src.domain;
import java.io.Serializable;
import java.time.LocalDate;

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
    private LocalDate startDate;
    private LocalDate  expectedEndDate;
    private LocalDate actualReturnDate;

    // Constructor
    public Renting(StoreMovie storeMovie, Movie movie, int idAlquiler, ModelRent modelRent,Customer customer, int days, LocalDate startDateRenting) {
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
        this.startDate = startDateRenting;
        this.expectedEndDate = startDate.plusDays(days);
        this.actualReturnDate = null;


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
    public void setTotalCost(float totalCost){
        this.totalCost = totalCost;
    }

    public void setReturnLate(boolean returnLate){
        this.returnLate = returnLate;
    }

    public boolean getReturnLate(){
        return returnLate;
    }
    

    // Methods 

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getExpectedEndDate() {
        return expectedEndDate;
    }

    public void setExpectedEndDate(LocalDate expectedEndDate) {
        this.expectedEndDate = expectedEndDate;
    }

    public LocalDate getActualReturnDate() {
        return actualReturnDate;
    }

    public void setActualReturnDate(LocalDate actualReturnDate) {
        this.actualReturnDate = actualReturnDate;
    }

    // Method for comprobing if a customer is able to rent a movie based on it´s age and movie´s pegi
    public boolean parentalControl(Customer customer, Movie movie){
        if (customer.getAge() < movie.getPegi()){
            return true;
        }
        return false;
    }

    // Method for extra price
    public float calculateLateFee(int days){
        return this.rent.calculateLateFee(days);
    }

    // Method for adding extra price
    public void addLateFee(float lateFee){
        this.totalCost += lateFee;
    }
}
