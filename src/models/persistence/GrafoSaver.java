package models.persistence;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import models.graphs.Grafo;
import models.graphs.Nodo;

public class GrafoSaver {

    public static void guardarGrafo(Grafo grafo, String ruta) {

        try (PrintWriter pw = new PrintWriter(new FileWriter(ruta))) {

            pw.println("NODOS");

            for (Nodo n : grafo.getNodos()) {
                pw.println(n.getId() + "," + n.getX() + "," + n.getY());
            }

            pw.println("ARISTAS");

            for (Nodo n : grafo.getNodos()) {
                for (Nodo.Conexion c : n.getConexiones()) {

                    if (n.getId().compareTo(c.destino.getId()) < 0 || !c.bidireccional) {
                        pw.println(n.getId() + "," + c.destino.getId() + "," + c.bidireccional);
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Error al guardar el grafo");
        }
    }
}
