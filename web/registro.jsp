<%-- 
    Document   : registro
    Created on : 28-feb-2019, 13:44:33
    Author     : josev
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registrarse</title>
    </head>
    <body>
        <form action="hotel" method="post">
            <table>
                <tr>
                    <td><input type="text" name="login" placeholder="Login"/></td>
                </tr>
                <tr>
                    <td><input type="password" name="password" placeholder="Password"/></td>
                </tr>
                <tr>
                    <td><input type="text" name="nombre" placeholder="Nombre"/></td>
                </tr>
                <tr>
                    <td><input type="text" name="apellidos" placeholder="Apellidos"/></td>
                </tr>
                <tr>
                    <td><input type="text" name="telefono" placeholder="Telefono"/></td>
                </tr>
                <tr>
                    <td><input type="submit" name="accion" value="Registrar"/>
                    <input type="submit" name="accion" value="Volver"</td>
                </tr>
            </table>
        </form>
    </body>
</html>
