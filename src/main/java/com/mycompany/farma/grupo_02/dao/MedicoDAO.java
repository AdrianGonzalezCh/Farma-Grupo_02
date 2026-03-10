package com.mycompany.farma.grupo_02.dao;

import com.mycompany.farma.grupo_02.conexion.ConexionOracle;
import com.mycompany.farma.grupo_02.model.Medico;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import oracle.jdbc.OracleTypes;

public class MedicoDAO {

    public Medico consultarMedicoPorId(int idMedico) throws SQLException {
        String sql = "{ call SP_CONSULTAR_MEDICO(?, ?) }";

        try (Connection conn = ConexionOracle.conectar();
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.setInt(1, idMedico);
            cs.registerOutParameter(2, OracleTypes.CURSOR);

            cs.execute();

            try (ResultSet rs = (ResultSet) cs.getObject(2)) {
                if (rs.next()) {
                    Medico medico = new Medico();
                    medico.setIdMedico(rs.getInt("ID_MEDICO"));
                    medico.setNombre(rs.getString("NOMBRE"));
                    medico.setApellido1(rs.getString("APELLIDO1"));
                    medico.setApellido2(rs.getString("APELLIDO2"));
                    medico.setNumeroColegiado(rs.getString("NUMERO_COLEGIADO"));
                    medico.setEspecialidad(rs.getString("ESPECIALIDAD"));
                    medico.setTelefono(rs.getString("TELEFONO"));
                    medico.setCorreo(rs.getString("CORREO"));
                    medico.setEstado(rs.getString("ESTADO"));
                    return medico;
                }
            }
        }

        return null;
    }

    public boolean insertarMedico(Medico medico) throws SQLException {
        String sql = "{ call SP_INSERTAR_MEDICO(?, ?, ?, ?, ?, ?, ?, ?) }";

        try (Connection conn = ConexionOracle.conectar();
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.setString(1, medico.getNombre());
            cs.setString(2, medico.getApellido1());
            cs.setString(3, medico.getApellido2());
            cs.setString(4, medico.getNumeroColegiado());
            cs.setString(5, medico.getEspecialidad());
            cs.setString(6, medico.getTelefono());
            cs.setString(7, medico.getCorreo());
            cs.setString(8, medico.getEstado());

            cs.execute();
            return true;
        }
    }

    public boolean actualizarMedico(Medico medico) throws SQLException {
        String sql = "{ call SP_ACTUALIZAR_MEDICO(?, ?, ?, ?, ?, ?, ?, ?, ?) }";

        try (Connection conn = ConexionOracle.conectar();
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.setInt(1, medico.getIdMedico());
            cs.setString(2, medico.getNombre());
            cs.setString(3, medico.getApellido1());
            cs.setString(4, medico.getApellido2());
            cs.setString(5, medico.getNumeroColegiado());
            cs.setString(6, medico.getEspecialidad());
            cs.setString(7, medico.getTelefono());
            cs.setString(8, medico.getCorreo());
            cs.setString(9, medico.getEstado());

            cs.execute();
            return true;
        }
    }

    public boolean desactivarMedico(int idMedico) throws SQLException {
        String sql = "{ call SP_DESACTIVAR_MEDICO(?) }";

        try (Connection conn = ConexionOracle.conectar();
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.setInt(1, idMedico);
            cs.execute();
            return true;
        }
    }
}