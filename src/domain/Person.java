package src.domain;

import java.io.Serializable;

public class Person implements Serializable{

    private static final Long serialVersionUID = 1L;
    private int identificationNumber;
    private String name;
    private String address;
    private int age;

    public Person(int id, int age, String name, String adress){
        setIdentificationNumber(id);
        setName(name);
        setAddress(adress);
        setAge(age);
    }

    public int getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(int identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String adress) {
        this.address = adress;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
