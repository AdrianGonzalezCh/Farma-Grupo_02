package com.mycompany.farma.grupo_02.test;

import com.mycompany.farma.grupo_02.dao.DispensacionDAO;
import java.sql.SQLException;

public class PruebaDispensacionDAO {

    public static void main(String[] args) {
        DispensacionDAO dispensacionDAO = new DispensacionDAO();

        try {
            int idDispensacion = dispensacionDAO.insertarDispensacion(
                    21,
                    1,
                    "Dispensacion realizada desde Java"
            );
            System.out.println("ID dispensacion generada: " + idDispensacion);

            boolean detalle1 = dispensacionDAO.dispensarMedicamento(
                    idDispensacion,
                    21,
                    1,
                    5
            );
            System.out.println("Dispensar detalle 1: " + detalle1);

            boolean detalle2 = dispensacionDAO.dispensarMedicamento(
                    idDispensacion,
                    22,
                    4,
                    3
            );
            System.out.println("Dispensar detalle 2: " + detalle2);

        } catch (SQLException e) {
            System.out.println("Error en dispensacion: " + e.getMessage());
        }
    }
}