package controller;

import View.ViewAdmin;
import View.ViewEntradasSalidas;
import View.ViewGeneralEmpleados;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class logic_EntradasSalidas {
    private ViewEntradasSalidas view;
    private ViewAdmin va;

    public logic_EntradasSalidas(ViewEntradasSalidas view, ViewAdmin va) {
        this.view = view;
        this.va = va;


        this.view.btnCerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
            }
        });

        this.view.btnRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.setVisible(false);
                va.setVisible(true);
            }
        });

        // Inicializar la tabla con datos de ejemplo
        initializeTable();
    }

    private void initializeTable() {
        DefaultTableModel model = (DefaultTableModel) view.table.getModel();
        // Ejemplo de datos, puedes cargar datos reales aquí
        model.addRow(new Object[]{"Producto 1", "Entrada", 10, 100.0});
        model.addRow(new Object[]{"Producto 2", "Salida", 5, 50.0});
    }

}
