package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import View.ViewActualizarContrasena;
import View.ViewBuscarProveedores;
import View.ViewClientes;
import View.ViewGeneralEmpleados;
import View.ViewIngresarProductos;
import View.ViewInventario;
import View.ViewLogin;
import View.ViewVentaProductos;

public class logic_GeneralEmpleados implements ActionListener {

    private ViewGeneralEmpleados vge;
    private ViewLogin vl;

    public logic_GeneralEmpleados(ViewGeneralEmpleados vge, ViewLogin vl) {
        this.vge = vge;
        this.vl = vl;
        activador();
    }

    public void activador() {
        vge.btnCerrar.addActionListener(this);
        vge.btnListadoClientes.addActionListener(this);
        vge.btnListarProveedores.addActionListener(this);
        vge.btnRegresar.addActionListener(this);
        vge.btnStock.addActionListener(this);
        vge.btnVenta.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == vge.btnCerrar) {
            int response = JOptionPane.showConfirmDialog(vge, "¿Desea salir de la Aplicación?", "Confirmar salida", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        } else if (source == vge.btnRegresar) {
            ViewLogin vl = new ViewLogin();
            if (vl != null) {
                vl.setVisible(true);
            }
            vge.dispose();
        } else if (source == vge.btnActualizarContrasena) {
            ViewActualizarContrasena vac = new ViewActualizarContrasena(vge);
            vac.setVisible(true);
            vge.setVisible(false);
        } else if (source == vge.btnListadoClientes) {
            ViewClientes vlc = new ViewClientes(vge);
            vlc.setVisible(true);
            vge.dispose();
        } else if (source == vge.btnListarProveedores) {
            ViewBuscarProveedores vbp = new ViewBuscarProveedores(vge);
            vbp.setVisible(true);
            vge.setVisible(false);
        } else if (source == vge.btnVenta) {
            ViewVentaProductos vvp = new ViewVentaProductos(vge);
            vvp.setVisible(true);
            vge.setVisible(false);
        }else if(source==vge.btnStock) {
        	ViewIngresarProductos viprod =new ViewIngresarProductos(vge);
        	viprod.setVisible(true);
        	vge.setVisible(false);
        }
    }
}