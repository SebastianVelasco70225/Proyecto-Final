public class Employee extends Person{
    private String charge;
    private StoreMovie movieStore;
    
    public Employee(int id, String name, String adress, String charge, StoreMovie movieStore){
        super(id,name,adress);
        setCharge(charge);
        setMovieStore(movieStore);
    }

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
