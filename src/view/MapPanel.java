package view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import models.graphs.Grafo;
import models.graphs.Nodo;

public class MapPanel extends JPanel{

    private Grafo grafo;
    
    public MapPanel(Grafo grafo){
        this.grafo = grafo;
        setBackground(Color.white);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        dibujarAristas(g);
        dibujarNodos(g);
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
