package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import View.ViewClientes;
import View.ViewHistorialCompras;
import model.client;
import model.clienteDAO;
import model.employee;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class logic_Clientes implements ActionListener {

    private ViewClientes view;
    private JFrame parentFrame;
	private clienteDAO cdao;

    public logic_Clientes(ViewClientes view, JFrame parentFrame) {
        this.view = view;
        this.parentFrame = parentFrame;
        this.cdao=new clienteDAO();
        activador();
        cargarDatos();
    }

    public void activador() {
        view.btnCerrar.addActionListener(this);
        view.btnRegresar.addActionListener(this);
        view.btnVerHistorial.addActionListener(this);
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
        } else if (source == view.btnVerHistorial) {
            openHistorialComprasView();
        }
    }
    
    private void openHistorialComprasView() {
        ViewHistorialCompras historialCompras = new ViewHistorialCompras(null);
        historialCompras.setVisible(true);
    }
    
    private void cargarDatos() {
        List<client> clients = cdao.getAllClient();
        DefaultTableModel model = (DefaultTableModel) view.tableClientes.getModel();
        model.setRowCount(0); // Limpiar la tabla antes de cargar los datos

        for (client client : clients) {
            model.addRow(new Object[]{
                client.getId(),
                client.getNombres(),
                client.getDireccion(),
                client.getContacto()
            });
        }
    }
}