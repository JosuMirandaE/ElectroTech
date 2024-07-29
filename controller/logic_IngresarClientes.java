package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JOptionPane;
import View.ViewClientes;
import View.ViewGeneralEmpleados;
import View.ViewIngresarClientes;
import model.client;
import model.clienteDAO;

public class logic_IngresarClientes implements ActionListener {
    private ViewIngresarClientes vic;
    private ViewClientes vc;
    private ViewGeneralEmpleados vge;
    private client cl;
    private clienteDAO cdao = new clienteDAO();

    public logic_IngresarClientes(ViewIngresarClientes vic, ViewClientes vc) {
        this.vic = vic;
        this.vc = vc;
        this.cl=new client();
        activador();
    }

    private void activador() {
        vic.btnGuardar.addActionListener(this);
//        vic.btnActualizar.addActionListener(this);
        vic.btnCerrar.addActionListener(this);
        vic.btnRegresar.addActionListener(this);
    }

    private boolean verifica() {
        boolean state = true;
        if (validateFields.validateNames(vic.txtNombre.getText())) {
            vic.txtNombre.setBackground(Color.green);
        } else {
            vic.txtNombre.setBackground(Color.red);
            state = false;
        }

        if (validateFields.validateDni(Long.parseLong(vic.txtCedula.getText()))) {
            vic.txtCedula.setBackground(Color.green);
        } else {
            vic.txtCedula.setBackground(Color.red);
            state = false;
        }

        if (validateFields.validateContact(vic.txtTelefono.getText())) {
            vic.txtTelefono.setBackground(Color.green);
        } else {
            vic.txtTelefono.setBackground(Color.red);
            state = false;
        }
        if (validateFields.validateDireccion(vic.txtDireccion.getText())) {
            vic.txtDireccion.setBackground(Color.green);
        } else {
            vic.txtDireccion.setBackground(Color.red);
            state = false;
        }
        if (validateFields.validateEmail(vic.txtEmail.getText())) {
            vic.txtEmail.setBackground(Color.green);
        } else {
            vic.txtEmail.setBackground(Color.red);
            state = false;
        }

        return state;
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vic.btnGuardar) {
            if (verifica()) {
                cl = new client(); // Crea un nuevo cliente
                cl.setNombres(vic.txtNombre.getText());
                cl.setDireccion(vic.txtDireccion.getText());
                cl.setContacto(vic.txtTelefono.getText());
                cl.setEmail(vic.txtEmail.getText());
                cl.setDni(vic.txtCedula.getText());
                if (cdao.registrarCliente(cl)) {
                    JOptionPane.showMessageDialog(vic, "Cliente Añadido Exitosamente.");
                    clean();
                } else {
                    JOptionPane.showMessageDialog(vic, "Error al añadir el cliente.");
                }
            } else {
                JOptionPane.showMessageDialog(vic, "Error de Formato Incorrecto");
            }
        } else if (e.getSource() == vic.btnCerrar) {
            int response = JOptionPane.showConfirmDialog(vic, "¿Desea salir de la Aplicación?", "Confirmar salida", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        } else if (e.getSource() == vic.btnRegresar) {
            ViewClientes vc = new ViewClientes(vge);
            vc.setVisible(true);
            vic.dispose();
        }
    }

    // Método para cargar datos del cliente
    private void loadClientData(String dni) {
        client existingClient = cdao.getClientByDni(dni); // Implementa este método en clienteDAO
        if (existingClient != null) {
            vic.txtCedula.setText(String.valueOf(existingClient.getId()));
            vic.txtNombre.setText(existingClient.getNombres());
            vic.txtDireccion.setText(existingClient.getDireccion());
            vic.txtTelefono.setText(existingClient.getContacto());
            // Aquí puedes cargar otros datos si es necesario
        } else {
            JOptionPane.showMessageDialog(vic, "Cliente no encontrado.");
        }
    }


    private void clean() {
        vic.txtNombre.setText("");
        vic.txtNombre.setBackground(Color.white);
        vic.txtCedula.setText("");
        vic.txtCedula.setBackground(Color.white);
        vic.txtEmail.setText("");
        vic.txtEmail.setBackground(Color.white);
        vic.txtTelefono.setText("");
        vic.txtTelefono.setBackground(Color.white);
        vic.txtDireccion.setText("");
        vic.txtDireccion.setBackground(Color.white);
    }
}

