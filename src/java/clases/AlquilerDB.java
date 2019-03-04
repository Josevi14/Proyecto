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
public class AlquilerDB {

    private static Connection conexion;

    public static void conectar(Connection conexion) {
        AlquilerDB.conexion = conexion;
    }

    public static ResultSet leerFechas(int idHabitacion) throws SQLException {
        ResultSet rs;
        Statement sentencia = conexion.createStatement();

        String sql = "SELECT fechaEntrada, fechaSalida FROM alquiler WHERE habitacion='" + idHabitacion + "'";
        rs = sentencia.executeQuery(sql);

        return rs;
    }

    public static void alquilarHabitacion(HttpServletRequest request) throws SQLException {
        HttpSession sesion = request.getSession();
        String strFechaEntrada = request.getParameter("fechaEntrada");//Este
        String strFechaSalida = request.getParameter("fechaSalida");//Este

        String login = (String) sesion.getAttribute("usuario");//Este
        int habitacion = Integer.valueOf(request.getParameter("idHabitacion"));//Este
        int precioDia = Integer.valueOf(request.getParameter("precioDia"));
        int dias = Integer.valueOf(request.getParameter("dias"));
        int precioTotal = precioDia * dias;//Este

        Statement sentencia = conexion.createStatement();

        String sql = "INSERT INTO alquiler(fechaEntrada, fechaSalida, costoTotal, usuario, habitacion) VALUES('" + strFechaEntrada + "', '" + strFechaSalida + "', '" + precioTotal + "', '" + login + "', '" + habitacion + "')";
        sentencia.executeUpdate(sql);
        sentencia.close();
    }

    public static ArrayList<Alquiler> mostrarReservas(HttpServletRequest request) throws SQLException {
        ResultSet rs;
        HttpSession sesion = request.getSession();
        Statement sentencia = conexion.createStatement();
        String login = (String) sesion.getAttribute("usuario");
        ArrayList<Alquiler> alquileres = new ArrayList<Alquiler>();

        String sql = "SELECT * FROM alquiler WHERE usuario='" + login + "'";
        rs = sentencia.executeQuery(sql);

        while (rs.next()) {
            alquileres.add(rowToAlquiler(rs));
        }
        
        rs.close();
        sentencia.close();

        return alquileres;
    }

    public static Alquiler rowToAlquiler(ResultSet rs) throws SQLException {
        int id, costoTotal, habitacion;
        String fechaEntrada, fechaSalida, usuario;

        id = rs.getInt("idAlquiler");
        fechaEntrada = rs.getString("fechaEntrada");
        fechaSalida = rs.getString("fechaSalida");
        costoTotal = rs.getInt("costoTotal");
        usuario = rs.getString("usuario");
        habitacion = rs.getInt("habitacion");

        Alquiler alquiler = new Alquiler(id, fechaEntrada, fechaSalida, costoTotal, usuario, habitacion);
        return alquiler;
    }
}
