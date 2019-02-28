<%-- 
    Document   : acceso
    Created on : 27-feb-2019, 18:52:21
    Author     : josev
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Acceso Hotel</title>
    </head>
    <body>
        <%
            String mensaje;
            mensaje = (String) session.getAttribute("error");
            if (mensaje != null) {
                out.print(mensaje);
                session.removeAttribute("error");
            }
        %>
        <form action="hotel" method="post" enctype="multipart/form-data">
            <input type="text" name="login" placeholder="login"/>
            <input type="password" name="password" placeholder="password"/>
            <input type="submit" name="accion" value="Acceder"/>
            <input type="submit" name="accion" value="Registrar"/>
        </form>
    </body>
</html>
