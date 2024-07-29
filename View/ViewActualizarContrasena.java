package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controller.logic_ActualizarContraseña;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ViewActualizarContrasena extends JFrame {

    private static final long serialVersionUID = 1L;
    public JPanel contentPane;
    private int mouseX, mouseY;
    public JTextField txtContrasenaActual;
    public JTextField txtNuevaContrasena;
    public JTextField txtConfirmarContrasena;
    public JPasswordField newPasswordField;
    public JPasswordField confirmPasswordField;
    public JButton btnActualizar;
    public JButton btnCerrar;
    public JButton btnRegresar;
    public static ViewGeneralEmpleados vge;

    public static void main(String[] args) {
    	
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ViewActualizarContrasena frame = new ViewActualizarContrasena(vge);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        
    }

    public ViewActualizarContrasena(ViewGeneralEmpleados vge) {
    	this.vge = vge;
        setUndecorated(true);
        setIconImage(Toolkit.getDefaultToolkit().getImage(ViewActualizarContrasena.class.getResource("/images/upc.png")));
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

        JLabel lblTitulo = new JLabel("Actualizar Contraseña");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 28));
        lblTitulo.setBounds(50, 11, 382, 40);
        pn_fondo.add(lblTitulo);

        JLabel lblContrasenaActual = new JLabel("Ingrese su contraseña actual:");
        lblContrasenaActual.setForeground(Color.WHITE);
        lblContrasenaActual.setFont(new Font("Arial", Font.BOLD, 18));
        lblContrasenaActual.setBounds(50, 70, 300, 20);
        pn_fondo.add(lblContrasenaActual);

        txtContrasenaActual = viewComponents.new RoundedTextField(20);
        txtContrasenaActual.setFont(new Font("Arial", Font.PLAIN, 16));
        txtContrasenaActual.setBounds(50, 100, 500, 30);
        pn_fondo.add(txtContrasenaActual);

        JLabel lblNuevaContrasena = new JLabel("Ingrese su nueva contraseña:");
        lblNuevaContrasena.setForeground(Color.WHITE);
        lblNuevaContrasena.setFont(new Font("Arial", Font.BOLD, 18));
        lblNuevaContrasena.setBounds(50, 150, 300, 20);
        pn_fondo.add(lblNuevaContrasena);

        txtNuevaContrasena = viewComponents.new RoundedTextField(20);
        txtNuevaContrasena.setFont(new Font("Arial", Font.PLAIN, 16));
        txtNuevaContrasena.setBounds(50, 180, 500, 30);
        pn_fondo.add(txtNuevaContrasena);

        JLabel lblConfirmarContrasena = new JLabel("Confirmar nueva contraseña:");
        lblConfirmarContrasena.setForeground(Color.WHITE);
        lblConfirmarContrasena.setFont(new Font("Arial", Font.BOLD, 18));
        lblConfirmarContrasena.setBounds(50, 230, 300, 20);
        pn_fondo.add(lblConfirmarContrasena);

        txtConfirmarContrasena = viewComponents.new RoundedTextField(20);
        txtConfirmarContrasena.setFont(new Font("Arial", Font.PLAIN, 16));
        txtConfirmarContrasena.setBounds(50, 260, 500, 30);
        pn_fondo.add(txtConfirmarContrasena);

        btnActualizar = viewComponents.new RoundedButton("Actualizar");
        btnActualizar.setFont(new Font("Arial", Font.BOLD, 16));
        btnActualizar.setBounds(230, 320, 150, 30);
        pn_fondo.add(btnActualizar);

        btnCerrar = new JButton();
        btnCerrar.setBounds(540, 11, 50, 50);
        btnCerrar.setIcon(new ImageIcon(ViewActualizarContrasena.class.getResource("/images/cerrarDegradado.png")));
        btnCerrar.setBorderPainted(false);
        btnCerrar.setContentAreaFilled(false);
        btnCerrar.setFocusPainted(false);
        pn_fondo.add(btnCerrar);

        btnRegresar = new JButton();
        btnRegresar.setBounds(520, 320, 60, 60);
        btnRegresar.setIcon(new ImageIcon(ViewActualizarContrasena.class.getResource("/images/regresar.png")));
        btnRegresar.setBorderPainted(false);
        btnRegresar.setContentAreaFilled(false);
        btnRegresar.setFocusPainted(false);
        pn_fondo.add(btnRegresar);
        
        logic_ActualizarContraseña lac=new logic_ActualizarContraseña(this,vge);
        
    }
}
