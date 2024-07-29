package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controller.DatabaseConnection;
import libreriaV3.connection;

public class ventaDAO {
	
	private venta v;
    private connection conn = new connection("Proyecto Final");
    private ResultSet res = null;

    public ventaDAO() {
        super();
    }

    public ventaDAO(venta v) {
        super();
        this.v = v;
    }

    public boolean RegistrarVenta(venta v) {
        try {
            String query = "INSERT INTO electro.ventas(codigo, empleado, cliente, fecha, producto, cantidad, precio_u, total) VALUES (?,?,?,?,?,?,?,?)";
            try (Connection connection = conn.openConn()) {
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, v.getCode());
                statement.setString(2, v.getEmpleado());
                statement.setString(3, v.getCliente());
                statement.setDate(4, new java.sql.Date(v.getDate().getTime()));
                statement.setString(5, v.getProducto());
                statement.setInt(6, v.getCantidad());
                statement.setDouble(7, v.getPrecioU());
                statement.setDouble(8, v.getTotal());

                int rowsAffected = statement.executeUpdate();
                conn.closeConn();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public double obtenerPrecioProducto(String producto) {
        double precio = 0.0;
        try {
            String query = "SELECT precio FROM electro.productos WHERE nombre = ?";
            try (Connection connection = conn.openConn()) {
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, producto);
                res = statement.executeQuery();
                if (res.next()) {
                    precio = res.getDouble("precio");
                }
                conn.closeConn();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return precio;
    }
    public boolean actualizarStockProducto(String producto, int cantidadVendida) {
        String query = "UPDATE productos SET stock = stock - ? WHERE nombre = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, cantidadVendida);
            pstmt.setString(2, producto);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}


