package src.domain;
import java.io.Serializable;
import java.util.List;

public class Report implements Serializable{

    private static final long serialVersionUID = 1L;
    private int id;
    private String title;
    private String content;
    private String type;
    private Employee employee;

    // Constructor for creating manual report

    public Report (int id, String title, String type, Employee employee, String content){
        setId(id);
        setTitle(title);
        setTitle(type);
        setEmployee(employee);
    }

    // Method for creating a financial report

    public static Report createReportIncome(int id, List<Voucher> vouchers, Employee employee){

        String title ="Reporte de ingresos";
        String type = "Financiero";

        if (vouchers == null || vouchers.isEmpty()){
            return new Report(id, title, type, employee, "No hay comprobantes registrados");
        }
        
        float totalIncome = 0;

        for (Voucher v : vouchers){
            totalIncome += v.getTotalAmount();
        } 

        String content = "Transacciones realizadas: " + vouchers.size() + "\n" + "ingresos totales: $" + totalIncome;
        
        

        return new Report(id, title, type,employee, content);
    }

    // Method for creating a report of completed rentals

    public static Report createReportRenting(int id, List<Renting> rentings, Employee employee) {

        String title = "Reporte de alquileres";
        String type = "Alquileres";

        if (rentings == null || rentings.isEmpty()){
            return new Report(id, title, type , employee, "No hay alquileres registrados");
        }

        StringBuilder sb = new StringBuilder();

        sb.append("Total de alquileres = ").append(rentings.size()).append("\n");

        for (Renting r: rentings){
            sb.append("- ID: ").append(r.getIdRenting())
            .append(" | Cliente: ").append(r.getCustomer().getName())
            .append(" | Películas: ").append(r.getMovie().getTitle())
            .append("\n");
        }

        return new Report(id, title, type,  employee, sb.toString());
    }

    // Method for creating a report of inventary movie 

    public static Report createReportInventory(int id, List<Movie> movies, Employee employee){

        String title = "Reporte de inventario de películas";
        String type = "Inventario";

        if (movies == null || movies.isEmpty()){
            return new Report(id, title, type, employee, "No hay peliculas en el catalogo");
        }

        StringBuilder sb = new StringBuilder();
        for (Movie m: movies){
            sb.append("- ").append(m.getTitle())
            .append(" | Disponible: ").append(m.isAvailable() ? "Si" : "No")
            .append("\n");
        }

        return new Report(id, title, type, employee, sb.toString());
    }

    public static Report createReport( int id, List<Renting> rentings, Employee employee){
        String title = "Reporte de devoluciones";
        String type = "Devoluciones";

        if (rentings == null || rentings.isEmpty()){
            return new Report(id, title, type, employee, "No hay devolucciones por el momento");
        }
        
        StringBuilder sb = new StringBuilder();
        for (Renting r: rentings){
            if (r.isState() == false){
                sb.append(" - El nombre de la persona es: ").append(r.getCustomer().getName())
                .append(" - la cantidad que rento la pelicula es; ").append(r.getRentalDays())
                .append("La pelicula que rento es: ").append(r.getMovie().getTitle())
                .append("El costo toal es: ").append(r.getFinalPrice());
            }
        }

        return new Report(id, title, type, employee, sb.toString());
    } 

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setType(String type){
        this.type = type;
    }

    public String getType (){
        return this.type;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Report [id=" + id + ", title=" + title + ", content=" + content + ", type=" + type + ", employee="
                + employee + "]";
    }
    
}