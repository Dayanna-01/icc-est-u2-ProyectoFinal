package view;

import controllers.MainController;
import controllers.ResultadoBusqueda;
import utils.ModoVisualizacion;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {

    private MainController controller;
    private MapPanel mapPanel;

    private JTextField txtInicio;
    private JTextField txtDestino;

    public ControlPanel(MainController controller, MapPanel mapPanel) {
        this.controller = controller;
        this.mapPanel = mapPanel;

        setLayout(new GridLayout(0, 1, 5, 5));
        setPreferredSize(new Dimension(220, 0));

        add(new JLabel("Nodo Inicio:"));
        txtInicio = new JTextField();
        add(txtInicio);

        add(new JLabel("Nodo Destino:"));
        txtDestino = new JTextField();
        add(txtDestino);

        JButton btnBFS = new JButton("Ejecutar BFS");
        JButton btnDFS = new JButton("Ejecutar DFS");
        /// AÑADIR
        /// 1. mas nodos minimo 30 y si se aumenta y cierro se guarde y de igual manera que cuando elimine y se guarde y se quede con lo que agregó o eliminó
        /// 2. acomodar los tamaños de los botones
        /// 3. Las direcciones deben ser unidireccionales y biireccionales
        /// 4. Añadir el boton de agregar y eliminar nodos
        /// 5. añadir el boton ver resultados 
        /// ejemplo
        /// De nodo A -> J con metodo BFS O DFS recorre en x segundos

//holaaa
//kkgkg
        add(btnBFS);
        add(btnDFS);

        JRadioButton rbExploracion = new JRadioButton("Exploración", true);
        JRadioButton rbRuta = new JRadioButton("Ruta Final");

        ButtonGroup grupo = new ButtonGroup();
        grupo.add(rbExploracion);
        grupo.add(rbRuta);

        add(rbExploracion);
        add(rbRuta);

        rbExploracion.addActionListener(e ->
                controller.setModoVisualizacion(ModoVisualizacion.EXPLORACION));

        rbRuta.addActionListener(e ->
                controller.setModoVisualizacion(ModoVisualizacion.RUTA_FINAL));

        btnBFS.addActionListener(e -> ejecutarBFS());
       
    }

    private void ejecutarBFS() {
        ResultadoBusqueda resultado = controller.ejercutarBFS(
                txtInicio.getText().trim(),
                txtDestino.getText().trim()
        );
        mapPanel.mostrarResultado(resultado);
    }

    private void ejecutarDFS(){
        ResultadoBusqueda resultado = controller.ejecutarDFS(
                txtInicio.getText().trim(),
                txtDestino.getText().trim()
        );  
        mapPanel.mostrarResultado(resultado);  
    }
   
}
