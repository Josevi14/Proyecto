<%-- 
    Document   : misReservas
    Created on : 04-mar-2019, 13:45:56
    Author     : josev
--%>

<%@page import="servlets.hotel"%>
<%@page import="clases.Habitacion"%>
<%@page import="clases.HabitacionDB"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="clases.Alquiler"%>
<%@page import="clases.AlquilerDB"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mis Reservas</title>
    </head>
    <body>
        <table>
            <tr>
                <th>Id</th>
                <th>Fecha Entrada</th>
                <th>Fecha Salida</th>
                <th>Coste Total</th>
                <th>Habitacion</th>
            </tr>
            <%
                ArrayList<Alquiler> alquileres = AlquilerDB.mostrarReservas(request);
                ArrayList<Alquiler> array;
                hotel.exportarXML(alquileres, response, session);
                array = hotel.importarXML(session);

                Iterator it = array.iterator();
                while (it.hasNext()) {
                    Alquiler a = (Alquiler) it.next();
            %>
            <tr>
                <td><%=a.getIdAlquiler()%></td>
                <td><%=a.getFechaEntrada()%></td>
                <td><%=a.getFechaSalida()%></td>
                <td><%=a.getCostoTotal()%></td>
                <td><%=a.getHabitacion()%></td>
            </tr>
            <%
                }
            %>
            <%--<form action="hotel" method="post">
                <tr>
                    <td><%=a.getIdAlquiler()%></td>
                    <td><%=a.getFechaEntrada()%></td>
                    <td><%=a.getFechaSalida()%></td>
                    <td><%=a.getCostoTotal()%></td>
                    <td><%=h.getNumero()%></td>
                </tr>
                <input type="hidden" name="idAlquiler" value="<%=a.getIdAlquiler()%>"
            </form>--%>
        </table>

    </body>
</html>
