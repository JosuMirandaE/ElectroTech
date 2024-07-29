package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

import View.ViewAdmin;
import View.ViewBuscarEmpleados;
import View.ViewBuscarProveedores;
import View.ViewClientes;
import View.ViewIngresarEmpleado;
import View.ViewInventario;
import View.ViewLogin;
import View.ViewRecuperarContraseña;

public class logic_Admin implements ActionListener {

    private ViewAdmin va;
    private ViewLogin vl;

    public logic_Admin(ViewAdmin va, ViewLogin vl) {
        this.va = va;
        this.vl = vl;
        activador();
    }

    public void activador() {
        va.btnBuscarEmpleados.addActionListener(this);
        va.btnStock.addActionListener(this);
        va.btnCerrar.addActionListener(this);
        va.btnRecuperarContrasena.addActionListener(this);
        va.btnIngresarEmpleado.addActionListener(this);
        va.btnRegresar.addActionListener(this);
        va.btnListarProveedores.addActionListener(this);
        va.btnListadoClientes.addActionListener(this); // Añade el listener para el botón de Listado de Clientes
        va.btnInforme.addActionListener(this); // Añade el listener para el botón de Generar Informe
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == va.btnCerrar) {
            int response = JOptionPane.showConfirmDialog(va, "¿Desea salir de la Aplicación?", "Confirmar salida", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        } else if (source == va.btnRegresar) {
            ViewLogin vl = new ViewLogin();
            if (vl != null) {
                vl.setVisible(true);
            }
            va.dispose();
        } else if (source == va.btnBuscarEmpleados) {
            ViewBuscarEmpleados vbe = new ViewBuscarEmpleados(va);
            vbe.setVisible(true);
            va.setVisible(false);
        } else if (source == va.btnRecuperarContrasena) {
            ViewRecuperarContraseña vrc = new ViewRecuperarContraseña(va);
            vrc.setVisible(true);
            va.setVisible(false);
        } else if (source == va.btnIngresarEmpleado) {
            ViewIngresarEmpleado vie = new ViewIngresarEmpleado(va);
            vie.setVisible(true);
            va.setVisible(false);
        } else if (source == va.btnListarProveedores) {
            ViewBuscarProveedores vlp = new ViewBuscarProveedores(va);
            vlp.setVisible(true);
            va.setVisible(false);
        } else if (source == va.btnListadoClientes) {
            ViewClientes vlc = new ViewClientes(va);
            vlc.setVisible(true);
            va.setVisible(false);
        } else if (source == va.btnStock) {
            ViewInventario vi = new ViewInventario(va);
            vi.setVisible(true);
            va.setVisible(false);
        } else if (source == va.btnInforme) {
            generarInformePdf();
        }
    }

    public void generarInformePdf() {
        try {
            // Crear directorio si no existe
            File directorio = new File("src/informe");
            if (!directorio.exists()) {
                directorio.mkdirs();
            }

            // Crear archivo PDF
            File archivo = new File("src/informe/InformeVentas.txt");
            FileWriter writer = new FileWriter(archivo);

            // Obtener datos de la base de datos
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/electro", "root", "1234");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT idventas, fecha, cliente, total FROM ventas");

            // Escribir datos en el archivo PDF
            writer.write("ID Venta\tFecha\tCliente\tTotal\n");
            while (rs.next()) {
                writer.write(rs.getInt("idventas") + "\t" + rs.getDate("fecha") + "\t" + rs.getString("cliente") + "\t" + rs.getDouble("total") + "\n");
            }

            writer.close();
            con.close();

            JOptionPane.showMessageDialog(va, "Informe PDF generado exitosamente.");
        } catch (IOException e1) {
            ((Throwable) e1).printStackTrace();
        }catch( SQLException e2) {
        	e2.printStackTrace();
        }
    }
}


