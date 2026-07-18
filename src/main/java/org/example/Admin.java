package org.example;

import java.util.Scanner;

public class Admin extends Usuario {

    public Admin(String name, String lastName, String email, String country, String password) {
        super(name, lastName, email, country, password);
    }

    @Override
    public void cambiarContrasena(String nuevaContrasena, Usuario usuarioObjetivo){

        // *** si el admin cambió su propia contraseña *** //
        if (usuarioObjetivo == null || usuarioObjetivo.getEmail().equals(this.getEmail())) {
            this.setPassword(nuevaContrasena);
            System.out.println("Admin (" + this.getEmail() + ") cambió su propia contraseña.");
        }

        // *** si el admin cambió contraseña de otro usuario *** //
        else {
            usuarioObjetivo.setPassword(nuevaContrasena);
            System.out.println("Admin (" + this.getEmail() + ") cambió la contraseña del usuario: " + usuarioObjetivo.getEmail());
        }
    }

    @Override
    public String toString(){
        return this.getName() + " " + this.getLastName() +  ", " + this.getEmail()
                + ", " + this.getCountry() + ". Admin";
    }
}
