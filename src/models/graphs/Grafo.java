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
        Nodo n1 = nodos.get(id1);
        Nodo n2 = nodos.get(id2);

        if (n1 != null && n2 != null) {
            n1.agregarVecino(n2);
            n2.agregarVecino(n1);
        }
    }

    public Nodo getNodo(String id){
        return nodos.get(id);
    }

    public Collection<Nodo> getNodos(){
        return nodos.values();
    }
}