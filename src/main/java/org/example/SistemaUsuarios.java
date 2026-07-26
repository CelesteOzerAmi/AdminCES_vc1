package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SistemaUsuarios {

    public Scanner scanner = new Scanner(System.in);

    private static SistemaUsuarios instancia;

    private final List<Usuario> usuariosList;

    private Usuario usuarioLogin;

    private SistemaUsuarios() {
        this.usuariosList = new ArrayList<>();
        this.usuarioLogin = null;
    }

    public static SistemaUsuarios getInstance() {
        if (instancia == null) {
            instancia = new SistemaUsuarios();
        }
        return instancia;
    }

    public boolean hayUsuarioLogueado() {
        return this.usuarioLogin != null;
    }

    public boolean esAdminLogueado() {
        return this.usuarioLogin instanceof Admin;
    }

    public boolean esTesterLogueado() {
        return this.usuarioLogin instanceof Tester;
    }

    // *** carga de usuarios *** //
     public void cargarUsuarios(){
        usuariosList.add(new Admin("María", "Gonzalez", "maria.gonzalez@email.com", "Uruguay", "maria123"));
        usuariosList.add(new Admin("Diego", "Alvarez", "diego.alv@email.com", "Uruguay", "diego123"));
        usuariosList.add(new Tester("Martin", "Alvarez", "martin.alv@email.com", "Uruguay", "diego123", "junior"));
        usuariosList.add(new Tester("Jose", "Alvarez", "jose.alv@email.com", "Uruguay", "diego123", "senior"));
        usuariosList.add(new Tester("Juana", "Alvarez", "juana.alv@email.com", "Uruguay", "diego123", "junior"));
    }

    // *** registro *** //
    public  void registrarUsuario() throws EmailExisteException {

        String name = "";
        String lastName = "";
        String email = "";
        String password = "";
        String country = "";

        System.out.print("Ingrese nombre: ");
        name = scanner.nextLine();
        while(name.isBlank()){
            System.out.println("Nombre es un campo obligatorio");
            System.out.print("Ingrese nombre: ");
            name = scanner.nextLine();
        }

        System.out.print("Ingrese apellido: ");
        lastName = scanner.nextLine();
        while(lastName.isBlank()){
            System.out.println("Apellido es un campo obligatorio");
            System.out.print("Ingrese apellido: ");
            lastName = scanner.nextLine();
        }

        System.out.print("Ingrese email: ");
        email = scanner.nextLine();

        if(!email.contains("@") || !email.contains(".")){
            System.out.println("Formato incorrecto. Reintente");
            return;
        }

        String finalEmail = email;

        try {
            if(usuariosList.stream().anyMatch(u -> u.getEmail().equalsIgnoreCase(finalEmail))){
                throw new EmailExisteException("Email ya registrado");
            }
        } catch (EmailExisteException e) {
            System.out.println("Email ya registrado. Intente nuevamente con otro email");
            return;
        }

        System.out.print("Ingrese contraseña (minimo 8 caracteres): ");
        password = scanner.nextLine();

        while(password.length() < 8){
            System.out.println("Contraseña debe tener al menos 8 caracteres");
            System.out.println("Ingrese contraseña");
            password = scanner.nextLine();
        }

        System.out.print("Confirme contraseña: ");
        String passwordConfirmation = scanner.nextLine();

        if(!passwordConfirmation.equals(password)){
            System.out.println("Contraseñas no coinciden");
            return;
        }

        System.out.print("Ingrese país: ");
        country = scanner.nextLine();

        // si usuario logueado es admin, se crea un usuario de tipo tester //
        if(usuarioLogin instanceof Admin){
            String rol = "";
            while(rol.isBlank()){
                System.out.println("Ingrese rol de usuario tester");
                System.out.println("1. Junior | 2. Senior | 3. Líder | 0. Cancelar");
                String option = scanner.nextLine();
                switch (option){
                    case "1":
                        rol = "Junior";
                        break;
                    case "2":
                        rol = "Senior";
                        break;
                    case "3":
                        rol = "Lider";
                        break;
                    case "0":
                        System.out.println("Operación cancelada");
                        break;
                    default:
                        System.out.println("Opción incorrecta");
                        break;
                }
                if(option.equals("0")){
                    return;
                }
            }
            System.out.println("Tester creado correctamente");
            usuariosList.add(new Tester(name, lastName, email, country, password, rol));
            return;
        }

        // si no existe usuario logueado, se crea usuario admin //
        usuariosList.add(new Admin(name, lastName, email, country, password));
        System.out.println("Usuario registrado correctamente.");
    }

    // *** buscar usuario por email *** //
    public  Usuario buscarUsuario(String email) throws UsuarioNoEncontradoException{
        try {
            return usuariosList.stream().filter(u -> u.getEmail().equals(email))
                   .findFirst()
                   .orElseThrow(() -> new UsuarioNoEncontradoException("Usuario no encontrado"));
        } catch (UsuarioNoEncontradoException e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Por favor, intente con otro correo.");
            return null;
        }
    }

    // *** buscar usuario por email *** //
    public  void buscarUsuarioPorEmail() throws UsuarioNoEncontradoException {
        if(usuarioLogin == null || usuarioLogin instanceof Tester){
            System.out.println("Debe ser administrador para buscar usuarios");
            return;
        }

        System.out.println("Ingrese email del usuario | 0. Cancelar");
        String email = scanner.nextLine();
        while(email.isEmpty()){
            System.out.println("Ingrese email del usuario | 0. Cancelar");
            email = scanner.nextLine();
        }

        if(email.equals("0")){
            System.out.println("Cancelando...");
            return;
        }

        Usuario usuarioEncontrado = buscarUsuario(email);
        if(usuarioEncontrado != null){
            System.out.println(usuarioEncontrado.toString());
        }

    }

    // *** login *** //
    public  void loginUsuario() throws UsuarioNoEncontradoException {
        if(usuarioLogin != null){
            System.out.println("Usuario ya logueado");
            return;
        }

        System.out.print("Ingrese email: ");
        String email = scanner.nextLine();
        Usuario usuarioLog =  buscarUsuario(email);
        if(usuarioLog == null){
            return;
        }

        System.out.print("Ingrese contraseña: ");
        String password = scanner.nextLine();
        if(usuarioLog.getPassword().equals(password)){
            usuarioLogin = usuarioLog;
            System.out.println("Login exitoso. Bienvenido " + usuarioLogin.getName());
        } else  {
            System.out.println("Credenciales incorrectas");
        }
    }

    // *** listado para ordenar usuarios por clase *** //
    public  List<Usuario> obtenerUsuariosOrdenadosPorClase(List<Usuario> usuariosList) {
        return usuariosList.stream()
                .sorted((u1, u2) -> {
                    if (u1 instanceof Admin) return -1;
                    if (u2 instanceof Admin) return 1;
                    return 0;
                })
                .collect(Collectors.toList());
    }

    // *** ver usuarios *** //
    public  void verUsuarios(){
        if(usuarioLogin == null || usuarioLogin instanceof Tester){
            System.out.println("Debe ser administrador para ver usuarios");
            return;
        }

        for (Usuario u : obtenerUsuariosOrdenadosPorClase(usuariosList).stream().toList()){
            System.out.println(u.toString());
        }
    }

    // *** cambiar contraseña *** //
    public  void ejecutarCambioDeContrasena() throws UsuarioNoEncontradoException {
        Usuario usuarioObjetivo = null;

        if(usuarioLogin != null){
            System.out.println("Ingrese email del usuario");
            String emailUsuario = scanner.nextLine();
            usuarioObjetivo = buscarUsuario(emailUsuario);

            if(usuarioObjetivo == null){
                return;
            }

            System.out.println("Ingrese nueva contraseña. Minimo 8 caracteres");
            String nuevaContrasenia = "";
            nuevaContrasenia = scanner.nextLine();

            if(nuevaContrasenia.length() < 8){
                System.out.println("Contraseña debe tener al menos 8 caracteres");
                return;
            }

            System.out.println("Confirme contraseña");
            String passwordConfirmation = scanner.nextLine();

            if(!passwordConfirmation.equals(nuevaContrasenia)){
                System.out.println("Contraseñas no coinciden");
                return;
            }

            try {
                usuarioLogin.cambiarContrasena(nuevaContrasenia, usuarioObjetivo);
            } catch (SecurityException e) {
                System.err.println("Error de permisos: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("Error del sistema: " + e.getMessage());
            }
        }

        if(usuarioLogin == null){
            System.out.println("Ingrese su email");
            String email = scanner.nextLine();
            Usuario usuarioEmail = buscarUsuario(email);
            if(usuarioEmail == null){
                return;
            }
            System.out.println("Confirme usuario");
            System.out.println(usuarioEmail.toString());
            System.out.println("¿Usuario es correcto? 1. Sí | 0. No");
            if(!scanner.nextLine().equals("1")){
                System.out.println("Operación cancelada");
                return;
            }
            System.out.println("Ingrese nueva contraseña. Minimo 8 caracteres");
            String nuevaContrasenia = scanner.nextLine();

            if(nuevaContrasenia.length() < 8){
                System.out.println("Contraseña debe tener al menos 8 caracteres");
                return;
            }

            System.out.println("Confirme contraseña");
            String passwordConfirmation = scanner.nextLine();

            if(!passwordConfirmation.equals(nuevaContrasenia)){
                System.out.println("Contraseñas no coinciden");
                return;
            }
            usuarioEmail.cambiarContrasena(nuevaContrasenia, null);
        }
    }

    // *** cambiar email *** //
    public  void cambiarEmail() throws EmailExisteException {
        if(usuarioLogin instanceof Admin){
            System.out.println("Ingrese nuevo email");
            String nuevoEmail = scanner.nextLine();
            if(!nuevoEmail.contains("@") || !nuevoEmail.contains(".")){
                System.out.println("Formato incorrecto. Reintente");
                return;
            }

            String finalEmail = nuevoEmail;
            Usuario adminEmail = usuariosList.stream().filter(a -> a.getEmail().equalsIgnoreCase(finalEmail)).findFirst().orElse(null);
            if(adminEmail != null){
                throw new EmailExisteException("Email ya se encuentra registrado");
            }
            System.out.println("Email cambiado correctamente");
        } else {
            System.out.println("Debe estar logueado para cambiar email");
        }
    }

    // *** cerrar sesión *** //
    public  void cerrarSesion(){
        if(usuarioLogin == null){
            System.out.println("Debe estar logueado para cerrar sesión");
            return;
        }
        System.out.println("¿Confirmar cierre de sesión? 1. Confirmar | 0. Cancelar");
        if(scanner.nextLine().equals("1")){
            System.out.println("Cerrando sesión...");
            usuarioLogin = null;
        }
    }
}
