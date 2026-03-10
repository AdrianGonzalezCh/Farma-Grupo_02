package com.mycompany.farma.grupo_02.test;

import com.mycompany.farma.grupo_02.dao.MedicoDAO;
import com.mycompany.farma.grupo_02.model.Medico;
import java.sql.SQLException;

public class PruebaMedicoDAO {

    public static void main(String[] args) {
        MedicoDAO medicoDAO = new MedicoDAO();

        try {
            Medico medico = medicoDAO.consultarMedicoPorId(1);

            if (medico != null) {
                System.out.println("Medico encontrado:");
                System.out.println(medico);
            } else {
                System.out.println("No se encontro el medico.");
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar medico: " + e.getMessage());
        }
    }
}