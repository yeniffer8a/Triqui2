/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.mycompany.triqui;

import java.util.Scanner;

/**
 *
 * @author Usuario
 */
public class Triqui {

    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in);
        int elegir;
        char[][] matriz = new char[3][3];
        System.out.println("Bienvenido al juego \n");

        do {
            System.out.println("Escoja una opci�n para empezar el juego: \n 1. usuario1 vs usuario2 \n 2. usuario vs maquina \n 3. Puntajes \n 4.Salir de Juego");
            elegir = leer.nextInt();// variable que guarda la informaci�n de que opci�n del juego elige el usuario
        } while (elegir < 1 || elegir > 4);//fin haga mientras
        
        switch (elegir) {
            case 1:
                Multijugador(matriz);
                break;

            case 2:
                Desafio(matriz);
                break;

            case 3:
                Puntaje(matriz);
                break;

            default:
                Salir(matriz);

       
            }//finsegun

    }//finprincipal

    public static void Multijugador(char tablero[][]) {
        char J1 = 'X';
        char J2 = 'O';
        char guion = '-';
        boolean turno = true;//true j1, false j2
        rellenarMatriz(tablero, guion);
        int fila, columna;
        boolean posvalida, correcto;

        while  (!fin_partida(tablero, guion)) {

            do {
                mostrar_Turno_Actual(turno);

                mostrarMatriz(tablero);

                correcto = false;
                if (turno) {
                    fila = pedirInteger("Dame la fila");

                    columna = pedirInteger("Dame la columna");
                    posvalida = validar_posicion(tablero, fila, columna);
                } else {
                    fila = (int) (Math.random() * (3 - 1 + 1) + 1);

                    columna = (int) (Math.random() * (3 - 1 + 1) + 1);
                    posvalida = validar_posicion(tablero, fila, columna);
                }//finsi

                if (posvalida) {

                    if (!Hay_valor_posicion(tablero, fila, columna, guion)) {

                        correcto = true;
                    } else {

                        System.out.println("Ya hay un marca");

                    }

                } else {

                    System.out.println("La posici�n no es valida");

                }

            } while (!correcto);//fin haga mientras

            if (turno) {

                insertarEn(tablero, fila, columna, J1);

            } else {

                insertarEn(tablero, fila, columna, J2);

            }

            turno = !turno; //Para cambiar el turno. Cuando se llegue al final, que se cambie el turno
        }//finmientras

        mostrarMatriz(tablero);
        mostrarganador(tablero, J1, J2, guion);

    }//M�todo para poner las marcas, valida y muestra quien gana

    public static void insertarEn(char[][] matriz, int fila, int columna, char simbolo) {

        matriz[fila][columna] = simbolo;
    }//fininsertar marca

    public static void mostrarganador(char[][] matriz, char J1, char J2, char simDef) {

        char simbolo = coincidenciaLineal(matriz, simDef);

        if (simbolo != simDef) {

            if (simbolo == J1) {

                System.out.println("Ha ganado jugador 1");
            } else {
                System.out.println("Ha ganado jugador 2");
            }

            return;
        }

        simbolo = coincidenciaColumna(matriz, simDef);

        if (simbolo != simDef) {

            if (simbolo == J1) {

                System.out.println("Ha ganado jugador 1");
            } else {
                System.out.println("Ha ganado jugador 2");
            }

            return;
        }

        simbolo = coincidenciaDiagonal(matriz, simDef);

        if (simbolo != simDef) {

            if (simbolo == J1) {

                System.out.println("Ha ganado jugador 1");
            } else {
                System.out.println("Ha ganado jugador 2");
            }
        }

        System.out.println("Hay empate");
    }//finmetodo mostrar ganador	

    public static void rellenarMatriz(char[][] matriz, char simbolo) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {

                matriz[i][j] = simbolo;

            }

        }

    }//fin metodo validar rellenarmatriz

    public static void mostrarMatriz(char[][] matriz) {

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {

                if (j != 2) {
                    System.out.print("[" + matriz[i][j] + "] ");
                } else {
                    System.out.println("[" + matriz[i][j] + "]");
                }//Imprime matriz vac�a

            }

        }

    }//fin m�todo mostratMatriz

    public static boolean validar_posicion(char[][] tablero, int fila, int columna) {

        if (fila >= 0 && fila < tablero.length && columna >= 0 && columna < tablero.length) {
            return true;
        }
        return false;
    }//finmetodo validar posicion

    public static boolean Hay_valor_posicion(char[][] matriz, int fila, int columna, char simboloDef) {

        if (matriz[fila][columna] != simboloDef) {

            return true;
        }
        return false;

    }//fin metodo hayvalor posicion

    public static boolean matrizllena(char[][] matriz, char simboloDef) {

        for (int i = 0; i < matriz.length; i++) {

            for (int j = 0; j < matriz[0].length; j++) {

                if (matriz[i][j] == simboloDef) {

                    return false;
                }

            }

        }

        return true;

    }//validar si matriz esta llena

    public static boolean fin_partida(char[][] matriz, char simboloDef) {
        if (matrizllena(matriz, simboloDef) || coincidenciaLineal(matriz, simboloDef) != simboloDef
                || coincidenciaColumna(matriz, simboloDef) != simboloDef
                || coincidenciaDiagonal(matriz, simboloDef) != simboloDef) {
            return true;
        }
        return false;
    }//fin metodo finpartida

    public static void mostrar_Turno_Actual(boolean turno) {
        if (turno) {
            System.out.println("Le toca al jugador 1");
        } else {
            System.out.println("Le toca al jugador 2");
        }
    }//finmetodo mostrar turno

    public static int pedirInteger(String mensaje) {
        int dato;
        Scanner leer = new Scanner(System.in);
        System.out.println(mensaje);
        dato = leer.nextInt();
        return dato;

    }//pedir donde quiere poner la marca

    public static char coincidenciaLineal(char[][] matriz, char simboloDef) {

        char simbolo;

        boolean coincidencia;

        for (int i = 0; i < matriz.length; i++) {

            coincidencia = true;

            simbolo = matriz[i][0];

            if (simbolo != simboloDef) {

                for (int j = 1; j < matriz[0].length; j++) {

                    if (simbolo != matriz[i][j]) {

                        coincidencia = false; //La idea es que no debe coincidir

                    }
                }

                if (coincidencia) {

                    return simbolo;

                }
            }
        }

        return simboloDef;
    }//fin metodo validar triqui lineal

    public static char coincidenciaColumna(char[][] matriz, char simboloDef) {

        char simbolo;

        boolean coincidencia;

        for (int j = 0; j < matriz.length; j++) {

            coincidencia = true;

            simbolo = matriz[j][0];

            if (simbolo != simboloDef) {

                for (int i = 1; i < matriz[0].length; i++) {

                    if (simbolo != matriz[i][j]) {

                        coincidencia = false; //La idea es que no debe coincidir

                    }
                }

                if (coincidencia) {

                    return simbolo;

                }
            }
        }

        return simboloDef;
    }//fin metodo validar triqui Columna

    public static char coincidenciaDiagonal(char[][] matriz, char simboloDef) {

        char simbolo;

        boolean coincidencia = true;

        //diagonal principal
        simbolo = matriz[0][0];

        if (simbolo != simboloDef) {

            for (int i = 1; i < matriz.length; i++) {

                if (simbolo != matriz[i][i]) {

                    coincidencia = false;
                }

                if (coincidencia) {
                    return simbolo;
                }

            }

        }//fin matriz diagonal

        //diagonal inversa
        simbolo = matriz[0][2];

        if (simbolo != simboloDef) {

            for (int i = 1, j = 1; i < matriz.length; i++, j--) {

                if (simbolo != matriz[i][j]) {

                    coincidencia = false;
                }

            }

            if (coincidencia) {
                return simbolo;
            }

        }

        return simboloDef;

    }//finmetodo validar triqui diagonal e inversa

    public static void Desafio(char matriz[][]) {

    }//Metodo usuario vs maquina

    public static void Puntaje(char matriz[][]) {

    }//metodo puntaje

    public static void Salir(char matriz[][]) {
        System.out.print("Gracias por jugar");
    }//fin metodo salir del juego
}

