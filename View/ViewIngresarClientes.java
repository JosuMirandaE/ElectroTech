package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import controller.logic_IngresarClientes;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ViewIngresarClientes extends JFrame {

    private static final long serialVersionUID = 1L;
    public JPanel contentPane;
    public int mouseX, mouseY;
    public JLabel lbl_titulo;
    public JTextField txtNombre;
    public JTextField txtDireccion;
    public JTextField txtTelefono;
    public JTextField txtEmail;
    public JTextField txtRazonSocial;
    public JTextField txtCedula;
    public JButton btnGuardar;
    public JButton btnActualizar;
    public JButton btnRegresar;
    public JButton btnCerrar;
    public static ViewClientes vc;
    public static ViewGeneralEmpleados vge;


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ViewClientes vc = new ViewClientes(vge); // Instancia de ViewClientes
                    ViewIngresarClientes frame = new ViewIngresarClientes(vc);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ViewIngresarClientes(ViewClientes vc) {
        this.vc = vc;
        setUndecorated(true);
        setIconImage(Toolkit.getDefaultToolkit().getImage(ViewIngresarClientes.class.getResource("/images/upc.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 900, 580);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel pn_fondo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image image = new ImageIcon(getClass().getResource("/images/horizon.png")).getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        pn_fondo.setBounds(0, 0, 900, 650);
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

        ViewComponents viewComponents = new ViewComponents();

        JLabel lbl_titulo = new JLabel("INGRESAR CLIENTE");
        lbl_titulo.setForeground(new Color(255, 255, 255));
        lbl_titulo.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 50));
        lbl_titulo.setBounds(150, 20, 600, 50);
        pn_fondo.add(lbl_titulo);

        int labelWidth = 250;
        int labelHeight = 40;
        int textFieldWidth = 350;  // Tamaño reducido
        int textFieldHeight = 40;  // Tamaño reducido
        int yPosition = 100;
        int xLabel = 100;
        int xTextField = 360;

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setForeground(Color.WHITE);
        lblNombre.setFont(new Font("Arial", Font.BOLD, 24));
        lblNombre.setBounds(xLabel, yPosition, labelWidth, labelHeight);
        pn_fondo.add(lblNombre);

        txtNombre = viewComponents.new RoundedTextField(20);
        txtNombre.setFont(new Font("Arial", Font.PLAIN, 20));
        txtNombre.setBounds(xTextField, yPosition, textFieldWidth, textFieldHeight);
        pn_fondo.add(txtNombre);

        yPosition += 70;
        JLabel lblDireccion = new JLabel("Dirección:");
        lblDireccion.setForeground(Color.WHITE);
        lblDireccion.setFont(new Font("Arial", Font.BOLD, 24));
        lblDireccion.setBounds(xLabel, yPosition, labelWidth, labelHeight);
        pn_fondo.add(lblDireccion);

         txtDireccion = viewComponents.new RoundedTextField(20);
        txtDireccion.setFont(new Font("Arial", Font.PLAIN, 20));
        txtDireccion.setBounds(xTextField, yPosition, textFieldWidth, textFieldHeight);
        pn_fondo.add(txtDireccion);

        yPosition += 70;
        JLabel lblTelefono = new JLabel("Teléfono:");
        lblTelefono.setForeground(Color.WHITE);
        lblTelefono.setFont(new Font("Arial", Font.BOLD, 24));
        lblTelefono.setBounds(xLabel, yPosition, labelWidth, labelHeight);
        pn_fondo.add(lblTelefono);

         txtTelefono = viewComponents.new RoundedTextField(20);
        txtTelefono.setFont(new Font("Arial", Font.PLAIN, 20));
        txtTelefono.setBounds(xTextField, yPosition, textFieldWidth, textFieldHeight);
        pn_fondo.add(txtTelefono);

        yPosition += 70;
        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setForeground(Color.WHITE);
        lblEmail.setFont(new Font("Arial", Font.BOLD, 24));
        lblEmail.setBounds(xLabel, yPosition, labelWidth, labelHeight);
        pn_fondo.add(lblEmail);

        txtEmail = viewComponents.new RoundedTextField(20);
        txtEmail.setFont(new Font("Arial", Font.PLAIN, 20));
        txtEmail.setBounds(xTextField, yPosition, textFieldWidth, textFieldHeight);
        pn_fondo.add(txtEmail);

        yPosition += 70;
        JLabel lblCedula = new JLabel("Número de Cédula:");
        lblCedula.setForeground(Color.WHITE);
        lblCedula.setFont(new Font("Arial", Font.BOLD, 24));
        lblCedula.setBounds(xLabel, yPosition, labelWidth, labelHeight);
        pn_fondo.add(lblCedula);

        txtCedula = viewComponents.new RoundedTextField(20);
        txtCedula.setFont(new Font("Arial", Font.PLAIN, 20));
        txtCedula.setBounds(xTextField, yPosition, textFieldWidth, textFieldHeight);
        pn_fondo.add(txtCedula);

        yPosition += 70;  // Mover el botón de guardar más arriba
        btnGuardar = viewComponents.new RoundedButton("GUARDAR");
        btnGuardar.setFont(new Font("Arial", Font.BOLD, 24));
        btnGuardar.setBounds(xTextField + 100, yPosition, 210, 40);
        pn_fondo.add(btnGuardar);

//        btnActualizar = viewComponents.new RoundedButton("ACTUALIZAR");
//        btnActualizar.setFont(new Font("Arial", Font.BOLD, 24));
//        btnActualizar.setBounds(xTextField - 250, yPosition, 210, 40); // Colocado al lado izquierdo de GUARDAR
//        pn_fondo.add(btnActualizar);

        btnRegresar = new JButton();
        btnRegresar.setBounds(830, 509, 60, 60);
        btnRegresar.setIcon(new ImageIcon(ViewIngresarClientes.class.getResource("/images/regresar.png")));
        btnRegresar.setBorderPainted(false);
        btnRegresar.setContentAreaFilled(false);
        btnRegresar.setFocusPainted(false);
        pn_fondo.add(btnRegresar);

        btnCerrar = new JButton();
        btnCerrar.setBounds(840, 11, 50, 50);
        btnCerrar.setIcon(new ImageIcon(ViewIngresarClientes.class.getResource("/images/cerrarDegradado.png")));
        btnCerrar.setBorderPainted(false);
        btnCerrar.setContentAreaFilled(false);
        btnCerrar.setFocusPainted(false);
        pn_fondo.add(btnCerrar);
        
        logic_IngresarClientes lic=new logic_IngresarClientes(this, vc);
    }
}

