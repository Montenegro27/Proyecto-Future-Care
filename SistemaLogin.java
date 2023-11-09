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
        System.out.println("3. Agendar citas");
        System.out.println("4. Salir");

        Scanner scanner = new Scanner(System.in);
        int opcion = scanner.nextInt();
        
        switch (opcion) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                System.exit(0);
            default:
                System.out.println("Opción no válida. Inténtalo de nuevo.");
        }
    }    

    public static void mostrarMenuPaciente(Usuario usuario) {
        System.out.println("Bienvenido, Paciente " + usuario.getUsuario());
        String pacienteId = usuario.getNumSeguro(); // Obtiene el número de ID
        System.out.println("Número de ID: " + pacienteId);
        System.out.println("1. Ver historial médico");
        System.out.println("2. Mostrar información del paciente");
        System.out.println("3. Proximas citas");
        System.out.println("4. Salir");
        
        Scanner scanner = new Scanner(System.in);
        int opcion = scanner.nextInt();
        
        historial paciente = obtenerPacientePorId(pacienteId); // Obtiene el paciente por su número de seguro
        
        switch (opcion) {
            case 1:
                if (paciente != null) {
                    mostrarMenuinfomedicop(usuario, scanner);
                } else {
                    System.out.println("No se encontró información del paciente.");
                }
                break;
            case 2:
                if (paciente != null) {
                    mostrarMenuinfopaciente(paciente);
                } else {
                    System.out.println("No se encontró información del paciente.");
                }
                break;
            case 3:
                break;
            case 4:
                System.exit(0);
            default:
                System.out.println("Opción no válida. Inténtalo de nuevo.");
        }
    }    

    public static void mostrarMenuinfopaciente(historial paciente) {
        System.out.println("Información Médica de " + paciente.getNombre());
        System.out.println("1. Ver nombre");
        System.out.println("2. Ver edad");
        System.out.println("3. Ver sexo");
        System.out.println("4. Ver grupo sanguíneo");
        System.out.println("5. Ver altura y peso");
        System.out.println("6. Ver medicamento actual");
        System.out.println("7. Ver alergias");
        System.out.println("8. Salir");

        Scanner scanner = new Scanner(System.in);
        int opcion = scanner.nextInt();

        switch (opcion) {
            case 1:
                System.out.println("Nombre: " + paciente.getNombre());
                break;
            case 2:
                System.out.println("Edad: " + paciente.getEdad());
                break;
            case 3:
                System.out.println("Sexo: " + paciente.getSexo());
                break;
            case 4:
                System.out.println("Grupo Sanguíneo: " + paciente.getGrupoSanguineo());
                break;
            case 5:
                System.out.println("Altura y Peso: " + paciente.getAlturaPeso());
                break;
            case 6:
                System.out.println("Medicamento Actual: " + paciente.getMedicamentoActual());
                break;
            case 7:
                System.out.println("Alergias: " + paciente.getAlergias());
                break;
            case 8:
                System.exit(0);
                break;
            default:
                System.out.println("Opción no válida. Inténtalo de nuevo.");
        }
    }

    public static void mostrarMenuinfomedicop(Usuario usuario, Scanner scanner) {
        boolean salir = false;
        String pacienteId = usuario.getNumSeguro(); // Obtiene el número de ID

        System.out.println("Historial Médico de " + usuario.getUsuario());

        while (!salir) {
            System.out.println("Menú de Historial Médico:");
            System.out.println("1. Ver antecedentes médicos");
            System.out.println("2. Ver antecedentes quirúrgicos");
            System.out.println("3. Salir");

            int opcion = scanner.nextInt();

            historial paciente = obtenerDatosHistorial(pacienteId);

            switch (opcion) {
                case 1:
                    if (paciente != null && paciente.getAntecedentesMedicos() != null) {
                        System.out.println("Antecedentes Médicos: " + paciente.getAntecedentesMedicos());
                    } else {
                        System.out.println("No se encontró información de antecedentes médicos.");
                    }
                    break;
                case 2:
                    if (paciente != null && paciente.getAntecedentesQuirurgicos() != null) {
                        System.out.println("Antecedentes Quirúrgicos: " + paciente.getAntecedentesQuirurgicos());
                    } else {
                        System.out.println("No se encontró información de antecedentes quirúrgicos.");
                    }
                    break;
                case 3:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
        }
    }


    public static historial obtenerDatosHistorial(String idPaciente) {
        try (BufferedReader reader = new BufferedReader(new FileReader("historialm.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] partes = line.split(",");
                String idCSV = partes[0].trim();

                if (idCSV.equals(idPaciente.trim())) {
                    String antecedentesMedicos = partes[6];
                    String antecedentesQuirurgicos = partes[7];

                    // Crea y devuelve un objeto historial con los datos leídos
                    return new historial(idCSV, antecedentesMedicos, antecedentesQuirurgicos);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("No se encontró información del paciente con ID: " + idPaciente);
        return null;
    }
    

    public static historial obtenerPacientePorId(String idPaciente) {
        try (BufferedReader reader = new BufferedReader(new FileReader("historialm.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] partes = line.split(",");
                String idCSV = partes[0].trim();

                if (idCSV.equals(idPaciente.trim())) {
                    String nombre = partes[1];
                    String edad = partes[2];
                    String sexo = partes[3];
                    String grupoSanguineo = partes[4];
                    String alturaPeso = partes[5];
                    String medicamentoActual = partes[8];
                    String alergias = partes[9];

                    // Crea y devuelve un objeto historial con los datos leídos
                    return new historial(idCSV, nombre, edad, sexo, grupoSanguineo, alturaPeso, medicamentoActual, alergias);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("No se encontró información del paciente con ID: " + idPaciente);
        return null;
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
