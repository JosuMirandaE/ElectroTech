//package controller;
//
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//import javax.swing.JOptionPane;
//
//import View.ViewActualizarContrasena;
//import View.ViewGeneralEmpleados;
//
//public class logic_ActualizarContraseña implements ActionListener {
//	
//	private ViewActualizarContrasena vac;
//	private ViewGeneralEmpleados vge;
//	
//	public logic_ActualizarContraseña(ViewActualizarContrasena vac,ViewGeneralEmpleados vge) {
//		this.vac=vac;
//		this.vge=vge;
//		activador();
//	}
//	
//	public void activador() {
//		vac.btnActualizar.addActionListener(this);
//		vac.btnCerrar.addActionListener(this);
//		vac.btnRegresar.addActionListener(this);
//	}
//	
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		// TODO Auto-generated method stub
//		if(e.getSource()==vac.btnActualizar) {
//			
//		}else if(e.getSource()==vac.btnCerrar) {
//			int response = JOptionPane.showConfirmDialog(vac, "¿Desea salir de la Aplicación?", "Confirmar salida", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
//		    if (response == JOptionPane.YES_OPTION) {
//		        System.exit(0);
//		    }
//		}else if (e.getSource()==vac.btnRegresar) {
//			if(vge!=null) {
//				vge.setVisible(true);
//			}
//			vac.dispose();
//		}
//	}
//
//}

package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import View.ViewActualizarContrasena;
import View.ViewGeneralEmpleados;

public class logic_ActualizarContraseña implements ActionListener {

    private ViewActualizarContrasena vac;
    private ViewGeneralEmpleados vge;

    public logic_ActualizarContraseña(ViewActualizarContrasena vac, ViewGeneralEmpleados vge) {
        this.vac = vac;
        this.vge = vge;
        activador();
    }

    public void activador() {
        vac.btnActualizar.addActionListener(this);
        vac.btnCerrar.addActionListener(this);
        vac.btnRegresar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vac.btnActualizar) {
        	String newPassword = vac.txtNuevaContrasena.getText();
            String confirmPassword = vac.txtConfirmarContrasena.getText();

            if (newPassword.isEmpty() || confirmPassword.isEmpty()) {
                JOptionPane.showMessageDialog(vac, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (!newPassword.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(vac, "Las contraseñas no coinciden.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                boolean success = false;
                try {
                    success = actualizarContrasena(newPassword);
                    if (success) {
                        JOptionPane.showMessageDialog(vac, "Contraseña actualizada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(vac, "Error al actualizar la contraseña.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(vac, "Error al actualizar la contraseña: " + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
//            validar();
        } else if (e.getSource() == vac.btnCerrar) {
            int response = JOptionPane.showConfirmDialog(vac, "¿Desea salir de la Aplicación?", "Confirmar salida", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        } else if (e.getSource() == vac.btnRegresar) {
            if (vge != null) {
                vge.setVisible(true);
            }
            vac.dispose();
        }
    }

    

    private boolean actualizarContrasena(String nuevaContrasena) {
        // Lógica para actualizar la contraseña en tu modelo de usuario
        // Aquí deberías implementar la lógica de actualización en tu base de datos o sistema de almacenamiento
        // Retorna true si la actualización fue exitosa, false en caso contrario
        // Por ejemplo:
        // return userDatabase.updatePassword(nuevaContrasena);
        return true;
    }
    
}


