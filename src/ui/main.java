package src.ui;

import java.io.IOException;
import src.data.FileManager;
import src.domain.Customer;
import src.domain.Movie;
import src.domain.StoreMovie;

public class Main {
    

    public static void main(String[] args) {
        
        StoreMovie store = null;

        try {
            store = FileManager.loadStore();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar los datos: " + e.getMessage());
        }

        if (store == null) {
            store = new StoreMovie("PeliPlus", "71484", "Parque Caldas", 20000f);

            store.addMovies(new Movie("inception", 15, 2010, "Ciencia Ficcion", store, 1));
            store.addCustomers(new Customer(1068217112, 18, "Leonardo Rivera", "Palermo"));
        }

        try {
            FileManager.saveStore(store);
        } catch (IOException e) {
            System.out.println("Error al guardar los datos: " + e.getMessage());
        }
    }
}