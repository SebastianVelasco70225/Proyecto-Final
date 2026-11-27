package src.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StoreMovie implements Serializable {

    // Atributes
    private static  final long serialVersionUID = 1L;
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


    // Constructor
    public StoreMovie (String name, String nit, String address, float basePrice){
        setName(name);
        setNit(nit);
        setAddress(address);
        setBaseprice(basePrice);
    }

    // Setters y Getters
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
    
    public List<Renting>  getRentings(){
        return this.rentings;
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

    //Method of search by id

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
    public List<Renting> filterActiveRentals(){
        rentingException();
        List<Renting> activeRentings= new ArrayList<>();
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

    //Method for filtering by inactive status and later return 

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

    // Method for filtering rentings by a proxime end date 
    public List<Renting> searchRentingByEndProximity(){
        rentingException();
        List<Renting> rentingProximeToEnd= filterActiveRentals();
        for (Renting r: rentingProximeToEnd){
            if(r.getRentalDays()>2){
                rentingProximeToEnd.remove(r);
            }
        }
        return rentingProximeToEnd;
    }
    
    // Movie control methods

    // Method for creating new movies and adding them to the catalog
    public void createMovies(String title, int pegi,int release, String category){
        Movie m=new Movie(title, pegi, release, category, this);
        m.setId(movies.size());
        addMovies(m);
    }
    
    // Method for adding movies to the catalog
    public void addMovies(Movie movie) {
        movies.add(movie);
    }

    // Method for searching movies by title parcial match and total match
    public List<Movie> searchMoviesByTitle(String parcialTitle, int option){
        moviesException();
        List <Movie> parcialTitleMatch= new ArrayList<>();
        for (Movie m: this.movies) {
            if (parcialTitleMatch.contains(m)){
                continue;
            }
            if(option==1){ 
                if (m.getTitle().trim().toLowerCase().contains(parcialTitle.trim().toLowerCase())&& m.isAvailable()){
                parcialTitleMatch.add(m);
            }
            } else if(option==2){
                if (m.getTitle().trim().toLowerCase().contains(parcialTitle.trim().toLowerCase())){
                parcialTitleMatch.add(m);  
            }
            } else if (option==3){
                if (m.getTitle().trim().toLowerCase().contains(parcialTitle.trim().toLowerCase())&&!(m.isAvailable())){
                parcialTitleMatch.add(m);  
            }
            }
    }
        if(parcialTitleMatch.isEmpty()){
            throw new IllegalArgumentException("No se encontró una película con este nombre");
        }
        return parcialTitleMatch;
    }
    
    // Method for searching movies by ID
    public Movie searchMovieById(int id){
        if(id<0||id>=movies.size()){
            throw new IllegalArgumentException("No se encontró una película con ese ID");
        }
        return movies.get(id);
    }

    public List<Movie> filterCategory(String category, int option){
        moviesException();
        List<Movie> categoryMovies= new ArrayList<>();
        for (Movie m : this.movies) {
            if (categoryMovies.contains(m)){
                continue;
            } 
            if(option==1){ 
            if(m.getCategory().trim().equalsIgnoreCase(category.trim())&&m.isAvailable()){
                categoryMovies.add(m);
            }
            } else if (option==2){
            if(m.getCategory().trim().equalsIgnoreCase(category.trim())){
                categoryMovies.add(m);
            }
            } else if (option==3){
            if(m.getCategory().trim().equalsIgnoreCase(category.trim())&&!(m.isAvailable())){
                categoryMovies.add(m);
            }
            }
        }
        if(categoryMovies.isEmpty()){
            throw new IllegalArgumentException("No hay películas de la categoría ingresada");
        }
        return categoryMovies;
    } 
    
    // Method for filter Movies by availabylity
    public List<Movie> filterAvailable(int option){
        moviesException();
        List<Movie> availableMovies=new ArrayList<>();
        List<Movie> notAvailableMovies=new ArrayList<>();
        for(Movie m: this.movies){
            if(m.isAvailable()){
                availableMovies.add(m);
                continue;
            } 
            notAvailableMovies.add(m);
        }
        if(option>2||option<=0){
            throw new IllegalArgumentException("Opción inválida");
        }
        
        if(option==1){
            if(availableMovies.isEmpty()){
                throw new IllegalArgumentException("No se encontraron películas disponibles");
            }
            return availableMovies;
        }
        if(notAvailableMovies.isEmpty()){
            throw new IllegalArgumentException("No se encontraron películas rentadas");
        }
        return notAvailableMovies;
    }

    // Method for know the copies available of a certain movie
    public int copiesAvailable(String title){
        moviesException();
        int copies=0;
        for (Movie m: this.movies){
            if(m.getTitle().trim().equalsIgnoreCase(title.trim())&&m.isAvailable()){
                copies++;
            }
        }
        if(copies==0){
            throw new IllegalArgumentException("Hay 0 copias disponibles de la película");
        }
        return copies;
    }

    // Method for decission taking help, returns movies with only one copie
    public List<Movie> moviesWithOneCopie(){
        List<Movie> movies2=new ArrayList<>();
        for (Movie m: this.movies){
            if(copiesAvailable(m.getTitle())==1){
                movies2.add(m);
            }
        }
        if(movies2.isEmpty()){
            throw new IllegalArgumentException("No hay películas con una sola copia disponible");
        }
        return movies2;
    } 

    // Method to eliminate movies from inventary
    public void eliminateMovie(Movie movie, int option){
        moviesException();
        if(option>2||option<=0){
            throw new IllegalArgumentException("Opción inválida");
        }
        if(option==1) {
        for(Movie m: this.movies){
            if(movie.getTitle().trim().equalsIgnoreCase(m.getTitle().trim())){
                m.switchActive();
                if(m.isAvailable()){
                    movies.remove(m);
                }
            }
        } 
        return;
        }
        movie.switchActive();
        if(movie.isAvailable()){
            movies.remove(movie);
        }
    }

    public List<Renting> filterbyCustomer(String name){

        List<Renting> listTemporal = new ArrayList<>();

        for (Renting r : this.rentings){
            if (r.getCustomer().getName().trim().equalsIgnoreCase(name.trim())){
                listTemporal.add(r);
            }
        }

        if (listTemporal.isEmpty()){
            throw new IllegalArgumentException("El cliente no tiene alquileres");
        }

        return listTemporal;
    }

    public List<Renting> filterbyReturnDay(int days){
        List<Renting> listTemporal = new ArrayList<>();
        for (Renting r : this.rentings){
            if (r.getRentalDays() == days){
                listTemporal.add(r);
            }
        }

        if (listTemporal.isEmpty()) {
            throw new IllegalArgumentException("No hay rentas previstar para esa cantidad de dias");
        }

        return listTemporal;
    }

    public List<Renting> filterReturnRenting (){
        
        List<Renting> listTemporal = new ArrayList<>();

        for(Renting r: this.rentings){
            if (r.isState() == false) {
                listTemporal.add(r);
            }
        }

        if (listTemporal.isEmpty()) {
            throw new IllegalArgumentException("No hay devoluviones registradas aun");
        }

        return listTemporal;
    }
    
    // Auxiliary methods
    
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