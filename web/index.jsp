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
        <script src="js/jquery.js"></script>
        <script src="js/index.js"></script>
        <title>Hotel Bella Vista</title>
    </head>
    <body>
        <label>Desde</label>
        <select id="diaEntrada">
            
        </select>
        <select id="mesEntrada">
            
        </select>
        <select id="anioEntrada">
            
        </select><br/><br/>
        <label>Hasta</label>
        <select id="diaSalida">
            
        </select>
        <select id="mesSalida">
            
        </select>
        <select id="anioSalida">
            
        </select>
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
