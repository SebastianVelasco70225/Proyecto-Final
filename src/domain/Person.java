package src.domain;

import java.io.Serializable;

public class Person implements Serializable{

    private static final long serialVersionUID = 1L;

    // Atributes 
    private int identificationNumber;
    private String name;
    private String address;
    private int age;

    // Constructor
    public Person(int id, int age, String name, String adress){
        setIdentificationNumber(id);
        setName(name);
        setAddress(adress);
        setAge(age);
    }

    // Getters and Setters
    public int getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(int identificationNumber) {
        if(identificationNumber<0){
            throw new IllegalArgumentException("Número de identificación inválido");
        }
        this.identificationNumber = identificationNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.isBlank()){
            throw new IllegalArgumentException("El nombre no puede ser vacío");
        }
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String adress) {
        if(adress.isBlank()){
            throw new IllegalArgumentException("La dirección no puede ser vacío");
        }
        this.address = adress;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age<0){
            throw new IllegalArgumentException("Edad inválida");
        }
        this.age = age;
    }

}
