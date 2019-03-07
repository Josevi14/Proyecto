<%
    int tipo;
    String login;
    login = (String) session.getAttribute("usuario");
    tipo = (Integer) session.getAttribute("tipo");

    if (login == null || tipo != 3) {
        response.sendRedirect("acceso.jsp");
    }
%>
<%-- 
    Document   : menuAdmin
    Created on : 28-feb-2019, 12:32:19
    Author     : josev
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Menu Admin</title>
        <link rel="stylesheet" type="text/css" href="estilos/index.css"/>

    </head>
    <body>

        <form action="hotel" method="post">
            <input type="submit" name="accion" value="Agregar Recepcionista"/>
            <input type="submit" name="accion" value="Generar PDF"/>
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
                <th>Login</th>
                <th>Nombre</th>
                <th>Apellidos</th>
                <th>Telefono</th>
                <th>Tipo</th>
            </tr>
            <jsp:include page="vistaUsuarios.jsp"></jsp:include>
        </table>
    </body>
</html>
