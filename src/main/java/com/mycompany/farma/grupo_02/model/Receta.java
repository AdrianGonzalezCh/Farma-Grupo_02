package com.mycompany.farma.grupo_02.model;

import java.sql.Date;

public class Receta {

    private int idReceta;
    private int idPaciente;
    private int idMedico;
    private Date fechaEmision;
    private String estado;
    private String observaciones;

    public Receta() {
    }

    public int getIdReceta() {
        return idReceta;
    }

    public void setIdReceta(int idReceta) {
        this.idReceta = idReceta;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public int getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
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
        return "Receta{" +
                "idReceta=" + idReceta +
                ", idPaciente=" + idPaciente +
                ", idMedico=" + idMedico +
                ", fechaEmision=" + fechaEmision +
                ", estado='" + estado + '\'' +
                ", observaciones='" + observaciones + '\'' +
                '}';
    }
}