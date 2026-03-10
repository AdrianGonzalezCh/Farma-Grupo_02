package com.mycompany.farma.grupo_02.dao;

import com.mycompany.farma.grupo_02.conexion.ConexionOracle;
import com.mycompany.farma.grupo_02.model.Medicamento;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import oracle.jdbc.OracleTypes;

public class MedicamentoDAO {

    public Medicamento consultarMedicamentoPorId(int idMedicamento) throws SQLException {
        String sql = "{ call SP_CONSULTAR_MEDICAMENTO(?, ?) }";

        try (Connection conn = ConexionOracle.conectar();
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.setInt(1, idMedicamento);
            cs.registerOutParameter(2, OracleTypes.CURSOR);

            cs.execute();

            try (ResultSet rs = (ResultSet) cs.getObject(2)) {
                if (rs.next()) {
                    Medicamento medicamento = new Medicamento();
                    medicamento.setIdMedicamento(rs.getInt("ID_MEDICAMENTO"));
                    medicamento.setCodigo(rs.getString("CODIGO"));
                    medicamento.setNombre(rs.getString("NOMBRE"));
                    medicamento.setPresentacion(rs.getString("PRESENTACION"));
                    medicamento.setRequiereReceta(rs.getString("REQUIERE_RECETA"));
                    medicamento.setStockActual(rs.getInt("STOCK_ACTUAL"));
                    medicamento.setStockMinimo(rs.getInt("STOCK_MINIMO"));
                    medicamento.setEstado(rs.getString("ESTADO"));
                    return medicamento;
                }
            }
        }

        return null;
    }

    public java.util.List<Medicamento> listarMedicamentos() throws SQLException {
    java.util.List<Medicamento> lista = new java.util.ArrayList<>();
    String sql = "{ call SP_LISTAR_MEDICAMENTOS(?) }";

    try (Connection conn = ConexionOracle.conectar();
         CallableStatement cs = conn.prepareCall(sql)) {

        cs.registerOutParameter(1, OracleTypes.CURSOR);
        cs.execute();

        try (ResultSet rs = (ResultSet) cs.getObject(1)) {
            while (rs.next()) {
                Medicamento medicamento = new Medicamento();
                medicamento.setIdMedicamento(rs.getInt("ID_MEDICAMENTO"));
                medicamento.setCodigo(rs.getString("CODIGO"));
                medicamento.setNombre(rs.getString("NOMBRE"));
                medicamento.setPresentacion(rs.getString("PRESENTACION"));
                medicamento.setRequiereReceta(rs.getString("REQUIERE_RECETA"));
                medicamento.setStockActual(rs.getInt("STOCK_ACTUAL"));
                medicamento.setStockMinimo(rs.getInt("STOCK_MINIMO"));
                medicamento.setEstado(rs.getString("ESTADO"));
                lista.add(medicamento);
            }
        }
    }

    return lista;
}
    public boolean insertarMedicamento(Medicamento medicamento) throws SQLException {
        String sql = "{ call SP_INSERTAR_MEDICAMENTO(?, ?, ?, ?, ?, ?, ?) }";

        try (Connection conn = ConexionOracle.conectar();
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.setString(1, medicamento.getCodigo());
            cs.setString(2, medicamento.getNombre());
            cs.setString(3, medicamento.getPresentacion());
            cs.setString(4, medicamento.getRequiereReceta());
            cs.setInt(5, medicamento.getStockActual());
            cs.setInt(6, medicamento.getStockMinimo());
            cs.setString(7, medicamento.getEstado());

            cs.execute();
            return true;
        }
    }

    public boolean actualizarMedicamento(Medicamento medicamento) throws SQLException {
        String sql = "{ call SP_ACTUALIZAR_MEDICAMENTO(?, ?, ?, ?, ?, ?, ?, ?) }";

        try (Connection conn = ConexionOracle.conectar();
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.setInt(1, medicamento.getIdMedicamento());
            cs.setString(2, medicamento.getCodigo());
            cs.setString(3, medicamento.getNombre());
            cs.setString(4, medicamento.getPresentacion());
            cs.setString(5, medicamento.getRequiereReceta());
            cs.setInt(6, medicamento.getStockActual());
            cs.setInt(7, medicamento.getStockMinimo());
            cs.setString(8, medicamento.getEstado());

            cs.execute();
            return true;
        }
    }

    public boolean desactivarMedicamento(int idMedicamento) throws SQLException {
        String sql = "{ call SP_DESACTIVAR_MEDICAMENTO(?) }";

        try (Connection conn = ConexionOracle.conectar();
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.setInt(1, idMedicamento);
            cs.execute();
            return true;
        }
    }
}