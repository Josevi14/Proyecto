<%-- 
    Document   : vistaHabitacionesRecepcion
    Created on : 05-mar-2019, 17:09:16
    Author     : josev
--%>

<%@page import="java.io.File"%>
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
        <th><%=h.getNumero()%></th>
        <td><img width="200" height="150" src="<%="imagenes" + File.separator + tH.getImagen()%>"/></td>
        <td><%=tH.getNombre()%></td>
        <td><%=tH.getDescripcion()%></td>
        <td><%=tH.getPrecioDia()%></td>
        <td><input type="submit" name="accion" value="Editar"/></td>
        <input type="hidden" name="idHabitacion" id="idHabitacion" value="<%=h.getIdHabitacion()%>"/>
        <input type="hidden" name="precioDia" id="precioDia" value="<%=tH.getPrecioDia()%>"/>
        <input type="hidden" name="accion" value="Reservar"/>
    </tr>
</form>
<%
    }
%>
