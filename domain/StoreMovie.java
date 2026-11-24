import java.util.ArrayList;
import java.util.List;

public class StoreMovie {

    private String name;
    private String nit;
    private String address;
    private List<Movie> movies;
    private List<Customer> customers;
    private List<Employee> employees;
    private List<Renting> rentings;
    private List<Report> reports;
    private List<Voucher> voucher;


    public StoreMovie (String name, String nit, String address){
        setName(name);
        setNit(nit);
        setAddress(address);
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
        return movies;
    }


    public void addMovies(Movie movie) {
        movies.add(movie);
    }

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
    public List<Customer> getCustomers() {
        return customers;
    }


    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }


    public List<Employee> getEmployees() {
        return employees;
    }


    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
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

    public void moviesException(){
        if(movies.isEmpty()){
            throw new IllegalArgumentException("La lista de películas está vacía");
        }
    }
}