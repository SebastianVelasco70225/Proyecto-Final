package src.domain;

import java.io.Serializable;

public class Employee extends Person implements Serializable{

    // Atributes
    public static final long serialVersionUID = 1L;
    private String charge;
    private StoreMovie movieStore;
    
    // Constructor
    public Employee(int id,int age, String name, String adress, String charge, StoreMovie movieStore){
        super(id,age,name,adress);
        setCharge(charge);
        setMovieStore(movieStore);
    }

    // Getters and Setters
    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public StoreMovie getMovieStore(){
        return movieStore;
    }

    public void setMovieStore(StoreMovie movieStore){
        this.movieStore=movieStore;
    }
}
