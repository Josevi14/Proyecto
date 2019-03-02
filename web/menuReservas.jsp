<%-- 
    Document   : menuReservas
    Created on : 28-feb-2019, 17:59:16
    Author     : josev
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reservar una habitacion</title>
    </head>
    <body>
        <table>
               <form action="hotel" method="post">
                   <tr>
                       <td><label for="fechaEntrada">Fecha Entrada: </label></td>
                       <td><input type="date" name="fechaEntrada"</td>
                   </tr>
                   <tr>
                       <td><label for="fechaSalida">Fecha Salida: </label></td>
                       <td><input type="date" name="fechaSalida"</td>
                   </tr>
                   <tr>
                       <td><input type="submit" name="accion" value="Reservar"</td>
                   </tr>
                </form> 
        </table>
        
    </body>
</html>
