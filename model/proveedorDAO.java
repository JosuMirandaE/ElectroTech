package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import libreriaV3.files;
import libreriaV3.connection;

public class proveedorDAO {
	
	private proveedor prv;
    private connection conn=new connection("Proyecto Final");
    private ResultSet res=null;
    private boolean state;

    public proveedorDAO() {
        super();
    }
    public proveedorDAO(proveedor prv) {
        super();
        this.prv=new proveedor();
    }

    public boolean insertarProveedores(proveedor prv) {
        try {
            String query="INSERT INTO electro.proveedores(nombres,email,codigo,dni,telefono,razonSocial) VALUES (?,?,?,?,?,?)";
            try(Connection connection=conn.openConn()) {
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, prv.getNames());
                statement.setString(2, prv.getEmail());
                statement.setString(3, prv.getCode());
                statement.setString(4, prv.getDni());
                statement.setString(5, prv.getTelefono());
                statement.setString(6, prv.getSocial());

                //ejecutar la consulta
                int rowsAffected = statement.executeUpdate();
                //cerrar la conexion
                conn.closeConn();
                //retorna tru si al menos una fila fue afectada
                return rowsAffected>0;
            }
        }catch(SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public List<proveedor> getAllProveedores() {
        List<proveedor> proveedores = new ArrayList<>();
        String query = "SELECT idproveedor, nombres, codigo, razonSocial FROM proveedores";

        try (Connection connection = conn.openConn();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                proveedor prov = new proveedor(
                    resultSet.getInt("idproveedor"),
                    resultSet.getString("nombres"),
                    null, // email (puedes ajustar esto si tienes el campo en la tabla)
                    null, // dni (puedes ajustar esto si tienes el campo en la tabla)
                    resultSet.getString("codigo"),
                    null, // telefono (puedes ajustar esto si tienes el campo en la tabla)
                    resultSet.getString("razonSocial")
                );
                proveedores.add(prov);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return proveedores;
    }
}

