package org.example;

import java.util.Scanner;

public class Main {

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int opcion = -1;
        SistemaUsuarios.cargarUsuarios();
        while (opcion != 0){
            System.out.println("Seleccione una opcion!");
            System.out.println("1: Registro");
            System.out.println("2: Login");
            System.out.println("3: Ver usuarios");
            System.out.println("0: Salir");

            opcion = scanner.nextInt();

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
                    System.out.println("Ver usuarios");
                    SistemaUsuarios.verUsuarios();
                    break;
                case 0:
                    System.out.println("Salir");
                    break;
                default:
                    System.out.println("Opcion invalida :/");
                    break;
            }

        }

    }

}




