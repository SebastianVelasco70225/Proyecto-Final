package src.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import src.domain.StoreMovie;

public class FileManager {
    private static final String File_Name = "src/data/store_data.dat";

    // Method for save
    public static void  saveStore(StoreMovie store) throws IOException{

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(File_Name))){
            oos.writeObject(store);
        }
    }

    // Method for load

    public static StoreMovie loadStore() throws IOException, ClassNotFoundException {
        File file = new File(File_Name);
        if(!file.exists()){
            return null;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new  FileInputStream(File_Name))){
            return (StoreMovie) ois.readObject();
        }
    }
}