<%
    int tipo;
    String login;
    login = (String) session.getAttribute("usuario");
    tipo = (Integer) session.getAttribute("tipo");

    if (login == null || tipo != 2) {
        response.sendRedirect("acceso.jsp");
    }
%>
<%-- 
    Document   : menuRecepcion
    Created on : 28-feb-2019, 12:31:58
    Author     : josev
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Menu Recepcion</title>
        <link rel="stylesheet" type="text/css" href="estilos/index.css"/>

    </head>
    <body>
        <form action="hotel" method="post">
            <input type="submit" name="accion" value="Agregar Habitacion"/>
            <input type="submit" name="accion" value="Agregar Tipo Habitacion"/>
            <input type="submit" name="accion" value="Cerrar Sesion"/>
        </form>
        <%
            if (session.getAttribute("mensaje") != null) {
                String mensaje = (String) session.getAttribute("mensaje");
                out.print(mensaje);
            }
        %>
        <table id="table">
            <tr>
                <th>Numero</th>
                <th></th>
                <th>Tipo</th>
                <th>Descripcion</th>
                <th>Precio por Dia</th>
            </tr>
            <jsp:include page="vistaHabitacionesRecepcion.jsp"></jsp:include>
        </table>
    </body>
</html>
