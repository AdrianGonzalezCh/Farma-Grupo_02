package com.mycompany.farma.grupo_02.dao;

import com.mycompany.farma.grupo_02.conexion.ConexionOracle;
import com.mycompany.farma.grupo_02.model.Usuario;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import oracle.jdbc.OracleTypes;

public class UsuarioDAO {

    public Usuario login(String username, String clave) throws SQLException {
    String sql = "{ call SP_LOGIN_USUARIO(?, ?, ?) }";

    try (Connection conn = ConexionOracle.conectar();
         CallableStatement cs = conn.prepareCall(sql)) {

        cs.setString(1, username);
        cs.setString(2, clave);
        cs.registerOutParameter(3, OracleTypes.CURSOR);

        cs.execute();

        try (ResultSet rs = (ResultSet) cs.getObject(3)) {
            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("ID_USUARIO"));
                usuario.setIdRol(rs.getInt("ID_ROL"));
                usuario.setCedula(rs.getString("CEDULA"));
                usuario.setNombre(rs.getString("NOMBRE"));
                usuario.setApellido1(rs.getString("APELLIDO1"));
                usuario.setApellido2(rs.getString("APELLIDO2"));
                usuario.setCorreo(rs.getString("CORREO"));
                usuario.setUsername(rs.getString("USERNAME"));
                usuario.setClave(rs.getString("CLAVE"));
                usuario.setEstado(rs.getString("ESTADO"));
                return usuario;
            }
        }
    }

    return null;
}
    public Usuario consultarUsuarioPorId(int idUsuario) throws SQLException {
        String sql = "{ call SP_CONSULTAR_USUARIO(?, ?) }";

        try (Connection conn = ConexionOracle.conectar();
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.setInt(1, idUsuario);
            cs.registerOutParameter(2, OracleTypes.CURSOR);

            cs.execute();

            try (ResultSet rs = (ResultSet) cs.getObject(2)) {
                if (rs.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setIdUsuario(rs.getInt("ID_USUARIO"));
                    usuario.setIdRol(rs.getInt("ID_ROL"));
                    usuario.setCedula(rs.getString("CEDULA"));
                    usuario.setNombre(rs.getString("NOMBRE"));
                    usuario.setApellido1(rs.getString("APELLIDO1"));
                    usuario.setApellido2(rs.getString("APELLIDO2"));
                    usuario.setCorreo(rs.getString("CORREO"));
                    usuario.setUsername(rs.getString("USERNAME"));
                    usuario.setClave(rs.getString("CLAVE"));
                    usuario.setEstado(rs.getString("ESTADO"));
                    return usuario;
                }
            }
        }

        return null;
    }

    public boolean insertarUsuario(Usuario usuario) throws SQLException {
        String sql = "{ call SP_INSERTAR_USUARIO(?, ?, ?, ?, ?, ?, ?, ?, ?) }";

        try (Connection conn = ConexionOracle.conectar();
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.setInt(1, usuario.getIdRol());
            cs.setString(2, usuario.getCedula());
            cs.setString(3, usuario.getNombre());
            cs.setString(4, usuario.getApellido1());
            cs.setString(5, usuario.getApellido2());
            cs.setString(6, usuario.getCorreo());
            cs.setString(7, usuario.getUsername());
            cs.setString(8, usuario.getClave());
            cs.setString(9, usuario.getEstado());

            cs.execute();
            return true;
        }
    }

    public boolean actualizarUsuario(Usuario usuario) throws SQLException {
        String sql = "{ call SP_ACTUALIZAR_USUARIO(?, ?, ?, ?, ?, ?, ?, ?, ?) }";

        try (Connection conn = ConexionOracle.conectar();
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.setInt(1, usuario.getIdUsuario());
            cs.setInt(2, usuario.getIdRol());
            cs.setString(3, usuario.getNombre());
            cs.setString(4, usuario.getApellido1());
            cs.setString(5, usuario.getApellido2());
            cs.setString(6, usuario.getCorreo());
            cs.setString(7, usuario.getUsername());
            cs.setString(8, usuario.getClave());
            cs.setString(9, usuario.getEstado());

            cs.execute();
            return true;
        }
    }

    public boolean desactivarUsuario(int idUsuario) throws SQLException {
        String sql = "{ call SP_DESACTIVAR_USUARIO(?) }";

        try (Connection conn = ConexionOracle.conectar();
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.setInt(1, idUsuario);
            cs.execute();
            return true;
        }
    }
}