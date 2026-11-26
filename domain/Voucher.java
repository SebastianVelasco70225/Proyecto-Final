public class Voucher {

    private int id;
    private Renting renting;
    private float totalAmount;

    public Voucher(int id, Renting renting, float totalAmount ){
        setId(id);
        setRenting(renting);
        setTotalAmount(totalAmount);
    }

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
    
    
}