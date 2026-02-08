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
        setPreferredSize(new Dimension(150, 0));

        add(new JLabel("Nodo Inicio:"));
        txtInicio = new JTextField();
        add(txtInicio);

        add(new JLabel("Nodo Destino:"));
        txtDestino = new JTextField();
        add(txtDestino);

        JButton btnBFS = new JButton("Ejecutar BFS");
        JButton btnDFS = new JButton("Ejecutar DFS");

        add(btnBFS);
        add(btnDFS);

        btnBFS.addActionListener(e -> ejecutarBFS());
        btnDFS.addActionListener(e -> ejecutarDFS());

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


        btnAgregarNodo.addActionListener(e -> {
            try {
                String id = JOptionPane.showInputDialog("ID del nodo:");
                if(id == null || id.isEmpty()) return;

                int x = Integer.parseInt(JOptionPane.showInputDialog("X:"));
                int y = Integer.parseInt(JOptionPane.showInputDialog("Y:"));

                controller.agregarNodo(id, x, y);
                mapPanel.repaint();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Datos inválidos");
            }
        });

        btnEliminarNodo.addActionListener(e -> {
            String id = JOptionPane.showInputDialog("ID del nodo a eliminar:");
            if(id == null || id.isEmpty()) return;

            controller.eliminarNodo(id);
            mapPanel.repaint();
        });


        btnAgregarConexion.addActionListener(e -> {
            String a = JOptionPane.showInputDialog("Nodo origen:");
            String b = JOptionPane.showInputDialog("Nodo destino:");

            if(a == null || b == null || a.isEmpty() || b.isEmpty()) return;

            int tipo = JOptionPane.showConfirmDialog(
                null,
                "¿Bidireccional?",
                "Tipo de conexión",
                JOptionPane.YES_NO_OPTION
            );

            boolean bidireccional = (tipo == JOptionPane.YES_OPTION);

            controller.agregarConexion(a, b, bidireccional);
            mapPanel.repaint();
        });

        btnEliminarConexion.addActionListener(e -> {
            String a = JOptionPane.showInputDialog("Nodo origen:");
            String b = JOptionPane.showInputDialog("Nodo destino:");

            if(a == null || b == null || a.isEmpty() || b.isEmpty()) return;

            controller.eliminarConexion(a, b);
            mapPanel.repaint();
        });


        btnVerResultados.addActionListener(e -> {
            ResultadosDialog dialog =
                new ResultadosDialog((JFrame) SwingUtilities.getWindowAncestor(this));

            ResultadoBusqueda r = controller.ejercutarBFS(
                txtInicio.getText().trim(),
                txtDestino.getText().trim()
            );

            dialog.agregarResultado(
                txtInicio.getText(),
                txtDestino.getText(),
                true,
                false,
                r.getTiempo()
            );

            dialog.setVisible(true);
        });
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
