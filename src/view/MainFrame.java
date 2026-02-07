package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controllers.MainController;

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
    }
}
