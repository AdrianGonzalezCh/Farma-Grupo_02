package com.mycompany.farma.grupo_02.dao;

import com.mycompany.farma.grupo_02.conexion.ConexionOracle;
import com.mycompany.farma.grupo_02.model.Receta;
import com.mycompany.farma.grupo_02.model.RecetaDetalle;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import oracle.jdbc.OracleTypes;

public class RecetaDAO {

    public int insertarReceta(Receta receta) throws SQLException {
        String sql = "{ call SP_INSERTAR_RECETA_CON_ID(?, ?, ?, ?) }";

        try (Connection conn = ConexionOracle.conectar();
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.setInt(1, receta.getIdPaciente());
            cs.setInt(2, receta.getIdMedico());
            cs.setString(3, receta.getObservaciones());
            cs.registerOutParameter(4, java.sql.Types.INTEGER);

            cs.execute();

            return cs.getInt(4);
        }
    }

    public boolean insertarDetalleReceta(RecetaDetalle detalle) throws SQLException {
        String sql = "{ call SP_INSERTAR_RECETA_DETALLE(?, ?, ?, ?, ?) }";

        try (Connection conn = ConexionOracle.conectar();
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.setInt(1, detalle.getIdReceta());
            cs.setInt(2, detalle.getIdMedicamento());
            cs.setString(3, detalle.getDosis());
            cs.setInt(4, detalle.getCantidadAutorizada());
            cs.setString(5, detalle.getIndicaciones());

            cs.execute();
            return true;
        }
    }

    public Receta consultarRecetaPorId(int idReceta) throws SQLException {
        String sql = "{ call SP_CONSULTAR_RECETA(?, ?) }";

        try (Connection conn = ConexionOracle.conectar();
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.setInt(1, idReceta);
            cs.registerOutParameter(2, OracleTypes.CURSOR);

            cs.execute();

            try (ResultSet rs = (ResultSet) cs.getObject(2)) {
                if (rs.next()) {
                    Receta receta = new Receta();
                    receta.setIdReceta(rs.getInt("ID_RECETA"));
                    receta.setFechaEmision(rs.getDate("FECHA_EMISION"));
                    receta.setEstado(rs.getString("ESTADO"));
                    receta.setObservaciones(rs.getString("OBSERVACIONES"));
                    receta.setIdPaciente(rs.getInt("ID_PACIENTE"));
                    receta.setIdMedico(rs.getInt("ID_MEDICO"));
                    return receta;
                }
            }
        }

        return null;
    }
}