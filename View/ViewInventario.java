package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.logic_Inventario;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ViewInventario extends JFrame {

    private static final long serialVersionUID = 1L;
    public JPanel contentPane;
    public int mouseX, mouseY;
    public JLabel lblInventario;
    public JTable tableInventario;
    public JButton btnAnadirProducto;
    public JButton btnVerEntradasSalidas;
    public JButton btnCerrar;
    public JButton btnRegresar;
    public static ViewAdmin va;
    public ViewInventario vi;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ViewInventario frame = new ViewInventario(va);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ViewInventario(ViewAdmin va) {
        this.va = va;
        setUndecorated(true);
        setIconImage(Toolkit.getDefaultToolkit().getImage(ViewInventario.class.getResource("/images/upc.png")));
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

        JLabel lblInventario = new JLabel("Inventario");
        lblInventario.setForeground(Color.WHITE);
        lblInventario.setFont(new Font("Arial", Font.BOLD, 28));
        lblInventario.setBounds(300, 30, 150, 40);
        pn_fondo.add(lblInventario);
        
        String[] columnNames = {"ID", "Nombre", "precio", "Stock","Proveedor"};
        DefaultTableModel model = new DefaultTableModel(null, columnNames);
        tableInventario = new JTable(model);
        tableInventario.setFont(new Font("Arial", Font.PLAIN, 16));
        tableInventario.setFillsViewportHeight(true);
        
        
//        DefaultTableModel model = new DefaultTableModel();
//        model.addColumn("Código");
//        model.addColumn("Producto");
//        model.addColumn("Cantidad");

        JTable table = new JTable(model);
        table.setFont(new Font("Arial", Font.PLAIN, 16));
        table.setBounds(50, 100, 600, 300);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 100, 600, 300);
        pn_fondo.add(scrollPane);

        btnAnadirProducto = viewComponents.new RoundedButton("Añadir Producto");
        btnAnadirProducto.setFont(new Font("Arial", Font.BOLD, 16));
        btnAnadirProducto.setBounds(50, 420, 250, 40);
        pn_fondo.add(btnAnadirProducto);

        btnVerEntradasSalidas = viewComponents.new RoundedButton("Ver Entradas y Salidas");
        btnVerEntradasSalidas.setFont(new Font("Arial", Font.BOLD, 16));
        btnVerEntradasSalidas.setBounds(320, 420, 250, 40);
        pn_fondo.add(btnVerEntradasSalidas);

        btnCerrar = new JButton();
        btnCerrar.setBounds(640, 11, 50, 50);
        btnCerrar.setIcon(new ImageIcon(ViewInventario.class.getResource("/images/cerrarDegradado.png")));
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

        logic_Inventario li = new logic_Inventario(this, va);
    }
}
