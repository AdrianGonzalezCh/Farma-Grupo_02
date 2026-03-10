package com.mycompany.farma.grupo_02.dao;

import com.mycompany.farma.grupo_02.conexion.ConexionOracle;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class DispensacionDAO {

    public int insertarDispensacion(int idReceta, int idUsuario, String observaciones) throws SQLException {
        String sql = "{ call SP_INSERTAR_DISPENSACION_CON_ID(?, ?, ?, ?) }";

        try (Connection conn = ConexionOracle.conectar();
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.setInt(1, idReceta);
            cs.setInt(2, idUsuario);
            cs.setString(3, observaciones);
            cs.registerOutParameter(4, java.sql.Types.INTEGER);

            cs.execute();

            return cs.getInt(4);
        }
    }

    public boolean dispensarMedicamento(int idDispensacion, int idRecetaDetalle, int idMedicamento, int cantidad)
            throws SQLException {
        String sql = "{ call SP_DISPENSAR_MEDICAMENTO(?, ?, ?, ?) }";

        try (Connection conn = ConexionOracle.conectar();
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.setInt(1, idDispensacion);
            cs.setInt(2, idRecetaDetalle);
            cs.setInt(3, idMedicamento);
            cs.setInt(4, cantidad);

            cs.execute();
            return true;
        }
    }

    public boolean anularDispensacion(int idDispensacion) throws SQLException {
        String sql = "{ call SP_ANULAR_DISPENSACION(?) }";

        try (Connection conn = ConexionOracle.conectar();
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.setInt(1, idDispensacion);
            cs.execute();
            return true;
        }
    }
}