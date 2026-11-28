package src.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class StoreMovie implements Serializable {

    // Atributes
    private static  final long serialVersionUID = 1L;
    private String name;
    private String nit;
    private String address;
    private float basePrice;
    private LocalDate currentDate = LocalDate.now();
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
        setBasePrice(basePrice);
    }

    // Setters y Getters
    public void setBasePrice(float basePrice){
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
        if(nit.isBlank()){
            throw new IllegalArgumentException("El NIT no puede ser vacío");
        }
        this.nit = nit;
    }


    public String getAddress() {
        return address;
    }


    public void setAddress(String address) {
        if(address.isBlank()){
            throw new IllegalArgumentException("Dirección inválida");
        }
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

    public LocalDate getCurrentDate() {
        return currentDate;
    }
    
    
    
    
    //Renting control methods
    

    public void  createRenting(Movie movie, Customer customer, int days, Employee employee ){
        
        validateNumber(days);

        if(customer == null || !customer.isActive()){
            throw new IllegalArgumentException("Error: el cliente no esta activo");
        }

        if (movie != null && movie.isAvailable() && movie.isActive()){
            ModelRent rent = new ModelRent(this.basePrice);

            int amountRent = this.rentings.size() + 1;

            Renting newRenting = new Renting(this, movie, amountRent, rent, customer, days, this.currentDate);
            customer.getRentings().add(newRenting);
            movie.setAvailable(false);
            this.rentings.add(newRenting);

            int amountVouncher = voucher.size() + 1;

            Voucher newVoucher = new Voucher(amountVouncher, newRenting, newRenting.getTotalCost(), employee, this.currentDate);
            this.voucher.add(newVoucher);

        } else {
            throw new IllegalArgumentException("Error: La película no esta disponible");
        }
    }

    //Returns control method

    public float returnRenting (int idRentig, Employee emmEmployee){

        validateNumber(idRentig);

        float lateFee = 0;
        
        Renting renting = searchRenting(idRentig);

        if (!renting.isState()){
            throw new IllegalArgumentException("Error: Este alquiler ya fue devuelto");
        }
        
        renting.setActualReturnDate(this.currentDate);
        long lateDays = ChronoUnit.DAYS.between(renting.getExpectedEndDate(), this.currentDate);

        if (lateDays > 0){
            lateFee = renting.calculateLateFee((int) lateDays);
            renting.setReturnLate(true);
            renting.addLateFee(lateFee);
            
            int idVoucher = voucher.size() + 1;
            Voucher newVoucher = new Voucher(idVoucher, renting, lateFee, emmEmployee, this.currentDate);
            this.voucher.add(newVoucher);
        }
            renting.getMovie().setAvailable(true);
            renting.setState(false);
            return lateFee;
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
        
        rentingProximeToEnd.removeIf(r -> {
        long daysLeft = ChronoUnit.DAYS.between(this.currentDate, r.getExpectedEndDate());
        return daysLeft > 2;
    });

        return rentingProximeToEnd;
    }

    // Report´s Methods

    public void createReport(typeReport option, Employee employee){
        if (option == typeReport.Financial){
            Report financialReport = Report.createReportIncome(reports.size() + 1, this.voucher, employee, this.currentDate);
            reports.add(financialReport);
        } else if (option == typeReport.Inventory){
            Report inventaryReport = Report.createReportInventory(reports.size() + 1, this.movies, employee, this.currentDate);
            reports.add(inventaryReport);
        } else {
            throw new IllegalArgumentException("Este tipo de reporte requiere fecha de inicio y fin");
        }
    }

    public void createReport(typeReport option, Employee employee, String content){
        if (option == typeReport.General){
            Report newReport = new Report(reports.size() + 1 , "Reporte General", "General", employee, content, this.currentDate);
            reports.add(newReport);
        }
    }

    public void createReport(typeReport option, Employee employee, LocalDate startDate, LocalDate endDate){
        if (option == typeReport.Rentals){
            Report rentingReport = Report.createReportRenting(reports.size() + 1, this.rentings, employee, startDate, endDate, this.currentDate);
            reports.add(rentingReport);
        } else if (option == typeReport.Returns){
            Report returnReport = Report.createReportReturn(reports.size() + 1, this.rentings, employee, startDate, endDate, this.currentDate);
            reports.add(returnReport);
        } else {
            createReport(option, employee);
        }
    }
    // Method for creating new movies and adding them to the catalog

    public void createMovies(String title, int pegi,int release, String category){
        Movie m=new Movie(title, pegi, release, category, this);
        m.setId(movies.size());
        addMovies(m);
    }

    // Method for filtering rentings by expected date

    public List<Renting> filterByExpectedReturnDate(LocalDate expectedDate){

        if(expectedDate == null){
            throw new IllegalArgumentException("Error fecha invalida");
        }

        rentingException();
        List<Renting> temporalList = new ArrayList<>();

        for(Renting r: this.rentings){
            if (r.getExpectedEndDate().equals(expectedDate)){
                temporalList.add(r);
            }
        }
        if(temporalList.isEmpty()){
            throw new IllegalArgumentException("No hay alquileres con fecha de devolucion prevista para esa fecha");
        }

        return  temporalList;
    }
    
    // Method for adding movies to the catalog

    public void addMovies(Movie movie) {
        movies.add(movie);
    }

    // Method for searching movies by title parcial match and total match

    public List<Movie> searchMoviesByTitle(String parcialTitle, movieFilter option){
        moviesException();
        if(parcialTitle.isBlank()){
            throw new IllegalArgumentException("El título no puede ser vacío");
        }
        List <Movie> parcialTitleMatch= new ArrayList<>();
        for (Movie m: this.movies) {
            if (parcialTitleMatch.contains(m)){
                continue;
            }
            if(movieFilter.Available == option){ 
                if (m.getTitle().trim().toLowerCase().contains(parcialTitle.trim().toLowerCase())&& m.isAvailable()){
                parcialTitleMatch.add(m);
            }
            } else if(option == movieFilter.All){
                if (m.getTitle().trim().toLowerCase().contains(parcialTitle.trim().toLowerCase())){
                parcialTitleMatch.add(m);  
            }
            } else if (option == movieFilter.Renting){
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
        for(Movie m: this.movies){
            if(m.getId()==id){
                return m;
            }
        }
        throw new IllegalArgumentException("No se encontró una película con este ID");
    }

    public List<Movie> filterCategory(String category, movieFilter option){
        moviesException();
        List<Movie> categoryMovies= new ArrayList<>();
        for (Movie m : this.movies) {
            if (categoryMovies.contains(m)){
                continue;
            } 
            if(option == movieFilter.Available){ 
            if(m.getCategory().trim().equalsIgnoreCase(category.trim())&&m.isAvailable()){
                categoryMovies.add(m);
            }
            } else if (option == movieFilter.All){
            if(m.getCategory().trim().equalsIgnoreCase(category.trim())){
                categoryMovies.add(m);
            }
            } else if (option == movieFilter.Renting){
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

    public List<Movie> filterAvailable( movieFilter option){
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
        if(option == movieFilter.Available){
            if(availableMovies.isEmpty()){
                throw new IllegalArgumentException("No se encontraron películas disponibles");
            }
            return availableMovies;
        }
        if(option == movieFilter.Renting) {
        if(notAvailableMovies.isEmpty()){
            throw new IllegalArgumentException("No se encontraron películas rentadas");
        }
        return notAvailableMovies;
    }
        throw new IllegalArgumentException("Opción inválida");
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

    private int copiesAvailable2(String title){
        moviesException();
        int copies=0;
        for (Movie m: this.movies){
            if(m.getTitle().trim().equalsIgnoreCase(title.trim())&&m.isAvailable()){
                copies++;
            }
        }
        return copies;
    }

    // Method for decission taking help, returns movies with only one copie

    public List<Movie> moviesWithOneCopie(){
        List<Movie> movies2=new ArrayList<>();
        for (Movie m: this.movies){
            if(copiesAvailable2(m.getTitle())== 1 && m.isAvailable()){
                movies2.add(m);
            }
        }
        if(movies2.isEmpty()){
            throw new IllegalArgumentException("No hay películas con una sola copia disponible");
        }
        return movies2;
    } 

    // Method to eliminate movies from inventary

    public void deleteMovie(Movie movie, deleteOption option){
        moviesException();
        if(option == deleteOption.All_copies) {
            List<Movie> eliminatedMovies= new ArrayList<>();
            for(Movie m: this.movies){
                if(movie.getTitle().trim().equalsIgnoreCase(m.getTitle().trim())){
                    if (hasRentingHistory(m)){
                        m.switchActive();
                    }else {
                        if (!m.isAvailable()){
                            throw new IllegalArgumentException("La pelicula no puede ser eliminada");
                        } else {
                            eliminatedMovies.add(m);
                        }
                    }
                }
            }
        
        this.movies.removeAll(eliminatedMovies);

        } else if (option == deleteOption.One_only){
            if(hasRentingHistory(movie)){
                movie.switchActive();
            } else {
                this.movies.remove(movie);
            }
        }
    }
    
    // Method for update Movie

    public void updateMovie(int id, String title, int pegi, int release, String category){

        validateNumber(pegi);
        validateNumber(release);
        validateString(category);
        validateString(title);

        Movie temporalMovie = searchMovieById(id);

        temporalMovie.setTitle(title);
        temporalMovie.setPegi(pegi);
        temporalMovie.setRelease(release);
        temporalMovie.setCategory(category);
    }

    // Methods for filtering customer by name

    public List<Renting> filterByCustomer(String name){

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

    // Method for filtering customers by id

    public Customer searchCustomerById(int id){

        validateNumber(id);
        customerException();
        for (Customer c: this.customers){
            if (c.getIdentificationNumber() == id){
                return  c;
            }
        }

        throw new IllegalArgumentException("No hay cienntes con ese numero de identificacion");
    }

    // Metho for update data ofCustomer 

    public void updateCustomer( int id, int age, String name, String address){

        validateNumber(age);
        validateString(name);
        validateString(address);

        Customer tempCustomer = searchCustomerById(id);

        tempCustomer.setAge(age);
        tempCustomer.setName(name);
        tempCustomer.setAddress(address);

    }


    // Method for filtering rentings overdeu

    public List<Renting> filterOverdueRentals(){
        List <Renting> overdueRentals = new ArrayList<>();

        rentingException();
        for (Renting r: filterActiveRentals()){
            if (this.currentDate.isAfter(r.getExpectedEndDate())){
                overdueRentals.add(r);
            }
        }

        if (overdueRentals.isEmpty()){
            throw new IllegalArgumentException("No se encontraron renta vencidas");
        }

        return overdueRentals;
    }

    // Method for delet customer

    public void deleteCustomer(int id){
        Customer tempCustomer = searchCustomerById(id);
        if (tempCustomer == null){
            throw new IllegalArgumentException("Error: el cliente no fue encontrado");
        }

        boolean hasRentings = false;
        for(Renting r: this.rentings){
            if (r.getCustomer().getIdentificationNumber() == tempCustomer.getIdentificationNumber()){
                hasRentings = true;
                break;
            }
        }

        if (hasRentings){
            tempCustomer.setActive(false);
        } else {
            customers.remove(tempCustomer);
        }
    }

    
    // Method for filtering rentings by inactive

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

    public void advanceDays(int days){
        validateNumber(days);
        this.currentDate = this.currentDate.plusDays(days);
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

    public void validateString(String string){
        if (string == null || string.isBlank()){
            throw new IllegalArgumentException("Error: la cadena es invalida vuelvalo a intentar");
        }
    }

    private boolean hasRentingHistory(Movie movie){
        for(Renting r: this.rentings){
            if (r.getMovie().equals(movie)){
                return true;
            }
        }
        return false;
    }


    public enum movieFilter {
        Available,
        Renting,
        All
    }
    public enum typeReport {
        Financial,
        Rentals,
        Returns,
        Inventory,
        General
    }

    public enum deleteOption {
        All_copies,
        One_only 
    }
}