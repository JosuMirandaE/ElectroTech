package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import libreriaV3.files;
import libreriaV3.connection;

public class employeeDAO {
    
    private employee e;
    private connection conn=new connection("Proyecto Final");
    private ResultSet res=null;
    private boolean state;
    
    public employeeDAO() {
        super();
    }
    public employeeDAO(employee e) {
        super();
        this.e=e;
    }
    
    public boolean registrarEmpleado(employee e) {
        try {
            String query = "INSERT INTO electro.empleados (nombres, dni, codigo, email, fecha, user, psw) VALUES (?,?,?,?,?,?,?)";
            try (Connection connection = conn.openConn()) {
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, e.getNames());
                statement.setString(2, e.getDni());
                statement.setString(3, e.getCode());
                statement.setString(4, e.getEmail());
                statement.setDate(5, new java.sql.Date(e.getDate().getTime()));
                statement.setString(6, e.getUser());
                statement.setString(7, e.getPsw());

                // Ejecutar la consulta
                int rowsAffected = statement.executeUpdate();
                // Cerrar la conexión
                conn.closeConn();
                // Retorna true si al menos una fila fue afectada
                return rowsAffected > 0;
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
            return false;
        }
    }
    public List<String> getAllEmployeesCombo() {
        List<String> empleados = new ArrayList<>();
        String query = "SELECT DISTINCT nombres FROM electro.empleados";

        try (Connection connection = conn.openConn();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                empleados.add(resultSet.getString("nombres"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return empleados;
    }
    public employee getEmployeeByDni(String dni) {
        String query = "SELECT * FROM electro.empleados WHERE dni = ?";
        try (Connection connection = conn.openConn()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, dni);
            ResultSet res = statement.executeQuery();

            if (res.next()) {
                return new employee(
                    res.getInt("idempleados"),
                    res.getString("nombres"),
                    res.getString("dni"),
                    res.getString("codigo"),
                    res.getString("email"),
                    res.getDate("fecha"),
                    res.getString("user"),
                    res.getString("psw")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Retorna null si no se encuentra el empleado
    }
    public employee validateUser(String username, String password) {
        String query = "SELECT * FROM electro.empleados WHERE user = ? AND psw = ?";
        try (Connection connection = conn.openConn()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            res = statement.executeQuery();

            if (res.next()) {
                return new employee(
                    res.getInt("idempleados"),
                    res.getString("nombres"),
                    res.getString("dni"),
                    res.getString("codigo"),
                    res.getString("email"),
                    res.getDate("fecha"),
                    res.getString("user"),
                    res.getString("psw")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Retorna null si no se encuentra el usuario
    }
    public List<employee> getAllEmployees() {
        List<employee> employees = new ArrayList<>();
        String query = "SELECT * FROM electro.empleados";

        try (Connection connection = conn.openConn();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                employee emp = new employee(
                    resultSet.getInt("idempleados"),
                    resultSet.getString("nombres"),
                    resultSet.getString("dni"),
                    resultSet.getString("codigo"),
                    resultSet.getString("email"),
                    resultSet.getDate("fecha"),
                    resultSet.getString("user"),
                    resultSet.getString("psw")
                );
                employees.add(emp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }
    public employee getEmployeeByUser(String username) {
        String query = "SELECT * FROM electro.empleados WHERE user = ?";
        try (Connection connection = conn.openConn();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            ResultSet res = statement.executeQuery();

            if (res.next()) {
                return new employee(
                    res.getInt("idempleados"),
                    res.getString("nombres"),
                    res.getString("dni"),
                    res.getString("codigo"),
                    res.getString("email"),
                    res.getDate("fecha"),
                    res.getString("user"),
                    res.getString("psw")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Retorna null si no se encuentra el empleado
    }
}
