package models.graphs;

import java.util.ArrayList;
import java.util.List;

public class Nodo {
    private String id;
    private int x;
    private int y;
    private List<Nodo> vecinos;

    public Nodo(String id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.vecinos = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public List<Nodo> getVecinos() {
        return vecinos;
    }

    public void agregarVecino(Nodo nodo) {
        if (!vecinos.contains(nodo)) {
            vecinos.add(nodo);
        }
    }

    @Override
    public String toString(){
        return id;
    }
}
