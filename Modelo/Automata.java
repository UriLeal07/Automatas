package automatatest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Automata {
    String[] alfabeto;
    int[] estados;
    int estado_inicial;
    int[] estados_finales;
    int[][] matriz_T;
    
    public Automata() {
        
    }
    
    public boolean leerAutomata(String ruta) throws IOException, FileNotFoundException {
        String cadena = "";
        BufferedReader b = new BufferedReader(new FileReader(ruta));
        int cont = 0;
        
        while ((cadena = b.readLine()) != null && cont < 8) {
            String[] aux;
            cont++;
            switch (cont) {
                case 2:
                    aux = cadena.split(",");
                    alfabeto = new String[aux.length];
                    alfabeto = aux;
                    break;
                case 4:
                    aux = cadena.split(",");
                    estados = new int[aux.length];
                    for (int i = 0; i < estados.length; i++) {
                        estados[i] = Integer.parseInt(aux[i]);
                    }
                    break;
                case 6:
                    estado_inicial = Integer.parseInt(cadena);
                    break;
                case 8:
                    aux = cadena.split(",");
                    estados_finales = new int[aux.length];
                    for (int i = 0; i < estados_finales.length; i++) {
                        estados_finales[i] = Integer.parseInt(aux[i]);
                    }
                    break;
            }
        }
        
        if (cadena == null && cont < 8) {
            System.out.println("ERROR AL LEER EL ARCHIVO");
            return false;
        } else {
            matriz_T = new int[estados.length][alfabeto.length];
            for (int i = 0; i < estados.length; i++) {
                for (int j = 0; j < alfabeto.length; j++) {
                    matriz_T[i][j] = -1;
                }
            }
            while ((cadena = b.readLine()) != null) {
                String[] aux = cadena.split(",");
                String[] tmp = aux[0].split(" ");
                int col = -1;
                boolean ban = false;
                int i = 0;
                while (i < alfabeto.length && !ban) {
                    if (alfabeto[i].equals(tmp[1])) {
                        col = i;
                        ban = true;
                    }
                    i++;
                }
                matriz_T[Integer.parseInt(tmp[0])][col] = Integer.parseInt(aux[1]);
            }
            return true;
        }
    }
    
    public boolean comprobarCadena(String cadena) {
        int estado_actual = estado_inicial;
        for (int i = 0; i < cadena.length(); i++) {
            char aux = cadena.charAt(i);
            int j = 0;
            boolean ban = false;
            while (j < alfabeto.length && !ban) {
                if (alfabeto[j].equals(String.valueOf(aux))) {
                    if (matriz_T[estado_actual][j] != -1) {
                        estado_actual = matriz_T[estado_actual][j];
                        ban = true;
                    }
                }
                j++;
            }
            if (!ban) {
                return false;
            }
        }
        boolean ban = false;
        int i = 0;
        while (i < estados_finales.length && !ban) {
            if (estados_finales[i] == estado_actual) {
                ban = true;
            }
            i++;
        }
        return ban;
    }
}
