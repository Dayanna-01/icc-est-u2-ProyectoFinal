package view;

import controllers.MainController;
import controllers.ResultadoBusqueda;
import models.persistence.GrafoLoader;
import utils.ModoVisualizacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ControlPanel extends JPanel {

    private MainController controller;
    private MapPanel mapPanel;
    private ResultadosDialog resultadosDialog;


    private JTextField txtInicio;
    private JTextField txtDestino;

    public ControlPanel(MainController controller, MapPanel mapPanel) {
        this.controller = controller;
        this.mapPanel = mapPanel;

        resultadosDialog = new ResultadosDialog(
            (JFrame) SwingUtilities.getWindowAncestor(this)
        );


        setLayout(new GridLayout(0, 1, 5, 5));
        setPreferredSize(new Dimension(150, 0));

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

        JButton btnAgregarNodo = new JButton("Agregar Nodo");
        JButton btnEliminarNodo = new JButton("Eliminar Nodo");
        JButton btnAgregarConexion = new JButton("Agregar Conexión");
        JButton btnEliminarConexion = new JButton("Eliminar Conexión");
        JButton btnVerResultados = new JButton("Ver Resultados");

        add(btnAgregarNodo);
        add(btnEliminarNodo);
        add(btnAgregarConexion);
        add(btnEliminarConexion);
        add(btnVerResultados);


        rbExploracion.addActionListener(e ->
                controller.setModoVisualizacion(ModoVisualizacion.EXPLORACION));

        rbRuta.addActionListener(e ->
                controller.setModoVisualizacion(ModoVisualizacion.RUTA_FINAL));

       btnBFS.addActionListener(e -> {
            ResultadoBusqueda r = controller.ejercutarBFS(
                txtInicio.getText().trim(),
                txtDestino.getText().trim()
            );

            mapPanel.mostrarResultado(r);

            resultadosDialog.agregarResultado(
                txtInicio.getText(),
                txtDestino.getText(),
                "BFS",
                r.getTiempo()
            );
        });


        btnDFS.addActionListener(e -> {
            ResultadoBusqueda r = controller.ejecutarDFS(
                txtInicio.getText().trim(),
                txtDestino.getText().trim()
            );

            mapPanel.mostrarResultado(r);

            resultadosDialog.agregarResultado(
                txtInicio.getText(),
                txtDestino.getText(),
                "DFS",
                r.getTiempo()
            );
        });


        btnAgregarNodo.addActionListener(e -> {
            String id = JOptionPane.showInputDialog("ID del nodo:");
            int x = Integer.parseInt(JOptionPane.showInputDialog("X:"));
            int y = Integer.parseInt(JOptionPane.showInputDialog("Y:"));

            controller.getGrafo().agregarNodo(id, x, y);
            mapPanel.repaint();
        });

        btnEliminarNodo.addActionListener(e -> {
            String id = JOptionPane.showInputDialog("ID del nodo a eliminar:");
            controller.getGrafo().eliminarNodo(id);
            mapPanel.repaint();
        });

        btnAgregarConexion.addActionListener(e -> {
            String a = JOptionPane.showInputDialog("Nodo origen:");
            String b = JOptionPane.showInputDialog("Nodo destino:");

            int tipo = JOptionPane.showConfirmDialog(
                null,
                "¿Bidireccional?",
                "Tipo de conexión",
                JOptionPane.YES_NO_OPTION
            );

            boolean bidireccional = (tipo == JOptionPane.YES_OPTION);
            controller.getGrafo().conectar(a, b, bidireccional);
            mapPanel.repaint();
        });

        btnEliminarConexion.addActionListener(e -> {
            String a = JOptionPane.showInputDialog("Nodo origen:");
            String b = JOptionPane.showInputDialog("Nodo destino:");

            controller.getGrafo().eliminarConexion(a, b);
            mapPanel.repaint();
        });

        btnVerResultados.addActionListener(e -> resultadosDialog.setVisible(true));




       
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
