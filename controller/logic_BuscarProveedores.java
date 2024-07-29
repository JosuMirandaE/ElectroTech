package controller;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import View.ViewAdmin;
import View.ViewBuscarProveedores;
import View.ViewGeneralEmpleados;
import View.ViewIngresarProveedores;
import View.ViewLogin;
import model.proveedor;
import model.proveedorDAO;

public class logic_BuscarProveedores implements ActionListener{
    private ViewBuscarProveedores view;
    private JFrame parentFrame;
    private proveedorDAO pdao = new proveedorDAO();

    public logic_BuscarProveedores(ViewBuscarProveedores view, JFrame parentFrame) {
        this.view = view;
        this.parentFrame = parentFrame;
        activador();
        cargarDatos();
        // Inicializa otros componentes y listeners aquí
    }
    

	private void activador() {
        view.btnCerrar.addActionListener(this);
        view.btnIngresarProveedor.addActionListener(this);
        view.btnRegresar.addActionListener(this);
    }

    private void cargarDatos() {
        List<proveedor> proveedores = pdao.getAllProveedores();
        DefaultTableModel model = (DefaultTableModel) view.tableProveedores.getModel();
        model.setRowCount(0); // Limpiar la tabla antes de cargar los datos

        for (proveedor prov : proveedores) {
            model.addRow(new Object[]{
                prov.getId(), 
                prov.getNames(), 
                prov.getCode(), 
                prov.getSocial()
            });
        }
    }
    


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.btnCerrar) {
            int response = JOptionPane.showConfirmDialog(view, "¿Desea salir de la Aplicación?", "Confirmar salida", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        } else if (e.getSource() == view.btnRegresar) {
        	if (parentFrame != null) {
                parentFrame.setVisible(true);
            }
            view.dispose();
        } else if (e.getSource() == view.btnIngresarProveedor) {
            ViewIngresarProveedores vip = new ViewIngresarProveedores(view);
            vip.setVisible(true);
            view.setVisible(false);
        }
    }
}



