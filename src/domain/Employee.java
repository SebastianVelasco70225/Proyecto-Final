package src.domain;

import java.io.Serializable;

public class Employee extends Person implements Serializable{

    // Atributes
    public static final long serialVersionUID = 1L;
    private String charge;
    private StoreMovie movieStore;
    
    // Constructor
    public Employee(int id,int age, String name, String adress, String charge, StoreMovie movieStore){
        super(id,0,name,adress);
        checkAge(age);
        setAge(age);
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

    public void checkAge(int age){
        if(age<17){
            throw new IllegalArgumentException("Edad inválida, debes tener al menos 17 años para ser empleado");
        }
    }
}
