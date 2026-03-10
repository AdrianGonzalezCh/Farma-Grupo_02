package com.mycompany.farma.grupo_02.test;

import com.mycompany.farma.grupo_02.dao.UsuarioDAO;
import com.mycompany.farma.grupo_02.model.Usuario;
import java.sql.SQLException;

public class PruebaLoginUsuario {

    public static void main(String[] args) {
        UsuarioDAO dao = new UsuarioDAO();

        try {
            Usuario usuario = dao.login("admin", "admin123");

            if (usuario != null) {
                System.out.println("Login correcto:");
                System.out.println(usuario);
            } else {
                System.out.println("Credenciales invalidas.");
            }
        } catch (SQLException e) {
            System.out.println("Error en login: " + e.getMessage());
        }
    }
}