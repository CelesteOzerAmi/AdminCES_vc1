package org.example;

public class Tester extends Usuario{

    private String rol;

    public Tester(String name, String lastName, String email, String country, String password, String rol){
        super(name, lastName, email, country, password);
        this.rol = rol;
    }
}
