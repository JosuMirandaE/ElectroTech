package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import View.ViewComponents.RoundedButton;
import controller.DatabaseConnection;
import controller.logic_HistorialCompras;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ViewHistorialCompras extends JFrame {
    
    private static final long serialVersionUID = 1L;
    public JPanel contentPane;
    public int mouseX, mouseY;
    public JLabel lblInventario;
    public JTable table;
    public RoundedButton btnBuscar;
    public JButton btnCerrar;
    public JButton btnRegresar;
    public JComboBox<String> clientes;
    public static ViewGeneralEmpleados vge;
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ViewHistorialCompras frame = new ViewHistorialCompras(vge);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ViewHistorialCompras(ViewGeneralEmpleados vge) {
        this.vge = vge;
        setUndecorated(true);
        setIconImage(Toolkit.getDefaultToolkit().getImage(ViewHistorialCompras.class.getResource("/images/upc.png")));
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

        JLabel lblInventario = new JLabel("Historial de Compras");
        lblInventario.setForeground(Color.WHITE);
        lblInventario.setFont(new Font("Arial", Font.BOLD, 28));
        lblInventario.setBounds(50, 20, 300, 40);
        pn_fondo.add(lblInventario);

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Producto");
        model.addColumn("Cantidad");
        model.addColumn("Fecha");

        table = new JTable(model);
        table.setFont(new Font("Arial", Font.PLAIN, 16));
        table.setBounds(50, 100, 600, 300);

        JScrollPane historial = new JScrollPane(table);
        historial.setBounds(50, 120, 600, 300);
        pn_fondo.add(historial);

        clientes = new JComboBox<>();
        clientes.setFont(new Font("Arial", Font.PLAIN, 16));
        clientes.setBounds(50, 70, 370, 30);
        pn_fondo.add(clientes);

        btnBuscar = viewComponents.new RoundedButton("Buscar");
        btnBuscar.setFont(new Font("Arial", Font.BOLD, 16));
        btnBuscar.setBounds(440, 70, 110, 35);
        pn_fondo.add(btnBuscar);

        btnCerrar = new JButton();
        btnCerrar.setBounds(640, 11, 50, 50);
        btnCerrar.setIcon(new ImageIcon(ViewHistorialCompras.class.getResource("/images/cerrarDegradado.png")));
        btnCerrar.setBorderPainted(false);
        btnCerrar.setContentAreaFilled(false);
        btnCerrar.setFocusPainted(false);
        pn_fondo.add(btnCerrar);

        btnRegresar = new JButton();
        btnRegresar.setBounds(630, 429, 60, 60);
        btnRegresar.setIcon(new ImageIcon(ViewBuscarEmpleados.class.getResource("/images/regresar.png")));
        btnRegresar.setBorderPainted(false);
        btnRegresar.setContentAreaFilled(false);
        btnRegresar.setFocusPainted(false);
        pn_fondo.add(btnRegresar);

        loadClientes(); // Cargar clientes cuando se crea la vista

        // Crear la lógica después de inicializar los componentes
        logic_HistorialCompras lh = new logic_HistorialCompras(this, vge);
    }

    private void loadClientes() {
        System.out.println("Cargando clientes...");
        try (Connection con = DatabaseConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM clientes")) {

            while (rs.next()) {
                String cliente = rs.getString("nombres");
                System.out.println("Cliente encontrado: " + cliente);
                clientes.addItem(cliente); // Usar clientes directamente
            }
        } catch (SQLException e) {
            System.err.println("Error al cargar clientes: " + e.getMessage());
        }
    }

    private void loadHistorialCompras() {
        String selectedCliente = (String) clientes.getSelectedItem();
        if (selectedCliente != null) {
            try (Connection con = DatabaseConnection.getConnection();
                 PreparedStatement pstmt = con.prepareStatement(
                     "SELECT producto, cantidad, fecha FROM ventas WHERE cliente = ?")) {

                pstmt.setString(1, selectedCliente);
                ResultSet rs = pstmt.executeQuery();

                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.setRowCount(0); // Limpiar tabla antes de agregar nuevas filas

                while (rs.next()) {
                    String producto = rs.getString("producto");
                    int cantidad = rs.getInt("cantidad");
                    Date fecha = rs.getDate("fecha");
                    model.addRow(new Object[]{producto, cantidad, fecha});
                }
            } catch (SQLException e) {
                System.err.println("Error al cargar historial de compras: " + e.getMessage());
            }
        }
    }
}
