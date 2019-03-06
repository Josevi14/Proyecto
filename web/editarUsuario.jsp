<%-- 
    Document   : editarUsuario
    Created on : 06-mar-2019, 18:20:05
    Author     : josev
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar Usuario</title>
    </head>
    <body>
        <table>
            <form action="hotel" method="post">
                <table>
                    <tr>
                        <td><label>Login</label></td>
                        <td><input type="text" name="login"/></td>
                    </tr>
                    <tr>
                        <td><label>Clave</label></td>
                        <td><input type="password" name="password"/></td>
                    </tr>
                    <tr>
                        <td><label>Nombre</label></td>
                        <td><input type="text" name="nombre"/></td>
                    </tr>
                    <tr>
                        <td><label>Apellidos</label></td>
                        <td><input type="text" name="apellidos"/></td>
                    </tr>
                    <tr>
                        <td><label>Telefono</label></td>
                        <td><input type="text" name="telefono"/></td>
                    </tr>
                    <tr>
                        <td><input type="submit" name="accion" value="Actualizar"/></td>
                        <td><input type="submit" name="accion" value="Volver"/></td>
                        <input type="hidden" name="idUsuario" value="<%=request.getParameter("idUsuario")%>"/>
                    </tr>
                </table>
            </form>
        </table>
    </body>
</html>
