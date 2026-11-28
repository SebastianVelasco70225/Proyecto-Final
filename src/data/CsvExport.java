package src.data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import src.domain.*;

public class CsvExport {

    private static final String EXPORT_FOLDER = "src/data/exports/";

    public static void  exportsMovies( List<Movie> movies, String fileName) throws IOException{
        File file = new File(EXPORT_FOLDER + fileName);

        if (file.getParentFile() != null){
            file.getParentFile().mkdirs();
        }
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            
            
            writer.write("Id; Title; Pegi; Relance; Category; Disponible; Activa");
            writer.newLine();

            for (Movie m : movies){
                writer.write(
                    m.getId() + ";" +
                    m.getTitle() + ";" +
                    m.getPegi() + ";" +
                    m.getRelease() + ";"+
                    m.getCategory() + ";" +
                    m.isAvailable()+ ";" +
                    m.isActive()
                );
                writer.newLine();;
            }
        }
    }

    public static void exportReport (List<Report> reports, String fileName) throws IOException {

        File file = new File(EXPORT_FOLDER + fileName);
        if (file.getParentFile() != null) {
            file.getParentFile().mkdirs();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, StandardCharsets.UTF_8))) {
        writer.write('\uFEFF'); // BOM para que Excel lea bien tildes y ñ
        writer.write("ID;Titulo;Tipo;Fecha;Empleado;Contenido");
        writer.newLine();

        for (Report r : reports) {
            String employeeName = (r.getEmployee() != null) ? r.getEmployee().getName() : "N/A";
            String cleanContent = (r.getContent() != null) ? r.getContent().replace("\n", " | ").replace(";", ",") : "";

            writer.write(
                r.getId() + ";" +
                r.getTitle() + ";" +
                r.getType() + ";" +
                r.getIsDate() + ";" +
                employeeName + ";" +
                cleanContent
            );
            writer.newLine();
        }
        }
    }

    public static void exportRenting(List<Renting> rentings, String fileName) throws IOException {

        File file = new File(EXPORT_FOLDER + fileName);
        if (file.getParentFile() != null) {
            file.getParentFile().mkdirs();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
        writer.write("ID;Cliente;Pelicula;Dias;CostoTotal;FechaInicio;FechaPrevista;FechaDevolucion;Estado;DevolucionTarde");
        writer.newLine();

        for (Renting r : rentings) {
            String returnDate = (r.getActualReturnDate() != null) ? r.getActualReturnDate().toString() : "Pendiente";
            String status = r.isState() ? "Activo" : "Devuelto";
            String late = r.getReturnLate() ? "Si" : "No";

            writer.write(
                r.getIdRenting() + ";" +
                r.getCustomer().getName() + ";" +
                r.getMovie().getTitle() + ";" +
                r.getRentalDays() + ";" +
                r.getTotalCost() + ";" +
                r.getStartDate() + ";" +
                r.getExpectedEndDate() + ";" +
                returnDate + ";" +
                status + ";" +
                late
            );
            writer.newLine();
        }
        }
    }

    public static void exportCustomers(List<Customer> customers, String fileName) throws IOException {
    File file = new File(EXPORT_FOLDER + fileName);
    if (file.getParentFile() != null) {
        file.getParentFile().mkdirs();
    }

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
        writer.write("ID;Nombre;Edad;Direccion;Estado;CantidadAlquileres");
        writer.newLine();

        for (Customer c : customers) {
            String status = c.isActive() ? "Activo" : "Inactivo";
            int totalRentings = (c.getRentings() != null) ? c.getRentings().size() : 0;

            writer.write(
                c.getIdentificationNumber() + ";" +
                c.getName() + ";" +
                c.getAge() + ";" +
                c.getAddress() + ";" +
                status + ";" +
                totalRentings
            );
            writer.newLine();
        }
    }
}
}
