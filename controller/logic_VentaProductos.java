package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import View.ViewGeneralEmpleados;
import View.ViewVentaProductos;
import model.employee;
import model.producto;
import model.productoDAO;
import model.venta;
import model.ventaDAO;

public class logic_VentaProductos implements ActionListener {

    private ViewVentaProductos vvp;
    private ViewGeneralEmpleados vge;
    private venta v = new venta();
    private ventaDAO vdao = new ventaDAO();
    private producto prod;

    public logic_VentaProductos(ViewVentaProductos vvp, ViewGeneralEmpleados vge) {
        this.vvp = vvp;
        this.vge = vge;
        this.prod = new producto();
        activador();
    }

    public void activador() {
        vvp.btnCerrar.addActionListener(this);
        vvp.btnRegresar.addActionListener(this);
        vvp.btnVenderProducto.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vvp.btnCerrar) {
            int response = JOptionPane.showConfirmDialog(vvp, "¿Desea salir de la Aplicación?", "Confirmar salida", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        } else if (e.getSource() == vvp.btnRegresar) {
            if (vge != null) {
                vge.setVisible(true);
            }
            vvp.dispose();
        } else if (e.getSource() == vvp.btnVenderProducto) {
            if (verifica()) {
                int cantidad = (Integer) vvp.spCantidad.getValue();
                String producto = (String) vvp.cmbProducto.getSelectedItem();
                
                // Consultar el stock disponible
                int stockDisponible = new productoDAO().obtenerStockProducto(producto);
                
                if (cantidad > stockDisponible) {
                    JOptionPane.showMessageDialog(vvp, "Stock insuficiente. Disponible: " + stockDisponible, "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    v.setCantidad(cantidad);
                    v.setCliente((String) vvp.cmbCliente.getSelectedItem());
                    v.setProducto(producto);
                    v.setEmpleado((String) vvp.cmbEmpleado.getSelectedItem());
                    v.setPrecioU(obtenerPrecioProducto(producto));
                    v.setTotal(cantidad * v.getPrecioU());
                    
                    if (vdao.RegistrarVenta(v)) {
                        // Actualizar stock después de registrar la venta
                        if (vdao.actualizarStockProducto(producto, cantidad)) {
                            JOptionPane.showMessageDialog(vvp, "Venta realizada con éxito");
                        } else {
                            JOptionPane.showMessageDialog(vvp, "Error al actualizar el stock del producto");
                        }
                    } else {
                        JOptionPane.showMessageDialog(vvp, "Error al realizar la venta");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(vvp, "Complete todos los campos");
            }
        }
    }
    private boolean verifica() {
        boolean state = true;

        Integer cantidad = (Integer)vvp.spCantidad.getValue();
        String cliente = (String)vvp.cmbCliente.getSelectedItem();
        String producto = (String) vvp.cmbProducto.getSelectedItem();

        if (cantidad != null && validateFields.validateCantidad(cantidad)) {
            vvp.spCantidad.setBackground(Color.green);
        } else {
            vvp.spCantidad.setBackground(Color.red);
            state = false;
        }

        if (cliente != null && validateFields.validateNames(cliente)) {
            vvp.cmbCliente.setBackground(Color.green);
        } else {
            vvp.cmbCliente.setBackground(Color.red);
            state = false;
        }

        if (producto != null && validateFields.validateNames(producto)) {
            vvp.cmbProducto.setBackground(Color.green);
        } else {
            vvp.cmbProducto.setBackground(Color.red);
            state = false;
        }
        return state;
    }

    private double obtenerPrecioProducto(String producto) {
        // Aquí deberías obtener el precio del producto seleccionado desde la base de datos
        // Asegúrate de que `vdao` tenga el método adecuado para esta operación
        return vdao.obtenerPrecioProducto(producto);
    }
}

