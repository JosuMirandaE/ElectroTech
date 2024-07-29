package model;

public interface configurable {
	
	public final String ERNames="^([A-Z][a-zá-ú]{1,}\s?){2,5}$";
	public final String ERAge="^[0-9]{1,2}$";
	public final String ERDni="^[0|1|2][0-9]{8}[-]?[0-9]$";
	public final String EREmail="^[a-z][a-z0-9.-]+[@][a-z]{3,}[.][a-z]{2,3}$";
	public final String ERNameCosas="^([A-Z]?[a-zá-ú]{1,}\s?)+$";
	public final String ERProveedor="^([A-Z]?[a-zá-ú]{1,}\s?){2,5}$";
	public final String ERDescripcion="^([A-Z]?[a-zá-ú]{1,}\s?)+$";
	public final String ERCantidad="^[0-9]+$";
    public final String ERPrecio="^[0-9]+[.][0-1]{2}$";
    public final String ERDireccion="^([A-Z]?[a-z]+\s?){2,}$";
    public final String ERContact="^(09)[0-9]{8}$";
    public final String ERRazons="^([A-Z][a-zá-ú]{1,}\s?){2,3}$";
}
