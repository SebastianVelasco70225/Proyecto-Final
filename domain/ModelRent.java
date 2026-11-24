

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
            throw new IllegalAccessError("El precio base no puede ser menor a 0");
        }
        this.basePrice = basePrice;
    }
    
    public static void Moviesrelanse(Movie movie, float basePrice){
        float price = 0 ;
        if(movie.getRelease() == 2026){
            price = (float) (basePrice + ( basePrice * 0.2));
            if(movie.getPegi() >= 16){
                price = (float) (price + (basePrice * 0.2));
            }
            movie.setRentPrice(price);
        } else if (movie.getRelease() < 2026 && movie.getRelease() > 2021){
            price = basePrice;
            if(movie.getPegi() >= 16){
                price = (float) (price + (basePrice * 0.2));
            }
            movie.setRentPrice(price);
        } 
        price = (float) (basePrice - (basePrice * 0.3));
        if(movie.getPegi() >= 16){
                price = (float) (price + (basePrice * 0.2));
            }
        movie.setRentPrice(price); 
    }
}