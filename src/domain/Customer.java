package src.domain;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Customer extends Person implements Serializable {

    private static final long serialVersionUID = 1L;
    
    // Atributes
    private List<Renting> renting;

    // Constructor for adding a Customer without renting
    public Customer(int id,int age, String name, String adress){
        super(id,age,name,adress);
        this.renting = new ArrayList<>();
    }

    // Constructor for adding a Customer with renting
    public Customer(int id, int age, String name, String adress, List<Renting> rentings){
        super(id,age,name,adress);
        this.renting = (rentings != null) ? renting : new ArrayList<>();
    }
    
    // Getters and Setters
    public List<Renting> getRenting() {
        return renting;
    }
    public void setRenting(List<Renting> renting) {
        this.renting = renting;
    }

}
