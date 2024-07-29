package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.List;

import controller.logic_Login;

public class ViewLogin extends JFrame {
    private static final long serialVersionUID = 1L;
    public JPanel contentPane;
    public int mouseX, mouseY;
    public JTextField txt_usuario;
    public JPasswordField txt_clave;
    public JLabel lbl_logo;
    public JLabel lvl_bienvenidos;
    public JLabel lbl_introduccion;
    public JLabel lbl_usuario2;
    public JLabel lbl_clave2;
    public JButton btnCerrar;
    public JButton btnEntrar;
    public JButton btnYoutube;
    public JButton btnFacebook;
    public JButton btnTwitter;
    public JButton btnModo;

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ViewLogin frame = new ViewLogin();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ViewLogin() {
        setUndecorated(true); // Quita la barra de título
        setIconImage(Toolkit.getDefaultToolkit().getImage(ViewLogin.class.getResource("/images/upc.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1100, 570);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel pn_fondo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image image = new ImageIcon(getClass().getResource("/images/fondoGod.png")).getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        pn_fondo.setBounds(0, 0, 1100, 600);
        contentPane.add(pn_fondo);
        pn_fondo.setLayout(null);

        // Listener para arrastrar y soltar
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

        // Botón de cerrar
        btnCerrar = new JButton();
        btnCerrar.setBounds(1040, 11, 50, 50);
        pn_fondo.add(btnCerrar);
        btnCerrar.setIcon(new ImageIcon(ViewLogin.class.getResource("/images/cerrarDegradado.png")));
        btnCerrar.setBorderPainted(false);
        btnCerrar.setContentAreaFilled(false);
        btnCerrar.setFocusPainted(false);

        lbl_logo = new JLabel("");
        lbl_logo.setIcon(new ImageIcon(ViewLogin.class.getResource("/images/LogoProyect.png")));
        lbl_logo.setBounds(-20, -28, 553, 196);
        pn_fondo.add(lbl_logo);

        lvl_bienvenidos = new JLabel("BIENVENIDOS");
        lvl_bienvenidos.setForeground(new Color(0, 0, 0));
        lvl_bienvenidos.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 50));
        lvl_bienvenidos.setBounds(722, 60, 241, 64); // Ajuste en la posición Y
        pn_fondo.add(lvl_bienvenidos);

        lbl_introduccion = new JLabel("- Ingresa tus datos -");
        lbl_introduccion.setForeground(new Color(0, 0, 0));
        lbl_introduccion.setFont(new Font("Tw Cen MT", Font.BOLD, 33));
        lbl_introduccion.setBounds(707, 117, 274, 64); // Ajuste en la posición Y
        pn_fondo.add(lbl_introduccion);

        // TextField personalizado con bordes redondeados
        txt_usuario = new ViewComponents().new RoundedTextField(10);
        txt_usuario.setFont(new Font("Arial Narrow", Font.PLAIN, 25));
        txt_usuario.setBounds(690, 177, 326, 44); // Ajuste en la posición Y
        pn_fondo.add(txt_usuario);

        // Imagen de usuario
        lbl_usuario2 = new JLabel();
        ImageIcon usuarioIcon = new ImageIcon(ViewLogin.class.getResource("/images/usuario2.png"));
        Image usuarioImage = usuarioIcon.getImage().getScaledInstance(50, 44, Image.SCALE_SMOOTH);
        lbl_usuario2.setIcon(new ImageIcon(usuarioImage));
        lbl_usuario2.setBounds(635, 177, 50, 44); // Ajuste en la posición Y
        pn_fondo.add(lbl_usuario2);

        // JPasswordField para la clave
        txt_clave = new ViewComponents().new RoundedPasswordField(10);
        txt_clave.setFont(new Font("Arial Narrow", Font.PLAIN, 25));
        txt_clave.setBounds(690, 237, 326, 44); // Ajuste en la posición Y
        pn_fondo.add(txt_clave);

        // Imagen de clave
        lbl_clave2 = new JLabel();
        ImageIcon claveIcon = new ImageIcon(ViewLogin.class.getResource("/images/clave2.png"));
        Image claveImage = claveIcon.getImage().getScaledInstance(50, 44, Image.SCALE_SMOOTH);
        lbl_clave2.setIcon(new ImageIcon(claveImage));
        lbl_clave2.setBounds(635, 237, 50, 44); // Ajuste en la posición Y
        pn_fondo.add(lbl_clave2);

     // Panel con animación de imágenes
        String[] imagePaths = {"/images/login1.png", "/images/login2.png", "/images/login3.png"};
        int panelWidth = 684; // Ancho del panel
        int panelHeight = 532; // Alto del panel
        AnimatedPanel animatedPanel = new AnimatedPanel(imagePaths, panelWidth, panelHeight);
        animatedPanel.setBounds(40, 140, panelWidth, panelHeight);
        pn_fondo.add(animatedPanel);

        // Label de iniciar sesión
        JLabel lbl_iniciarSesion = new JLabel("¿Deseas iniciar sesión?");
        lbl_iniciarSesion.setForeground(Color.BLACK);
        lbl_iniciarSesion.setFont(new Font("Arial", Font.ITALIC, 20));
        lbl_iniciarSesion.setBounds(650, 287, 300, 30); // Ajuste en la posición Y
        pn_fondo.add(lbl_iniciarSesion);

        // Botón de Entrar
        btnEntrar = new ViewComponents().new RoundedButton("Entrar");
        btnEntrar.setFont(new Font("Arial", Font.BOLD, 20));
        btnEntrar.setForeground(Color.WHITE);
        btnEntrar.setBackground(new Color(76, 82, 165));
        btnEntrar.setBounds(645, 327, 150, 40); // Ajuste en la posición Y
        pn_fondo.add(btnEntrar);

        // Botón transparente con imagen para el modo oscuro
        ImageIcon modoIcon = new ImageIcon(ViewLogin.class.getResource("/images/modo.png"));
        Image modoImage = modoIcon.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        btnModo = new JButton(new ImageIcon(modoImage));
        btnModo.setBounds(815, 327, 40, 40); // Ajusta la posición y tamaño
        animatedPanel.setOpaque(false); // Asegura que el panel es transparente
        btnModo.setOpaque(false);
        btnModo.setContentAreaFilled(false);
        btnModo.setBorderPainted(false);
        pn_fondo.add(btnModo);

        // Botones de redes sociales
        btnYoutube = new JButton(new ImageIcon(ViewLogin.class.getResource("/images/youtube.png")));
        btnYoutube.setBounds(1000, 470, 90, 90);
        btnYoutube.setOpaque(false);
        btnYoutube.setContentAreaFilled(false);
        btnYoutube.setBorderPainted(false);
        pn_fondo.add(btnYoutube);

        btnFacebook = new JButton(new ImageIcon(ViewLogin.class.getResource("/images/facebook.png")));
        btnFacebook.setBounds(900, 470, 90, 90);
        btnFacebook.setOpaque(false);
        btnFacebook.setContentAreaFilled(false);
        btnFacebook.setBorderPainted(false);
        pn_fondo.add(btnFacebook);

        btnTwitter = new JButton(new ImageIcon(ViewLogin.class.getResource("/images/instagram.png")));
        btnTwitter.setBounds(800, 470, 90, 90);
        btnTwitter.setOpaque(false);
        btnTwitter.setContentAreaFilled(false);
        btnTwitter.setBorderPainted(false);
        pn_fondo.add(btnTwitter);

        logic_Login ll = new logic_Login(this);
    }


    private class AnimatedPanel extends JPanel {
        private static final long serialVersionUID = 1L;
        private List<Image> images;
        private int currentIndex = 0;
        private int xPosition = -1; // Inicialmente fuera del panel
        private final int xMax; // Máxima posición horizontal para la animación
        private final int centerPosition; // Posición para centrar la imagen
        private final int delay = 15; // Tiempo de actualización (en milisegundos)
        private final int imageWidth;
        private final int imageHeight;
        private final int centerDuration = 4000; // Duración en el centro (en milisegundos)
        private Timer timer;

        public AnimatedPanel(String[] imagePaths, int panelWidth, int panelHeight) {
            images = new ArrayList<>();
            for (String path : imagePaths) {
                try {
                    BufferedImage img = ImageIO.read(getClass().getResource(path));
                    images.add(img);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (!images.isEmpty()) {
                BufferedImage firstImage = (BufferedImage) images.get(0);
                imageWidth = firstImage.getWidth();
                imageHeight = firstImage.getHeight();
            } else {
                imageWidth = 0;
                imageHeight = 0;
            }

            xMax = panelWidth + imageWidth; // Ajusta el tamaño del panel y la imagen
            centerPosition = (panelWidth - imageWidth) / 2;

            timer = new Timer(delay, e -> {
                xPosition += 5;
                if (xPosition > centerPosition + imageWidth) {
                    xPosition = -imageWidth; // Resetear a la posición inicial
                    currentIndex = (currentIndex + 1) % images.size();
                }
                repaint();
            });
            timer.start();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (!images.isEmpty()) {
                Image currentImage = images.get(currentIndex);
                int imageX = xPosition;
                if (xPosition > centerPosition && xPosition < centerPosition + imageWidth) {
                    imageX = centerPosition; // Centra la imagen cuando está en el centro
                }
                g.drawImage(currentImage, imageX, 0, imageWidth, imageHeight, this);
            }
        }
    }
}