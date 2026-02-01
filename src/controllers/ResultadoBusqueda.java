package controllers;

import java.util.List;


import models.graphs.Nodo;
import utils.ModoVisualizacion;

public class ResultadoBusqueda {
    private List<Nodo> ruta;
    private List<Nodo> visitados;
    private long tiempo;
    private ModoVisualizacion modo;

    public ResultadoBusqueda(List<Nodo> ruta, List<Nodo> visitados, long tiempo, ModoVisualizacion modo){
        this.ruta = ruta;
        this.visitados = visitados;
        this.tiempo = tiempo;
        this.modo = modo;
    }

    public List<Nodo> getRuta() {
        return ruta;
    }

    public List<Nodo> getVisitados() {
        return visitados;
    }

    public long getTiempo() {
        return tiempo;
    }

    public ModoVisualizacion getModo() {
        return modo;
    }
    

}
