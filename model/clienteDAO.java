package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import libreriaV3.connection;

public class clienteDAO {

    private client c;
    private connection conn = new connection("Proyecto Final");

    public clienteDAO() {
    }

    public clienteDAO(client c) {
        this.c = c;
    }

    public boolean registrarCliente(client c) {
        try (Connection connection = conn.openConn()) {
            String query = "INSERT INTO electro.clientes (nombres, direccion, contacto,email,dni) VALUES (?,?,?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, c.getNombres());
            statement.setString(2, c.getDireccion());
            statement.setString(3, c.getContacto());
            statement.setString(4, c.getEmail());
            statement.setString(5, c.getDni());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<client> getAllClient() {
        List<client> clients = new ArrayList<>();
        String query = "SELECT * FROM electro.clientes";

        try (Connection connection = conn.openConn()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                client cl = new client(
                        rs.getInt("idclientes"),
                        rs.getString("nombres"),
                        rs.getString("direccion"),
                        rs.getString("contacto"),
                        rs.getString("email"),
                        rs.getString("dni")
                );
                clients.add(cl);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clients;
    }
    public List<String> getAllClientesCombo() {
        List<String> clientes = new ArrayList<>();
        String query = "SELECT DISTINCT nombres FROM electro.clientes";

        try (Connection connection = conn.openConn();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                clientes.add(resultSet.getString("nombres"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }
    public client getClientByDni(String dni) {
        client cl = null;
        String query = "SELECT * FROM electro.clientes WHERE dni = ?";

        try (Connection connection = conn.openConn()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, dni);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                cl = new client(
                    rs.getInt("idclientes"),
                    rs.getString("nombres"),
                    rs.getString("direccion"),
                    rs.getString("contacto"),
                    rs.getString("email"),
                    rs.getString("dni")
                    );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cl;
    }

}