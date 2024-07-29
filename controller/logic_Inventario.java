package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import View.ViewAdmin;
import View.ViewEntradasSalidas;
import View.ViewGeneralEmpleados;
import View.ViewIngresarProductos;
import View.ViewInventario;
import model.producto;
import model.productoDAO;
import model.proveedor;

public class logic_Inventario implements ActionListener {
    
    private ViewInventario vi;
    private ViewAdmin va;
    private productoDAO pdao=new productoDAO();
    
    public logic_Inventario(ViewInventario vi, ViewAdmin va) {
        this.vi = vi;
        this.va = va;
        activador();
        cargarDatos();
    }
    
    public void activador() {
        vi.btnAnadirProducto.addActionListener(this);
        vi.btnVerEntradasSalidas.addActionListener(this); // Nuevo botón
        vi.btnCerrar.addActionListener(this);
        vi.btnRegresar.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vi.btnAnadirProducto) {
            ViewIngresarProductos vip = new ViewIngresarProductos(vi);
            vip.setVisible(true);
            vi.setVisible(false);
        } else if (e.getSource() == vi.btnVerEntradasSalidas) {
            ViewEntradasSalidas ves = new ViewEntradasSalidas(va);
            ves.setVisible(true);
            vi.setVisible(false);
        } else if (e.getSource() == vi.btnCerrar) {
            int response = JOptionPane.showConfirmDialog(vi, "¿Desea salir de la Aplicación?", "Confirmar salida", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        } else if (e.getSource() == vi.btnRegresar) {
            if (va != null) {
            	va.setVisible(true);
            }
            vi.dispose();
        }
    }
    public void cargarDatos() {
    	List<producto> productos = pdao.getAllProductos();
        DefaultTableModel model = (DefaultTableModel) vi.tableInventario.getModel();
        model.setRowCount(0); // Limpiar la tabla antes de cargar los datos

        for (producto prov : productos) {
            model.addRow(new Object[]{
                prov.getId(), 
                prov.getNombre(), 
                prov.getPrecio(), 
                prov.getStock(),
                prov.getProveedor()
            });
        }
    }
}
