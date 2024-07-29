package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controller.DatabaseConnection;
import libreriaV3.connection;
import model.producto;

public class productoDAO{
    private producto p;
    private connection conn = new connection("Proyecto Final");
    private boolean state;

    public productoDAO() {
        super();
    }

    public productoDAO(producto p) {
        super();
        this.p = p;
    }

    public boolean insertarProducto(producto p) {
        try {
            String query = "INSERT INTO electro.productos (nombre,descripcion,precio,stock,proveedor) VALUES(?,?,?,?,?)";
            try (Connection connection = conn.openConn()) {
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, p.getNombre());
                statement.setString(2, p.getDescripcion());
                statement.setDouble(3, p.getPrecio());
                statement.setInt(4, p.getStock());
                statement.setString(5, p.getProveedor());

                // ejecutar la consulta
                int rowsAffected = statement.executeUpdate();
                // cerrar la conexion
                conn.closeConn();
                // retornar true si al menos una fila fue afectada;
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public List<String> getAllProductosCombo() {
        List<String> productos = new ArrayList<>();
        String query = "SELECT DISTINCT nombre FROM electro.productos";

        try (Connection connection = conn.openConn();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                productos.add(resultSet.getString("nombre"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }
    public int obtenerStockProducto(String producto) {
        String query = "SELECT stock FROM productos WHERE nombre = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, producto);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("stock");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; // Retorna 0 si el producto no se encuentra o hay un error
    }
    public List<producto> getAllProductos() {
        List<producto> productos = new ArrayList<>();
        String query = "SELECT * FROM electro.productos";

        try (Connection connection = conn.openConn();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                producto prov = new producto(
                    resultSet.getInt("idproductos"),
                    resultSet.getString("nombre"),
                    null, // email (puedes ajustar esto si tienes el campo en la tabla)
                    resultSet.getDouble("precio"),
                    resultSet.getInt("stock"),
                    resultSet.getString("proveedor")
                );
                productos.add(prov);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }
    // Nuevo método para actualizar un producto
    public boolean actualizarProducto(producto pr) {
        String query = "UPDATE productos SET descripcion = ?, precio = ?, stock = ?, proveedor = ? WHERE nombre = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, pr.getDescripcion());
            pstmt.setDouble(2, pr.getPrecio());
            pstmt.setInt(3, pr.getStock());
            pstmt.setString(4, pr.getProveedor());
            pstmt.setString(5, pr.getNombre());
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public List<String> getAllProveedores() {
        List<String> proveedores = new ArrayList<>();
        String query = "SELECT DISTINCT nombres FROM electro.proveedores";

        try (Connection connection = conn.openConn();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                proveedores.add(resultSet.getString("nombres"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return proveedores;
    }
}