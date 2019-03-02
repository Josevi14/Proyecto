<%-- 
    Document   : vistaHabitaciones
    Created on : 28-feb-2019, 16:58:07
    Author     : josev
--%>
<%@page import="clases.tipoHabitacion"%>
<%@page import="clases.tipoHabitacionDB"%>
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
    rs = HabitacionDB.leerHabitaciones();
    rs.beforeFirst();
    
    while (rs.next()) {
        Habitacion habitacion = HabitacionDB.rowToRoom(rs);
        habitaciones.add(habitacion);
    }
    
    Iterator it = habitaciones.iterator();
    int contador = 1;
    while (it.hasNext()) {
        Habitacion h = (Habitacion) it.next();
        tipoHabitacion tH = tipoHabitacionDB.consultarTipo(h);
        %>
        <form action="hotel" method="post">
            <tr id="<%=contador%>">
                <td><%=h.getIdHabitacion()%></td>
                <td><%=h.getNumero()%></td>
                <td><%=tH.getNombre()%></td>
                <td><%=tH.getDescripcion()%></td>
                <td><%=tH.getPrecioDia()%></td>
                <td><input type="submit" name="accion" value="Reservar"/></td>
            <input type="hidden" name="idHabitacion" value="<%=h.getIdHabitacion()%>"/>
            <input type="hidden" name="precioDia" value="<%=tH.getPrecioDia()%>"/>
            </tr>
        </form>
        <%
            contador++;
    }
%>

