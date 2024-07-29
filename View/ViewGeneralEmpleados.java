package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import View.ViewComponents.RoundedTextField;
import controller.logic_GeneralEmpleados;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewGeneralEmpleados extends JFrame {
    private static final long serialVersionUID = 1L;
    public JTable tableClientes;
    public JPanel contentPane;
    public int mouseX, mouseY;
    public JLabel lbl_titulo;
    public JTextField txtNombreEmpleado;
    public JButton btnCerrar;
    public JButton btnListarProveedores;
    public JButton btnListadoClientes;
    public JButton btnStock;
    public JButton btnVenta;
    public JButton btnActualizarContrasena;
    public JButton btnRegresar;
    public static String username;  // Variable para almacenar el nombre del usuario
    
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ViewGeneralEmpleados frame = new ViewGeneralEmpleados(username);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ViewGeneralEmpleados(String username) {
        this.username = username;  // Inicializar la variable
        setUndecorated(true);
        setIconImage(Toolkit.getDefaultToolkit().getImage(ViewGeneralEmpleados.class.getResource("/images/upc.png")));
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
            @Override
            public void mousePressed(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
            }
        });
        pn_fondo.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int newX = getX() + e.getX() - mouseX;
                int newY = getY() + e.getY() - mouseY;
                setLocation(newX, newY);
            }
        });

        btnCerrar = new JButton();
        btnCerrar.setBounds(440, 11, 50, 50);
        pn_fondo.add(btnCerrar);
        btnCerrar.setIcon(new ImageIcon(ViewGeneralEmpleados.class.getResource("/images/cerrarDegradado.png")));
        btnCerrar.setBorderPainted(false);
        btnCerrar.setContentAreaFilled(false);
        btnCerrar.setFocusPainted(false);

        lbl_titulo = new JLabel("BIENVENIDO");
        lbl_titulo.setForeground(new Color(255, 255, 255));
        lbl_titulo.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 50));
        lbl_titulo.setBounds(134, 11, 222, 50);
        pn_fondo.add(lbl_titulo);

        // Añadir texto para imprimir el nombre del empleado
        txtNombreEmpleado = new ViewComponents().new RoundedTextField(10);
        txtNombreEmpleado.setFont(new Font("Arial Narrow", Font.PLAIN, 25));
        txtNombreEmpleado.setBounds(95, 70, 300, 40);
        txtNombreEmpleado.setText(username);  // Mostrar el nombre del usuario
        pn_fondo.add(txtNombreEmpleado);

        // Añadir botones de forma independiente con RoundedButton de ViewComponents
        int buttonWidth = 350;
        int buttonHeight = 70;
        int centerX = (pn_fondo.getWidth() - buttonWidth) / 2;
        int yPosition = 130;

        // Usar los métodos de la clase ViewComponents para crear los botones redondeados
        ViewComponents viewComponents = new ViewComponents();
        
        btnListarProveedores = viewComponents.new RoundedButton("LISTAR PROVEEDORES");
        btnListarProveedores.setFont(new Font("Arial", Font.BOLD, 18));  // Letras más grandes
        btnListarProveedores.setBounds(centerX, yPosition, buttonWidth, buttonHeight);
        pn_fondo.add(btnListarProveedores);
        
        yPosition += 80;
        btnListadoClientes = viewComponents.new RoundedButton("LISTADO DE CLIENTES");
        btnListadoClientes.setFont(new Font("Arial", Font.BOLD, 18));  // Letras más grandes
        btnListadoClientes.setBounds(centerX, yPosition, buttonWidth, buttonHeight);
        pn_fondo.add(btnListadoClientes);

        yPosition += 80;
        btnStock = viewComponents.new RoundedButton("REGISTRAR PRODUCTO");
        btnStock.setFont(new Font("Arial", Font.BOLD, 18));  // Letras más grandes
        btnStock.setBounds(centerX, yPosition, buttonWidth, buttonHeight);
        pn_fondo.add(btnStock);

        yPosition += 80;
        btnVenta = viewComponents.new RoundedButton("VENTA");
        btnVenta.setFont(new Font("Arial", Font.BOLD, 18));  // Letras más grandes
        btnVenta.setBounds(centerX, yPosition, buttonWidth, buttonHeight);
        pn_fondo.add(btnVenta);
        
        btnRegresar = new JButton();
        btnRegresar.setBounds(430, 629, 60, 60);
        btnRegresar.setIcon(new ImageIcon(ViewGeneralEmpleados.class.getResource("/images/regresar.png")));
        btnRegresar.setBorderPainted(false);
        btnRegresar.setContentAreaFilled(false);
        btnRegresar.setFocusPainted(false);
        pn_fondo.add(btnRegresar);
        
        logic_GeneralEmpleados lge=new logic_GeneralEmpleados(this, null);
    }
}
