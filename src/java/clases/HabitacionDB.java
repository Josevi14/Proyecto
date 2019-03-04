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

}
