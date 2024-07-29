package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

import View.ViewAdmin;
import View.ViewRecuperarContraseña;
import model.employee;
import model.employeeDAO;

public class logic_RecuperarContraseña implements ActionListener {
    private ViewRecuperarContraseña vrc;
    private ViewAdmin va;
    private employeeDAO edao = new employeeDAO();

    public logic_RecuperarContraseña(ViewRecuperarContraseña vrc, ViewAdmin va) {
        this.vrc = vrc;
        this.va = va;
        activador();
    }

    private void activador() {
        vrc.btnRecuperar.addActionListener(this);
        vrc.btnRegresar.addActionListener(this);
        vrc.btnCerrar.addActionListener(this);
    }

    private boolean verificar() {
        boolean state = true;
        String dni = vrc.txtCedula.getText();
        if (validateDni(dni)) {
            vrc.txtCedula.setBackground(Color.green);
        } else {
            vrc.txtCedula.setBackground(Color.red);
            state = false;
        }
        return state;
    }

    private boolean validateDni(String dni) {
        // Dummy validation, replace with actual validation logic
        return dni.length() == 10; // Assume valid DNI has 10 characters
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vrc.btnRecuperar) {
            if (verificar()) {
                String dni = vrc.txtCedula.getText();
                employee em = edao.getEmployeeByDni(dni);
                if (em != null) {
                    String password = em.getPsw();
                    vrc.txtContraseña.setText("Su contraseña es: " + password);
                } else {
                    vrc.txtContraseña.setText("Empleado no encontrado.");
                }
            } else {
                vrc.txtContraseña.setText("Por favor, ingrese un número de cédula válido.");
            }
        } else if (e.getSource() == vrc.btnCerrar) {
            int response = JOptionPane.showConfirmDialog(vrc, "¿Desea salir de la Aplicación?", "Confirmar salida", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        } else if (e.getSource() == vrc.btnRegresar) {
            if (va != null) {
                va.setVisible(true);
            }
            vrc.dispose();
        }
    }
}
