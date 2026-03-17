package com.mycompany.farma.grupo_02.test;

import com.mycompany.farma.grupo_02.dao.DispensacionDAO;
import java.sql.SQLException;

public class PruebaAnularDispensacion {

    public static void main(String[] args) {
        DispensacionDAO dispensacionDAO = new DispensacionDAO();

        try {
            boolean anulada = dispensacionDAO.anularDispensacion(3);
            System.out.println("Dispensacion anulada: " + anulada);
        } catch (SQLException e) {
            System.out.println("Error al anular dispensacion: " + e.getMessage());
        }
    }
}