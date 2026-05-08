package org.example;

public class Usuario {

    private String name;
    private String email;
    private String password;

    public Usuario(String name, String email, String password){
        this.password = password;
        this.email = email;
        this.password = password;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
