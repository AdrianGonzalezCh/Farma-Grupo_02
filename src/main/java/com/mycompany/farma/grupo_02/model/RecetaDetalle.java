package com.mycompany.farma.grupo_02.model;

public class RecetaDetalle {

    private int idRecetaDetalle;
    private int idReceta;
    private int idMedicamento;
    private String dosis;
    private int cantidadAutorizada;
    private int cantidadDispensada;
    private String indicaciones;

    public RecetaDetalle() {
    }

    public int getIdRecetaDetalle() {
        return idRecetaDetalle;
    }

    public void setIdRecetaDetalle(int idRecetaDetalle) {
        this.idRecetaDetalle = idRecetaDetalle;
    }

    public int getIdReceta() {
        return idReceta;
    }

    public void setIdReceta(int idReceta) {
        this.idReceta = idReceta;
    }

    public int getIdMedicamento() {
        return idMedicamento;
    }

    public void setIdMedicamento(int idMedicamento) {
        this.idMedicamento = idMedicamento;
    }

    public String getDosis() {
        return dosis;
    }

    public void setDosis(String dosis) {
        this.dosis = dosis;
    }

    public int getCantidadAutorizada() {
        return cantidadAutorizada;
    }

    public void setCantidadAutorizada(int cantidadAutorizada) {
        this.cantidadAutorizada = cantidadAutorizada;
    }

    public int getCantidadDispensada() {
        return cantidadDispensada;
    }

    public void setCantidadDispensada(int cantidadDispensada) {
        this.cantidadDispensada = cantidadDispensada;
    }

    public String getIndicaciones() {
        return indicaciones;
    }

    public void setIndicaciones(String indicaciones) {
        this.indicaciones = indicaciones;
    }

    @Override
    public String toString() {
        return "RecetaDetalle{" +
                "idRecetaDetalle=" + idRecetaDetalle +
                ", idReceta=" + idReceta +
                ", idMedicamento=" + idMedicamento +
                ", dosis='" + dosis + '\'' +
                ", cantidadAutorizada=" + cantidadAutorizada +
                ", cantidadDispensada=" + cantidadDispensada +
                ", indicaciones='" + indicaciones + '\'' +
                '}';
    }
}