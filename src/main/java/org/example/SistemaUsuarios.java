package org.example;

import java.sql.Array;
import java.util.Arrays;
import java.util.Scanner;

public class SistemaUsuarios {

    public static Scanner scanner = new Scanner(System.in);

    public static Usuario[] usuarios = new Usuario[10];


    // *** carga de usuarios *** //
    static public void cargarUsuarios(){
        usuarios[0] = new Admin("María", "Gonzalez", "maria.gonzalez@email.com", "Uruguay", "maria123");
        usuarios[1] = new Admin("Diego", "Alvaez", "diego.alv@email.com", "Uruguay", "diego123");
        usuarios[2] = new Tester("Martin", "Alvarez", "martin.alv@email.com", "Uruguay", "diego123", "junior");
        usuarios[3] = new Tester("Martin", "Alvarez", "martin.alv@email.com", "Uruguay", "diego123", "junior");
        usuarios[4] = new Tester("Martin", "Alvarez", "martin.alv@email.com", "Uruguay", "diego123", "junior");
    }


    // *** registro *** //
    public static void registrarUsuario() {

        String name = "";
        String lastName = "";
        String email = "";
        String password = "";
        String country = "";

        Usuario usuario = new Usuario(name, lastName, email, password, country);

        System.out.print("Ingrese nombre: ");
        usuario.setName(scanner.nextLine());

        System.out.print("Ingrese apellido: ");
        usuario.setLastName(scanner.nextLine());

        System.out.print("Ingrese email: ");
        usuario.setEmail(scanner.nextLine());

        System.out.print("Ingrese contraseña: ");
        usuario.setPassword(scanner.nextLine());

        System.out.println("Confirme contraseña");
        String passwordConfirmation = scanner.nextLine();

        if(!passwordConfirmation.equals(usuario.getPassword())){
            System.out.println("Contraseñas no coinciden :(");
            return;
        }

        System.out.print("Ingrese país: ");
        usuario.setCountry(scanner.nextLine());

        int cantidadUsuarios = (int) Arrays.stream(usuarios).count();
        usuarios[cantidadUsuarios] = new Usuario(name, lastName, email, password, country);
        System.out.println("Usuario registrado correctamente.");
    }


    // *** login *** //
    public static void loginUsuario() {

        System.out.print("Ingrese email: ");
        String email = scanner.nextLine();

        System.out.print("Ingrese contraseña: ");
        String password = scanner.nextLine();

        Usuario usuarioLogin = null;
        for (Usuario u : usuarios) {
            if(u.getEmail().equals(email)){
                usuarioLogin = u;
            }
        }

        if (usuarioLogin != null && email.equals(usuarioLogin.getEmail())
                && password.equals(usuarioLogin.getPassword())) {

            System.out.println("Login exitoso. Bienvenido " + usuarioLogin.getName());

        } else {

            System.out.println("Credenciales incorrectas.");

        }
    }


    // *** ver usuarios *** //
    public static void verUsuarios(){
        for (Usuario u : usuarios) {
            if(u != null){
                System.out.println(u.getName() + " " + u.getLastName() + ", " + u.getEmail() + ", " + u.getCountry() + ". ");
            }

        }
    }
}
