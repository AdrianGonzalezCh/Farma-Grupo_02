package com.mycompany.farma.grupo_02.test;

import com.mycompany.farma.grupo_02.dao.PacienteDAO;
import com.mycompany.farma.grupo_02.model.Paciente;
import java.sql.SQLException;

public class PruebaPacienteCRUD {

    public static void main(String[] args) {
        PacienteDAO pacienteDAO = new PacienteDAO();

        try {
            // INSERTAR
            Paciente nuevoPaciente = new Paciente();
            nuevoPaciente.setCedula("9-9999-9999");
            nuevoPaciente.setNombre("Laura");
            nuevoPaciente.setApellido1("Mora");
            nuevoPaciente.setApellido2("Vega");
            nuevoPaciente.setTelefono("89998888");
            nuevoPaciente.setCorreo("laura@email.com");
            nuevoPaciente.setEstado("ACTIVO");

            boolean insertado = pacienteDAO.insertarPaciente(nuevoPaciente);
            System.out.println("Insertar paciente: " + insertado);

            // CONSULTAR
            Paciente paciente = pacienteDAO.consultarPacientePorId(1);
            System.out.println("Paciente consultado:");
            System.out.println(paciente);

            // ACTUALIZAR
            if (paciente != null) {
                paciente.setTelefono("70001111");
                paciente.setCorreo("carlos.actualizado@email.com");
                paciente.setEstado("ACTIVO");

                boolean actualizado = pacienteDAO.actualizarPaciente(paciente);
                System.out.println("Actualizar paciente: " + actualizado);
            }

            // DESACTIVAR
            boolean desactivado = pacienteDAO.desactivarPaciente(3);
            System.out.println("Desactivar paciente: " + desactivado);

        } catch (SQLException e) {
            System.out.println("Error en CRUD de paciente: " + e.getMessage());
        }
    }
}