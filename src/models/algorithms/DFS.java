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

    // Metodo principal que el sistema llama para buscar la ruta
    public static List<Nodo> buscarRuta(Grafo grafo, Nodo inicio, Nodo destino, List<Nodo> visitadosOrden){
        
        // Si el nodo inicio o destino estan bloqueados, no existe camino
         if (inicio.isBloqueado() || destino.isBloqueado()) {
            return new ArrayList<>();
        }
        
        // Conjunto para almacenar nodos ya visitados
        Set<Nodo> visitados = new HashSet<>();

        // Mapa que guarda quien fue el nodo anterior
        Map<Nodo, Nodo> precursor = new HashMap<>();

        // Llamada al metodo DFS recursivo
        boolean encontrado = dfsRecursivo(inicio, destino, visitados, precursor, visitadosOrden);

        // Si se encontró el destino se reconstruye la ruta
        if (encontrado) {
            return reconstruirRuta(precursor, inicio, destino);
        }

        // Si no se encontró camino se retorna lista vacia
        return new ArrayList<>();
    }


    // METODO RECURSIVO
    private static boolean dfsRecursivo(
        Nodo actual,
        Nodo destino,
        Set<Nodo> visitados,
        Map<Nodo, Nodo> precursor,
        List<Nodo> visitadosOrden
    )  {
        // Si el nodo esta bloqueado no se puede pasar
        if (actual.isBloqueado()) return false;

        // Marcar nodo como visitado
        visitados.add(actual);

        // Guardar el orden de exploración
        visitadosOrden.add(actual);

        // Si llegamos al destino terminamos la busqueda
        if (actual.equals(destino)) {
            return true;
        }

        // Recorrer todos los vecinos del nodo actual
        for (Nodo.Conexion c : actual.getConexiones()) {
            Nodo vecino = c.destino;

            // Solo visitar nodos no visitados y no bloqueados
            if (!visitados.contains(vecino) && !vecino.isBloqueado()) {

                //Guardamos desde que nodo llegamos
                precursor.put(vecino, actual);

                // Llamada recursiva
                boolean encontrado = dfsRecursivo(
                        vecino,
                        destino,
                        visitados,
                        precursor,
                        visitadosOrden
                );
                // Si alguna rama encontró el destino, terminamos
                if (encontrado) {
                    return true;
                }
            }
        }
        //Si ninugn vecino llevó al destino
        return false;
    }


    // RECONSTRUCCIÓN DE LA RUTA
    private static List<Nodo> reconstruirRuta(Map<Nodo,Nodo> predecesor, Nodo inicio, Nodo destino) {
        
        List<Nodo> ruta = new ArrayList<>();
        Nodo actual = destino;

        // Retrocede desde el destino hasta el inicio
        while (actual != null) {
            ruta.add(actual);
            actual = predecesor.get(actual);    
        }

        // Invertir la lista para obtener inicio → destino
        Collections.reverse(ruta);

        // Validar que realmente empieza en el nodo inicial
        if (!ruta.isEmpty() && ruta.get(0).equals(inicio)) {
            return ruta;
        }
        return new ArrayList<>();
    }
}




