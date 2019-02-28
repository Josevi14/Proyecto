<%-- 
    Document   : vistaHabitaciones
    Created on : 28-feb-2019, 16:58:07
    Author     : josev
--%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="clases.Habitacion"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="clases.HabitacionDB"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    ResultSet rs;
    ArrayList<Habitacion> habitaciones = new ArrayList<Habitacion>();
    Iterator it = habitaciones.iterator();
    rs = HabitacionDB.leerHabitaciones();

    while (rs.next()) {
        Habitacion habitacion;
        habitacion = HabitacionDB.rowToRoom(rs);
        habitaciones.add(habitacion);
    }

    while (it.hasNext()) {
        Habitacion h = (Habitacion) it.next();
        %>
        <tr>
            <td><%=h.getIdHabitacion()%></td>
            <td><%=h.getNumero()%></td>
            <td><%=h.getTipo()%></td>
        </tr>

        <%
    }
%>

