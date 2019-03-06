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
import java.util.ArrayList;
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

        String sql = "INSERT INTO usuarios VALUES('" + login + "','" + password + "','" + nombre + "','" + apellidos + "','" + telefono + "','1')";
        sentencia.executeUpdate(sql);
    }
    
    public static void registrarRecepcionista(HttpServletRequest request) throws SQLException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        password = hotel.getMD5(password);
        String nombre = request.getParameter("nombre");
        String apellidos = request.getParameter("apellidos");
        int telefono = Integer.valueOf(request.getParameter("telefono"));

        Statement sentencia = conexion.createStatement();

        String sql = "INSERT INTO usuarios VALUES('" + login + "','" + password + "','" + nombre + "','" + apellidos + "','" + telefono + "','2')";
        sentencia.executeUpdate(sql);
    }

    public static Usuario consultarUsuario(String login) throws SQLException {
        Usuario usuario;
        ResultSet rs;

        Statement sentencia = conexion.createStatement();

        String sql = "SELECT * FROM usuarios WHERE login = '" + login + "'";
        rs = sentencia.executeQuery(sql);
        usuario = rowToUser(rs);
        sentencia.close();

        return usuario;
    }

    public static ArrayList<Usuario> leerUsuarios() throws SQLException {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        ResultSet rs;
        Statement sentencia = conexion.createStatement();

        String sql = "SELECT * FROM usuarios WHERE tipo != 3";
        rs = sentencia.executeQuery(sql);

        while (rs.next()) {
            usuarios.add(rowToUser(rs));
        }

        rs.close();
        sentencia.close();

        return usuarios;
    }

    public static String consultarTipoUsuario(Usuario usuario) throws SQLException {
        String tipo;
        ResultSet rs;
        Statement sentencia = conexion.createStatement();
        
        String sql = "SELECT nombre FROM tipousuario WHERE idTipo = '" + usuario.getTipo() + "'";
        rs = sentencia.executeQuery(sql);
        rs.absolute(1);
        tipo = rs.getString("nombre");
        sentencia.close();
        
        return tipo;
    }
    
    public static String eliminarUsuario(HttpServletRequest request) throws SQLException {
        String mensaje = null;
        boolean existe = AlquilerDB.mostrarReservasUsuario(request);
        String login = request.getParameter("login");
        Statement sentencia = conexion.createStatement();
        
        if(!existe){
            String sql = "DELETE FROM usuarios WHERE login = '" + login + "'";
            sentencia.executeUpdate(sql);
            sentencia.close();
        } else {
            mensaje = "No se puede eliminar el usuario, tiene habitaciones reservadas";
        }
        
        return mensaje;
    }
    
    public static void actualizarUsuario(HttpServletRequest request) throws SQLException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        password = hotel.getMD5(password);
        String nombre = request.getParameter("nombre");
        String apellidos = request.getParameter("apellidos");
        int telefono = Integer.valueOf(request.getParameter("telefono"));
        String id = request.getParameter("idUsuario");
        Statement sentencia = conexion.createStatement();
        
        String sql = "UPDATE usuarios SET login = '" + login + "', clave = '" + password + "', nombre = '" + nombre + "', "
                + "apellidos = '" + apellidos + "', telefono = '" + telefono + "' WHERE login = '" + id +"'";
        
        sentencia.executeUpdate(sql);
        sentencia.close();
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
