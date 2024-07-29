package model;

import libreriaV3.generic;

public class producto {
	
	private generic<Integer,Double>dtp1;
	private generic<String,String>dtp2;
	
	
	public producto() {
		super();
		dtp1=new generic<>();
		dtp2=new generic<>();
	}
	public producto(int id,String nombre,String desc,double precio,int stock,String proveedor){
		dtp1=new generic<>(id,stock,precio);
		dtp2=new generic<>(nombre,desc,proveedor);
	}	
	//getters
	public int getId() {
		return dtp1.getAtributo1();
	}
	public int getStock() {
		return dtp1.getAtributo2();
	}
	public double getPrecio() {
		return dtp1.getAtributo3();
	}
	public String getNombre() {
		return dtp2.getAtributo1();
	}
	public String getDescripcion() {
		return dtp2.getAtributo2();
	}
	public String getProveedor() {
		return dtp2.getAtributo3();
	}
	//setters
	public void setId(int id) {
		dtp1.setAtributo1(id);
	}
	public void setStock(int stock) {
		dtp1.setAtributo2(stock);
	}
	public void setPrecio(double precio) {
		dtp1.setAtributo3(precio);
	}
	public void setNombre(String nombre) {
		dtp2.setAtributo1(nombre);
	}
	public void setDescripcion(String desc) {
		dtp2.setAtributo2(desc);
	}
	public void setProveedor(String prov) {
		dtp2.setAtributo3(prov);
	}
	
}