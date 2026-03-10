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

   public java.util.List<Paciente> listarPacientes() throws SQLException {
    java.util.List<Paciente> lista = new java.util.ArrayList<>();
    String sql = "{ call SP_LISTAR_PACIENTES(?) }";

    try (Connection conn = ConexionOracle.conectar();
         CallableStatement cs = conn.prepareCall(sql)) {

        cs.registerOutParameter(1, OracleTypes.CURSOR);
        cs.execute();

        try (ResultSet rs = (ResultSet) cs.getObject(1)) {
            while (rs.next()) {
                Paciente paciente = new Paciente();
                paciente.setIdPaciente(rs.getInt("ID_PACIENTE"));
                paciente.setCedula(rs.getString("CEDULA"));
                paciente.setNombre(rs.getString("NOMBRE"));
                paciente.setApellido1(rs.getString("APELLIDO1"));
                paciente.setApellido2(rs.getString("APELLIDO2"));
                paciente.setTelefono(rs.getString("TELEFONO"));
                paciente.setCorreo(rs.getString("CORREO"));
                paciente.setEstado(rs.getString("ESTADO"));
                lista.add(paciente);
            }
        }
    }

    return lista;
}
    
    
    
    public boolean insertarPaciente(Paciente paciente) throws SQLException {
        String sql = "{ call SP_INSERTAR_PACIENTE(?, ?, ?, ?, ?, ?, ?) }";

        try (Connection conn = ConexionOracle.conectar();
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.setString(1, paciente.getCedula());
            cs.setString(2, paciente.getNombre());
            cs.setString(3, paciente.getApellido1());
            cs.setString(4, paciente.getApellido2());
            cs.setString(5, paciente.getTelefono());
            cs.setString(6, paciente.getCorreo());
            cs.setString(7, paciente.getEstado());

            cs.execute();
            return true;
        }
    }

    public boolean actualizarPaciente(Paciente paciente) throws SQLException {
        String sql = "{ call SP_ACTUALIZAR_PACIENTE(?, ?, ?, ?, ?, ?, ?) }";

        try (Connection conn = ConexionOracle.conectar();
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.setInt(1, paciente.getIdPaciente());
            cs.setString(2, paciente.getNombre());
            cs.setString(3, paciente.getApellido1());
            cs.setString(4, paciente.getApellido2());
            cs.setString(5, paciente.getTelefono());
            cs.setString(6, paciente.getCorreo());
            cs.setString(7, paciente.getEstado());

            cs.execute();
            return true;
        }
    }

    public boolean desactivarPaciente(int idPaciente) throws SQLException {
        String sql = "{ call SP_DESACTIVAR_PACIENTE(?) }";

        try (Connection conn = ConexionOracle.conectar();
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.setInt(1, idPaciente);
            cs.execute();
            return true;
        }
    }
}