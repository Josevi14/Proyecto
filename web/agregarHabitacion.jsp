<%-- 
    Document   : agregarHabitacion
    Created on : 05-mar-2019, 18:39:30
    Author     : josev
--%>

<%@page import="clases.tipoHabitacionDB"%>
<%@page import="clases.tipoHabitacion"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="estilos/index.css"/>

        <title>Agregar Habitacion</title>
    </head>
    <body>
        <table>
            <form action="hotel" method="post">
                <tr>
                    <td><label>Numero</label></td>
                    <td><input type="text" name="numero"/></td>
                </tr>
                <%--<td><select name="imagen">
                            <%
                            String ruta = this.getServletContext().getRealPath("/") + "imagenes" +File.separator;
                            String[] array = hotel.listarFicheros(ruta);
                            for(int i = 0; i < array.length; i++){
                                %><option><%=array[i]%></option><%
                            }
                            %>
                        </select>
                    </td>--%>
                <tr>
                    <td><label>Tipo</label></td>
                    <td><select name="tipo">
                            <%
                                ArrayList<tipoHabitacion> tipos = tipoHabitacionDB.consultarTipos();
                                Iterator it = tipos.iterator();
                                while (it.hasNext()) {
                                    tipoHabitacion tipo = (tipoHabitacion) it.next();
                            %><option><%=tipo.getId() + " " + tipo.getNombre()%></option><%
                                }
                            %>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><input type="submit" name="accion" value="Añadir"/>
                        <input type="submit" name="accion" value="Volver"/></td>

                <input type="hidden" name="idHabitacion" value="<%=request.getParameter("idHabitacion")%>"/>
                </tr>
            </form>
        </table>
    </body>
</html>
