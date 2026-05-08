package org.example;

public class Usuario {

    private String name;
    private String lastName;
    private String email;
    private String country;
    private String password;

    public Usuario(String name, String lastName, String email, String country, String password){
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.country = country;
        this.password = password;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName(){
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry(){
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
