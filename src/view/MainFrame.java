package view;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controllers.MainController;
import models.persistence.GrafoLoader;

public class MainFrame extends JFrame {

    public MainFrame(MainController controller) {

        setTitle("Proyecto Final - Estructura de Datos");
        setSize(900, 600);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        MapPanel mapPanel = new MapPanel(controller.getGrafo());
        ControlPanel controlPanel = new ControlPanel(controller, mapPanel);

        add(mapPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.EAST);

        setLocationRelativeTo(null);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                GrafoLoader.guardarGrafo(
                    controller.getGrafo(),
                    "resources/config/grafo.txt"
            );
            }       
        });
    }
}

