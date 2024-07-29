package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import View.ViewAdmin;
import View.ViewBuscarEmpleados;
import model.employee;
import model.employeeDAO;

public class logic_BuscarEmpleados implements ActionListener {
    private ViewBuscarEmpleados vbe;
    private ViewAdmin va;
    private employeeDAO edao;

    public logic_BuscarEmpleados(ViewBuscarEmpleados vbe, ViewAdmin va) {
        this.vbe = vbe;
        this.va = va;
        this.edao = new employeeDAO();
        activador();
        cargarDatos();
    }

    private void activador() {
        vbe.btnCerrar.addActionListener(this);
        vbe.btnRegresar.addActionListener(this);
    }

    private void cargarDatos() {
        List<employee> employees = edao.getAllEmployees();
        DefaultTableModel model = (DefaultTableModel) vbe.tableResultados.getModel();
        model.setRowCount(0); // Limpiar la tabla antes de cargar los datos

        for (employee emp : employees) {
            model.addRow(new Object[]{
                emp.getId(),
                emp.getNames(),
                emp.getDni(),
                emp.getCode(),
                emp.getUser(),
                emp.getPsw()
            });
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vbe.btnCerrar) {
            int response = JOptionPane.showConfirmDialog(vbe, "¿Desea salir de la Aplicación?", "Confirmar salida", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        } else if (e.getSource() == vbe.btnRegresar) {
            if (va != null) {
                va.setVisible(true);
            }
            vbe.dispose();
        }
    }
    
}