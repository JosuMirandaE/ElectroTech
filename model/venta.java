package model;

import java.util.Date;
import java.util.Random;

import libreriaV3.generic;

public class venta {
	
	private generic<String,String> dtv1;
	private generic<Integer,Double> dtv2;
	private generic<Date,?> dtv3;
	
	public venta() {
		super();
		dtv1 = new generic<>();
		dtv2 = new generic<>();
		dtv3 = new generic<>(new Date());
	}
	
	public venta(int id, String code, String empleado, String cliente, Date fecha, String producto, int cantidad, double precio, double total) {
		super();
		dtv1 = new generic<>(code, empleado, cliente, producto);
		dtv2 = new generic<>(id, cantidad, precio, total);
		dtv3 = new generic<>(new Date());
	}
	
	public String getEmpleado() {
		return dtv1.getAtributo2();
	}
	
	public void setEmpleado(String employee) {
		dtv1.setAtributo2(employee);
	}
	
	public String getCliente() {
		return dtv1.getAtributo3();
	}
	
	public void setCliente(String cliente) {
		this.dtv1.setAtributo3(cliente);
	}
	
	public String getProducto() {
		return dtv1.getAtributo4();
	}
	
	public void setProducto(String producto) {
		this.dtv1.setAtributo4(producto);
	}
	
	public int getCantidad() {
		return dtv2.getAtributo2();
	}
	
	public void setCantidad(int cantidad) {
		this.dtv2.setAtributo2(cantidad);
	}
	
	public String generarCode() {
		Random rd = new Random();
		String code = "V";
		for (int i = 0; i < 5; i++) {
			code += rd.nextInt(10);
		}
		this.dtv1.setAtributo1(code);
		return code;
	}
	
	public String getCode() {
		if (dtv1.getAtributo1() == null) {
			generarCode();
		}
		return dtv1.getAtributo1();
	}
	
	public int getId() {
		return dtv2.getAtributo1();
	}
	
	public void setId(int id) {
		this.dtv2.setAtributo1(id);
	}
	
	public double getPrecioU() {
		return dtv2.getAtributo3();
	}
	
	public void setPrecioU(double precio) {
		this.dtv2.setAtributo3(precio);
	}
	
	public double getTotal() {
		return dtv2.getAtributo4();
	}
	
	public void setTotal(double total) {
		this.dtv2.setAtributo4(total);
	}
	
	public Date getDate() {
		return dtv3.getAtributo1();
	}
}

