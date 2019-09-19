package automatatest;

import java.io.IOException;
import java.util.Scanner;

public class AutomataTest {
    public static void main(String[] args) {
        Automata automata = new Automata();
        Scanner leer = new Scanner(System.in);
        boolean ban_archivo = false;
        try {
            ban_archivo = automata.leerAutomata("FORMATO_AFD.txt");
            if (ban_archivo) {
                String cadena = leer.nextLine();
                boolean ban = automata.comprobarCadena(cadena);
                if (ban) {
                    System.out.println("LA CADENA PERTENECE AL LENGUAJE");
                } else {
                    System.out.println("LA CADENA NO PERTENECE AL LENGUAJE");
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
