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


    public void setMovies(List<Movie> movies) {
        this.movies = movies;
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

    
}