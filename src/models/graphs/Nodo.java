package models.graphs;

import java.util.ArrayList;
import java.util.List;

public class Nodo {

    private String id;
    private int x;
    private int y;
    private List<Conexion> conexiones;

    public Nodo(String id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.conexiones = new ArrayList<>();
    }

    public String getId() { return id; }
    public int getX() { return x; }
    public int getY() { return y; }

    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }


    public List<Conexion> getConexiones() {
        return conexiones;
    }

    public void conectar(Nodo destino, boolean bidireccional) {
        conexiones.add(new Conexion(destino, bidireccional));
    }

    public void eliminarConexion(Nodo destino) {
        conexiones.removeIf(c -> c.destino.equals(destino));
    }

    @Override
    public String toString() {
        return id;
    }

    public static class Conexion {
        public Nodo destino;
        public boolean bidireccional;

        public Conexion(Nodo destino, boolean bidireccional) {
            this.destino = destino;
            this.bidireccional = bidireccional;
        }
    }
}
