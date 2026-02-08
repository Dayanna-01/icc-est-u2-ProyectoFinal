package models.algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import models.graphs.Grafo;
import models.graphs.Nodo;

public class DFS {
    
    public static List<Nodo> buscarRuta(Grafo grafo, Nodo inicio, Nodo destino, List<Nodo> visitadosOrden){
        
         if (inicio.isBloqueado() || destino.isBloqueado()) {
            return new ArrayList<>();
        }
        
        Set<Nodo> visitados = new HashSet<>();
        Map<Nodo, Nodo> precursor = new HashMap<>();

        boolean encontrado = dfsRecursivo(inicio, destino, visitados, precursor, visitadosOrden);

        if (encontrado) {
            return reconstruirRuta(precursor, inicio, destino);
        }
        return new ArrayList<>();
    }

    private static boolean dfsRecursivo(
        Nodo actual,
        Nodo destino,
        Set<Nodo> visitados,
        Map<Nodo, Nodo> precursor,
        List<Nodo> visitadosOrden
    )  {
    
        if (actual.isBloqueado()) return false;

        visitados.add(actual);
        visitadosOrden.add(actual);

        if (actual.equals(destino)) {
            return true;
        }

        for (Nodo.Conexion c : actual.getConexiones()) {
            Nodo vecino = c.destino;

            if (!visitados.contains(vecino) && !vecino.isBloqueado()) {
                precursor.put(vecino, actual);
                boolean encontrado = dfsRecursivo(
                        vecino,
                        destino,
                        visitados,
                        precursor,
                        visitadosOrden
                );
                if (encontrado) {
                    return true;
                }
            }
        }
        return false;
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