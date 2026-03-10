package com.mycompany.farma.grupo_02.model;

public class DispensacionDetalle {

    private int idDispensacionDetalle;
    private int idDispensacion;
    private int idRecetaDetalle;
    private int idMedicamento;
    private int cantidadDispensada;

    public DispensacionDetalle() {
    }

    public int getIdDispensacionDetalle() {
        return idDispensacionDetalle;
    }

    public void setIdDispensacionDetalle(int idDispensacionDetalle) {
        this.idDispensacionDetalle = idDispensacionDetalle;
    }

    public int getIdDispensacion() {
        return idDispensacion;
    }

    public void setIdDispensacion(int idDispensacion) {
        this.idDispensacion = idDispensacion;
    }

    public int getIdRecetaDetalle() {
        return idRecetaDetalle;
    }

    public void setIdRecetaDetalle(int idRecetaDetalle) {
        this.idRecetaDetalle = idRecetaDetalle;
    }

    public int getIdMedicamento() {
        return idMedicamento;
    }

    public void setIdMedicamento(int idMedicamento) {
        this.idMedicamento = idMedicamento;
    }

    public int getCantidadDispensada() {
        return cantidadDispensada;
    }

    public void setCantidadDispensada(int cantidadDispensada) {
        this.cantidadDispensada = cantidadDispensada;
    }

    @Override
    public String toString() {
        return "DispensacionDetalle{" +
                "idDispensacionDetalle=" + idDispensacionDetalle +
                ", idDispensacion=" + idDispensacion +
                ", idRecetaDetalle=" + idRecetaDetalle +
                ", idMedicamento=" + idMedicamento +
                ", cantidadDispensada=" + cantidadDispensada +
                '}';
    }
}