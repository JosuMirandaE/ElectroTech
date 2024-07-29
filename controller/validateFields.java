package controller;

import model.configurable;

public class validateFields implements configurable {
    
    public static boolean validateNames(String names) {
        return names.matches(ERNames);
    }
    
    public static boolean validateAge(int age) {
        return String.valueOf(age).matches(ERAge);
    }
    
    public static boolean validateDni(long dni) {
        return String.valueOf(dni).matches(ERDni);
    }
    
    public static boolean validateEmail(String email) {
        return email.matches(EREmail);
    }
    
    public static boolean validateNameCosas(String namecosas) {
        return namecosas.matches(ERNameCosas);
    }
    
    public static boolean validateDescripcion(String descripcion) {
        return descripcion.matches(ERDescripcion);
    }
    
    public static boolean validateCantidad(int cantidad) {
        return String.valueOf(cantidad).matches(ERCantidad);
    }
    
    public static boolean validatePrecio(double precio) {
        return String.valueOf(precio).matches(ERPrecio);
    }
    
    public static boolean validateProveedor(String proveedor) {
        return proveedor.matches(ERProveedor);
    }
    
    public static boolean validateContact(String contact) {
        return contact.matches(ERContact);
    }
    
    public static boolean validateDireccion(String dir) {
        return dir.matches(ERDireccion);
    }
    
    public static boolean validateRazons(String razons) {
        return razons.matches(ERRazons);
    }
}


