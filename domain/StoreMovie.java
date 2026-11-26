import java.util.ArrayList;
import java.util.List;

public class StoreMovie {

    private String name;
    private String nit;
    private String address;
    private float basePrice;
    private List<Movie> movies = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();
    private List<Employee> employees = new ArrayList<>();
    private List<Renting> rentings = new ArrayList<>();
    private List<Report> reports = new ArrayList<>();
    private List<Voucher> voucher = new ArrayList<>();


    public StoreMovie (String name, String nit, String address, float basePrice){
        setName(name);
        setNit(nit);
        setAddress(address);
    }

    public void setBaseprice(float basePrice){
        if(basePrice <= 0 ){
            throw new IllegalArgumentException("El precio base debe de ser mayor a 0");
        }
        this.basePrice = basePrice;
    }

    public float getBasePrice(){
        return this.basePrice;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getNit() {
        return nit;
    }


    public void setNit(String nit) {
        this.nit = nit;
    }


    public String getAddress() {
        return address;
    }


    public void setAddress(String address) {
        this.address = address;
    }


    public List<Movie> getMovies() {
        moviesException();
        return movies;
    }


    
    public List<Customer> getCustomers() {
        customerException();
        return customers;
    }
    
    
    public void addCustomers(Customer customer) {
        customers.add(customer);
    }
    
    
    public List<Employee> getEmployees() {
        employeeException();
        return employees;
    }
    
    
    public void addEmployees(Employee employee) {
        employees.add(employee);
    }
    
    
    public List<Renting> getRentings() {
        return rentings;
    }
    
    
    public void setRentings(List<Renting> rentings) {
        this.rentings = rentings;
    }
    
    
    public List<Report> getReports() {
        return reports;
    }
    
    
    public void setReports(List<Report> reports) {
        this.reports = reports;
    }
    
    
    public List<Voucher> getVoucher() {
        return voucher;
    }
    
    
    public void setVoucher(List<Voucher> voucher) {
        this.voucher = voucher;
    }
    public void addMovies(Movie movie) {
        movies.add(movie);
    }

    //Renting control methods

    public void  createRenting(Movie movie, Customer customer, int days, boolean stateRenting){
        try {
            if (movie.isAvailable()){
                ModelRent rent = new ModelRent(this.basePrice);

                int amountRent = rentings.size() + 1;

                Renting newRenting = new Renting(this, movie, amountRent, rent, customer, days, stateRenting);
                this.rentings.add(newRenting);

                Voucher newVoucher = new Voucher(amountRent, newRenting, newRenting.getFinalPrice());
                this.voucher.add(newVoucher);

            } else {
                throw new IllegalArgumentException("Error: La película no esta disponible");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }


    // Movie control methods
    
    public Movie searchMovies(String title){
        moviesException();
        Movie movie=movies.getFirst();
        for (int i = 0; i < movies.size(); i++) {
            movie=movies.get(i);
            if (movie.getTitle().trim().toLowerCase().equals(title.trim().toLowerCase())){
                return movie;
            }
        }
        throw new IllegalArgumentException("No se encontró una película con este nombre"); 
    }
    
    public List<Movie> filterCategory(String category){
        moviesException();
        List<Movie> categoryMovies= new ArrayList<>();
        Movie movie=movies.getFirst();
        for (int i = 0; i < movies.size(); i++) {
            movie=movies.get(i);
            if(category.trim().toLowerCase()==movie.getCategory().trim().toLowerCase()){
                categoryMovies.add(movie);
            }
        }
        if(categoryMovies.isEmpty()){
            throw new IllegalArgumentException("No hay películas de la categoría ingresada");
        }
        return categoryMovies;
    } 
    
    public int copysAvailable(String title){
        moviesException();
        int j=0;
        for (int i = 0; i < movies.size(); i++) {
            Movie movie=movies.get(i);
            if (movie.getTitle().trim().toLowerCase().equals(title.trim().toLowerCase())) {
                j++;
            }
        }
        if(j==0){
            throw new IllegalArgumentException("Hay 0 copias disponibles de la película");
        }
        return j;
    }
    
    // Auxxiliary methods
    
    public void moviesException(){
        if(movies.isEmpty()){
            throw new IllegalArgumentException("La lista de películas está vacía");
        }
    }
    
    public void customerException(){
        if(customers.isEmpty()){
            throw new IllegalArgumentException("La lista de clientes está vacía");
        }
    }
    
    public void employeeException(){
        if(employees.isEmpty()){
            throw new IllegalArgumentException("La lista de empleados está vacía");
        }
    }
    
}