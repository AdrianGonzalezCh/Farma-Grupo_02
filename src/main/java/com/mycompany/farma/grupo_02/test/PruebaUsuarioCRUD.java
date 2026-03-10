package com.mycompany.farma.grupo_02.test;

import com.mycompany.farma.grupo_02.dao.UsuarioDAO;
import com.mycompany.farma.grupo_02.model.Usuario;
import java.sql.SQLException;

public class PruebaUsuarioCRUD {

    public static void main(String[] args) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        try {
            Usuario nuevoUsuario = new Usuario();
            nuevoUsuario.setIdRol(2);
            nuevoUsuario.setCedula("8-8888-8888");
            nuevoUsuario.setNombre("Daniel");
            nuevoUsuario.setApellido1("Solano");
            nuevoUsuario.setApellido2("Rojas");
            nuevoUsuario.setCorreo("daniel@farma.com");
            nuevoUsuario.setUsername("daniel");
            nuevoUsuario.setClave("daniel123");
            nuevoUsuario.setEstado("ACTIVO");

            boolean insertado = usuarioDAO.insertarUsuario(nuevoUsuario);
            System.out.println("Insertar usuario: " + insertado);

            Usuario usuario = usuarioDAO.consultarUsuarioPorId(1);
            System.out.println("Usuario consultado:");
            System.out.println(usuario);

            if (usuario != null) {
                usuario.setCorreo("admin.actualizado@farma.com");
                usuario.setUsername("admin");
                usuario.setClave("admin123");
                usuario.setEstado("ACTIVO");

                boolean actualizado = usuarioDAO.actualizarUsuario(usuario);
                System.out.println("Actualizar usuario: " + actualizado);
            }

            boolean desactivado = usuarioDAO.desactivarUsuario(2);
            System.out.println("Desactivar usuario: " + desactivado);

        } catch (SQLException e) {
            System.out.println("Error en CRUD de usuario: " + e.getMessage());
        }
    }
}