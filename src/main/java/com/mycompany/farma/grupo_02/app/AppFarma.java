package com.mycompany.farma.grupo_02.app;

import com.mycompany.farma.grupo_02.dao.MedicamentoDAO;
import com.mycompany.farma.grupo_02.dao.MedicoDAO;
import com.mycompany.farma.grupo_02.dao.PacienteDAO;
import com.mycompany.farma.grupo_02.dao.UsuarioDAO;
import com.mycompany.farma.grupo_02.model.Medicamento;
import com.mycompany.farma.grupo_02.model.Medico;
import com.mycompany.farma.grupo_02.model.Paciente;
import com.mycompany.farma.grupo_02.model.Usuario;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class AppFarma {

    private static final UsuarioDAO usuarioDAO = new UsuarioDAO();
    private static final PacienteDAO pacienteDAO = new PacienteDAO();
    private static final MedicoDAO medicoDAO = new MedicoDAO();
    private static final MedicamentoDAO medicamentoDAO = new MedicamentoDAO();

    public static void main(String[] args) {
        iniciarAplicacion();
    }

    private static void iniciarAplicacion() {
        Usuario usuarioLogueado = login();

        if (usuarioLogueado == null) {
            JOptionPane.showMessageDialog(null, "Inicio de sesion cancelado o invalido.");
            return;
        }

        JOptionPane.showMessageDialog(
                null,
                "Bienvenido " + usuarioLogueado.getNombre() + " " + usuarioLogueado.getApellido1()
                        + "\nRol ID: " + usuarioLogueado.getIdRol(),
                "Farma Grupo 02",
                JOptionPane.INFORMATION_MESSAGE
        );

        menuPrincipal(usuarioLogueado);
    }

    private static Usuario login() {
        while (true) {
            String username = JOptionPane.showInputDialog(null, "Ingrese el nombre de usuario:", "Login", JOptionPane.QUESTION_MESSAGE);
            if (username == null) {
                return null;
            }

            String clave = JOptionPane.showInputDialog(null, "Ingrese la clave:", "Login", JOptionPane.QUESTION_MESSAGE);
            if (clave == null) {
                return null;
            }

            try {
                Usuario usuario = usuarioDAO.login(username.trim(), clave.trim());
                if (usuario != null) {
                    return usuario;
                }

                JOptionPane.showMessageDialog(null, "Credenciales invalidas.", "Login", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error en login: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                return null;
            }
        }
    }

    private static void menuPrincipal(Usuario usuarioLogueado) {
        while (true) {
            String menu = """
                    Seleccione una opcion:

                    1. Registrar paciente
                    2. Consultar paciente por ID
                    3. Listar pacientes

                    4. Registrar medicamento
                    5. Consultar medicamento por ID
                    6. Listar medicamentos

                    7. Registrar usuario
                    8. Consultar usuario por ID

                    9. Registrar medico
                    10. Consultar medico por ID

                    0. Salir
                    """;

            String opcionStr = JOptionPane.showInputDialog(null, menu, "Menu Principal - Farma", JOptionPane.QUESTION_MESSAGE);

            if (opcionStr == null) {
                int salir = JOptionPane.showConfirmDialog(
                        null,
                        "Desea salir del sistema?",
                        "Confirmar salida",
                        JOptionPane.YES_NO_OPTION
                );

                if (salir == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(null, "Hasta luego.");
                    break;
                } else {
                    continue;
                }
            }

            try {
                int opcion = Integer.parseInt(opcionStr.trim());

                switch (opcion) {
                    case 1 -> registrarPaciente();
                    case 2 -> consultarPaciente();
                    case 3 -> listarPacientes();
                    case 4 -> registrarMedicamento();
                    case 5 -> consultarMedicamento();
                    case 6 -> listarMedicamentos();
                    case 7 -> registrarUsuario();
                    case 8 -> consultarUsuario();
                    case 9 -> registrarMedico();
                    case 10 -> consultarMedico();
                    case 0 -> {
                        JOptionPane.showMessageDialog(null, "Hasta luego.");
                        return;
                    }
                    default -> JOptionPane.showMessageDialog(null, "Opcion invalida.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Debe ingresar un numero valido.");
            }
        }
    }

    private static void registrarPaciente() {
        try {
            String cedula = pedirTexto("Cedula:");
            if (cedula == null) return;

            String nombre = pedirTexto("Nombre:");
            if (nombre == null) return;

            String apellido1 = pedirTexto("Primer apellido:");
            if (apellido1 == null) return;

            String apellido2 = pedirTexto("Segundo apellido:");
            if (apellido2 == null) return;

            String telefono = pedirTexto("Telefono:");
            if (telefono == null) return;

            String correo = pedirTexto("Correo:");
            if (correo == null) return;

            Paciente paciente = new Paciente();
            paciente.setCedula(cedula);
            paciente.setNombre(nombre);
            paciente.setApellido1(apellido1);
            paciente.setApellido2(apellido2);
            paciente.setTelefono(telefono);
            paciente.setCorreo(correo);
            paciente.setEstado("ACTIVO");

            boolean resultado = pacienteDAO.insertarPaciente(paciente);
            JOptionPane.showMessageDialog(null, "Paciente registrado: " + resultado);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar paciente: " + e.getMessage());
        }
    }

    private static void consultarPaciente() {
        try {
            Integer id = pedirEntero("Ingrese el ID del paciente:");
            if (id == null) return;

            Paciente paciente = pacienteDAO.consultarPacientePorId(id);

            if (paciente != null) {
                JOptionPane.showMessageDialog(null, paciente.toString(), "Paciente encontrado", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontro el paciente.");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al consultar paciente: " + e.getMessage());
        }
    }

    private static void listarPacientes() {
        try {
            List<Paciente> pacientes = pacienteDAO.listarPacientes();

            if (pacientes.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No hay pacientes registrados.");
                return;
            }

            StringBuilder sb = new StringBuilder();
            for (Paciente p : pacientes) {
                sb.append(p).append("\n\n");
            }

            mostrarTextoGrande("Lista de pacientes", sb.toString());

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar pacientes: " + e.getMessage());
        }
    }

    private static void registrarMedicamento() {
        try {
            String codigo = pedirTexto("Codigo:");
            if (codigo == null) return;

            String nombre = pedirTexto("Nombre:");
            if (nombre == null) return;

            String presentacion = pedirTexto("Presentacion:");
            if (presentacion == null) return;

            String requiereReceta = pedirTexto("Requiere receta? (SI/NO):");
            if (requiereReceta == null) return;

            Integer stockActual = pedirEntero("Stock actual:");
            if (stockActual == null) return;

            Integer stockMinimo = pedirEntero("Stock minimo:");
            if (stockMinimo == null) return;

            Medicamento medicamento = new Medicamento();
            medicamento.setCodigo(codigo);
            medicamento.setNombre(nombre);
            medicamento.setPresentacion(presentacion);
            medicamento.setRequiereReceta(requiereReceta.toUpperCase());
            medicamento.setStockActual(stockActual);
            medicamento.setStockMinimo(stockMinimo);
            medicamento.setEstado("ACTIVO");

            boolean resultado = medicamentoDAO.insertarMedicamento(medicamento);
            JOptionPane.showMessageDialog(null, "Medicamento registrado: " + resultado);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar medicamento: " + e.getMessage());
        }
    }

    private static void consultarMedicamento() {
        try {
            Integer id = pedirEntero("Ingrese el ID del medicamento:");
            if (id == null) return;

            Medicamento medicamento = medicamentoDAO.consultarMedicamentoPorId(id);

            if (medicamento != null) {
                JOptionPane.showMessageDialog(null, medicamento.toString(), "Medicamento encontrado", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontro el medicamento.");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al consultar medicamento: " + e.getMessage());
        }
    }

    private static void listarMedicamentos() {
        try {
            List<Medicamento> medicamentos = medicamentoDAO.listarMedicamentos();

            if (medicamentos.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No hay medicamentos registrados.");
                return;
            }

            StringBuilder sb = new StringBuilder();
            for (Medicamento m : medicamentos) {
                sb.append(m).append("\n\n");
            }

            mostrarTextoGrande("Lista de medicamentos", sb.toString());

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar medicamentos: " + e.getMessage());
        }
    }

    private static void registrarUsuario() {
        try {
            Integer idRol = pedirEntero("ID Rol (1=ADMINISTRADOR, 2=FARMACEUTICO):");
            if (idRol == null) return;

            String cedula = pedirTexto("Cedula:");
            if (cedula == null) return;

            String nombre = pedirTexto("Nombre:");
            if (nombre == null) return;

            String apellido1 = pedirTexto("Primer apellido:");
            if (apellido1 == null) return;

            String apellido2 = pedirTexto("Segundo apellido:");
            if (apellido2 == null) return;

            String correo = pedirTexto("Correo:");
            if (correo == null) return;

            String username = pedirTexto("Username:");
            if (username == null) return;

            String clave = pedirTexto("Clave:");
            if (clave == null) return;

            Usuario usuario = new Usuario();
            usuario.setIdRol(idRol);
            usuario.setCedula(cedula);
            usuario.setNombre(nombre);
            usuario.setApellido1(apellido1);
            usuario.setApellido2(apellido2);
            usuario.setCorreo(correo);
            usuario.setUsername(username);
            usuario.setClave(clave);
            usuario.setEstado("ACTIVO");

            boolean resultado = usuarioDAO.insertarUsuario(usuario);
            JOptionPane.showMessageDialog(null, "Usuario registrado: " + resultado);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar usuario: " + e.getMessage());
        }
    }

    private static void consultarUsuario() {
        try {
            Integer id = pedirEntero("Ingrese el ID del usuario:");
            if (id == null) return;

            Usuario usuario = usuarioDAO.consultarUsuarioPorId(id);

            if (usuario != null) {
                JOptionPane.showMessageDialog(null, usuario.toString(), "Usuario encontrado", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontro el usuario.");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al consultar usuario: " + e.getMessage());
        }
    }

    private static void registrarMedico() {
        try {
            String nombre = pedirTexto("Nombre:");
            if (nombre == null) return;

            String apellido1 = pedirTexto("Primer apellido:");
            if (apellido1 == null) return;

            String apellido2 = pedirTexto("Segundo apellido:");
            if (apellido2 == null) return;

            String colegiado = pedirTexto("Numero de colegiado:");
            if (colegiado == null) return;

            String especialidad = pedirTexto("Especialidad:");
            if (especialidad == null) return;

            String telefono = pedirTexto("Telefono:");
            if (telefono == null) return;

            String correo = pedirTexto("Correo:");
            if (correo == null) return;

            Medico medico = new Medico();
            medico.setNombre(nombre);
            medico.setApellido1(apellido1);
            medico.setApellido2(apellido2);
            medico.setNumeroColegiado(colegiado);
            medico.setEspecialidad(especialidad);
            medico.setTelefono(telefono);
            medico.setCorreo(correo);
            medico.setEstado("ACTIVO");

            boolean resultado = medicoDAO.insertarMedico(medico);
            JOptionPane.showMessageDialog(null, "Medico registrado: " + resultado);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar medico: " + e.getMessage());
        }
    }

    private static void consultarMedico() {
        try {
            Integer id = pedirEntero("Ingrese el ID del medico:");
            if (id == null) return;

            Medico medico = medicoDAO.consultarMedicoPorId(id);

            if (medico != null) {
                JOptionPane.showMessageDialog(null, medico.toString(), "Medico encontrado", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontro el medico.");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al consultar medico: " + e.getMessage());
        }
    }

    private static String pedirTexto(String mensaje) {
        String dato = JOptionPane.showInputDialog(null, mensaje, "Farma", JOptionPane.QUESTION_MESSAGE);
        if (dato == null) {
            return null;
        }
        return dato.trim();
    }

    private static Integer pedirEntero(String mensaje) {
        String dato = JOptionPane.showInputDialog(null, mensaje, "Farma", JOptionPane.QUESTION_MESSAGE);
        if (dato == null) {
            return null;
        }

        try {
            return Integer.valueOf(dato.trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Debe ingresar un numero valido.");
            return null;
        }
    }

    private static void mostrarTextoGrande(String titulo, String contenido) {
        JTextArea area = new JTextArea(20, 50);
        area.setText(contenido);
        area.setEditable(false);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);

        JOptionPane.showMessageDialog(null, area, titulo, JOptionPane.INFORMATION_MESSAGE);
    }
}