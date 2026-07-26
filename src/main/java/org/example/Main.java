package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws EmailExisteException, UsuarioNoEncontradoException {

        SistemaUsuarios sistema = SistemaUsuarios.getInstance();
        sistema.cargarUsuarios();

        int opcion = -1;
        sistema.cargarUsuarios();

        while (opcion != 0){
            System.out.println("Seleccione una opcion");
            if(!sistema.hayUsuarioLogueado()){
                System.out.println("1: Registro");
                System.out.println("2: Login");
                System.out.println("3: Cambiar contraseña");
                System.out.println("0: Salir");
            }
            if(sistema.esTesterLogueado()){
                System.out.println("7: Cerrar sesión");
                System.out.println("0: Salir");
            }
            if(sistema.esAdminLogueado()) {
                System.out.println("1. Alta usuario tester");
                System.out.println("3: Cambiar contraseña");
                System.out.println("4: Cambiar email");
                System.out.println("5: Ver usuarios");
                System.out.println("6: Buscar usuarios por email");
                System.out.println("7: Cerrar sesión");
                System.out.println("0: Salir");
            }

            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opción incorrecta. Ingrese un número.");
                continue;
            }

            switch (opcion){
                case 1:
                    System.out.println("Registro");
                    sistema.registrarUsuario();
                    break;
                case 2:
                    System.out.println("Login");
                    sistema.loginUsuario();
                    break;
                case 3:
                    System.out.println("Cambiar contraseña");
                    sistema.ejecutarCambioDeContrasena();
                    break;
                case 4:
                    System.out.println("Cambiar email");
                    sistema.cambiarEmail();
                    break;
                case 5:
                    System.out.println("Ver usuarios");
                    sistema.verUsuarios();
                    break;
                case 6:
                    System.out.println("Buscar usuarios por email");
                    sistema.buscarUsuarioPorEmail();
                    break;
                case 7:
                    System.out.println("Cerrar sesión");
                    sistema.cerrarSesion();
                    break;
                case 0:
                    System.out.println("Salir");
                    break;
                default:
                    System.out.println("Opcion invalida");
                    break;
            }

        }

    }

}




