package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import View.ViewHistorialCompras;
import View.ViewGeneralEmpleados;

public class logic_HistorialCompras implements ActionListener {

    private ViewHistorialCompras view;
    private ViewGeneralEmpleados parentFrame;

    public logic_HistorialCompras(ViewHistorialCompras view, ViewGeneralEmpleados parentFrame) {
        this.view = view;
        this.parentFrame = parentFrame;
        activador();
    }

    public void activador() {
        view.btnCerrar.addActionListener(this);
        view.btnRegresar.addActionListener(this);
        view.btnBuscar.addActionListener(this); // Agregar listener para btnBuscar
        loadClientes(); // Cargar clientes al iniciar
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == view.btnCerrar) {
            int response = JOptionPane.showConfirmDialog(view, "¿Desea salir de la Aplicación?", "Confirmar salida", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        } else if (source == view.btnRegresar) {
            if (parentFrame != null) {
                parentFrame.setVisible(true);
            }
            view.dispose();
        } else if (source == view.btnBuscar) {
            loadHistorialCompras();
        }
    }
    private void loadClientes() {
        System.out.println("Cargando clientes...");
        try (Connection con = DatabaseConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM clientes")) {

            while (rs.next()) {
                String cliente = rs.getString("nombres");
                System.out.println("Cliente encontrado: " + cliente);
                view.clientes.addItem(cliente); // Asegúrate de que 'clientes' esté bien definido en ViewHistorialCompras
            }
        } catch (SQLException e) {
            System.err.println("Error al cargar clientes: " + e.getMessage());
        }
    }
    private void loadHistorialCompras() {
        String selectedCliente = (String) view.clientes.getSelectedItem();
        if (selectedCliente != null) {
            try (Connection con = DatabaseConnection.getConnection();
                 PreparedStatement pstmt = con.prepareStatement(
                     "SELECT producto, cantidad, fecha FROM electro.ventas WHERE cliente = ?")) {

                pstmt.setString(1, selectedCliente);
                ResultSet rs = pstmt.executeQuery();

                DefaultTableModel model = (DefaultTableModel) view.table.getModel();
                model.setRowCount(0); // Limpiar tabla antes de agregar nuevas filas

                while (rs.next()) {
                    String producto = rs.getString("producto");
                    int cantidad = rs.getInt("cantidad");
                    Date fecha = rs.getDate("fecha");
                    model.addRow(new Object[]{producto, cantidad, fecha});
                }
            } catch (SQLException e) {
                System.err.println("Error al cargar historial de compras: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}