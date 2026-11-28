package src.ui;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.annotation.ElementType;
import java.nio.charset.StandardCharsets;

import src.data.FileManager;
import src.domain.StoreMovie;
import src.domain.StoreMovie.FilterOption;

import java.util.List;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
public class Main {
    StoreMovie store=null;
    Scanner sc= new Scanner(System.in, StandardCharsets.UTF_8);
    public void main(String[] args) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        try {
            store = FileManager.loadStore();
            System.out.println("Cargado exitósamente");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar los datos: " + e.getMessage());
        }

        if (store == null) {
            store = new StoreMovie("PeliPlus", "71484", "Parque Caldas", 20000f);
        }

        try {
            FileManager.saveStore(store);
        } catch (IOException e) {
            System.out.println("Error al guardar los datos: " + e.getMessage());
        }

        System.out.println("Tienda: "+store.getName());
        System.out.println("Fecha actual: "+store.getCurrentDate());

        int option=-1;
        while (option!=0) { 
        try {
            System.out.println("Ingresa una de las siguientes opciones.");
            System.out.println("1. Gestión de películas");
            System.out.println("2. Gestión de clientes");
            System.out.println("3. Alquileres y devoluciones");
            System.out.println("4. Apoyo a decisiones (Alertas)");
            System.out.println("5. Reportes y exportación");
            System.out.println("6. Simular paso del tiempo");
            System.out.println("0. Guardar y salir");
            option=sc.nextInt();sc.nextLine();
            System.out.println();
            chooseMenu(option);
        } catch (InputMismatchException e){ 
            System.out.println("Dato ingresado de formato inválido");
            option=10;
        }
        }
        System.out.println("Saliendo...");
        sc.close();
    }


    public void moviesAdministrationMenu(){
        int option =10;
        while(option!=0){ 
            System.out.println("Ingresa una opción");
            System.out.println();
            System.out.println("1. Registrar nueva película/ejemplar");
            System.out.println("2. Listar catálogo completo");
            System.out.println("3. Buscar por título");
            System.out.println("4. Filtrar por categoría");
            System.out.println("5. Filtrar por disponibilidad");
            System.out.println("6. Buscar película por ID exacto");
            System.out.println("7. Actualizar datos de una película");
            System.out.println("8. Eliminar o desactivar película");
            System.out.println("0. Volver al menú anterior");
            option=sc.nextInt();sc.nextLine();
            switch(option){
                case 1: newMovie();
                break;
                case 2:showCatalog();
                break;
                case 3: movieSearch();
                break;
            }
        }
        System.out.println("Volviendo...");
        System.out.println();
        return;
    }

    public void chooseMenu(int option){
        switch(option){
            case 0:
            break;
            case 1: moviesAdministrationMenu();
            break;
            case 2: 
            break;
            case 3: 
            break;
            case 4: 
            break;
            case 5:
            break;
            case 6: 
            break;
        }
    }

    public void newMovie(){
        try {
        System.out.println("Ingresa los datos de la película");
        System.out.println();
        System.out.println("Título: ");
        String title=sc.nextLine();
        System.out.println("Clasificación PEGI: ");
        int pegi=sc.nextInt();sc.nextLine();
        System.out.println("Año de lanzamiento: ");
        int release=sc.nextInt();sc.nextLine();
        System.out.println("Categoría Principal: ");
        String category=sc.nextLine();
        store.createMovies(title,pegi,release,category);
        System.out.println("Película agregada con éxito");
        } catch (IllegalArgumentException e){
            System.out.println("Error: "+e.getMessage());
        }
    }

    public void showCatalog(){
        try {
        for(src.domain.Movie m: store.getMovies()){
            System.out.println("Título: "+m.getTitle());
            System.out.println("Año de lanzamiento: "+m.getRelease());
            System.out.println("Categoría principal: "+m.getCategory());
            System.out.println("PEGI: "+m.getPegi());
            System.out.println("ID: "+m.getId());
            System.out.println("---------------------------------------");
            System.out.println();
        }
    } catch (IllegalArgumentException e){
        System.out.println("Error: "+e.getMessage());
    }
    }

    public void movieSearch(){
        try { 
        System.out.println();
        System.out.println("Ingresa el título de la película");
        String title= sc.nextLine();
        List<src.domain.Movie>coincidences=store.searchMoviesByTitle(title, StoreMovie.FilterOption.ALL);
        for(src.domain.Movie m: coincidences){
            System.out.println("Título: "+m.getTitle());
            System.out.println("Año de lanzamiento: "+m.getRelease());
            System.out.println("Categoría principal: "+m.getCategory());
            System.out.println("PEGI: "+m.getPegi());
            System.out.println("ID: "+m.getId());
            System.out.println("---------------------------------------");
            System.out.println();
        }
        } catch (IllegalArgumentException e){
            System.out.println("Error: "+e.getMessage());
        }
    }

}