package model;

import java.util.Date;
import java.util.Random;

import libreriaV3.generic;

public class employee {
	
	private generic<String,String>dte1;
	private generic<Date,Integer>dte2;
	private generic<String,String>dte3;
	
	public employee() {
		super();
		dte1=new generic<>();
		dte2=new generic<>();
		dte3=new generic<>();
		// TODO Auto-generated constructor stub
	}

	public employee(int id,String names, String dni,String code,String email, Date fecha,String user,String psw) {
		dte1=new generic<>(names,dni, code, email);
		dte2=new generic<>(new Date(),id);
		dte3=new generic<>(user,psw);
		// TODO Auto-generated constructor stub
	}
	public String getNames() {
		return dte1.getAtributo1();
	}
	public void setNames(String names_) {
		this.dte1.setAtributo1(names_);
	}
	public String getEmail() {
		return dte1.getAtributo4();
	}
	public void setEmail(String email_) {
		this.dte1.setAtributo4(email_);
	}
	public String generarCode() {
		Random rd=new Random();
		String code="E";
		for(int i=0;i<5;i++) {
			code+=rd.nextInt(10);
		}
		this.dte1.setAtributo3(code);
		return code;
	}
	public String getCode() {
		generarCode();
		return dte1.getAtributo3();
	}
	public void setCode(String code) {
		generarCode();
		this.dte1.setAtributo3(code);
	}
	public String getDni() {
		return dte1.getAtributo2();
	}
	public void setDni(String dni_) {
		this.dte1.setAtributo2(dni_);
	}
	
	public Date getDate() {
        if (dte2.getAtributo1() == null) {
            dte2.setAtributo1(new Date());
        }
        return dte2.getAtributo1();
    }
	public int getId() {
		return dte2.getAtributo3();
	}
	public void setId(int id) {
		this.dte2.setAtributo3(id);
	}
    public void generarUserName() {
        String nombre = getNames();
        String dni = getDni();
        
        String userName = "";
        if (nombre.length() >= 3) {
            userName = nombre.substring(0, 3);
        } else {
            userName = nombre;
        }

        String dniStr = String.valueOf(dni);
        if (dniStr.length() >= 3) {
            userName += dniStr.substring(dniStr.length() - 3);
        } else {
            userName += dniStr;
        }
        this.dte3.setAtributo1(userName);
    }
	public String getUser() {
		generarUserName();
		return dte3.getAtributo1();
	}

	public String getPsw() {
		
		return dte3.getAtributo3();
	}
	public void setPsw(String psw) {
		this.dte3.setAtributo3(psw);
	}
	
}
