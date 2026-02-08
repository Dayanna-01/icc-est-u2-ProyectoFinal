package models.persistence;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import models.graphs.Grafo;
import models.graphs.Nodo;

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

                    String origen = p[0];
                    String destino = p[1];

                    boolean bidireccional = true; 

                    if (p.length == 3) {
                        bidireccional = Boolean.parseBoolean(p[2]);
                    }

                    grafo.conectar(origen, destino, bidireccional);
                }




            }

        } catch (IOException e) {
            System.out.println("Error al cargar grafo");
        }
        return grafo;
    }


    public static void guardarGrafo(Grafo grafo, String ruta) {
        try (FileWriter fw = new FileWriter(ruta)) {

            fw.write("NODOS\n");
            for (Nodo n : grafo.getNodos()) {
                fw.write(n.getId() + "," + n.getX() + "," + n.getY() + "\n");
            }

            fw.write("ARISTAS\n");
            for (Nodo n : grafo.getNodos()) {
                for (Nodo.Conexion c : n.getConexiones()) {
                    fw.write(n.getId() + "," + c.destino.getId() + "," + c.bidireccional + "\n");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
