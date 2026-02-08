package view;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.*;


import controllers.ResultadoBusqueda;
import models.graphs.Grafo;
import models.graphs.Nodo;
import utils.ModoVisualizacion;

public class MapPanel extends JPanel {

    private Image mapa;
    private Grafo grafo;
    private ResultadoBusqueda resultado;

    private int pasoAnimacion = 0;
    private Timer timer;

    private Nodo nodoInicio;
    private Nodo nodoFin;

    public MapPanel(Grafo grafo) {
        this.grafo = grafo;
        mapa = new ImageIcon("resources/map/mapa.png").getImage();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                for (Nodo n : grafo.getNodos()) {
                    int dx = e.getX() - n.getX();
                    int dy = e.getY() - n.getY();

                    if (Math.sqrt(dx * dx + dy * dy) <= 8) {
                        n.toggleBloqueado();
                        repaint();
                        break;
                    }
                }
            }
        });

    }

    public void mostrarResultado(ResultadoBusqueda resultado) {
        this.resultado = resultado;
        pasoAnimacion = 0;

        if (resultado.getRuta() != null && !resultado.getRuta().isEmpty()) {
            nodoInicio = resultado.getRuta().get(0);
            nodoFin = resultado.getRuta().get(resultado.getRuta().size() - 1);
        }

        if (timer != null && timer.isRunning()) {
            timer.stop();
        }

        timer = new Timer(300, e -> {
            pasoAnimacion++;
            repaint();

            List<Nodo> lista = resultado.getModo() == ModoVisualizacion.EXPLORACION
                    ? resultado.getVisitados()
                    : resultado.getRuta();

            if (pasoAnimacion >= lista.size()) {
                timer.stop();
            }
        });

        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
         g.drawImage(mapa, 0, 0, getWidth(), getHeight(), this);
        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);

        g2.drawImage(mapa, 0, 0, getWidth(), getHeight(), this);

        dibujarAristas(g2);
        dibujarNodos(g2);

        if (resultado != null) {
            List<Nodo> lista = resultado.getModo() == ModoVisualizacion.EXPLORACION
                    ? resultado.getVisitados()
                    : resultado.getRuta();

            g2.setColor(resultado.getModo() == ModoVisualizacion.EXPLORACION
                    ? Color.ORANGE : Color.RED);

            g2.setStroke(new BasicStroke(3.0f));

            int limite = Math.min(pasoAnimacion, lista.size() - 1);

            for (int i = 0; i < limite; i++) {
                Nodo a = lista.get(i);
                Nodo b = lista.get(i + 1);
                g2.drawLine(a.getX(), a.getY(), b.getX(), b.getY());
            }
        }
    }

    private void dibujarNodos(Graphics2D g) {
        for (Nodo n : grafo.getNodos()) {

            if (n.equals(nodoInicio)) {
                g.setColor(Color.GREEN);
                g.fillOval(n.getX() - 8, n.getY() - 8, 16, 16);

            } else if (n.equals(nodoFin)) {
                g.setColor(Color.RED);
                g.fillOval(n.getX() - 8, n.getY() - 8, 16, 16);

            } else {
                if (n.isBloqueado()) {
                    g.setColor(Color.GRAY);
                } else if (n.equals(nodoInicio)) {
                    g.setColor(Color.GREEN);
                } else if (n.equals(nodoFin)) {
                    g.setColor(Color.RED);
                } else {
                    g.setColor(Color.BLUE);
                }

                g.fillOval(n.getX() - 6, n.getY() - 6, 12, 12);
            }

            g.setColor(Color.BLACK);
            g.drawString(n.getId(), n.getX() + 6, n.getY() - 6);
        }
    }

    private void dibujarAristas(Graphics2D g) {
        g.setColor(Color.DARK_GRAY);
        g.setStroke(new BasicStroke(2.5f));

        for (Nodo n : grafo.getNodos()) {
            for (Nodo.Conexion c : n.getConexiones()) {
                if (c.bidireccional) {
                    g.drawLine(n.getX(), n.getY(),
                               c.destino.getX(), c.destino.getY());
                } else {
                    dibujarFlecha(g, n.getX(), n.getY(),
                                  c.destino.getX(), c.destino.getY());
                }
            }
        }
    }

    private void dibujarFlecha(Graphics g, int x1, int y1, int x2, int y2) {
        g.drawLine(x1, y1, x2, y2);

        double ang = Math.atan2(y2 - y1, x2 - x1);
        int t = 10;

        int xA = (int) (x2 - t * Math.cos(ang - Math.PI / 6));
        int yA = (int) (y2 - t * Math.sin(ang - Math.PI / 6));

        int xB = (int) (x2 - t * Math.cos(ang + Math.PI / 6));
        int yB = (int) (y2 - t * Math.sin(ang + Math.PI / 6));

        g.drawLine(x2, y2, xA, yA);
        g.drawLine(x2, y2, xB, yB);
    }
}
