package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controller.logic_IngresarEmpleado;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;



public class ViewIngresarEmpleado extends JFrame {

    private static final long serialVersionUID = 1L;
    public JPanel contentPane;
    public int mouseX, mouseY;
    public JLabel lbl_titulo;
    public JTextField txtNombres;
    public JTextField txtDni;
    public JTextField txtCodigo;
    public JTextField txtEmail;
    public JTextField txtFechaIngreso;
    public JButton btnGuardar;
    public JButton btnSalir;
    public JButton btnCerrar;
    public static ViewAdmin va;
    public JLabel lblEmail;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ViewIngresarEmpleado frame = new ViewIngresarEmpleado(va);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public ViewIngresarEmpleado(ViewAdmin va) {
        this.va = va;
   
        setUndecorated(true);
        setIconImage(Toolkit.getDefaultToolkit().getImage(ViewIngresarEmpleado.class.getResource("/images/upc.png")));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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

        btnCerrar = new JButton();
        btnCerrar.setBounds(840, 11, 50, 50);
        pn_fondo.add(btnCerrar);
        btnCerrar.setIcon(new ImageIcon(ViewIngresarEmpleado.class.getResource("/images/cerrarDegradado.png")));
        btnCerrar.setBorderPainted(false);
        btnCerrar.setContentAreaFilled(false);
        btnCerrar.setFocusPainted(false);

        JLabel lbl_titulo = new JLabel("INGRESAR EMPLEADO");
        lbl_titulo.setForeground(new Color(255, 255, 255));
        lbl_titulo.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 50));
        lbl_titulo.setBounds(115, 11, 500, 50);
        pn_fondo.add(lbl_titulo);

        ViewComponents viewComponents = new ViewComponents();

        int labelWidth = 250;
        int labelHeight = 40;
        int textFieldWidth = 400;
        int textFieldHeight = 50;
        int centerX = (pn_fondo.getWidth() - textFieldWidth - labelWidth - 20) / 2;
        int yPosition = 100;

        JLabel lblNombres = new JLabel("Nombres Completos:");
        lblNombres.setForeground(Color.WHITE);
        lblNombres.setFont(new Font("Arial", Font.BOLD, 24));
        lblNombres.setBounds(centerX, yPosition, labelWidth, labelHeight);
        pn_fondo.add(lblNombres);

        txtNombres = viewComponents.new RoundedTextField(20);
        txtNombres.setFont(new Font("Arial", Font.PLAIN, 20));
        txtNombres.setBounds(centerX + labelWidth + 20, yPosition, textFieldWidth, textFieldHeight);
        pn_fondo.add(txtNombres);

        yPosition += 80;
        JLabel lblDni = new JLabel("DNI:");
        lblDni.setForeground(Color.WHITE);
        lblDni.setFont(new Font("Arial", Font.BOLD, 24));
        lblDni.setBounds(centerX, yPosition, labelWidth, labelHeight);
        pn_fondo.add(lblDni);

        txtDni = viewComponents.new RoundedTextField(20);
        txtDni.setFont(new Font("Arial", Font.PLAIN, 20));
        txtDni.setBounds(centerX + labelWidth + 20, yPosition, textFieldWidth, textFieldHeight);
        pn_fondo.add(txtDni);

        yPosition += 80;
        lblEmail = new JLabel("Email:");
        lblEmail.setForeground(Color.WHITE);
        lblEmail.setFont(new Font("Arial", Font.BOLD, 24));
        lblEmail.setBounds(centerX, yPosition, labelWidth, labelHeight);
        pn_fondo.add(lblEmail);

        txtEmail = viewComponents.new RoundedTextField(20);
        txtEmail.setFont(new Font("Arial", Font.PLAIN, 20));
        txtEmail.setBounds(centerX + labelWidth + 20, yPosition, textFieldWidth, textFieldHeight);
        pn_fondo.add(txtEmail);

        yPosition += 80;
        btnGuardar = viewComponents.new RoundedButton("GUARDAR");
        btnGuardar.setFont(new Font("Arial", Font.BOLD, 24));
        btnGuardar.setBounds(centerX + labelWidth + 20, yPosition, textFieldWidth, textFieldHeight);
        pn_fondo.add(btnGuardar);
        
        btnSalir = new JButton();
        btnSalir.setBounds(830, 509, 60, 60);
        btnSalir.setIcon(new ImageIcon(ViewIngresarEmpleado.class.getResource("/images/regresar.png")));
        btnSalir.setBorderPainted(false);
        btnSalir.setContentAreaFilled(false);
        btnSalir.setFocusPainted(false);
        pn_fondo.add(btnSalir);

        logic_IngresarEmpleado vie = new logic_IngresarEmpleado(this,va);
    }
}
