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
        
        String sql = "SELECT * FROM tipohabitacion where idTipo = '"+h.getTipo()+"'";
        rs = sentencia.executeQuery(sql);
        tipo = rowToTypeRoom(rs);
        
        return tipo;
    }
    
    public static tipoHabitacion rowToTypeRoom(ResultSet rs) throws SQLException {
        int id, precio;
        String nombre, descripcion;
        rs.first();
        
        id = rs.getInt("idTipo");
        nombre = rs.getString("nombre");
        descripcion = rs.getString("descripcion");
        precio = rs.getInt("precioDia");
        tipoHabitacion tipoHabitacion = new tipoHabitacion(id, nombre, descripcion, precio);
        return tipoHabitacion;
    }
}
