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
    ArrayList<Habitacion> habitaciones = HabitacionDB.leerHabitaciones();
    Iterator it = habitaciones.iterator();
    while (it.hasNext()) {
        Habitacion h = (Habitacion) it.next();
        tipoHabitacion tH = tipoHabitacionDB.consultarTipo(h);
%>
<form action="hotel" method="post" id="<%=h.getIdHabitacion()%>">
    <tr id="<%=h.getIdHabitacion()%>">
        <td><%=h.getIdHabitacion()%></td>
        <td><%=h.getNumero()%></td>
        <td><%=tH.getNombre()%></td>
        <td><%=tH.getDescripcion()%></td>
        <td><%=tH.getPrecioDia()%></td>
        <td><input type="date" name="fechaEntrada" id="fechaEntrada<%=h.getIdHabitacion()%>"/></td>
        <td><input type="date" name="fechaSalida" id="fechaSalida<%=h.getIdHabitacion()%>"/></td>
        <td><input type="submit" name="accion" value="Reservar" id="reservar<%=h.getIdHabitacion()%>"/></td>
        <input type="hidden" name="idHabitacion" id="idHabitacion" value="<%=h.getIdHabitacion()%>"/>
        <input type="hidden" name="precioDia" id="precioDia" value="<%=tH.getPrecioDia()%>"/>
        <input type="hidden" name="accion" value="Reservar"/>
    </tr>
</form>
<%
    }
%>