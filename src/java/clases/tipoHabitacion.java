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
public class tipoHabitacion {
    private int id;
    private String nombre;
    private String descripcion;
    private String imagen;
    private int precioDia;

    public tipoHabitacion(int id, String nombre, String descripcion, int precioDia) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioDia = precioDia;
    }
    
    public tipoHabitacion(int id, String nombre, String descripcion, String imagen, int precioDia) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.precioDia = precioDia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPrecioDia() {
        return precioDia;
    }

    public void setPrecioDia(int precioDia) {
        this.precioDia = precioDia;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    
}
