package model;

import java.util.Random;

import libreriaV3.generic;

public class proveedor {

	private generic<String,String>dtp1;
	private generic<String,Integer>dtp2;

	public proveedor() {
		super();
		dtp1=new generic<>();
		dtp2=new generic<>();
		// TODO Auto-generated constructor stub
	}
	public proveedor(int id, String names, String email, String dni,String code,String telefono,String social) {
	    dtp1 = new generic<>(names,email,dni, code);
	    dtp2 = new generic<>(telefono, social,id);
	}

	public int getId() {
		return dtp2.getAtributo3();
	}
	public void setId(int id) {
		this.dtp2.setAtributo3(id);
	}
	public String getNames() {
		return dtp1.getAtributo1();
	}
	public void setNames(String names_) {
		this.dtp1.setAtributo1(names_);
	}
	public String getEmail() {
		return dtp1.getAtributo2();
	}
	public void setEmail(String email_) {
		this.dtp1.setAtributo2(email_);
	}
	public void setCode(String code_) {
		this.dtp1.setAtributo4(code_);
	}
	public String getTelefono() {
		return dtp2.getAtributo1();
	}
	public void setTelefono(String contact) {
		this.dtp2.setAtributo1(contact);
	}
	public String getDni() {
		return dtp1.getAtributo3();
	}
	public void setDni(String dni_) {
		this.dtp1.setAtributo3(dni_);
	}
	public String getSocial() {
		return dtp2.getAtributo2();
	}
	public void setSocial(String social) {
		this.dtp2.setAtributo2(social);
	}
	
	public String generarCode() {
        Random rd=new Random();
        String code="P";
        for(int i=0;i<5;i++) {
            code+=rd.nextInt(10);
        }
        this.dtp1.setAtributo4(code);
        return code;
    }
	public String getCode() {
        generarCode();
        return dtp1.getAtributo4();
    }
	
	
}
