package models.graphs;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Grafo {

    private Map<String, Nodo> nodos = new HashMap<>();

    public void agregarNodo(String id, int x, int y) {
        nodos.putIfAbsent(id, new Nodo(id, x, y));
    }

    public void eliminarNodo(String id) {
        Nodo eliminado = nodos.remove(id);
        if (eliminado != null) {
            for (Nodo n : nodos.values()) {
                n.eliminarConexion(eliminado);
            }
        }
    }

    public void conectar(String a, String b, boolean bidireccional) {
        Nodo n1 = nodos.get(a);
        Nodo n2 = nodos.get(b);

        if (n1 != null && n2 != null) {
            n1.conectar(n2, bidireccional);
            if (bidireccional) {
                n2.conectar(n1, true);
            }
        }
    }

    public void eliminarConexion(String a, String b) {
        Nodo n1 = nodos.get(a);
        Nodo n2 = nodos.get(b);

        if (n1 != null && n2 != null) {
            n1.eliminarConexion(n2);
            n2.eliminarConexion(n1);
        }
    }

    public Nodo getNodo(String id) {
        return nodos.get(id);
    }

    public Collection<Nodo> getNodos() {
        return nodos.values();
    }

    public void organizarEnGrilla(int ancho, int separacion) {
    int cols = ancho / separacion;
    int i = 0;

    for (Nodo n : nodos.values()) {
        int x = (i % cols) * separacion + 50;
        int y = (i / cols) * separacion + 50;
        n.setX(x);
        n.setY(y);
        i++;
    }
}


}
