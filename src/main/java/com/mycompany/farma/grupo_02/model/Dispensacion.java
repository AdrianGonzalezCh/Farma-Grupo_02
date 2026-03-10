package com.mycompany.farma.grupo_02.model;

import java.sql.Date;

public class Dispensacion {

    private int idDispensacion;
    private int idReceta;
    private int idUsuario;
    private Date fechaDispensacion;
    private String estado;
    private String observaciones;

    public Dispensacion() {
    }

    public int getIdDispensacion() {
        return idDispensacion;
    }

    public void setIdDispensacion(int idDispensacion) {
        this.idDispensacion = idDispensacion;
    }

    public int getIdReceta() {
        return idReceta;
    }

    public void setIdReceta(int idReceta) {
        this.idReceta = idReceta;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Date getFechaDispensacion() {
        return fechaDispensacion;
    }

    public void setFechaDispensacion(Date fechaDispensacion) {
        this.fechaDispensacion = fechaDispensacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @Override
    public String toString() {
        return "Dispensacion{" +
                "idDispensacion=" + idDispensacion +
                ", idReceta=" + idReceta +
                ", idUsuario=" + idUsuario +
                ", fechaDispensacion=" + fechaDispensacion +
                ", estado='" + estado + '\'' +
                ", observaciones='" + observaciones + '\'' +
                '}';
    }
}