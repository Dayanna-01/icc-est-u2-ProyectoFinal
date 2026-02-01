package view;

import java.awt.BorderLayout;
import java.awt.Panel;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

import controllers.MainController;

public class MainFrame extends JFrame{

    public MainFrame(MainController controller){
        setTitle("Proyecto Final - Estructura de datos");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        add(new MapPanel(controller.geGrafo()), BorderLayout.CENTER);
        add(new JPanel(), BorderLayout.EAST);

        setLocationRelativeTo(null);
        setVisible(true);

    }
    
}
