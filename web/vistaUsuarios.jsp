<%-- 
    Document   : vistaUsuarios
    Created on : 06-mar-2019, 16:57:56
    Author     : josev
--%>

<%@page import="clases.Usuario"%>
<%@page import="clases.UsuarioDB"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    ArrayList<Usuario> usuarios = UsuarioDB.leerUsuarios();
    Iterator it = usuarios.iterator();
    while (it.hasNext()) {
        Usuario usuario = (Usuario) it.next();
        String tipo = UsuarioDB.consultarTipoUsuario(usuario);
%>
<form action="hotel" method="post">
    <tr>
        <td><%=usuario.getLogin()%></td>
        <td><%=usuario.getNombre()%></td>
        <td><%=usuario.getApellidos()%></td>
        <td><%=usuario.getTelefono()%></td>
        <td><%=tipo%></td>
        <td><%if(usuario.getTipo() == 2){%><input type="submit" name="accion" value="Editar"/><%}%>
            <input type="submit" name="accion" value="Eliminar"/></td>
        <input type="hidden" name="idUsuario" value="<%=usuario.getLogin()%>"/>
    </tr>
</form>
<%
    }
%>
