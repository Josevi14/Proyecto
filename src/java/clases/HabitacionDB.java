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

    public static ResultSet leerHabitaciones() throws SQLException {
        ResultSet rs;
        Statement sentencia = conexion.createStatement();

        String sql = "SELECT * FROM habitaciones";
        rs = sentencia.executeQuery(sql);

        return rs;
    }
    
    public static ResultSet leerFechas(int idHabitacion) throws SQLException {
        ResultSet rs;
        Statement sentencia = conexion.createStatement();
        
        String sql = "SELECT fechaEntrada, fechaSalida FROM alquiler WHERE habitacion='"+idHabitacion+"'";
        rs = sentencia.executeQuery(sql);
        rs.first();
        
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

    public static void alquilarHabitacion(HttpServletRequest request) throws SQLException {
        HttpSession sesion = request.getSession();
        String strFechaEntrada = request.getParameter("fechaEntrada");//Este
        String strFechaSalida = request.getParameter("fechaSalida");//Este
        
        String login = (String) sesion.getAttribute("usuario");//Este
        int habitacion = Integer.valueOf(request.getParameter("idHabitacion"));//Este
        int precioDia = Integer.valueOf(request.getParameter("precioDia"));
        int dias = Integer.valueOf(request.getParameter("dias"));
        int precioTotal = precioDia*dias;//Este

        Statement sentencia = conexion.createStatement();

        String sql = "INSERT INTO alquiler(fechaEntrada, fechaSalida, costoTotal, usuario, habitacion) VALUES('"+strFechaEntrada+"', '"+strFechaSalida+"', '"+precioTotal+"', '"+login+"', '"+habitacion+"')";
        sentencia.executeUpdate(sql);
    }
    
    
}
