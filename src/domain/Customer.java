package src.domain;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Customer extends Person implements Serializable{

    private static final long serialVersionUID = 1L;
    
    // Atributes
    private List<Renting> rentings;

    // Constructor for adding a Customer without renting
    public Customer(int id,int age, String name, String adress){
        super(id,age,name,adress);
        this.rentings = new ArrayList<>();
    }

    // Constructor for adding a Customer with renting
    public Customer(int id, int age, String name, String adress, List<Renting> rentings){
        super(id,age,name,adress);
        this.rentings = (rentings != null) ? rentings : new ArrayList<>();
    }
    
    // Getters and Setters
    public List<Renting> getRentings() {
        return rentings;
    }
    public void addRenting(Renting renting) {
        rentings.add(renting);
    }

}
