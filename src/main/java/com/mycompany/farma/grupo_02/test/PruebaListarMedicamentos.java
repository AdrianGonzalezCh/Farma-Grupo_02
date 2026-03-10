package com.mycompany.farma.grupo_02.test;

import com.mycompany.farma.grupo_02.dao.MedicamentoDAO;
import com.mycompany.farma.grupo_02.model.Medicamento;
import java.sql.SQLException;
import java.util.List;

public class PruebaListarMedicamentos {

    public static void main(String[] args) {
        MedicamentoDAO dao = new MedicamentoDAO();

        try {
            List<Medicamento> medicamentos = dao.listarMedicamentos();
            for (Medicamento m : medicamentos) {
                System.out.println(m);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar medicamentos: " + e.getMessage());
        }
    }
}