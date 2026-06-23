package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SistemaUsuarios {

    public static Scanner scanner = new Scanner(System.in);

    static List<Usuario> usuariosList = new ArrayList<>();

    static Usuario usuarioLogin = null;

    // *** carga de usuarios *** //
    static public void cargarUsuarios(){
        usuariosList.add(new Admin("María", "Gonzalez", "maria.gonzalez@email.com", "Uruguay", "maria123"));
        usuariosList.add(new Admin("Diego", "Alvarez", "diego.alv@email.com", "Uruguay", "diego123"));
        usuariosList.add(new Tester("Martin", "Alvarez", "martin.alv@email.com", "Uruguay", "diego123", "junior"));
        usuariosList.add(new Tester("Martin", "Alvarez", "martin.alv@email.com", "Uruguay", "diego123", "junior"));
        usuariosList.add(new Tester("Martin", "Alvarez", "martin.alv@email.com", "Uruguay", "diego123", "junior"));
    }


    // *** registro *** //
    public static void registrarUsuario() {

        String name = "";
        String lastName = "";
        String email = "";
        String password = "";
        String country = "";

        System.out.print("Ingrese nombre: ");
        name = scanner.nextLine();

        System.out.print("Ingrese apellido: ");
        lastName = scanner.nextLine();

        System.out.print("Ingrese email: ");
        email = scanner.nextLine();

        System.out.print("Ingrese contraseña: ");
        password = scanner.nextLine();

        System.out.println("Confirme contraseña");
        String passwordConfirmation = scanner.nextLine();

        if(!passwordConfirmation.equals(password)){
            System.out.println("Contraseñas no coinciden :(");
            return;
        }

        System.out.print("Ingrese país: ");
        country = scanner.nextLine();

        usuariosList.add(new Admin(name, lastName, email, password, country));
        System.out.println("Usuario registrado correctamente.");
    }

    // *** buscar usuario por email *** //
    public static Usuario buscarUsuario(){
        System.out.println("Ingrese email del usuario");
        System.out.println("0. Cancelar");
        String email = scanner.nextLine();
        while(email.isEmpty()){
            System.out.println("Ingrese email del usuario");
            System.out.println("0. Cancelar");
            email = scanner.nextLine();
        }
        if(email.equals("0")){
            System.out.println("Cancelando...");
            return null;
        }

        String emailBuscar = email;
        Usuario usuario =  usuariosList.stream().filter(u -> u.getEmail().equals(emailBuscar))
                                                                                    .findFirst()
                                                                                    .orElse(null);
        if(usuario == null){
            System.out.println("Usuario no encontrado");
        }
        return usuario;
    }

    // *** buscar usuario por email *** //
    public static String buscarUsuarioPorEmail(){

        Usuario usuario = buscarUsuario();
        if(usuario != null){
            return usuario.toString();
        }
        return "Usuario no encontrado";
    }

    // *** buscar usuario por nombre *** //
    public static String buscarUsuarioPorNombre(){
        System.out.println("Ingrese primer nombre del usuario");
        System.out.println("0. Cancelar");
        String nombre = scanner.nextLine();
        while(nombre.isEmpty()){
            System.out.println("Ingrese nombre del usuario");
            System.out.println("0. Cancelar");
            nombre = scanner.nextLine();
        }
        if(nombre.equals("0")){
            System.out.println("Cancelando...");
            return null;
        }

        String nombreBuscar = nombre;
        List<Usuario> usuariosPorNombre = new ArrayList<>();
        for(Usuario u : usuariosList){
            if(u.getName().contains(nombreBuscar)){
                usuariosPorNombre.add(u);
            }
        }

        if(usuariosPorNombre.isEmpty()){
            return "Usuario no encontrado";
        }
        return usuariosPorNombre.toString();
    }


    // *** login *** //
    public static void loginUsuario() {

        System.out.print("Ingrese email: ");
        String email = scanner.nextLine();

        System.out.print("Ingrese contraseña: ");
        String password = scanner.nextLine();

        for (Usuario u : usuariosList) {
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
        for (Usuario u : usuariosList) {
            if(u != null){
                System.out.println(u.getName() + " " + u.getLastName() + ", " + u.getEmail() + ", " + u.getCountry() + ". ");
            }

        }
    }

    // *** cambiar contraseña *** //
    public static void ejecutarCambioDeContrasena() {
        Usuario usuarioObjetivo = null;

        if(usuarioLogin != null){
            usuarioObjetivo = buscarUsuario();
            if(usuarioObjetivo == null){
                System.out.println("Usuario no encontrado.");
                return;
            }
        }

        System.out.println("Ingrese nueva contraseña");
        String nuevaContrasenia = scanner.nextLine();

        System.out.println("Confirme contraseña");
        String passwordConfirmation = scanner.nextLine();

        if(!passwordConfirmation.equals(nuevaContrasenia)){
            System.out.println("Contraseñas no coinciden :(");
            return;
        }

        try {
            usuarioLogin.cambiarContrasena(nuevaContrasenia, usuarioObjetivo);
        } catch (SecurityException e) {
            System.err.println("Error de Permisos: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error del sistema: " + e.getMessage());
        }
    }
}
