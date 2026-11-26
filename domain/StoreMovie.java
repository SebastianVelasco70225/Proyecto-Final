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
        setBaseprice(basePrice);
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
    
    
    public void addMovies(Movie movie) {
        movies.add(movie);
    }

    //Renting control methods

    public void  createRenting(Movie movie, Customer customer, int days ){
        
            validateNumber(days);

            if (movie.isAvailable()){
                ModelRent rent = new ModelRent(this.basePrice);

                int amountRent = rentings.size() + 1;

                movie.setAvailable(false);

                Renting newRenting = new Renting(this, movie, amountRent, rent, customer, days);
                this.rentings.add(newRenting);

                Voucher newVoucher = new Voucher(amountRent, newRenting, newRenting.getFinalPrice());
                this.voucher.add(newVoucher);

            } else {
                throw new IllegalArgumentException("Error: La película no esta disponible");
            }
    }

    //Returns control method

    public float returnRenting (int idRentig, int outDays){
        validateNumber(outDays);
        validateNumber(idRentig);

        float latefee = 0;

        Renting renting = searchRenting(idRentig);

        if (!renting.isState()){
            throw new IllegalArgumentException("Error: Este alquiler ya fue devuelto");
        }

        if (outDays > renting.getRentalDays()){
            latefee = renting.calculateLateFee(outDays);
            renting.setReturnLate(true);
            renting.addLateFee(latefee);

            int idVoucher = voucher.size() + 1;
            Voucher newVoucher = new Voucher(idVoucher, renting, latefee);
            this.voucher.add(newVoucher);
        }
            renting.getMovie().setAvailable(true);
            renting.setState(false);
            return latefee;
    }

    //Method of serach by id

    public Renting searchRenting (int id){
        validateNumber(id);

        for(Renting r: this.rentings){
            if(r.getIdRenting() == id){
                return r;
            }
        }
        throw new IllegalArgumentException("No se encontro el alquiler");
    }

    //Method for filtering by active status
    public List<Renting> filterActiveRentals(List<Renting> activeRentings){
        rentingException();
        for(Renting r : this.rentings){
            if (r.isState() == true){
                activeRentings.add(r);
            }
        }
        return activeRentings;
    }

    //Method for filtering by inactive status
    public List<Renting> filterInactiveRentals(){
        rentingException();
        List <Renting> temporalList = new ArrayList<>(); 
        for (Renting r : this.rentings){
            if (r.isState() == false){
                temporalList.add(r);
            }
        }
        return temporalList;
    }

    //Method for filtering by inactiva status and later return 

    public List<Renting> filterLaterReturn(){
        rentingException();
        List <Renting> temporalList = new ArrayList<>();
        
        for (Renting r: filterInactiveRentals()){
            if(r.getReturnLate() == true){
                temporalList.add(r);
            }
        }
        return temporalList;
    }


    // Movie control methods
    
    public Movie searchMovies(String title){
        moviesException();
        
        for (Movie m: this.movies) {
            if (m.getTitle().trim().equalsIgnoreCase(title.trim())){
                return m;
            }
        }
        throw new IllegalArgumentException("No se encontró una película con este nombre"); 
    }
    
    public List<Movie> filterCategory(String category){
        moviesException();

        List<Movie> categoryMovies= new ArrayList<>();

        for (Movie m : this.movies) {
            if(m.getCategory().trim().equalsIgnoreCase(category.trim())){
                categoryMovies.add(m);
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

    public void rentingException(){
        if(rentings.isEmpty()){
            throw new IllegalArgumentException("La lista de alquileres esta vacá");
        }
    }
    
    public void validateNumber(int number){
        if (number <= 0) {
            throw new IllegalArgumentException("Error: el numero ingresado no puede ser menor a 0");
        }
    }
}