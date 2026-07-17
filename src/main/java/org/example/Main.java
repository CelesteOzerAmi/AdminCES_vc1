package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws EmailExisteException, UsuarioNoEncontradoException {

        int opcion = -1;
        SistemaUsuarios.cargarUsuarios();
        while (opcion != 0){
            System.out.println("Seleccione una opcion");
            if(SistemaUsuarios.usuarioLogin == null){
                System.out.println("1: Registro");
                System.out.println("2: Login");
                System.out.println("3: Cambiar contraseña");
                System.out.println("0: Salir");
            }
            if(SistemaUsuarios.usuarioLogin instanceof Tester){
                System.out.println("7: Cerrar sesión");
                System.out.println("0: Salir");
            }
            if(SistemaUsuarios.usuarioLogin instanceof Admin) {
                System.out.println("1. Alta usuario tester");
                System.out.println("3: Cambiar contraseña");
                System.out.println("4: Cambiar email");
                System.out.println("5: Ver usuarios");
                System.out.println("6: Buscar usuarios por email");
                System.out.println("7: Cerrar sesión");
                System.out.println("0: Salir");
            }

            try {
                opcion = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Opcion incorrecta. Intente nuevamente");
            }


            switch (opcion){
                case 1:
                    System.out.println("Registro");
                    SistemaUsuarios.registrarUsuario();
                    break;
                case 2:
                    System.out.println("Login");
                    SistemaUsuarios.loginUsuario();
                    break;
                case 3:
                    System.out.println("Cambiar contraseña");
                    SistemaUsuarios.ejecutarCambioDeContrasena();
                    break;
                case 4:
                    System.out.println("Cambiar email");
                    SistemaUsuarios.cambiarEmail();
                    break;
                case 5:
                    System.out.println("Ver usuarios");
                    SistemaUsuarios.verUsuarios();
                    break;
                case 6:
                    System.out.println("Buscar usuarios por email");
                    SistemaUsuarios.buscarUsuarioPorEmail();
                    break;
                case 7:
                    System.out.println("Cerrar sesión");
                    SistemaUsuarios.cerrarSesion();
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




