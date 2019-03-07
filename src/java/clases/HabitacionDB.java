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
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author josev
 */
public class HabitacionDB {

    private static Connection conexion;

    public static void conectar(Connection conexion) {
        HabitacionDB.conexion = conexion;
    }

    public static ArrayList<Habitacion> leerHabitaciones() throws SQLException {
        ArrayList<Habitacion> habitaciones = new ArrayList<>();
        ResultSet rs;
        Statement sentencia = conexion.createStatement();

        String sql = "SELECT * FROM habitaciones";
        rs = sentencia.executeQuery(sql);

        while (rs.next()) {
            habitaciones.add(rowToRoom(rs));
        }

        rs.close();
        sentencia.close();
        
        return habitaciones;
    }

    public static Habitacion rowToRoom(ResultSet rs) throws SQLException {
        int id, numero, tipo;

        id = rs.getInt("idHabitacion");
        numero = rs.getInt("numero");
        tipo = rs.getInt("tipo");

        Habitacion habitacion = new Habitacion(id, numero, tipo);
        return habitacion;
    }

    public static Habitacion consultarHabitacion(Alquiler a) throws SQLException {
        ResultSet rs;
        Habitacion h;
        Statement sentencia = conexion.createStatement();

        String sql = "SELECT * FROM habitaciones WHERE idHabitacion = '" + a.getHabitacion() + "'";
        rs = sentencia.executeQuery(sql);
        rs.absolute(1);
        h = rowToRoom(rs);

        rs.close();
        sentencia.close();

        return h;
    }
    
    public static void actualizarHabitacion(HttpServletRequest request) throws SQLException{
        int numero = Integer.valueOf(request.getParameter("numero"));
        String cadena = request.getParameter("tipo");
        int tipo = Integer.valueOf(cadena.substring(0, 1));
        int idHabitacion = Integer.valueOf(request.getParameter("idHabitacion"));
        
        Statement sentencia = conexion.createStatement();
        
        String sql = "UPDATE habitaciones SET numero = '" + numero + "',tipo = '" + tipo +"' WHERE idHabitacion = '" + idHabitacion + "'";
        sentencia.executeUpdate(sql);
        sentencia.close();
    }
    
    public static void agregarHabitacion(HttpServletRequest request) throws SQLException{
        int numero = Integer.valueOf(request.getParameter("numero"));
        String cadena = request.getParameter("tipo");
        int tipo = Integer.valueOf(cadena.substring(0, 1));
        
        Statement sentencia = conexion.createStatement();
        
        String sql = "INSERT INTO habitaciones(numero, tipo) VALUES('" + numero + "','" + tipo +"')";
        sentencia.executeUpdate(sql);
        sentencia.close();
    }
    
    public static String eliminarHabitacion(HttpServletRequest request) throws SQLException{
        int idHabitacion = Integer.valueOf(request.getParameter("idHabitacion"));
        boolean existe = AlquilerDB.mostrarReservasHabitacion(request);
        String mensaje = null;
        Statement sentencia = conexion.createStatement();
        
        if(!existe){
            String sql = "DELETE FROM habitaciones WHERE idHabitacion = '" + idHabitacion + "'";
            sentencia.executeUpdate(sql);
            sentencia.close();
        } else {
            mensaje = "No se puede borrar la habitacion, tiene usuarios con reserva";
        }
        
        return mensaje;
    }
}
