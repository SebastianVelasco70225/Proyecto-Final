package src.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import src.domain.StoreMovie;

public class FileManager {
    private static final String FILE_NAME = "src/data/store_data.dat";

    // Method for save
    public static void  saveStore(StoreMovie store) throws IOException{
        File file = new File(FILE_NAME);
        if (file.getParentFile() != null){
            file.getParentFile().mkdirs();
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))){
            oos.writeObject(store);
        }
    }

    // Method for load

    public static StoreMovie loadStore() throws IOException, ClassNotFoundException {
        File file = new File(FILE_NAME);
        if(!file.exists()){
            return null;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new  FileInputStream(FILE_NAME))){
            return (StoreMovie) ois.readObject();
        }
    }
}