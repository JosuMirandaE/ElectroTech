package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controller.logic_IngresarProveedores;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ViewIngresarProveedores extends JFrame {

    private static final long serialVersionUID = 1L;
    public JPanel contentPane;
    public int mouseX, mouseY;
    public JLabel lbl_titulo;
    public JTextField txtNombres;
    public JTextField txtEmail;
    public JTextField txtDni;
    public JTextField txtCodigo;
    public JTextField txtTelefono;
    public JTextField txtRazonSocial;
    public JButton btnGuardar;
    public JButton btnRegresar;
    public JButton btnCerrar;
    public static ViewBuscarProveedores vbp;


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ViewIngresarProveedores frame = new ViewIngresarProveedores(vbp);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ViewIngresarProveedores(ViewBuscarProveedores vbp) {
    	this.vbp = vbp;
        setUndecorated(true);
        setIconImage(Toolkit.getDefaultToolkit().getImage(ViewIngresarProveedores.class.getResource("/images/upc.png")));
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
        pn_fondo.setBounds(0, 0, 900, 580);
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

        JLabel lbl_titulo = new JLabel("INGRESAR PROVEEDOR");
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

        JLabel lblNombres = new JLabel("Nombres Completos:");
        lblNombres.setForeground(Color.WHITE);
        lblNombres.setFont(new Font("Arial", Font.BOLD, 24));
        lblNombres.setBounds(xLabel, yPosition, labelWidth, labelHeight);
        pn_fondo.add(lblNombres);

        txtNombres = viewComponents.new RoundedTextField(20);
        txtNombres.setFont(new Font("Arial", Font.PLAIN, 20));
        txtNombres.setBounds(xTextField, yPosition, textFieldWidth, textFieldHeight);
        pn_fondo.add(txtNombres);

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
        JLabel lblDni = new JLabel("DNI:");
        lblDni.setForeground(Color.WHITE);
        lblDni.setFont(new Font("Arial", Font.BOLD, 24));
        lblDni.setBounds(xLabel, yPosition, labelWidth, labelHeight);
        pn_fondo.add(lblDni);

        txtDni = viewComponents.new RoundedTextField(20);
        txtDni.setFont(new Font("Arial", Font.PLAIN, 20));
        txtDni.setBounds(xTextField, yPosition, textFieldWidth, textFieldHeight);
        pn_fondo.add(txtDni);

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
        JLabel lblRazonSocial = new JLabel("Razón Social:");
        lblRazonSocial.setForeground(Color.WHITE);
        lblRazonSocial.setFont(new Font("Arial", Font.BOLD, 24));
        lblRazonSocial.setBounds(xLabel, yPosition, labelWidth, labelHeight);
        pn_fondo.add(lblRazonSocial);

        txtRazonSocial = viewComponents.new RoundedTextField(20);
        txtRazonSocial.setFont(new Font("Arial", Font.PLAIN, 20));
        txtRazonSocial.setBounds(xTextField, yPosition, textFieldWidth, textFieldHeight);
        pn_fondo.add(txtRazonSocial);

        yPosition += 60;  // Mover el botón de guardar más arriba
        btnGuardar = viewComponents.new RoundedButton("GUARDAR");
        btnGuardar.setFont(new Font("Arial", Font.BOLD, 24));
        btnGuardar.setBounds(xTextField + 100, yPosition, 250, 50);
        pn_fondo.add(btnGuardar);

        btnRegresar = new JButton();
        btnRegresar.setBounds(830, 500, 60, 60);
        btnRegresar.setIcon(new ImageIcon(ViewIngresarProveedores.class.getResource("/images/regresar.png")));
        btnRegresar.setBorderPainted(false);
        btnRegresar.setContentAreaFilled(false);
        btnRegresar.setFocusPainted(false);
        pn_fondo.add(btnRegresar);
        
        btnCerrar = new JButton();
        btnCerrar.setBounds(840, 11, 50, 50);
        btnCerrar.setIcon(new ImageIcon(ViewIngresarProveedores.class.getResource("/images/cerrarDegradado.png")));
        btnCerrar.setBorderPainted(false);
        btnCerrar.setContentAreaFilled(false);
        btnCerrar.setFocusPainted(false);
        pn_fondo.add(btnCerrar);

        logic_IngresarProveedores lip=new logic_IngresarProveedores(this,vbp);
    }
}
