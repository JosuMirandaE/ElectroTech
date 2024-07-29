package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import View.ViewComponents.RoundedComboBox;
import View.ViewComponents.RoundedSpinner;
import View.ViewComponents.RoundedButton;
import controller.logic_VentaProductos;
import model.clienteDAO;
import model.employeeDAO;
import model.productoDAO;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class ViewVentaProductos extends JFrame {

    private static final long serialVersionUID = 1L;
    public JPanel contentPane;
    public int mouseX, mouseY;
    public JComboBox<String> cmbProducto;
    public JComboBox<String> cmbEmpleado;
    public JComboBox<String> cmbCliente;
    public RoundedSpinner spCantidad;
    public RoundedButton btnVenderProducto;
    public JButton btnCerrar;
    public JButton btnRegresar;
    public static ViewGeneralEmpleados vge;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ViewVentaProductos frame = new ViewVentaProductos(vge);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ViewVentaProductos(ViewGeneralEmpleados vge) {
        this.vge = vge;
        setUndecorated(true);
        setIconImage(Toolkit.getDefaultToolkit().getImage(ViewVentaProductos.class.getResource("/images/upc.png")));
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

        ViewComponents viewComponents = new ViewComponents();

        JLabel lbl_titulo = new JLabel("VENTA DE PRODUCTOS");
        lbl_titulo.setForeground(Color.WHITE);
        lbl_titulo.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 50));
        lbl_titulo.setBounds(150, 20, 600, 50);
        pn_fondo.add(lbl_titulo);

        int labelWidth = 250;
        int labelHeight = 40;
        int textFieldWidth = 350;
        int textFieldHeight = 40;
        int yPosition = 100;
        int xLabel = 100;
        int xTextField = 360;

        JLabel lblCliente = new JLabel("Cliente:");
        lblCliente.setForeground(Color.WHITE);
        lblCliente.setFont(new Font("Arial", Font.BOLD, 24));
        lblCliente.setBounds(xLabel, yPosition, labelWidth, labelHeight);
        pn_fondo.add(lblCliente);

        clienteDAO cDAO = new clienteDAO();
        List<String> clientes = cDAO.getAllClientesCombo();
        
        cmbCliente = new JComboBox<>(clientes.toArray(new String[0]));
        cmbCliente.setFont(new Font("Arial", Font.PLAIN, 20));
        cmbCliente.setBounds(xTextField, yPosition, textFieldWidth, textFieldHeight);
        pn_fondo.add(cmbCliente);
        
        yPosition += 70;
        JLabel lblEmpleado = new JLabel("Empleado:");
        lblEmpleado.setForeground(Color.WHITE);
        lblEmpleado.setFont(new Font("Arial", Font.BOLD, 24));
        lblEmpleado.setBounds(xLabel, yPosition, labelWidth, labelHeight);
        pn_fondo.add(lblEmpleado);

        employeeDAO employeeDAO = new employeeDAO();
        List<String> empleados = employeeDAO.getAllEmployeesCombo();

        cmbEmpleado = new JComboBox<>(empleados.toArray(new String[0]));
        cmbEmpleado.setFont(new Font("Arial", Font.PLAIN, 20));
        cmbEmpleado.setBounds(xTextField, yPosition, textFieldWidth, textFieldHeight);
        pn_fondo.add(cmbEmpleado);
        
        
        yPosition += 70;
        JLabel lblProducto = new JLabel("Producto:");
        lblProducto.setForeground(Color.WHITE);
        lblProducto.setFont(new Font("Arial", Font.BOLD, 24));
        lblProducto.setBounds(xLabel, yPosition, labelWidth, labelHeight);
        pn_fondo.add(lblProducto);

        productoDAO productoDAO = new productoDAO();
        List<String> productos = productoDAO.getAllProductosCombo();

        cmbProducto = new JComboBox<>(productos.toArray(new String[0]));
        cmbProducto.setFont(new Font("Arial", Font.PLAIN, 20));
        cmbProducto.setBounds(xTextField, yPosition, textFieldWidth, textFieldHeight);
        pn_fondo.add(cmbProducto);

        yPosition += 70;
        JLabel lblCantidad = new JLabel("Cantidad:");
        lblCantidad.setForeground(Color.WHITE);
        lblCantidad.setFont(new Font("Arial", Font.BOLD, 24));
        lblCantidad.setBounds(xLabel, yPosition, labelWidth, labelHeight);
        pn_fondo.add(lblCantidad);

        spCantidad = viewComponents.new RoundedSpinner();
        spCantidad.setModel(new SpinnerNumberModel(0, 0, 1000, 1));
        spCantidad.setFont(new Font("Arial", Font.PLAIN, 20));
        spCantidad.setBounds(xTextField, yPosition, textFieldWidth, textFieldHeight);
        pn_fondo.add(spCantidad);

        yPosition += 80;
        btnVenderProducto = viewComponents.new RoundedButton("Vender Producto");
        btnVenderProducto.setFont(new Font("Arial", Font.BOLD, 24));
        btnVenderProducto.setBounds(xTextField + 100, yPosition, 250, 50);
        pn_fondo.add(btnVenderProducto);

        btnCerrar = new JButton();
        btnCerrar.setBounds(840, 11, 50, 50);
        btnCerrar.setIcon(new ImageIcon(ViewVentaProductos.class.getResource("/images/cerrarDegradado.png")));
        btnCerrar.setBorderPainted(false);
        btnCerrar.setContentAreaFilled(false);
        btnCerrar.setFocusPainted(false);
        pn_fondo.add(btnCerrar);

        btnRegresar = new JButton();
        btnRegresar.setBounds(830, 509, 60, 60);
        btnRegresar.setIcon(new ImageIcon(ViewVentaProductos.class.getResource("/images/regresar.png")));
        btnRegresar.setBorderPainted(false);
        btnRegresar.setContentAreaFilled(false);
        btnRegresar.setFocusPainted(false);
        pn_fondo.add(btnRegresar);

        logic_VentaProductos lvp = new logic_VentaProductos(this, vge);

        // Start a thread to check the stock
        new Thread(new Runnable() {
            @Override
            public void run() {
                checkStock();
            }
        }).start();
    }

    private void checkStock() {
        productoDAO productoDAO = new productoDAO();
        List<String> productos = productoDAO.getAllProductosCombo();
        
        for (String producto : productos) {
            int stock = productoDAO.obtenerStockProducto(producto);
            if (stock <= 5) {
                JOptionPane.showMessageDialog(this, "Advertencia: El stock del producto '" + producto + "' es de " + stock + ". Es menor o igual a 5.", "Advertencia de Stock Bajo", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}


