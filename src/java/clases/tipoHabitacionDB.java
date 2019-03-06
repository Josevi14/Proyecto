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
import javax.servlet.http.HttpSession;

/**
 *
 * @author josev
 */
public class tipoHabitacionDB {
    private static Connection conexion;

    public static void conectar(Connection conexion) {
        tipoHabitacionDB.conexion = conexion;
    }
    
    public static tipoHabitacion consultarTipo(Habitacion h) throws SQLException{
        ResultSet rs;
        tipoHabitacion tipo;
        Statement sentencia = conexion.createStatement();
        
        String sql = "SELECT * FROM tipohabitacion WHERE idTipo = '"+h.getTipo()+"'";
        rs = sentencia.executeQuery(sql);
        tipo = rowToTypeRoom(rs);
        
        return tipo;
    }
    
    public static ArrayList<tipoHabitacion> consultarTipos() throws SQLException{
        ArrayList<tipoHabitacion> tipos = new ArrayList<>();
        ResultSet rs;
        Statement sentencia = conexion.createStatement();
        
        String sql = "SELECT * FROM tipohabitacion";
        rs = sentencia.executeQuery(sql);
        
        while(rs.next()){
            tipoHabitacion tipo;
            int id = rs.getInt("idTipo");
            String nombre = rs.getString("nombre");
            String descripcion = rs.getString("descripcion");
            int precioDia = rs.getInt("precioDia");
            tipo = new tipoHabitacion(id, nombre, descripcion, precioDia);
            tipos.add(tipo);
        }
                
        return tipos;
    }
    
    public static tipoHabitacion rowToTypeRoom(ResultSet rs) throws SQLException {
        int id, precio;
        String nombre, descripcion, imagen;
        rs.first();
        
        id = rs.getInt("idTipo");
        nombre = rs.getString("nombre");
        descripcion = rs.getString("descripcion");
        imagen = rs.getString("imagen");
        precio = rs.getInt("precioDia");
        tipoHabitacion tipoHabitacion = new tipoHabitacion(id, nombre, descripcion, imagen, precio);
        return tipoHabitacion;
    }
    
    public static void agregarTipoHabitacion(HttpServletRequest request) throws SQLException{
        HttpSession sesion = request.getSession();
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        int precioDia = Integer.valueOf(request.getParameter("precioDia"));
        String imagen = (String) sesion.getAttribute("imagen");
        
        Statement sentencia = conexion.createStatement();
        
        String sql = "INSERT INTO tipohabitacion(imagen, nombre, descripcion, precioDia) VALUES('" + imagen + "','" + nombre +"','" + descripcion + "','" + precioDia + "')";
        sentencia.executeUpdate(sql);
        sentencia.close();
    }
}
