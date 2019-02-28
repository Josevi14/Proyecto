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

/**
 *
 * @author josev
 */
public class HabitacionDB {
    private static Connection conexion;

    public static void conectar(Connection conexion) {
        HabitacionDB.conexion = conexion;
    }
    
    public static ResultSet leerHabitaciones() throws SQLException{
        ResultSet rs;
        Statement sentencia = conexion.createStatement();
        
        String sql = "SELECT * FROM habitaciones";
        rs = sentencia.executeQuery(sql);
        
        return rs;
    }
    
    public static Habitacion rowToRoom(ResultSet rs) throws SQLException {
        int id, numero, tipo;
        
        id = rs.getInt("idHabitacion");
        numero = rs.getInt("numero");
        tipo = rs.getInt("tipo");
        Habitacion habitacion = new Habitacion(id, numero, tipo);
        return habitacion;
    }
}
