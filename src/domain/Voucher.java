package src.domain;
import java.io.Serializable;
import java.time.LocalDate;

public class Voucher implements Serializable{

    private static final long serialVersionUID = 1L;

    // Atributes
    private int id;
    private Renting renting;
    private float totalAmount;
    private Employee employee;
    private LocalDate isDate;

    // Constructor
    public Voucher(int id, Renting renting, float totalAmount, Employee employee, LocalDate isDate ){
        setId(id);
        setRenting(renting);
        setTotalAmount(totalAmount);
        setEmployee(employee);
        setIsDate(isDate);
    }

    // Getters and Setters
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Renting getRenting() {
        return this.renting;
    }

    public void setRenting(Renting renting) {
        this.renting = renting;
    }

    public float getTotalAmount() {
        return this.totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Employee getEmployee() {
        return employee;
    }

    public LocalDate getIsDate() {
        return isDate;
    }

    public void setIsDate(LocalDate isDate) {
        this.isDate = isDate;
    }

    public void setEmployee(Employee employee) {

        if (employee == null) {
            throw new IllegalArgumentException("Error el empleado no ha sido cargado");
        }
        this.employee = employee;
    }
    
}