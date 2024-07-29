package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import View.ViewComponents.RoundedButton;
import controller.logic_Clientes;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ViewClientes extends JFrame {

    private static final long serialVersionUID = 1L;
    public JTable tableClientes;
    public JPanel contentPane;
    public int mouseX, mouseY;
    public JLabel lblClientes;
    public JButton btnIngresarCliente;
    public JButton btnCerrar;
    public JButton btnRegresar;
    public RoundedButton btnVerHistorial;
    private JFrame parentFrame;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ViewClientes frame = new ViewClientes(null);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ViewClientes(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        setUndecorated(true);
        setIconImage(Toolkit.getDefaultToolkit().getImage(ViewClientes.class.getResource("/images/upc.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 700, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel pn_fondo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image image = new ImageIcon(getClass().getResource("/images/fondoGran.png")).getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        pn_fondo.setBounds(0, 0, 700, 500);
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

        JLabel lblClientes = new JLabel("Clientes");
        lblClientes.setForeground(Color.WHITE);
        lblClientes.setFont(new Font("Arial", Font.BOLD, 28));
        lblClientes.setBounds(268, 32, 195, 40);
        pn_fondo.add(lblClientes);

        // Configuración del JTable
        String[] columnNames = {"ID","Nombres", "Direccion", "Contacto"};
        DefaultTableModel model = new DefaultTableModel(null, columnNames);
        tableClientes = new JTable(model);
        tableClientes.setFont(new Font("Arial", Font.PLAIN, 16));
        tableClientes.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(tableClientes);
        scrollPane.setBounds(50, 100, 600, 300);
        pn_fondo.add(scrollPane);

        btnIngresarCliente = viewComponents.new RoundedButton("Ingresar Cliente");
        btnIngresarCliente.setFont(new Font("Arial", Font.BOLD, 16));
        btnIngresarCliente.setBounds(50, 420, 250, 40);
        pn_fondo.add(btnIngresarCliente);
        btnIngresarCliente.addActionListener(e -> {
            ViewIngresarClientes ingresarClientes = new ViewIngresarClientes(null);
            ingresarClientes.setVisible(true);
        });

        btnVerHistorial = viewComponents.new RoundedButton("Ver Historial");
        btnVerHistorial.setFont(new Font("Arial", Font.BOLD, 16));
        btnVerHistorial.setBounds(320, 420, 250, 40);
        pn_fondo.add(btnVerHistorial);

        btnCerrar = new JButton();
        btnCerrar.setBounds(640, 11, 50, 50);
        btnCerrar.setIcon(new ImageIcon(ViewClientes.class.getResource("/images/cerrarDegradado.png")));
        btnCerrar.setBorderPainted(false);
        btnCerrar.setContentAreaFilled(false);
        btnCerrar.setFocusPainted(false);
        pn_fondo.add(btnCerrar);

        btnRegresar = new JButton();
        btnRegresar.setBounds(620, 420, 60, 60);
        btnRegresar.setIcon(new ImageIcon(ViewBuscarEmpleados.class.getResource("/images/regresar.png")));
        btnRegresar.setBorderPainted(false);
        btnRegresar.setContentAreaFilled(false);
        btnRegresar.setFocusPainted(false);
        pn_fondo.add(btnRegresar);

        logic_Clientes lc = new logic_Clientes(this, parentFrame);
    }
}

