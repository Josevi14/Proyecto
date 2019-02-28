/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.http.HttpServletRequest;
import servlets.hotel;

/**
 *
 * @author josev
 */
public class UsuarioDB {

    private static Connection conexion;

    public static void conectar(Connection conexion) {
        UsuarioDB.conexion = conexion;
    }

    public static boolean acceder(HttpServletRequest request) throws SQLException {
        boolean ok;
        ok = false;
        ResultSet rs;
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        password = hotel.getMD5(password);

        Statement sentencia = conexion.createStatement();

        String sql = "SELECT * FROM usuarios WHERE login ='" + login + "'";
        rs = sentencia.executeQuery(sql);
        rs.next();

        String clave = rs.getString("clave");
        int tipo = rs.getInt("tipo");

        if (password.equals(clave)) {
            ok = true;
            request.setAttribute("tipo", tipo);
        }

        return ok;
    }

    public static void registrar(HttpServletRequest request) throws SQLException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        password = hotel.getMD5(password);
        String nombre = request.getParameter("nombre");
        String apellidos = request.getParameter("apellidos");
        int telefono = Integer.valueOf(request.getParameter("telefono"));
        
        Statement sentencia = conexion.createStatement();
        
        String sql = "INSERT INTO usuarios VALUES('"+login+"','"+password+"','"+nombre+"','"+apellidos+"','"+telefono+"','1')";
        sentencia.executeUpdate(sql);
    }
    
    public static Usuario consultarUsuario(String login) throws SQLException{
        Usuario usuario;
        ResultSet rs;
        
        Statement sentencia = conexion.createStatement();
        
        String sql = "SELECT * FROM usuarios WHERE login = '"+ login +"'";
        rs = sentencia.executeQuery(sql);
        usuario = rowToUser(rs);
        
        return usuario;
    }
    
    public static Usuario rowToUser(ResultSet rs) throws SQLException {
        String login, clave, nombre, apellidos;
        int telefono, tipo;
        
        login = rs.getString("login");
        clave = rs.getString("clave");
        nombre = rs.getString("nombre");
        apellidos = rs.getString("apellidos");
        telefono = rs.getInt("telefono");
        tipo = rs.getInt("tipo");
        Usuario usuario = new Usuario(login, clave, nombre, apellidos, telefono, tipo);
        return usuario;
    }
}
