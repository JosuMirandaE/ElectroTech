package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import View.ViewComponents.RoundedButton;
import controller.logic_Admin;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ViewAdmin extends JFrame {
    private static final long serialVersionUID = 1L;
    public JPanel contentPane;
    public int mouseX, mouseY;
    public JButton btnCerrar;
    public JLabel lbl_titulo;
    public JButton btnIngresarEmpleado;
    public JButton btnRecuperarContrasena;
    public JButton btnBuscarEmpleados;
    public JButton btnListadoClientes;
    public JButton btnListarProveedores;
    public JButton btnStock;
    public JButton btnInforme;
    public JButton btnRegresar;
    public static ViewLogin vl;


    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ViewAdmin frame = new ViewAdmin(vl);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ViewAdmin(ViewLogin vl) {
    	this.vl = vl;
        setUndecorated(true);
        setIconImage(Toolkit.getDefaultToolkit().getImage(ViewAdmin.class.getResource("/images/upc.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 700);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel pn_fondo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image image = new ImageIcon(getClass().getResource("/images/fondoAdmin.png")).getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        pn_fondo.setBounds(0, 0, 500, 700);
        contentPane.add(pn_fondo);
        pn_fondo.setLayout(null);

        pn_fondo.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
            }
        });
        pn_fondo.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                int newX = e.getXOnScreen() - mouseX;
                int newY = e.getYOnScreen() - mouseY;
                setLocation(newX, newY);
            }
        });

        btnCerrar = new JButton();
        btnCerrar.setBounds(440, 11, 50, 50);
        pn_fondo.add(btnCerrar);
        btnCerrar.setIcon(new ImageIcon(ViewAdmin.class.getResource("/images/cerrarDegradado.png")));
        btnCerrar.setBorderPainted(false);
        btnCerrar.setContentAreaFilled(false);
        btnCerrar.setFocusPainted(false);
        

        lbl_titulo = new JLabel("ADMINISTRADOR");
        lbl_titulo.setForeground(new Color(255, 255, 255));
        lbl_titulo.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 50));
        lbl_titulo.setBounds(10, 11, 356, 50);
        pn_fondo.add(lbl_titulo);

        // Añadir botones de forma independiente con RoundedButton de ViewComponents
        int buttonWidth = 350;
        int buttonHeight = 70;
        int centerX = (pn_fondo.getWidth() - buttonWidth) / 2;
        int yPosition = 100;

        // Usar los métodos de la clase ViewComponents para crear los botones redondeados
        ViewComponents viewComponents = new ViewComponents();
        btnIngresarEmpleado = viewComponents.new RoundedButton("INGRESAR EMPLEADO");
        btnIngresarEmpleado.setFont(new Font("Arial", Font.BOLD, 18));  // Letras más grandes
        btnIngresarEmpleado.setBounds(centerX, yPosition, buttonWidth, buttonHeight);
        pn_fondo.add(btnIngresarEmpleado);     

        yPosition += 80;
        btnRecuperarContrasena = viewComponents.new RoundedButton("RECUPERAR CONTRASEÑA");
        btnRecuperarContrasena.setFont(new Font("Arial", Font.BOLD, 18));  // Letras más grandes
        btnRecuperarContrasena.setBounds(centerX, yPosition, buttonWidth, buttonHeight);
        pn_fondo.add(btnRecuperarContrasena);        

        yPosition += 80;
        btnBuscarEmpleados = viewComponents.new RoundedButton("BUSCAR EMPLEADOS");
        btnBuscarEmpleados.setFont(new Font("Arial", Font.BOLD, 18));  // Letras más grandes
        btnBuscarEmpleados.setBounds(centerX, yPosition, buttonWidth, buttonHeight);
        pn_fondo.add(btnBuscarEmpleados);
        
        yPosition += 80;
        btnListadoClientes = viewComponents.new RoundedButton("LISTADO DE CLIENTES");
        btnListadoClientes.setFont(new Font("Arial", Font.BOLD, 18));  // Letras más grandes
        btnListadoClientes.setBounds(centerX, yPosition, buttonWidth, buttonHeight);
        pn_fondo.add(btnListadoClientes);

        yPosition += 80;
        btnListarProveedores = viewComponents.new RoundedButton("LISTAR PROVEEDORES");
        btnListarProveedores.setFont(new Font("Arial", Font.BOLD, 18));  // Letras más grandes
        btnListarProveedores.setBounds(centerX, yPosition, buttonWidth, buttonHeight);
        pn_fondo.add(btnListarProveedores);
        
        yPosition += 80;
        btnInforme = viewComponents.new RoundedButton("GENERAR INFORME");
        btnInforme.setFont(new Font("Arial", Font.BOLD, 18));  // Letras más grandes
        btnInforme.setBounds(centerX, yPosition, buttonWidth, buttonHeight);
        pn_fondo.add(btnInforme);

        yPosition += 80;
        btnStock = viewComponents.new RoundedButton("STOCK");
        btnStock.setFont(new Font("Arial", Font.BOLD, 18));  // Letras más grandes
        btnStock.setBounds(centerX, yPosition, buttonWidth, buttonHeight);
        pn_fondo.add(btnStock);
               
        // Botón de regresar "interfaz anterior"
        btnRegresar = new JButton();
        btnRegresar.setBounds(430, 629, 60, 60);
        btnRegresar.setIcon(new ImageIcon(ViewAdmin.class.getResource("/images/regresar.png")));
        btnRegresar.setBorderPainted(false);
        btnRegresar.setContentAreaFilled(false);
        btnRegresar.setFocusPainted(false);
        pn_fondo.add(btnRegresar);
        
        logic_Admin la=new logic_Admin(this, vl);
    }
}