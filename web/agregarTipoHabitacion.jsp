<%-- 
    Document   : agregarTipoHabitacion
    Created on : 05-mar-2019, 18:39:50
    Author     : josev
--%>

<%@page import="servlets.hotel"%>
<%@page import="java.io.File"%>
<%@page import="clases.tipoHabitacion"%>
<%@page import="java.util.Iterator"%>
<%@page import="clases.tipoHabitacionDB"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agregar Tipo Habitacion</title>
        <link rel="stylesheet" type="text/css" href="estilos/index.css"/>

    </head>
    <body>
        <table>
            <form action="hotel" method="post" enctype="multipart/form-data">
                <tr>
                    <td><label>Nombre</label></td>
                    <td><input type="text" name="nombre"/></td>
                </tr>
                <tr>
                    <td><label>Descripcion</label></td>
                    <td><input type="text" name="descripcion"</td>
                </tr>
                <tr>
                    <td><label>Precio por dia</label></td>
                    <td><input type="text" name="precioDia"</td>
                </tr>
                <tr>
                    <td><label>Imagen</label></td>
                    <td><input type="file" name="imagen"</td>
                </tr>
                <tr>
                    <td><input type="submit" name="accion" value="Agregar"/>
                        <input type="submit" name="accion" value="Volver"/></td>
                </tr>
            </form>
        </table>
    </body>
</html>
