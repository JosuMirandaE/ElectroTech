package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import View.ViewComponents.RoundedTextField;
import controller.logic_RecuperarContraseña;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ViewRecuperarContraseña extends JFrame {

    private static final long serialVersionUID = 1L;
    public JPanel contentPane;
    public int mouseX, mouseY;
    public JTextField txtCedula;
    public JButton btnRecuperar;
    public JTextField txtContraseña;
    public JButton btnCerrar;
    public JButton btnRegresar;
    public static ViewAdmin va;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ViewRecuperarContraseña frame = new ViewRecuperarContraseña(va);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ViewRecuperarContraseña(ViewAdmin va) {
    	this.va=va;
        setUndecorated(true);
        setIconImage(Toolkit.getDefaultToolkit().getImage(ViewRecuperarContraseña.class.getResource("/images/upc.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel pn_fondo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image image = new ImageIcon(getClass().getResource("/images/fondx.png")).getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        pn_fondo.setBounds(0, 0, 600, 400);
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

        JLabel lblRecuperarContraseña = new JLabel("Recuperar Contraseña");
        lblRecuperarContraseña.setForeground(Color.WHITE);
        lblRecuperarContraseña.setFont(new Font("Arial", Font.BOLD, 28));
        lblRecuperarContraseña.setBounds(50, 11, 382, 40);
        pn_fondo.add(lblRecuperarContraseña);

        JLabel lblCedula = new JLabel("Ingrese su número de cédula:");
        lblCedula.setForeground(Color.WHITE);
        lblCedula.setFont(new Font("Arial", Font.BOLD, 18));
        lblCedula.setBounds(50, 100, 300, 20);
        pn_fondo.add(lblCedula);

        txtCedula = viewComponents.new RoundedTextField(20);
        txtCedula.setFont(new Font("Arial", Font.PLAIN, 16));
        txtCedula.setBounds(50, 130, 250, 30);
        pn_fondo.add(txtCedula);

        btnRecuperar = viewComponents.new RoundedButton("Recuperar");
        btnRecuperar.setFont(new Font("Arial", Font.BOLD, 16));
        btnRecuperar.setBounds(320, 130, 150, 30);
        pn_fondo.add(btnRecuperar);

        txtContraseña = viewComponents.new RoundedTextField(20);
        txtContraseña.setFont(new Font("Arial", Font.PLAIN, 16));
        txtContraseña.setBounds(50, 200, 420, 100);
        pn_fondo.add(txtContraseña);

        JLabel lblContraseña = new JLabel("Contraseña recuperada:");
        lblContraseña.setForeground(Color.WHITE);
        lblContraseña.setFont(new Font("Arial", Font.BOLD, 18));
        lblContraseña.setBounds(50, 170, 300, 20);
        pn_fondo.add(lblContraseña);

        btnCerrar = new JButton();
        btnCerrar.setBounds(540, 11, 50, 50);
        btnCerrar.setIcon(new ImageIcon(ViewRecuperarContraseña.class.getResource("/images/cerrarDegradado.png")));
        btnCerrar.setBorderPainted(false);
        btnCerrar.setContentAreaFilled(false);
        btnCerrar.setFocusPainted(false);
        pn_fondo.add(btnCerrar);

        btnRegresar = new JButton();
        btnRegresar.setBounds(520, 320, 60, 60);
        btnRegresar.setIcon(new ImageIcon(ViewRecuperarContraseña.class.getResource("/images/regresar.png")));
        btnRegresar.setBorderPainted(false);
        btnRegresar.setContentAreaFilled(false);
        btnRegresar.setFocusPainted(false);
        pn_fondo.add(btnRegresar);
        
        logic_RecuperarContraseña lrc= new logic_RecuperarContraseña(this,va);
    }
    
}
