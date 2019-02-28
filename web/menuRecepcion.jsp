<%
    int tipo;
    String login;
    login = (String) session.getAttribute("usuario");
    tipo = (Integer) session.getAttribute("tipo");
    
    if(login == null || tipo != 2){
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
    </head>
    <body>
    </body>
</html>
