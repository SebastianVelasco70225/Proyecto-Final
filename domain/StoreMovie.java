public class StoreMovie {

    private String name;
    private String nit;
    private String address;


    public StoreMovie (String name, String nit, String address){
        setName(name);
        setNit(nit);
        setAddress(address);
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getNit() {
        return nit;
    }


    public void setNit(String nit) {
        this.nit = nit;
    }


    public String getAddress() {
        return address;
    }


    public void setAddress(String address) {
        this.address = address;
    }
}