package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import View.ViewComponents.RoundedButton;
import controller.logic_EntradasSalidas;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ViewEntradasSalidas extends JFrame {

    private static final long serialVersionUID = 1L;
    public JPanel contentPane;
    public int mouseX, mouseY;
    public JLabel lblInventario;
    public JTable table;
    public JButton btnAgregarEntrada;
    public JButton btnAgregarSalida;
    public JButton btnCerrar;
    public JButton btnRegresar;
    public static ViewAdmin va;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ViewEntradasSalidas frame = new ViewEntradasSalidas(va);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ViewEntradasSalidas(ViewAdmin va) {
        this.va = va;
        setUndecorated(true);
        setIconImage(Toolkit.getDefaultToolkit().getImage(ViewEntradasSalidas.class.getResource("/images/upc.png")));
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

        JLabel lblInventario = new JLabel("Entradas y Salidas");
        lblInventario.setForeground(Color.WHITE);
        lblInventario.setFont(new Font("Arial", Font.BOLD, 28));
        lblInventario.setBounds(219, 31, 257, 40);
        pn_fondo.add(lblInventario);

        // Crear el modelo de la tabla
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Producto");
        model.addColumn("Estado");
        model.addColumn("Cantidad");
        model.addColumn("Total");

        // Crear el JTable con el modelo
        table = new JTable(model);
        table.setFont(new Font("Arial", Font.PLAIN, 16));
        table.setBounds(50, 100, 600, 300);

        // Agregar la tabla a un JScrollPane para poder hacer scroll si hay muchos datos
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 100, 600, 300);
        pn_fondo.add(scrollPane);     
     

        btnCerrar = new JButton();
        btnCerrar.setBounds(640, 11, 50, 50);
        btnCerrar.setIcon(new ImageIcon(ViewEntradasSalidas.class.getResource("/images/cerrarDegradado.png")));
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

        logic_EntradasSalidas les = new logic_EntradasSalidas(this, va);
    }
}