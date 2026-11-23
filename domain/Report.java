import java.util.List;

public class Report {

    private int id;
    private String title;
    private String content;

    // Constructor para crear un reporte manuelamente

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

    public static





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