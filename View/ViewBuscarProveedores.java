package View;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.logic_BuscarProveedores;

public class ViewBuscarProveedores extends JFrame {

    private static final long serialVersionUID = 1L;
    public JTable tableProveedores;
    public JPanel contentPane;
    public int mouseX, mouseY;
    public JLabel lblBuscarProveedores;
    public JButton btnIngresarProveedor;
    public JButton btnCerrar;
    public JButton btnRegresar;
    private JFrame parentFrame;
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ViewBuscarProveedores frame = new ViewBuscarProveedores(null);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    
    public ViewBuscarProveedores(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        setUndecorated(true);
        setIconImage(Toolkit.getDefaultToolkit().getImage(ViewBuscarProveedores.class.getResource("/images/upc.png")));
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

        JLabel lblBuscarProveedores = new JLabel("Buscar Proveedores");
        lblBuscarProveedores.setForeground(Color.WHITE);
        lblBuscarProveedores.setFont(new Font("Arial", Font.BOLD, 28));
        lblBuscarProveedores.setBounds(184, 30, 300, 40);
        pn_fondo.add(lblBuscarProveedores);

        // Configuración del JTable
        String[] columnNames = {"ID Proveedor", "Nombre", "Código", "Razón Social"};
        DefaultTableModel model = new DefaultTableModel(null, columnNames);
        tableProveedores = new JTable(model);
        tableProveedores.setFont(new Font("Arial", Font.PLAIN, 16));
        tableProveedores.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(tableProveedores);
        scrollPane.setBounds(50, 100, 600, 300);
        pn_fondo.add(scrollPane);

        btnIngresarProveedor = viewComponents.new RoundedButton("Ingresar Proveedor");
        btnIngresarProveedor.setFont(new Font("Arial", Font.BOLD, 16));
        btnIngresarProveedor.setBounds(50, 420, 250, 40);
        pn_fondo.add(btnIngresarProveedor);

        btnCerrar = new JButton();
        btnCerrar.setBounds(640, 11, 50, 50);
        btnCerrar.setIcon(new ImageIcon(ViewBuscarProveedores.class.getResource("/images/cerrarDegradado.png")));
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
        logic_BuscarProveedores lbp = new logic_BuscarProveedores(this, parentFrame);
    }

        
}
