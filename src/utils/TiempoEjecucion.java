package utils;

import java.io.FileWriter;
import java.io.IOException;

public class TiempoEjecucion {
    
    public static long iniciar(){
        return System.nanoTime();
    }

    public static long finalizar(long inicio){
        return System.nanoTime() - inicio;
    }

    public static void guardarCSV(String algoritmo, long tiempo, String rutaArchivo){
        try(FileWriter writer = new FileWriter(rutaArchivo, true)){
            writer.write(algoritmo + "," + tiempo + "\n");
        } catch (IOException e){
            System.out.println("Error al escribir CSV: " + e.getMessage());
        }
    }
}
