package src.domain;
import java.io.Serializable;

public class ModelRent implements Serializable{

    private static final long serialVersionUID = 1L;

    // Atributes
    private float basePrice;
    int currentYear = java.time.Year.now().getValue();

    // Constructor
    public ModelRent(float basePrice){
        setBasePrice(basePrice);
    }

    // Getters and Setters
    public float getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(float basePrice) {

        if (basePrice <= 0){
            throw new IllegalArgumentException("El precio base no puede ser menor a 0");
        }
        this.basePrice = basePrice;
    }
    
    // Methods

    // Method for getting the total price of a renting, based on the days and the movie
    public float calculateTotalPrice(Movie movie, int days){
        float totalPrice = 0 ;
        totalPrice = priceDays(days) + priceRelease(movie);
        return totalPrice;

    }

    // Method for getting the extra price based on the release year of a movie
    public float priceRelease(Movie movie){
        float extraCost = 0 ;
        if(movie.getRelease() == currentYear){
            extraCost = (this.basePrice + ( this.basePrice * 0.2f));
            return extraCost;
        } else if (movie.getRelease() < currentYear && movie.getRelease() > (currentYear - 5)){
            extraCost = this.basePrice;
            return extraCost;
        } 
        extraCost = (this.basePrice - (this.basePrice * 0.3f));
        return extraCost;
    }

    // Method for getting the price per day of a renting
    public float priceDays(int days){
        float price = 0;
        if (days > 4 ){
            price = ((this.basePrice * 0.3f) * (days - 4)) ;
            return price;
        }
        return price;
    }

    // Method for getting the extra price for returning the movie past the renting time
    public float calculateLateFee(Renting renting, int days){
        float extraCost = 0;
        if (days > renting.getRentalDays()){
            extraCost = (basePrice * 0.4f) * (days - renting.getRentalDays());
            return extraCost;
        }
        return  extraCost;
    }
}