package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import View.ViewIngresarProductos;
import View.ViewInventario;
import model.producto;
import model.productoDAO;

public class logic_IngresarProducto implements ActionListener {

    private ViewIngresarProductos viprod;
    private JFrame parentFrame;
    private ViewInventario vi;
    private producto pr;
    private productoDAO pdao = new productoDAO();

    public logic_IngresarProducto(ViewIngresarProductos viprod, JFrame parentFrame) {
        this.viprod = viprod;
        this.parentFrame = parentFrame;
        pdao=new productoDAO();
        pr=new producto();
        activador();
    }

    public void activador() {
        viprod.btnCerrar.addActionListener(this);
        viprod.btnGuardar.addActionListener(this);
        viprod.btnRegresar.addActionListener(this);
        viprod.btnActualizar.addActionListener(this); // Añadir el ActionListener para btnActualizar
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == viprod.btnCerrar) {
            int response = JOptionPane.showConfirmDialog(viprod, "¿Desea salir de la Aplicación?", "Confirmar salida", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        } else if (e.getSource() == viprod.btnGuardar) {
            if (verificar()) {
                pr.setNombre(viprod.txtNombre.getText());
                pr.setDescripcion(viprod.txtDescripcion.getText());
                pr.setStock((Integer) viprod.spnCantidad.getValue());
                pr.setPrecio(Double.parseDouble(viprod.txtPrecio.getText()));
                pr.setProveedor((String) viprod.cmbProveedor.getSelectedItem());
                if(pdao.insertarProducto(pr)) {
                    JOptionPane.showMessageDialog(viprod, "Producto Añadido Exitosamente.");
                    clean();
                } else {
                    JOptionPane.showMessageDialog(viprod, "No se pudo ingresar el producto :(");
                }
            } else {
                JOptionPane.showMessageDialog(viprod, "Error Formato Incorrecto");
            }
        } else if (e.getSource() == viprod.btnActualizar) {
            if (verificar()) {
                pr.setNombre(viprod.txtNombre.getText());
                pr.setDescripcion(viprod.txtDescripcion.getText());
                pr.setStock((Integer) viprod.spnCantidad.getValue());
                pr.setPrecio(Double.parseDouble(viprod.txtPrecio.getText()));
                pr.setProveedor((String) viprod.cmbProveedor.getSelectedItem());
                if (pdao.actualizarProducto(pr)) {
                    JOptionPane.showMessageDialog(viprod, "Producto Actualizado Exitosamente.");
                } else {
                    JOptionPane.showMessageDialog(viprod, "No se pudo actualizar el producto :(");
                }
            } else {
                JOptionPane.showMessageDialog(viprod, "Error Formato Incorrecto");
            }
        } else if (e.getSource() == viprod.btnRegresar) {
            if (parentFrame != null) {
                parentFrame.setVisible(true);
            }
            viprod.dispose();
        }    
    }

    private boolean verificar() {
        boolean state = true;
        if (validateFields.validateNames(viprod.txtNombre.getText())) {
            viprod.txtNombre.setBackground(Color.green);
        } else {
            viprod.txtNombre.setBackground(Color.red);
            state = false;
        }

        try {
            if (validateFields.validateDescripcion(viprod.txtDescripcion.getText())) {
                viprod.txtDescripcion.setBackground(Color.green);
            } else {
                viprod.txtDescripcion.setBackground(Color.red);
                state = false;
            }
        } catch (NumberFormatException e) {
            viprod.txtPrecio.setBackground(Color.red);
            state = false;
        }

        if (validateFields.validateCantidad((Integer) viprod.spnCantidad.getValue())) {
            viprod.spnCantidad.setBackground(Color.green);
        } else {
            viprod.spnCantidad.setBackground(Color.red);
            state = false;
        }

        if (viprod.cmbProveedor.getSelectedItem() != null && !viprod.cmbProveedor.getSelectedItem().toString().isEmpty()) {
            viprod.cmbProveedor.setBackground(Color.green);
        } else {
            viprod.cmbProveedor.setBackground(Color.red);
            state = false;
        }

        return state;
    }

    private void clean() {
        viprod.txtNombre.setText("");
        viprod.txtNombre.setBackground(Color.white);
        viprod.txtDescripcion.setText("");
        viprod.txtDescripcion.setBackground(Color.white);
        viprod.txtPrecio.setText("");
        viprod.txtPrecio.setBackground(Color.white);
        viprod.spnCantidad.setValue(0);
        viprod.spnCantidad.setBackground(Color.white);
        viprod.cmbProveedor.setSelectedIndex(-1);
        viprod.cmbProveedor.setBackground(Color.white);
    }
}