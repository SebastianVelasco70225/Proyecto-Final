

public class ModelRent {
    private float basePrice;
    private float totalPrice;

    public ModelRent(float basePrice){
        setBasePrice(basePrice);
    }

    public float getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(float basePrice) {

        if (basePrice <= 0){
            throw new IllegalArgumentException("El precio base no puede ser menor a 0");
        }
        this.basePrice = basePrice;
    }
    
    public void calculateTotalPrice(Movie movie, int days){
        this.totalPrice = priceDays(days) + priceRelease(movie);
    }
    public float getTotalPrice(){
        return this.totalPrice;
    }
    public float priceRelease(Movie movie){
        float price = 0 ;
        if(movie.getRelease() == 2026){
            price = (this.basePrice + ( this.basePrice * 0.2f));
            return price;
        } else if (movie.getRelease() < 2026 && movie.getRelease() > 2021){
            price = this.basePrice;
            return price;
        } 
        price = (this.basePrice - (this.basePrice * 0.3f));
        return price;
    }

    public float priceDays(int days){
        float price = 0;
        if (days > 4 ){
            price = ((this.basePrice * 0.3f) * (days - 4)) ;
            return price;
        }
        return price;
    }
}