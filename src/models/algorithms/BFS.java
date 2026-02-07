package models.algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import models.graphs.Grafo;
import models.graphs.Nodo;

public class BFS {
    public static List<Nodo> buscarRuta(Grafo grafo, Nodo inicio, Nodo destino, List<Nodo> visitadosOrden){
        Queue<Nodo> cola = new LinkedList<>();
        Set<Nodo> visitados = new HashSet<>();
        Map<Nodo, Nodo> precursor = new HashMap<>();

        cola.add(inicio);
        visitados.add(inicio);

        while (!cola.isEmpty()) {
            Nodo actual = cola.poll();
            visitadosOrden.add(actual);

            if (actual.equals(destino)) {
                return reconstruirRuta(precursor, inicio, destino);
            }

            for (Nodo.Conexion c : actual.getConexiones()) {
                Nodo vecino = c.destino;
                if (!visitados.contains(vecino)) {
                    visitados.add(vecino);
                    precursor.put(vecino, actual);
                    cola.add(vecino);
                }
            }
        }
        return new ArrayList<>();
    }

    private static List<Nodo> reconstruirRuta(Map<Nodo,Nodo> predecesor, Nodo inicio, Nodo destino) {
        List<Nodo> ruta = new ArrayList<>();
        Nodo actual = destino;

        while (actual != null) {
            ruta.add(actual);
            actual = predecesor.get(actual);
        }

        Collections.reverse(ruta);

        if (!ruta.isEmpty() && ruta.get(0).equals(inicio)) {
            return ruta;
        }
        
        return new ArrayList<>();
    }
}
