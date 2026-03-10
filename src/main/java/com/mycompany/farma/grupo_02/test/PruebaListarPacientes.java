package com.mycompany.farma.grupo_02.test;

import com.mycompany.farma.grupo_02.dao.PacienteDAO;
import com.mycompany.farma.grupo_02.model.Paciente;
import java.sql.SQLException;
import java.util.List;

public class PruebaListarPacientes {

    public static void main(String[] args) {
        PacienteDAO dao = new PacienteDAO();

        try {
            List<Paciente> pacientes = dao.listarPacientes();
            for (Paciente p : pacientes) {
                System.out.println(p);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar pacientes: " + e.getMessage());
        }
    }
}