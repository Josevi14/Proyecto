/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

/**
 *
 * @author josev
 */
public class Usuario {
    private String login;
    private String clave;
    private String nombre;
    private String apellidos;
    private int telefono;
    private int tipo;

    public Usuario(String login, String clave, String nombre, String apellidos, int telefono, int tipo) {
        this.login = login;
        this.clave = clave;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.tipo = tipo;
    }

    public Usuario(String login, String clave, String nombre, String apellidos, int telefono) {
        this.login = login;
        this.clave = clave;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.tipo = 1;
    }
    
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }  
}
