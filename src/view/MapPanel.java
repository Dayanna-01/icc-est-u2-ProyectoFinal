package view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JPanel;

import controllers.ResultadoBusqueda;
import models.graphs.Grafo;
import models.graphs.Nodo;
import utils.ModoVisualizacion;

public class MapPanel extends JPanel{

    private Grafo grafo;
    private ResultadoBusqueda resultado;
    
    public MapPanel(Grafo grafo){
        this.grafo = grafo;
        setBackground(Color.white);
    }

    public void mostrarResultado(ResultadoBusqueda resultado){
        this.resultado = resultado;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        dibujarAristas(g);
        dibujarNodos(g);

        if(resultado != null){
            if(resultado.getModo() == ModoVisualizacion.EXPLORACION){
                dibujarRecorrido(g, resultado.getVisitados(), Color.ORANGE);
            }else{
                dibujarRecorrido(g, resultado.getRuta(), Color.red);
            }
        }
    }

    private void dibujarRecorrido(Graphics g, List<Nodo> lista, Color color) {
        g.setColor(color);

        for(int i = 0; i < lista.size() -1; i++){
            Nodo a = lista.get(i);
            Nodo b = lista.get( i + 1);
            g.drawLine(a.getX(), a.getY(), b.getX(), b.getY());
        }
    }

    private void dibujarNodos(Graphics g) {
        g.setColor(Color.blue);

        for(Nodo n : grafo.getNodos()){
            g.fillOval(n.getX() - 6, n.getY() - 6, 12, 12);
            g.drawString(n.getId(), n.getX() + 8, n.getY() - 8);

        }
    }

    private void dibujarAristas(Graphics g) {
        g.setColor(Color.LIGHT_GRAY);
        for(Nodo n : grafo.getNodos()){
            for(Nodo v : n.getVecinos()){
                g.drawLine(n.getX(), n.getY(), v.getX(), v.getY());
            }
        }
        
    }

    
    
}
