

public class ModelRent {
    private float basePrice;

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
    
    public float calculateTotalPrice(Movie movie, int days){
        float totalPrice = 0 ;
        return totalPrice = priceDays(days) + priceRelease(movie);
    }

    public float priceRelease(Movie movie){
        float extraCost = 0 ;
        if(movie.getRelease() == 2026){
            extraCost = (this.basePrice + ( this.basePrice * 0.2f));
            return extraCost;
        } else if (movie.getRelease() < 2026 && movie.getRelease() > 2021){
            extraCost = this.basePrice;
            return extraCost;
        } 
        extraCost = (this.basePrice - (this.basePrice * 0.3f));
        return extraCost;
    }

    public float priceDays(int days){
        float price = 0;
        if (days > 4 ){
            price = ((this.basePrice * 0.3f) * (days - 4)) ;
            return price;
        }
        return price;
    }

    public float calculateLateFee(Renting renting, int days){
        float extraCost = 0;
        if (days > renting.getRentalDays()){
            extraCost = (basePrice * 0.4f) * (days - renting.getRentalDays());
            return extraCost;
        }
        return  extraCost;
    }
}