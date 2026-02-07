package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ResultadosDialog extends JDialog {

    private JTable tabla;
    private DefaultTableModel modelo;

    public ResultadosDialog(JFrame parent) {
        super(parent, "Resultados", true);

        String[] columnas = {
            "Nodo Inicio",
            "Nodo Fin",
            "BFS",
            "DFS",
            "Tiempo (ns)"
        };

        modelo = new DefaultTableModel(columnas, 0);
        tabla = new JTable(modelo);

        add(new JScrollPane(tabla), BorderLayout.CENTER);

        setSize(500, 300);
        setLocationRelativeTo(parent);
    }

    public void agregarResultado(String inicio, String fin,
                                 boolean bfs, boolean dfs,
                                 long tiempo) {

        modelo.addRow(new Object[]{
            inicio,
            fin,
            bfs ? "✔" : "—",
            dfs ? "✔" : "—",
            tiempo
        });
    }
}
