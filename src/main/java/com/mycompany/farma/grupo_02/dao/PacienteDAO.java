package com.mycompany.farma.grupo_02.dao;

import com.mycompany.farma.grupo_02.conexion.ConexionOracle;
import com.mycompany.farma.grupo_02.model.Paciente;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import oracle.jdbc.OracleTypes;

public class PacienteDAO {

    public Paciente consultarPacientePorId(int idPaciente) throws SQLException {
        String sql = "{ call SP_CONSULTAR_PACIENTE(?, ?) }";

        try (Connection conn = ConexionOracle.conectar();
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.setInt(1, idPaciente);
            cs.registerOutParameter(2, OracleTypes.CURSOR);

            cs.execute();

            try (ResultSet rs = (ResultSet) cs.getObject(2)) {
                if (rs.next()) {
                    Paciente paciente = new Paciente();
                    paciente.setIdPaciente(rs.getInt("ID_PACIENTE"));
                    paciente.setCedula(rs.getString("CEDULA"));
                    paciente.setNombre(rs.getString("NOMBRE"));
                    paciente.setApellido1(rs.getString("APELLIDO1"));
                    paciente.setApellido2(rs.getString("APELLIDO2"));
                    paciente.setTelefono(rs.getString("TELEFONO"));
                    paciente.setCorreo(rs.getString("CORREO"));
                    paciente.setEstado(rs.getString("ESTADO"));
                    return paciente;
                }
            }
        }

        return null;
    }
}