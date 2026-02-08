package controllers;

import java.util.ArrayList;
import java.util.List;

import models.algorithms.BFS;
import models.algorithms.DFS;
import models.graphs.Grafo;
import models.graphs.Nodo;
import models.persistence.GrafoLoader;
import models.persistence.GrafoSaver;   
import utils.ModoVisualizacion;
import utils.TiempoEjecucion;

public class MainController {

    private Grafo grafo;
    private ModoVisualizacion modo;

    public MainController(){
        this.grafo = GrafoLoader.cargarGrafo("resources/config/grafo.txt");
        this.modo = ModoVisualizacion.EXPLORACION;
        generarConexionesAutomaticas();
        grafo.organizarEnGrilla(600, 100);
    }


    public void agregarNodo(String id, int x, int y){
        grafo.agregarNodo(id, x, y);
        GrafoSaver.guardarGrafo(grafo, "resources/config/grafo.txt");
    }

    public void eliminarNodo(String id){
        grafo.eliminarNodo(id);
        GrafoSaver.guardarGrafo(grafo, "resources/config/grafo.txt");
    }

    public void agregarConexion(String a, String b, boolean bidireccional){
        grafo.conectar(a, b, bidireccional);
        GrafoSaver.guardarGrafo(grafo, "resources/config/grafo.txt");
    }

    public void eliminarConexion(String a, String b){
        grafo.eliminarConexion(a, b);
        GrafoSaver.guardarGrafo(grafo, "resources/config/grafo.txt");
    }


    public void setModoVisualizacion(ModoVisualizacion modo){
        this.modo = modo;
    }

    public ResultadoBusqueda ejercutarBFS(String idInicio, String idDestino){
        Nodo inicio = grafo.getNodo(idInicio);
        Nodo destino = grafo.getNodo(idDestino);

        List<Nodo> visitados = new ArrayList<>();

        long inicioTiempo = TiempoEjecucion.iniciar();
        List<Nodo> ruta = BFS.buscarRuta(grafo, inicio, destino, visitados);
        long tiempo = TiempoEjecucion.finalizar(inicioTiempo);

        TiempoEjecucion.guardarCSV("BFS", tiempo, "resources/results/tiempos.csv");

        return new ResultadoBusqueda(ruta,visitados,tiempo,modo);
    }

    private void generarConexionesAutomaticas() {
        Nodo anterior = null;

        for (Nodo actual : grafo.getNodos()) {
            if (anterior != null) {
                grafo.conectar(anterior.getId(), actual.getId(), true);
            }
            anterior = actual;
        }
    }

    public ResultadoBusqueda ejecutarDFS(String idInicio, String idDestino){
        Nodo inicio = grafo.getNodo(idInicio);
        Nodo destino = grafo.getNodo(idDestino);

        List<Nodo> visitados = new ArrayList<>();

        long inicioTiempo = TiempoEjecucion.iniciar();
        List<Nodo> ruta = DFS.buscarRuta(grafo, inicio, destino, visitados);
        long tiempo = TiempoEjecucion.finalizar(inicioTiempo);

        return new ResultadoBusqueda(ruta, visitados, tiempo, modo);
    }

    public Grafo getGrafo(){
        return grafo;
    }
}
