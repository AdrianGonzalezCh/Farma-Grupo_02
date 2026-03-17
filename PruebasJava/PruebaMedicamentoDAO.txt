package com.mycompany.farma.grupo_02.test;

import com.mycompany.farma.grupo_02.dao.MedicamentoDAO;
import com.mycompany.farma.grupo_02.model.Medicamento;
import java.sql.SQLException;

public class PruebaMedicamentoDAO {

    public static void main(String[] args) {
        MedicamentoDAO medicamentoDAO = new MedicamentoDAO();

        try {
            Medicamento medicamento = medicamentoDAO.consultarMedicamentoPorId(1);

            if (medicamento != null) {
                System.out.println("Medicamento encontrado:");
                System.out.println(medicamento);
            } else {
                System.out.println("No se encontro el medicamento.");
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar medicamento: " + e.getMessage());
        }
    }
}