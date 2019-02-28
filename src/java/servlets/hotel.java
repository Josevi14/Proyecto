/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import clases.HabitacionDB;
import clases.Usuario;
import clases.UsuarioDB;
import clases.tipoHabitacionDB;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author josev
 */
public class hotel extends HttpServlet {

    private Connection conexion;
    private final String url = "jdbc:mysql://localhost/hotel";
    private final String user = "root";
    private final String password = "";

    @Override
    public void init() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection(url, user, password);
            UsuarioDB.conectar(conexion);
            HabitacionDB.conectar(conexion);
            tipoHabitacionDB.conectar(conexion);
            // getServletContext()
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(hotel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion;
        HttpSession sesion;
        String url = "";

        sesion = request.getSession();
        accion = request.getParameter("accion");
        if (accion != null) {
            accion = accion.toUpperCase().replaceAll(" ", "");
            switch (accion) {
                case "ACCEDER":
                    try {
                        boolean ok;
                        int tipo;
                        ok = UsuarioDB.acceder(request);
                        if (ok) {
                            sesion.setAttribute("usuario", request.getParameter("login"));
                            tipo = (int) request.getAttribute("tipo");
                            sesion.setAttribute("tipo", tipo);

                            switch (tipo) {
                                case 1:
                                    url = "/index.jsp";
                                    break;
                                case 2:
                                    url = "/menuRecepcion.jsp";
                                    break;
                                default:
                                    url = "/menuAdmin.jsp";
                                    break;
                            }
                        } else {
                            // Preparamos el mensaje a través de la sesión
                            sesion.setAttribute("error", "Usuario incorrecto");
                            url = "/acceso.jsp";
                        }

                    } catch (SQLException ex) {
                        Logger.getLogger(hotel.class.getName()).log(Level.SEVERE, null, ex);
                        url = "/error.jsp";
                    }
                    break;
                case "REGISTRO":
                    url = "/registro.jsp";
                    break;
                case "REGISTRAR":
                    try {
                        UsuarioDB.registrar(request);
                        url = "/acceso.jsp";
                    } catch (SQLException ex) {
                        Logger.getLogger(hotel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case "RESERVAR":
                    url = "/menuReservas.jsp";
                    break;
            }
        }

        RequestDispatcher rd = request.getRequestDispatcher(url);
        rd.forward(request, response);
    }

    public static String getMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
