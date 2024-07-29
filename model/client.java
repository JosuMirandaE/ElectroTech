package model;

import libreriaV3.generic;

public class client {

    private generic <String,Integer> dtc1;
    private generic <String,String> dtc2;

    public client() {
    	dtc1=new generic<>();
    	dtc2=new generic<>();
    }
    
    public client(int id, String nombres, String direccion,String contacto,String email,String dni) {
        dtc1=new generic<>(email,dni,id);
        dtc2=new generic<>(nombres,direccion,contacto);
    }
    //getters
    public int getId() {
        return dtc1.getAtributo3();
    }
    public String getNombres() {
        return dtc2.getAtributo1();
    }
    public String getDireccion() {
        return dtc2.getAtributo2();
    }
    public String getContacto() {
        return dtc2.getAtributo3();
    }
    public String getEmail() {
    	return dtc1.getAtributo1();
    }
    public String getDni() {
    	return dtc1.getAtributo2();
    }
    //setters
    public void setEmail(String email) {
    	dtc1.setAtributo1(email);
    }
    public void setDni(String dni) {
    	dtc1.setAtributo2(dni);
    }
    public void setId(int id) {
        dtc1.setAtributo3(id);
    }
    public void setNombres(String nombres) {
        dtc2.setAtributo1(nombres);
    }
    public void setDireccion(String dir) {
        dtc2.setAtributo2(dir);
    }
    public void setContacto(String contact) {
        dtc2.setAtributo3(contact);
    }
}
