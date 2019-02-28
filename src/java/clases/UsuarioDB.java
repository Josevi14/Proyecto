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
    
    public static void conectar(Connection conexion){
        UsuarioDB.conexion = conexion;
    }
    
    public static boolean acceder(HttpServletRequest request) throws SQLException{
        boolean ok;
        ok = false;
        ResultSet rs;
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        password = hotel.getMD5(password);
        
        Statement sentencia = conexion.createStatement();
        
        String sql = "SELECT * FROM usuarios WHERE login ='"+ login +"'";
        rs = sentencia.executeQuery(sql);

        String clave = rs.getString("clave");
        int tipo = rs.getInt("tipo");
        
        if(password.equals(clave)){
            ok = true;
            request.setAttribute("tipo", tipo);
        }
        
        return ok;
    }
}
