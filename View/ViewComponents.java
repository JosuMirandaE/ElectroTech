package View;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class ViewComponents {
	
    // Clase para crear un JTextField con bordes redondeados
    public class RoundedTextField extends JTextField {
        private static final long serialVersionUID = 1L;
        private int radius;

        public RoundedTextField(int radius) {
            super();
            this.radius = radius;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
            super.paintComponent(g2);
            g2.dispose();
        }

        @Override
        protected void paintBorder(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getForeground());
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
            g2.dispose();
        }

        @Override
        public void setBorder(Border border) {
            super.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        }
    }
    
    // Clase para crear un JButton con bordes redondeados
    public class RoundedButton extends JButton {
        private Shape shape;
        public RoundedButton(String text) {
            super(text);
            setOpaque(false); // Hace que el fondo sea transparente
            setBackground(new Color(76, 82, 165)); // Establece el color de fondo
            setForeground(Color.WHITE); // Establece el color del texto
            setFocusPainted(false); // Quita el borde al enfocar
            setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Añade espacio alrededor del texto
        }
        protected void paintComponent(Graphics g) {
            if (getModel().isArmed()) {
                g.setColor(new Color(60, 66, 149)); // Cambia el color cuando se presiona el botón
            } else {
                g.setColor(getBackground());
            }
            g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 20, 20);
            super.paintComponent(g);
        }
        protected void paintBorder(Graphics g) {
            g.setColor(getForeground());
            g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 20, 20);
        }
        public boolean contains(int x, int y) {
            if (shape == null || !shape.getBounds().equals(getBounds())) {
                shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, 20, 20);
            }
            return shape.contains(x, y);
        }
    }
    
    
    // Clase para crear un JPasswordField con bordes redondeados
    public class RoundedPasswordField extends JPasswordField {
        private Shape shape;
        public RoundedPasswordField(int size) {
            super(size);
            setOpaque(false); // Hace que el fondo sea transparente
            setBorder(BorderFactory.createCompoundBorder(getBorder(), BorderFactory.createEmptyBorder(5, 10, 5, 5)));
            setBackground(new Color(255, 255, 255, 180)); // Establece el color de fondo con transparencia
        }
        protected void paintComponent(Graphics g) {
            g.setColor(getBackground());
            g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 20, 20);
            super.paintComponent(g);
        }
        protected void paintBorder(Graphics g) {
            g.setColor(getForeground());
            g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 20, 20);
        }
        public boolean contains(int x, int y) {
            if (shape == null || !shape.getBounds().equals(getBounds())) {
                shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, 20, 20);
            }
            return shape.contains(x, y);
        }
    }
    
    
    // Clase para crear un JComboBox con bordes redondeados
    public class RoundedComboBox<E> extends JComboBox<E> {
        private static final long serialVersionUID = 1L;
        private int radius;

        public RoundedComboBox() {
            super();
            radius = 15;
            setOpaque(false);
            setRenderer(new RoundedComboBoxRenderer());
            setBackground(Color.WHITE);
            setForeground(Color.BLACK);
            setFont(new Font("Arial", Font.PLAIN, 18));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
            super.paintComponent(g2);
            g2.dispose();
        }

        @Override
        protected void paintBorder(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getForeground());
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
            g2.dispose();
        }
        private class RoundedComboBoxRenderer extends BasicComboBoxRenderer {
            private static final long serialVersionUID = 1L;

            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (isSelected) {
                    setBackground(Color.LIGHT_GRAY);
                    setForeground(Color.BLACK);
                } else {
                    setBackground(Color.WHITE);
                    setForeground(Color.BLACK);
                }
                setFont(new Font("Arial", Font.PLAIN, 18));
                return this;
            }

            @Override
            public void paint(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
                super.paint(g2);
                g2.dispose();
            }
        }
		public void setText(String string) {
			// TODO Auto-generated method stub
			
		}

		public String getText() {
			// TODO Auto-generated method stub
			return null;
		}
    }
        
    // Clase para crear un JSpinner con bordes redondeados
    public class RoundedSpinner extends JSpinner {
        private static final long serialVersionUID = 1L;
        private int radius;

        public RoundedSpinner() {
            super();
            radius = 15;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
            super.paintComponent(g2);
            g2.dispose();
        }

        @Override
        protected void paintBorder(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getForeground());
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
            g2.dispose();
        }

		public void setText(String string) {
			// TODO Auto-generated method stub
			
		}

		public String getText() {
			// TODO Auto-generated method stub
			return null;
		}
    }
}
