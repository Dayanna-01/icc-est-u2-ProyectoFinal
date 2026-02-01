package models.persistence;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import models.graphs.Grafo;

public class GrafoLoader {
    
    public static Grafo cargarGrafo(String rutaArchivo){
        Grafo grafo = new Grafo();

        try(BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))){
            String linea;
            boolean leyendoNodos = false;
            boolean leyendoAristas = false;

            while ((linea = br.readLine()) != null) {
                linea = linea.trim();

                if (linea.equals("NODOS")) {
                    leyendoNodos = true;
                    leyendoAristas = false;
                    continue;
                }
                
                if (linea.equals("ARISTAS")) {
                    leyendoNodos = false;
                    leyendoAristas = true;
                    continue;
                }
                if (leyendoNodos && !linea.isEmpty()) {
                    String[] partes = linea.split(",");
                    String id = partes[0];
                    int x = Integer.parseInt(partes[1]);
                    int y = Integer.parseInt(partes[2]);

                    grafo.agregarNodo(id, x, y);
                }
                if (leyendoAristas && linea.isEmpty()) {
                    String[] partes = linea.split(",");
                    grafo.conectarNodos(partes[0], partes[1]);
                }
            }
        } catch (IOException e){
            System.out.println("Error al cargar el grafo: " + e.getMessage());
        }
        return grafo;
    }

    public static void guardarGrafo(Grafo grafo, String rutaArchivo){
        try(FileWriter writer = new FileWriter(rutaArchivo)){
            writer.write("NODOS\n");
            grafo.getNodos().forEach(n -> {
                try {
                    writer.write(n.getId() + "," + n.getX() + "," + n.getY() + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            writer.write("\nARISTAS\n");
            grafo.getNodos().forEach(n -> {
                n.getVecinos().forEach(v -> {
                    try {
                        writer.write(n.getId() + "," + v.getId() + "\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            });
        } catch (IOException e){
            System.out.println("Error al guardar el grafo: " + e.getMessage());
        }
    }
}
