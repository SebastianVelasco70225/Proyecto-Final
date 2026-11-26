import java.util.List;

public class Report {

    private int id;
    private String title;
    private String content;

    // Constructor for creating manual report

    public Report (int id, String title, String content){
        setId(id);
        setTitle(title);
        setTitle(title);
    }

    // Method for creating a financial report

    public static Report createReportIncome(int id, List<Voucher> vouchers){

        String title ="Reporte de ingresos";

        if (vouchers == null || vouchers.isEmpty()){
            return new Report(id, title, "No hay comprobantes registrados");
        }
        
        float totalIncome = 0;

        for (Voucher v : vouchers){
            totalIncome += v.getTotalAmount();
        } 

        String content = "Transacciones realizadas: " + vouchers.size() + "\n" + "ingresos totales: $" + totalIncome;
        
        

        return new Report(id, title,content);
    }

    // Method for creating a report of completed rentals

    public static Report createReportRentign(int id, List<Renting> rentings) {

        String title = "Reporte de alquileres";

        if (rentings == null || rentings.isEmpty()){
            return new Report(id, title, "No hay alquileres registrados");
        }

        StringBuilder sb = new StringBuilder();

        sb.append("Total de alquileres = ").append(rentings.size()).append("\n");

        for (Renting r: rentings){
            sb.append("- ID: ").append(r.getIdRenting())
            .append(" | Cliente: ").append(r.getCustomer())
            .append(" | Películas: ").append(r.getMovie())
            .append("\n");
        }

        return new Report(id, title, sb.toString());
    }

    // Method for creating a report of inventary movie 

    public static Report createReportInventory(int id, List<Movie> movies){

        String title = "Reporte de inventario de películas";

        if (movies == null || movies.isEmpty()){
            return new Report(id, title, "No hay peliculas en el catalogo");
        }

        StringBuilder sb = new StringBuilder();
        for (Movie m: movies){
            sb.append("- ").append(m.getTitle())
            .append(" | Disponible: ").append(m.isAvailable() ? "Si" : "No")
            .append("\n");
        }

        return new Report(id, title, sb.toString());

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
}