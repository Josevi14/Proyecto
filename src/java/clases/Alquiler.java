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
public class Alquiler {
    private int idAlquiler;
    private String fechaEntrada;
    private String fechaSalida;
    private int costoTotal;
    private String usuario;
    private int habitacion;

    public Alquiler(int idAlquiler, String fechaEntrada, String fechaSalida, int costoTotal, String usuario, int habitacion) {
        this.idAlquiler = idAlquiler;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.costoTotal = costoTotal;
        this.usuario = usuario;
        this.habitacion = habitacion;
    }

    public int getIdAlquiler() {
        return idAlquiler;
    }

    public void setIdAlquiler(int idAlquiler) {
        this.idAlquiler = idAlquiler;
    }

    public String getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(String fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public int getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(int costoTotal) {
        this.costoTotal = costoTotal;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(int habitacion) {
        this.habitacion = habitacion;
    }
}
