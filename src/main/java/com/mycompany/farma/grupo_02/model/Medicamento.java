package com.mycompany.farma.grupo_02.model;

public class Medicamento {

    private int idMedicamento;
    private String codigo;
    private String nombre;
    private String presentacion;
    private String requiereReceta;
    private int stockActual;
    private int stockMinimo;
    private String estado;

    public Medicamento() {
    }

    public int getIdMedicamento() {
        return idMedicamento;
    }

    public void setIdMedicamento(int idMedicamento) {
        this.idMedicamento = idMedicamento;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public String getRequiereReceta() {
        return requiereReceta;
    }

    public void setRequiereReceta(String requiereReceta) {
        this.requiereReceta = requiereReceta;
    }

    public int getStockActual() {
        return stockActual;
    }

    public void setStockActual(int stockActual) {
        this.stockActual = stockActual;
    }

    public int getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(int stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Medicamento{" +
                "idMedicamento=" + idMedicamento +
                ", codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", presentacion='" + presentacion + '\'' +
                ", requiereReceta='" + requiereReceta + '\'' +
                ", stockActual=" + stockActual +
                ", stockMinimo=" + stockMinimo +
                ", estado='" + estado + '\'' +
                '}';
    }
}