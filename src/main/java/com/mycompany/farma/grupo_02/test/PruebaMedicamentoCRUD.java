package com.mycompany.farma.grupo_02.test;

import com.mycompany.farma.grupo_02.dao.MedicamentoDAO;
import com.mycompany.farma.grupo_02.model.Medicamento;
import java.sql.SQLException;

public class PruebaMedicamentoCRUD {

    public static void main(String[] args) {
        MedicamentoDAO medicamentoDAO = new MedicamentoDAO();

        try {
            Medicamento nuevoMedicamento = new Medicamento();
            nuevoMedicamento.setCodigo("MED-999");
            nuevoMedicamento.setNombre("Cetirizina");
            nuevoMedicamento.setPresentacion("10 mg tabletas");
            nuevoMedicamento.setRequiereReceta("NO");
            nuevoMedicamento.setStockActual(90);
            nuevoMedicamento.setStockMinimo(10);
            nuevoMedicamento.setEstado("ACTIVO");

            boolean insertado = medicamentoDAO.insertarMedicamento(nuevoMedicamento);
            System.out.println("Insertar medicamento: " + insertado);

            Medicamento medicamento = medicamentoDAO.consultarMedicamentoPorId(1);
            System.out.println("Medicamento consultado:");
            System.out.println(medicamento);

            if (medicamento != null) {
                medicamento.setStockActual(120);
                medicamento.setStockMinimo(15);
                medicamento.setEstado("ACTIVO");

                boolean actualizado = medicamentoDAO.actualizarMedicamento(medicamento);
                System.out.println("Actualizar medicamento: " + actualizado);
            }

            boolean desactivado = medicamentoDAO.desactivarMedicamento(5);
            System.out.println("Desactivar medicamento: " + desactivado);

        } catch (SQLException e) {
            System.out.println("Error en CRUD de medicamento: " + e.getMessage());
        }
    }
}