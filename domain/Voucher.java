public class Voucher {

    private String id;
    private Renting renting;
    private float totalAmount;

    public Voucher(String id, Renting renting, float totalAmount ){
        setId(id);
        setRenting(renting);
        setTotalAmount(totalAmount);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Renting getRenting() {
        return renting;
    }

    public void setRenting(Renting renting) {
        this.renting = renting;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }
    
    
}