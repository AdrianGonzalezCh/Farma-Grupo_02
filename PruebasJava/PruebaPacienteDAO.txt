package com.mycompany.farma.grupo_02.test;

import com.mycompany.farma.grupo_02.dao.PacienteDAO;
import com.mycompany.farma.grupo_02.model.Paciente;
import java.sql.SQLException;

public class PruebaPacienteDAO {

    public static void main(String[] args) {
        PacienteDAO pacienteDAO = new PacienteDAO();

        try {
            Paciente paciente = pacienteDAO.consultarPacientePorId(1);

            if (paciente != null) {
                System.out.println("Paciente encontrado:");
                System.out.println(paciente);
            } else {
                System.out.println("No se encontro el paciente.");
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar paciente: " + e.getMessage());
        }
    }
}