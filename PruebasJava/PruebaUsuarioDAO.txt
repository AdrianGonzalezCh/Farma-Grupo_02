package com.mycompany.farma.grupo_02.test;

import com.mycompany.farma.grupo_02.dao.UsuarioDAO;
import com.mycompany.farma.grupo_02.model.Usuario;
import java.sql.SQLException;

public class PruebaUsuarioDAO {

    public static void main(String[] args) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        try {
            Usuario usuario = usuarioDAO.consultarUsuarioPorId(1);

            if (usuario != null) {
                System.out.println("Usuario encontrado:");
                System.out.println(usuario);
            } else {
                System.out.println("No se encontro el usuario.");
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar usuario: " + e.getMessage());
        }
    }
}