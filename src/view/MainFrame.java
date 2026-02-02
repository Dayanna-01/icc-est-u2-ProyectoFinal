package view;

import java.awt.BorderLayout;
import java.awt.Panel;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

import controllers.MainController;

public class MainFrame extends JFrame{
    private MainController controller;
    private MapPanel mapPanel;
    private ControlPanel controlPanel;

    public MainFrame(MainController controller){
        this.controller = controller;

        setTitle("Proyecto Final - Estructura de datos");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        mapPanel = new MapPanel(controller.getGrafo());
        controlPanel = new ControlPanel(controller, mapPanel);

        add(mapPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.EAST);

        setLocationRelativeTo(null);
        setVisible(true);

    }
    
}
