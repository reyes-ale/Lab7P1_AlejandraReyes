//fila 2 asiento 5
package lab7p1_alejandrareyes;

import java.util.Scanner;
import java.util.Random;

/**
 *
 * @author aleja
 */
public class Lab7P1_AlejandraReyes {

    static Scanner leer = new Scanner(System.in);
    static Random rand = new Random();

    public static void main(String[] args) {
        System.out.println("* * * * * M E N U * * * * * ");
        System.out.println("1. Tres en raya");
        System.out.println("2. Puntos de silla");
        System.out.println("3. Salir del programa");
        System.out.print("Ingrese una opcion: ");
        int opcion = leer.nextInt();

        while (opcion > 0 && opcion < 3) {
            switch (opcion) {
                case 1:
                    char resp = 's';
                    char[][] tablero = new char[3][3];
                    tablero = generartablero(tablero);
                    while (resp == 's' || resp == 'S') {
                        boolean gano = false;
                        char jugador = 'X';
                        int fila, col;
                        boolean valido;
                        char o='0';
                        char xo='X';

                        
                        System.out.println();
                        System.out.println("---Bienvenido al tres en raya---");
                        System.out.println("Tablero actual");
                        imprimirchar(tablero);

                        while (gano == false) {

                            System.out.println();
                            System.out.println("Es el turno de: " + jugador);
                            
                            if (jugador=='X'){
                                System.out.print("Ingrese la fila (0, 1, 2): ");
                                fila = leer.nextInt();
                                System.out.print("Ingrese la columna (0, 1, 2): ");
                                col = leer.nextInt();
                                
                                while (fila < 0 || fila > 2 || col < 0 || col > 2 || (valido = verificarposval(tablero, fila, col)) == false) {
                                    System.out.println("Posicion no valida o posicion ocupada. Intentelo de nuevo");
                                    System.out.print("Ingrese la fila (0, 1, 2): ");
                                    fila = leer.nextInt();
                                    System.out.print("Ingrese la columna (0, 1, 2): ");
                                    col = leer.nextInt();
                                }
                                if ((valido = verificarposval(tablero, fila, col)) == true){
                                    System.out.println("El usuario ha elegido la posicion: " + "(" + fila + "," + col + ")");
                                    System.out.println("Tablero actual");
                                    char x='x';
                                    tablero = llenarx(fila, col, valido,tablero,x);
                                    imprimirchar(tablero);
                                }
                                if (verificarvic(tablero,xo)==true){
                                System.out.println("El ganador es X");
                                break;
                            }
                            
                            }
                            else if (jugador=='0'){
                                fila = rand.nextInt(3);
                                col = rand.nextInt(3);
                                while((valido = verificarposval(tablero, fila, col)) == false){
                                   fila = rand.nextInt(3);
                                   col = rand.nextInt(3);  
                                }
                                tablero = llenarx(fila, col, valido,tablero,o);
                                System.out.println("La maquina ha elegido la posicion: " + "(" + fila + "," + col + ")");
                                System.out.println("Tablero actual");
                                imprimirchar(tablero); 
                                
                                if (verificarvic(tablero,o)==true){
                                System.out.println("El ganador es 0");
                                break;
                            }

                                
                                
                            }
                               
                            
                            
                            if (jugador=='X'){
                                jugador='0';
                            }
                            else {
                                jugador='X';
                                        } 
                            
                            if (gano==true){
                                break;
                            }
                            
                            }
                            

                       
                         System.out.println("Desea jugar de nuevo [s/n]: ");
                        resp = leer.next().charAt(0);   

                        }
                    
                    break;

                case 2:
                    System.out.print("Ingrese filas: ");
                    int filas = leer.nextInt();
                    System.out.print("Ingrese columnas: ");
                    int colum = leer.nextInt();
                    int[][] matriz = new int[filas][colum];
                    matriz = generamatal(filas, colum);
                    imprimir(matriz);
                    int menor=menor(matriz,filas);
                    int mayor = mayor(matriz,colum);
                            
                    encontrarpunsilla(matriz,menor, mayor,filas,colum);
                    break;
            }
            System.out.println();
            System.out.println("* * * * * M E N U * * * * * ");
            System.out.println("1. Tres en raya");
            System.out.println("2. Puntos de silla");
            System.out.println("3. Salir del programa");
            System.out.print("Ingrese una opcion: ");
            opcion = leer.nextInt();
            }
            

        }
    

    public static char[][] generartablero(char[][] matriz) {
        matriz = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                matriz[i][j] = ' ';

            }
        }
        return matriz;

    }

    public static void imprimirchar(char[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if (i == 0 && j == 0 || i == 1 && j == 0 || i == 2 && j == 0) {
                    System.out.print("[" + matriz[i][j] + ",");
                } else if (i == 0 && j == 2 || i == 1 && j == 2 || i == 2 && j == 2) {
                    System.out.print("," + matriz[i][j] + "]");
                } else {
                    System.out.print(matriz[i][j] + "");
                }

            }
            System.out.println("");
        }
    }

    public static void imprimir(int[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println("");

        }

    }

    public static char[][] llenarx(int fila, int col, boolean valido, char [][] tablero, char xo) {
        if (valido == true) {
            tablero[fila][col] = xo;
        } 
        return tablero;
    }

    
    public static boolean verificarposval(char[][] tablero, int fila, int columna) {
        boolean valid = false;
        if (tablero[fila][columna] == ' ') {
            valid = true;
        } else if (tablero[fila][columna] != 'X' || tablero[fila][columna] != 'Y') {
            valid = false;

        }
        return valid;
    }

    public static boolean verificarvic(char[][] tablero, char xo) {
        boolean gano = false;
        for (int i = 0; i < tablero.length; i++) {
            if ((tablero[0][i] != ' ' && tablero[0][i] == tablero[1][i] && tablero[0][i] == tablero[2][i]) || tablero[i][0] != ' ' && tablero[i][0] == tablero[i][1] && tablero[i][0] == tablero[i][2]) {
                return true;
            }
        }
            
        if ((tablero[0][0] == tablero[1][1] && tablero[0][0] == tablero[2][2] && tablero[0][0] != ' ') || (tablero[0][2] == tablero[1][1] && tablero[0][2] == tablero[2][0] && tablero[0][2] != ' ')) {
            return true; 
        }
        
        return gano;
    }
    
   

    public static int[][] generamatal(int fila, int col) {
        int[][] temporal = new int[fila][col];

        for (int i = 0; i < fila; i++) {
            for (int j = 0; j < col; j++) {

                temporal[i][j] = rand.nextInt(99);

            }
        }
        return temporal;

    }

    public static int menor (int [][] matriz, int fila){
       
        int menor=100;
        for (int i=0; i <matriz[0].length; i++){
            if (matriz[i][fila]<menor){
                menor=matriz[i][fila];
                
                
            }
        }
        return menor;
        
    }
    
    public static int mayor (int [][] matriz, int col){
        int [] arreglo = new int [2];
        int mayor=-100;
        for (int i=0; i <matriz.length; i++){
            if (matriz[i][col]>mayor){
                mayor=matriz[i][col];
                
                
            }
        }
        return mayor;
        
    }
    
    public static void encontrarpunsilla (int [][] matriz, int menor, int mayor, int fila, int col){
        
        for (int i = 0; i < fila; i++) {
            for (int j = 0; j < col; j++) {
                if (mayor==menor){
                    System.out.println("Punto de silla: " +i+","+j);
                }

            }
        }
        
    }
}


