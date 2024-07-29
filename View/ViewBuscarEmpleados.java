package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.logic_BuscarEmpleados;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ViewBuscarEmpleados extends JFrame {

    private static final long serialVersionUID = 1L;
    public JTable tableResultados;
    public JPanel contentPane;
    public int mouseX, mouseY;
    public JLabel lblBuscarEmpleados;
    public JTextField txtCedulaEmpleado;
    public JButton btnInformeGlobal;
    public JButton btnInformeEmpleado;
    public JButton btnCerrar;
    public JButton btnRegresar;
    public static ViewAdmin va;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ViewBuscarEmpleados frame = new ViewBuscarEmpleados(va);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ViewBuscarEmpleados(ViewAdmin va) {
        this.va = va;
        setUndecorated(true);
        setIconImage(Toolkit.getDefaultToolkit().getImage(ViewBuscarEmpleados.class.getResource("/images/upc.png")));
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

        JLabel lblBuscarEmpleados = new JLabel("Buscar Empleados");
        lblBuscarEmpleados.setForeground(Color.WHITE);
        lblBuscarEmpleados.setFont(new Font("Arial", Font.BOLD, 28));
        lblBuscarEmpleados.setBounds(189, 30, 300, 40);
        pn_fondo.add(lblBuscarEmpleados);

        // Configuración del JTable
        String[] columnNames = {"ID", "Nombres", "DNI", "Código", "User", "Password"};
        DefaultTableModel model = new DefaultTableModel(null,columnNames);
        tableResultados = new JTable(model);
        tableResultados.setFont(new Font("Arial", Font.PLAIN, 16));
        tableResultados.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(tableResultados);
        scrollPane.setBounds(50, 100, 600, 250);
        pn_fondo.add(scrollPane);

        btnCerrar = new JButton();
        btnCerrar.setBounds(640, 11, 50, 50);
        btnCerrar.setIcon(new ImageIcon(ViewBuscarEmpleados.class.getResource("/images/cerrarDegradado.png")));
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
        
        logic_BuscarEmpleados lbe =new logic_BuscarEmpleados(this,va);
    }
}

