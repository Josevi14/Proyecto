<%
    int tipo;
    String login;
    login = (String) session.getAttribute("usuario");
    tipo = (Integer) session.getAttribute("tipo");

    if (login == null || tipo != 1) {
        response.sendRedirect("acceso.jsp");
    }
%>
<%-- 
    Document   : index
    Created on : 26-feb-2019, 14:39:03
    Author     : josev
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hotel Bella Vista</title>
    </head>
    <body>
        <table>
            <tr>
                <th>Id</th>
                <th>Numero</th>
                <th>Tipo</th>
                <th>Descripcion</th>
                <th>Precio por Dia</th>
            </tr>
            <jsp:include page="vistaHabitaciones.jsp"></jsp:include>
        </table>

    </body>
</html>
