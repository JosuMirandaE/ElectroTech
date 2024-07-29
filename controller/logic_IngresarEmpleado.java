package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;

import View.ViewAdmin;
import View.ViewIngresarEmpleado;
import model.employee;
import model.employeeDAO;

public class logic_IngresarEmpleado implements ActionListener {
    private ViewIngresarEmpleado vie;
    private ViewAdmin va;
    private employee em=new employee();
    private employeeDAO edao=new employeeDAO();
    public logic_IngresarEmpleado(ViewIngresarEmpleado vie,ViewAdmin va) {
        this.vie = vie;
        this.va=va;
        activador();
    }

    private void activador() {
        vie.btnGuardar.addActionListener(this);
        vie.btnCerrar.addActionListener(this);
        vie.btnSalir.addActionListener(this);
    }

    private boolean verificar() {
        boolean state = true;
        if (validateFields.validateNames(vie.txtNombres.getText())) {
            vie.txtNombres.setBackground(Color.green);
        } else {
            vie.txtNombres.setBackground(Color.red);
            state = false;
        }

        try {
            if (validateFields.validateDni(Long.parseLong(vie.txtDni.getText()))) {
                vie.txtDni.setBackground(Color.green);
            } else {
                vie.txtDni.setBackground(Color.red);
                state = false;
            }
        } catch (NumberFormatException e) {
            vie.txtDni.setBackground(Color.red);
            state = false;
        }

        if (validateFields.validateEmail(vie.txtEmail.getText())) {
            vie.txtEmail.setBackground(Color.green);
        } else {
            vie.txtEmail.setBackground(Color.red);
            state = false;
        }

        return state;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vie.btnGuardar) {
            if (verificar()) {
                em.setNames(vie.txtNombres.getText());
                em.setDni(vie.txtDni.getText());
                em.setEmail(vie.txtEmail.getText());
                em.setCode(em.generarCode());
                em.setId(0);
                em.setPsw(em.getDni());
                edao.registrarEmpleado(em);
                JOptionPane.showMessageDialog(vie, "Empleado Añadido Exitosamente.");
                clean();
                
            }else {
            	JOptionPane.showMessageDialog(vie, "Error Formato Incorrecto");
            }
        }else if(e.getSource()==vie.btnCerrar) {
        	int response = JOptionPane.showConfirmDialog(vie, "¿Desea salir de la Aplicación?", "Confirmar salida", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		    if (response == JOptionPane.YES_OPTION) {
		        System.exit(0);
		    }
        }else if(e.getSource()==vie.btnSalir) {
			if(va!=null) {
				va.setVisible(true);
			}
			vie.dispose();
        }
    }
    private void clean() {
    	vie.txtNombres.setText("");
    	vie.txtNombres.setBackground(Color.white);
    	vie.txtDni.setText("");
    	vie.txtDni.setBackground(Color.white);
    	vie.txtEmail.setText("");
    	vie.txtEmail.setBackground(Color.white);
    }
}

