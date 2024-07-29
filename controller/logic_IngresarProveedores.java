package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;

import View.ViewBuscarProveedores;
import View.ViewGeneralEmpleados;
import View.ViewIngresarProveedores;
import model.proveedor;
import model.proveedorDAO;

public class logic_IngresarProveedores implements ActionListener {
	
	private ViewIngresarProveedores vip;
	private ViewBuscarProveedores vbp;
	private ViewGeneralEmpleados vge;
	private proveedor pv = new proveedor();
	private proveedorDAO pvdao = new proveedorDAO();
	
	public logic_IngresarProveedores(ViewIngresarProveedores vip,ViewBuscarProveedores vbp) {
		this.vip=vip;
		this.vbp=vbp;
		activador();
	}
	
	public void activador() {
		vip.btnCerrar.addActionListener(this);
		vip.btnGuardar.addActionListener(this);
		vip.btnRegresar.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==vip.btnCerrar) {
			int response = JOptionPane.showConfirmDialog(vbp, "¿Desea salir de la Aplicación?", "Confirmar salida", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		    if (response == JOptionPane.YES_OPTION) {
		        System.exit(0);
		    }
		}else if(e.getSource()==vip.btnGuardar) {
			if (verifica()) {
				pv.setNames(vip.txtNombres.getText());
                pv.setDni(vip.txtDni.getText());
                pv.setEmail(vip.txtEmail.getText());
                pv.setTelefono(vip.txtTelefono.getText());
                pv.setSocial(vip.txtRazonSocial.getText());
                pv.setCode(pv.generarCode());
                if(pvdao.insertarProveedores(pv)) {
                	JOptionPane.showMessageDialog(vip, "Proveedor registrado exitosamente");
                	clean();
                }else {
    				JOptionPane.showMessageDialog(vip, "No se pudo registrar el proveedor");
                }
			}else {
				JOptionPane.showMessageDialog(vip, "Error de Formato Incorrecto");
			}
		}else if(e.getSource()==vip.btnRegresar) {
			ViewBuscarProveedores vbp=new ViewBuscarProveedores(vge);
			vbp.setVisible(true);
			vip.dispose();
		}
		
	}
	private boolean verifica() {
		boolean state =true;
		if(validateFields.validateNameCosas(vip.txtNombres.getText())) {
			vip.txtNombres.setBackground(Color.green);
		}else {
			vip.txtNombres.setBackground(Color.red);
			state = false;
		}
		
		if (validateFields.validateDni(Long.parseLong(vip.txtDni.getText()))) {
			vip.txtDni.setBackground(Color.green);
		} else {
			vip.txtDni.setBackground(Color.red);
			state = false;
		}
		
		if(validateFields.validateContact(vip.txtTelefono.getText())) {
			vip.txtTelefono.setBackground(Color.green);
		}else {
			vip.txtTelefono.setBackground(Color.red);
			state = false;
		}
		if(validateFields.validateRazons(vip.txtRazonSocial.getText())) {
			vip.txtRazonSocial.setBackground(Color.green);
		}else {
			vip.txtRazonSocial.setBackground(Color.red);
			state = false;
		}
		if(validateFields.validateEmail(vip.txtEmail.getText())) {
			vip.txtEmail.setBackground(Color.green);
		}else {
			vip.txtEmail.setBackground(Color.red);
			state = false;
		}
		
		return state;
	}
	private void clean() {
    	vip.txtNombres.setText("");
    	vip.txtNombres.setBackground(Color.white);
    	vip.txtDni.setText("");
    	vip.txtDni.setBackground(Color.white);
    	vip.txtEmail.setText("");
    	vip.txtEmail.setBackground(Color.white);
    	vip.txtTelefono.setText("");
    	vip.txtTelefono.setBackground(Color.white);
    	vip.txtRazonSocial.setText("");
    	vip.txtRazonSocial.setBackground(Color.white);
    }

}
