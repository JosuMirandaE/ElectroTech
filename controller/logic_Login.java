package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import View.ViewAdmin;
import View.ViewGeneralEmpleados;
import View.ViewLogin;
import model.employee;
import model.employeeDAO;

public class logic_Login implements ActionListener {
    
    private ViewLogin vl;
    private employeeDAO edao = new employeeDAO();
    
    public logic_Login(ViewLogin vl) {
        this.vl = vl;
        activador();
    }

    private void activador() {
        vl.btnCerrar.addActionListener(this);
        vl.btnEntrar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vl.btnCerrar) {
            int response = JOptionPane.showConfirmDialog(vl, "¿Desea salir de la Aplicación?", "Confirmar salida", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        } else if (e.getSource() == vl.btnEntrar) {
            String username = vl.txt_usuario.getText();
            String password = new String(vl.txt_clave.getPassword());

            employee emp = edao.validateUser(username, password);
            if (emp != null) {
                JOptionPane.showMessageDialog(vl, "Bienvenido " + username);
                ViewGeneralEmpleados vge = new ViewGeneralEmpleados(username); // Pasa el nombre del usuario aquí
                vge.setVisible(true);
                vl.dispose();
            } else if (username.equals("admin") && password.equals("admin123")) {
                // Abrir ViewAdmin
                JOptionPane.showMessageDialog(vl, "Bienvenido " + username);
                ViewAdmin viewAdmin = new ViewAdmin(null);
                viewAdmin.setVisible(true);
                vl.dispose(); // Cerrar la ventana de login
            } else {
                JOptionPane.showMessageDialog(vl, "Nombre de usuario o contraseña incorrectos.");
            }
        }
    }
}