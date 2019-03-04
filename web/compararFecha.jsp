<%-- 
    Document   : compararFecha
    Created on : 03-mar-2019, 13:28:19
    Author     : josev
--%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="clases.HabitacionDB"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.util.Date"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    int idHabitacion = Integer.valueOf(request.getParameter("idHabitacion"));
    ResultSet rs;
    rs = HabitacionDB.leerFechas(idHabitacion);
    rs.beforeFirst();
    
    JSONArray jarray = new JSONArray();
    while(rs.next()){
        JSONObject obj = new JSONObject();
        String fechaEntrada = rs.getString("fechaEntrada");
        String fechaSalida = rs.getString("fechaSalida");
        
        obj.put("fechaEntrada", fechaEntrada);
        obj.put("fechaSalida", fechaSalida);
        jarray.add(obj);

    }
    
    out.print(jarray);
%>
