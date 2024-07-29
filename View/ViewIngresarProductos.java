package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import controller.logic_IngresarProducto;
import model.productoDAO;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ViewIngresarProductos extends JFrame {

    private static final long serialVersionUID = 1L;
    public JPanel contentPane;
    public int mouseX, mouseY;
    public JLabel lbl_titulo;
    public JTextField txtNombre;
    public JTextField txtDescripcion;
    public JTextField txtPrecio;
    public JSpinner spnCantidad;
    public JComboBox<String> cmbProveedor;
    public JButton btnGuardar;
    public JButton btnRegresar;
    public JButton btnCerrar;
    public JButton btnActualizar;
    public static ViewInventario vi;
    private JFrame parentFrame;


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ViewIngresarProductos frame = new ViewIngresarProductos(null);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ViewIngresarProductos(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        setUndecorated(true);
        setIconImage(Toolkit.getDefaultToolkit().getImage(ViewIngresarProductos.class.getResource("/images/upc.png")));
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

        JLabel lbl_titulo = new JLabel("INGRESAR PRODUCTO");
        lbl_titulo.setForeground(new Color(255, 255, 255));
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
        JLabel lblDescripcion = new JLabel("Descripción:");
        lblDescripcion.setForeground(Color.WHITE);
        lblDescripcion.setFont(new Font("Arial", Font.BOLD, 24));
        lblDescripcion.setBounds(xLabel, yPosition, labelWidth, labelHeight);
        pn_fondo.add(lblDescripcion);

        txtDescripcion = viewComponents.new RoundedTextField(20);
        txtDescripcion.setFont(new Font("Arial", Font.PLAIN, 20));
        txtDescripcion.setBounds(xTextField, yPosition, textFieldWidth, textFieldHeight);
        pn_fondo.add(txtDescripcion);

        yPosition += 70;
        JLabel lblPrecio = new JLabel("Precio:");
        lblPrecio.setForeground(Color.WHITE);
        lblPrecio.setFont(new Font("Arial", Font.BOLD, 24));
        lblPrecio.setBounds(xLabel, yPosition, labelWidth, labelHeight);
        pn_fondo.add(lblPrecio);

        txtPrecio = viewComponents.new RoundedTextField(20);
        txtPrecio.setFont(new Font("Arial", Font.PLAIN, 20));
        txtPrecio.setBounds(xTextField, yPosition, textFieldWidth, textFieldHeight);
        pn_fondo.add(txtPrecio);

        yPosition += 70;
        JLabel lblCantidad = new JLabel("Cantidad en Stock:");
        lblCantidad.setForeground(Color.WHITE);
        lblCantidad.setFont(new Font("Arial", Font.BOLD, 24));
        lblCantidad.setBounds(xLabel, yPosition, labelWidth, labelHeight);
        pn_fondo.add(lblCantidad);

        spnCantidad = viewComponents.new RoundedSpinner();
        spnCantidad.setModel(new SpinnerNumberModel(0, 0, 1000, 1));
        spnCantidad.setFont(new Font("Arial", Font.PLAIN, 20));
        spnCantidad.setBounds(xTextField, yPosition, textFieldWidth, textFieldHeight);
        pn_fondo.add(spnCantidad);

        yPosition += 70;
        JLabel lblProveedor = new JLabel("Proveedor:");
        lblProveedor.setForeground(Color.WHITE);
        lblProveedor.setFont(new Font("Arial", Font.BOLD, 24));
        lblProveedor.setBounds(xLabel, yPosition, labelWidth, labelHeight);
        pn_fondo.add(lblProveedor);

        productoDAO productoDAO = new productoDAO();
        List<String> proveedores = productoDAO.getAllProveedores();

        // Create and add the combo box to the panel
        cmbProveedor = new JComboBox<>(proveedores.toArray(new String[0]));
        cmbProveedor.setFont(new Font("Arial", Font.PLAIN, 20));
        cmbProveedor.setBounds(xTextField, yPosition, textFieldWidth, textFieldHeight);
        pn_fondo.add(cmbProveedor);

        yPosition += 80;
        btnGuardar = viewComponents.new RoundedButton("GUARDAR");
        btnGuardar.setFont(new Font("Arial", Font.BOLD, 24));
        btnGuardar.setBounds(xTextField + 100, yPosition, 250, 50);
        pn_fondo.add(btnGuardar);
        
        btnActualizar = viewComponents.new RoundedButton("ACTUALIZAR");
        btnActualizar.setFont(new Font("Arial", Font.BOLD, 24));
        btnActualizar.setBounds(100, yPosition, 250, 50);
        pn_fondo.add(btnActualizar);
        
        btnRegresar = new JButton();
        btnRegresar.setBounds(830, 500, 60, 60);
        btnRegresar.setIcon(new ImageIcon(ViewIngresarProductos.class.getResource("/images/regresar.png")));
        btnRegresar.setBorderPainted(false);
        btnRegresar.setContentAreaFilled(false);
        btnRegresar.setFocusPainted(false);
        pn_fondo.add(btnRegresar);

        btnCerrar = new JButton();
        btnCerrar.setBounds(840, 11, 50, 50);
        btnCerrar.setIcon(new ImageIcon(ViewIngresarProductos.class.getResource("/images/cerrarDegradado.png")));
        btnCerrar.setBorderPainted(false);
        btnCerrar.setContentAreaFilled(false);
        btnCerrar.setFocusPainted(false);
        pn_fondo.add(btnCerrar);

        logic_IngresarProducto lip = new logic_IngresarProducto(this, parentFrame);
    }
}
