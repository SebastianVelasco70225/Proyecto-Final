
import java.util.ArrayList;
import java.util.List;

public class Customer extends Person {
    private List<Renting> renting;

    public Customer(int id,int age, String name, String adress){
        super(id,age,name,adress);
        this.renting = new ArrayList<>();
    }

    public Customer(int id, int age, String name, String adress, List<Renting> rentings){
        super(id,age,name,adress);
        this.renting = (renting != null) ? renting : new ArrayList<>();
    }
    
    public List<Renting> getRenting() {
        return renting;
    }
    public void setRenting(List<Renting> renting) {
        this.renting = renting;
    }

}
