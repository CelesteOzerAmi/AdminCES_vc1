package org.example;

import java.util.Scanner;

public class Main {

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int opcion = -1;

        while (opcion != 0){
            System.out.println("Seleccione una opcion!");
            System.out.println("1: Registro");
            System.out.println("2: Login");
            System.out.println("0: Salir");

            opcion = scanner.nextInt();

            switch (opcion){
                case 1:
                    System.out.println("Registro");
                    registrarUsuario();
                    break;
                case 2:
                    System.out.println("Login");
                    loginUsuario();
                    break;
                case 0:
                    System.out.println("Salir");
                    break;
                default:
                    System.out.println("Opcion invalida!!!!!");
                    break;
            }

        }

    }

    public static Usuario usuarioRegistrado = null;

    public static void registrarUsuario() {

        if (usuarioRegistrado != null) {
            System.out.println("Ya existe un usuario registrado.");
            return;
        }

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

        System.out.print("Ingrese país: ");
        usuario.setCountry(scanner.nextLine());

        usuarioRegistrado = usuario;

        System.out.println("Usuario registrado correctamente.");
    }

    public static void loginUsuario() {

        if (usuarioRegistrado == null) {

            System.out.println("No existe un usuario registrado.");
            return;

        }

        System.out.print("Ingrese email: ");
        String email = scanner.nextLine();

        System.out.print("Ingrese contraseña: ");
        String password = scanner.nextLine();

        if (email.equals(usuarioRegistrado.getEmail())
                && password.equals(usuarioRegistrado.getPassword())) {

            System.out.println("Login exitoso. Bienvenido " + usuarioRegistrado.getName());

        } else {

            System.out.println("Credenciales incorrectas.");

        }
    }
}




