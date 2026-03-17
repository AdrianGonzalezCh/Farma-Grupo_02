package com.mycompany.farma.grupo_02.test;

import com.mycompany.farma.grupo_02.dao.MedicoDAO;
import com.mycompany.farma.grupo_02.model.Medico;
import java.sql.SQLException;

public class PruebaMedicoCRUD {

    public static void main(String[] args) {
        MedicoDAO medicoDAO = new MedicoDAO();

        try {
            Medico nuevoMedico = new Medico();
            nuevoMedico.setNombre("Roberto");
            nuevoMedico.setApellido1("Salas");
            nuevoMedico.setApellido2("Jimenez");
            nuevoMedico.setNumeroColegiado("COL-9999");
            nuevoMedico.setEspecialidad("Medicina Interna");
            nuevoMedico.setTelefono("85556666");
            nuevoMedico.setCorreo("roberto.salas@med.com");
            nuevoMedico.setEstado("ACTIVO");

            boolean insertado = medicoDAO.insertarMedico(nuevoMedico);
            System.out.println("Insertar medico: " + insertado);

            Medico medico = medicoDAO.consultarMedicoPorId(1);
            System.out.println("Medico consultado:");
            System.out.println(medico);

            if (medico != null) {
                medico.setEspecialidad("Medicina General");
                medico.setTelefono("81112222");
                medico.setEstado("ACTIVO");

                boolean actualizado = medicoDAO.actualizarMedico(medico);
                System.out.println("Actualizar medico: " + actualizado);
            }

            boolean desactivado = medicoDAO.desactivarMedico(2);
            System.out.println("Desactivar medico: " + desactivado);

        } catch (SQLException e) {
            System.out.println("Error en CRUD de medico: " + e.getMessage());
        }
    }
}