package models.persistence;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import models.graphs.Grafo;

public class GrafoLoader {

    public static Grafo cargarGrafo(String ruta) {
        Grafo grafo = new Grafo();

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            boolean nodos = false, aristas = false;

            while ((linea = br.readLine()) != null) {
                linea = linea.trim();

                if (linea.equals("NODOS")) {
                    nodos = true; aristas = false; continue;
                }
                if (linea.equals("ARISTAS")) {
                    nodos = false; aristas = true; continue;
                }

                if (nodos && !linea.isEmpty()) {
                    String[] p = linea.split(",");
                    grafo.agregarNodo(p[0], Integer.parseInt(p[1]), Integer.parseInt(p[2]));
                }

                if (aristas && !linea.isEmpty()) {
                    String[] p = linea.split(",");

                    String desde = p[0];
                    String hasta = p[1];

                    boolean bidireccional = true; 

                    if (p.length == 3) {
                        bidireccional = Boolean.parseBoolean(p[2]);
                    }

                    grafo.conectar(desde, hasta, bidireccional);
                }


            }

        } catch (IOException e) {
            System.out.println("Error al cargar grafo");
        }
        return grafo;
    }
}
