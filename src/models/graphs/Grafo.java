package models.graphs;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Grafo {
    private Map<String, Nodo> nodos;

    public Grafo(){
        nodos = new HashMap<>();
    }

    public void agregarNodo(String id, int x, int y){
        if (!nodos.containsKey(id)) {
            Nodo nodo = new Nodo(id, x, y);
            nodos.put(id, nodo);
        }
    }

    public void conectarNodos(String id1, String id2){
        Nodo nodo1 = nodos.get(id1);
        Nodo nodo2 = nodos.get(id2);

        if (nodo1 != null && nodo2 != null) {
            nodo1.agregarVecino(nodo2);
            nodo2.agregarVecino(nodo1);
        }
    }

    public Nodo getNodo(String id){
        return nodos.get(id);
    }

    public Collection<Nodo> getNodos(){
        return nodos.values();
    }
}