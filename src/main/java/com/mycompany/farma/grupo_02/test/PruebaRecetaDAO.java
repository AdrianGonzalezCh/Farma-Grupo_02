package com.mycompany.farma.grupo_02.test;

import com.mycompany.farma.grupo_02.dao.RecetaDAO;
import com.mycompany.farma.grupo_02.model.Receta;
import com.mycompany.farma.grupo_02.model.RecetaDetalle;
import java.sql.SQLException;

public class PruebaRecetaDAO {

    public static void main(String[] args) {
        RecetaDAO recetaDAO = new RecetaDAO();

        try {
            Receta receta = new Receta();
            receta.setIdPaciente(1);
            receta.setIdMedico(1);
            receta.setObservaciones("Receta creada desde Java");

            int idRecetaGenerada = recetaDAO.insertarReceta(receta);
            System.out.println("ID receta generada: " + idRecetaGenerada);

            RecetaDetalle detalle1 = new RecetaDetalle();
            detalle1.setIdReceta(idRecetaGenerada);
            detalle1.setIdMedicamento(1);
            detalle1.setDosis("1 capsula cada 8 horas");
            detalle1.setCantidadAutorizada(12);
            detalle1.setIndicaciones("Tomar por 4 dias");

            boolean detalleInsertado1 = recetaDAO.insertarDetalleReceta(detalle1);
            System.out.println("Detalle 1 insertado: " + detalleInsertado1);

            RecetaDetalle detalle2 = new RecetaDetalle();
            detalle2.setIdReceta(idRecetaGenerada);
            detalle2.setIdMedicamento(4);
            detalle2.setDosis("1 tableta cada 6 horas");
            detalle2.setCantidadAutorizada(8);
            detalle2.setIndicaciones("Tomar solo si hay dolor");

            boolean detalleInsertado2 = recetaDAO.insertarDetalleReceta(detalle2);
            System.out.println("Detalle 2 insertado: " + detalleInsertado2);

            Receta recetaConsultada = recetaDAO.consultarRecetaPorId(idRecetaGenerada);
            System.out.println("Receta consultada:");
            System.out.println(recetaConsultada);

        } catch (SQLException e) {
            System.out.println("Error en DAO de receta: " + e.getMessage());
        }
    }
}