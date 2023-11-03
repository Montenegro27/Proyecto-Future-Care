import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class SistemaLogin {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Iniciar sesión:");
            System.out.print("Usuario: ");
            String usuario = scanner.next();
            System.out.print("Contraseña: ");
            String contrasena = scanner.next();

            // Realizar la autenticación
            Usuario usuarioAutenticado = autenticar(usuario, contrasena);

            if (usuarioAutenticado != null) {
                if (usuarioAutenticado.getTipo().equals("medico")) {
                    mostrarMenuMedico(usuarioAutenticado);
                } else if (usuarioAutenticado.getTipo().equals("paciente")) {
                    mostrarMenuPaciente(usuarioAutenticado);
                }
            } else {
                System.out.println("Credenciales incorrectas. Inténtalo de nuevo.");
            }
        }
    }

    public static Usuario autenticar(String usuario, String contrasena) {
        try (BufferedReader reader = new BufferedReader(new FileReader("usuarios.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] partes = line.split(",");
                String usuarioCSV = partes[0];
                String contrasenaCSV = partes[1];
                String tipo = partes[2];
                String especialidad = partes[3];
                String numSeguro = partes[4];

                if (usuarioCSV.equals(usuario) && contrasenaCSV.equals(contrasena)) {
                    return new Usuario(usuario, tipo, especialidad, numSeguro);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null; // Si no se encuentra un usuario con las credenciales proporcionadas.
    }

    public static void mostrarMenuMedico(Usuario usuario) {
        System.out.println("Bienvenido, Médico " + usuario.getUsuario());
        System.out.println("Especialidad: " + usuario.getEspecialidad());
        // Menú específico para médicos
        System.out.println("1. Ver lista de pacientes");
        System.out.println("2. Registrar diagnóstico");
        System.out.println("3. Salir");
    }

    public static void mostrarMenuPaciente(Usuario usuario) {
        System.out.println("Bienvenido, Paciente " + usuario.getUsuario());
        System.out.println("Número de Seguro: " + usuario.getNumSeguro());
        // Menú específico para pacientes
        System.out.println("1. Ver historial médico");
        System.out.println("2. Solicitar cita");
        System.out.println("3. Salir");
    }
}

class Usuario {
    private String usuario;
    private String tipo;
    private String especialidad;
    private String numSeguro;

    public Usuario(String usuario, String tipo, String especialidad, String numSeguro) {
        this.usuario = usuario;
        this.tipo = tipo;
        this.especialidad = especialidad;
        this.numSeguro = numSeguro;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getTipo() {
        return tipo;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public String getNumSeguro() {
        return numSeguro;
    }
}

