// Este archivo debera ejecutarse para el funcionamiento del programa

import java.io.*;
import java.util.*;

public class SistemaLogin {

    private static SistemaLogin instancia;

    public static SistemaLogin getInstancia() {
        if (instancia == null) {
            instancia = new SistemaLogin();
        }
        return instancia;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        buclePrincipal: // Etiqueta de bucle para reiniciar el proceso
        while (true) {
            limpiarConsola(); // Limpiar la consola al inicio de cada iteración
            System.out.println("Iniciar sesión:");
            System.out.print("Usuario: ");
            String usuario = scanner.next();
            System.out.print("Contraseña: ");
            String contrasena = scanner.next();

            // Realizar la autenticación
            Usuario usuarioAutenticado = autenticar(usuario, contrasena);

            if (usuarioAutenticado != null) {
                if (usuarioAutenticado.getTipo().equals("medico")) {
                    System.out.println("Medico");
                    mostrarMenuMedico(usuarioAutenticado, scanner);
                    System.out.println("Medico");
                } else if (usuarioAutenticado.getTipo().equals("paciente")) {
                    if (!mostrarMenuPaciente(usuarioAutenticado, scanner)) {
                        break buclePrincipal; // Salir del bucle principal y reiniciar el proceso
                    }
                }
            } else {
                System.out.println("Credenciales incorrectas. Inténtalo de nuevo.");
                scanner.nextLine(); // Limpiar el buffer del scanner
                scanner.nextLine(); // Esperar a que el usuario presione Enter antes de continuar
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
    
    
    public static boolean mostrarMenuMedico(Usuario usuario, Scanner scanner) {
        limpiarConsola();
        // Menú específico para médicos
        System.out.println("Bienvenido, Médico " + usuario.getUsuario());
        System.out.println("Especialidad: " + usuario.getEspecialidad());
        bucleMenuMedico:
        while (true) {
            System.out.println("1. Ver historial de paciente");
            System.out.println("2. Registrar diagnóstico");
            System.out.println("3. Registrar cita");
            System.out.println("4. Salir");

            int opcion = obtenerOpcion(scanner);

            switch (opcion) {
                case 1:
                    System.out.println("Ingrese el id del paciente: ");
                    String id_paciente = scanner.next();
                    historial hist = obtenerPacientePorId(id_paciente);

                    if (hist != null) {
                        mostrarMenuHistorialMedicoMedico(hist);
                    } else {
                        System.out.println("No se encontró información para el ID proporcionado. Inténtalo de nuevo.");
                    }
                    break;
                
                case 3: 
                    Gestor.agendarCita();
                    break;

                case 4:
                    if (confirmarApagarPrograma(scanner)) {
                        return false; // Salir del programa
                    } else {
                        break bucleMenuMedico; // Salir del bucle del menú medico y regresar al inicio del bucle principal
                    }
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
        }
        return true;
    }
    public static boolean mostrarMenuPaciente(Usuario usuario, Scanner scanner) {
        bucleMenuPaciente:
        while (true) {
            limpiarConsola();
            System.out.println("Bienvenido, Paciente " + usuario.getUsuario());
            System.out.println("Número de ID: " + usuario.getNumSeguro());
            System.out.println("1. Ver historial médico");
            System.out.println("2. Mostrar información del paciente");
            System.out.println("3. Revisar citas");
            System.out.println("4. Salir");
    
            int opcion = obtenerOpcion(scanner);
    
            historial paciente = obtenerPacientePorId(usuario.getNumSeguro());
            switch (opcion) {
                case 1:
                    if (paciente != null) {
                        mostrarSubMenuAntecedentes(usuario);
                    } else {
                        System.out.println("No se encontró información del paciente.");
                    }
                    break;
                case 2:
                    if (paciente != null) {
                        mostrarMenuHistorialMedico(paciente);
                    } else {
                        System.out.println("No se encontró información del paciente.");
                    }
                    break;
                case 3:
                        System.out.println("Ingrese el ID del paciente: ");
                        String numSeguro = scanner.next();

                        Gestor.mostrarCitasPaciente(numSeguro);
                        break;
                
                case 4:
                    if (confirmarApagarPrograma(scanner)) {
                        return false; // Salir del programa
                    } else {
                        break bucleMenuPaciente; // Salir del bucle del menú paciente y regresar al inicio del bucle principal
                    }
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
        }
        return true; // Regresar true para seguir en el bucle principal
    }
    
    public static void mostrarMenuHistorialMedico(historial paciente) {
        Scanner scanner = new Scanner(System.in);

        do {
            limpiarConsola();
            System.out.println("Historial Médico de " + paciente.getNombre());
            System.out.println("1. Ver edad");
            System.out.println("2. Ver sexo");
            System.out.println("3. Ver grupo sanguíneo");
            System.out.println("4. Ver altura y peso");
            System.out.println("5. Ver medicamento actual");
            System.out.println("6. Ver alergias");
            System.out.println("7. Volver al menú paciente");

            int opcion = obtenerOpcion(scanner);

            switch (opcion) {
                case 1:
                    System.out.println("Edad: " + paciente.getEdad());
                    break;
                case 2:
                    System.out.println("Sexo: " + paciente.getSexo());
                    break;
                case 3:
                    System.out.println("Grupo Sanguíneo: " + paciente.getGrupoSanguineo());
                    break;
                case 4:
                    System.out.println("Altura y Peso: " + paciente.getAlturaPeso());
                    break;
                case 5:
                    System.out.println("Medicamento Actual: " + paciente.getMedicamentoActual());
                    break;
                case 6:
                    System.out.println("Alergias: " + paciente.getAlergias());
                    break;
                case 7:
                    return; // Salir y volver al menú paciente
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }

            System.out.println("¿Quieres ver más información? (si/no)");
            String respuesta = scanner.next().toLowerCase();

            if (!respuesta.equals("si")) {
                return; // Salir si la respuesta no es "si"
            }

        } while (true); // Repetir el bucle mientras el usuario quiera ver más información
    }

    public static void mostrarSubMenuAntecedentes(Usuario usuario) {
        Scanner scanner = new Scanner(System.in);

        do {
            limpiarConsola();
            System.out.println("Seleccione una opción:");
            System.out.println("1. Ver antecedentes médicos");
            System.out.println("2. Ver antecedentes quirúrgicos");
            System.out.println("3. Volver al menú paciente");

            int opcionSubMenu = obtenerOpcion(scanner);

            historial paciente = obtenerPacientePorId(usuario.getNumSeguro());

            switch (opcionSubMenu) {
                case 1:
                    if (paciente != null) {
                        System.out.println("Antecedentes Médicos: " + paciente.getantecedentesmed());
                    } else {
                        System.out.println("No se encontró información del paciente.");
                    }
                    break;
                case 2:
                    if (paciente != null) {
                        System.out.println("Antecedentes Quirúrgicos: " + paciente.getantecedentesqui());
                    } else {
                        System.out.println("No se encontró información del paciente.");
                    }
                    break;
                case 3:
                    return; // Salir y volver al menú paciente
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }

            System.out.println("¿Quieres ver más información? (si/no)");
            String respuesta = scanner.next().toLowerCase();

            if (!respuesta.equals("si")) {
                return; // Salir si la respuesta no es "si"
            }

        } while (true); // Repetir el bucle mientras el usuario quiera ver más información
    }    

    public static historial obtenerPacientePorId(String idPaciente) {
        try (BufferedReader reader = new BufferedReader(new FileReader("historialm.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] partes = line.split(",");
                String idCSV = partes[0].trim();
    
                if (idCSV.equals(idPaciente.trim())) {
                    // Asegúrate de que las partes se lean correctamente y coincidan con las propiedades de la clase historial
                    String nombre = partes[1];
                    String edad = partes[2];
                    String sexo = partes[3];
                    String grupoSanguineo = partes[4];
                    String alturaPeso = partes[5];
                    String antecedentesmed = partes[6];
                    String antecedentesqui = partes[7];
                    String medicamentoActual = partes[8];
                    String alergias = partes[9];
    
                    // Crea y devuelve un objeto historial con los datos leídos
                    return new historial(idCSV, nombre, edad, sexo, grupoSanguineo, alturaPeso, antecedentesmed, antecedentesqui, medicamentoActual, alergias);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("No se encontró información del paciente con ID: " + idPaciente);
        return null;
    }

    public static void limpiarConsola() {
        try {
            final String os = System.getProperty("os.name");
    
            if (os.contains("Windows")) {
                // Si el sistema operativo es Windows, utiliza "cls"
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // En otros sistemas operativos, utiliza "clear"
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (final Exception e) {
            // Maneja excepciones si ocurren al intentar limpiar la consola
            e.printStackTrace();
        }
    }    
    
    public static int obtenerOpcion(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (java.util.InputMismatchException e) {
                // Capturar la excepción si no se ingresa un número
                System.out.println("Por favor, ingresa un número válido.");
                scanner.nextLine(); // Limpiar el buffer del scanner
            }
        }
    }

    public static boolean confirmarApagarPrograma(Scanner scanner) {
        System.out.print("¿Quieres apagar el programa? (si/no): ");
        String respuesta = scanner.next().toLowerCase();
        return respuesta.equals("si");
    }



    public static void mostrarMenuHistorialMedicoMedico(historial paciente) {
        Scanner scanner = new Scanner(System.in);

        do {
            limpiarConsola();
            System.out.println("Historial Médico de " + paciente.getNombre());
            System.out.println("1. Ver edad");
            System.out.println("2. Ver sexo");
            System.out.println("3. Ver grupo sanguíneo");
            System.out.println("4. Ver altura y peso");
            System.out.println("5. Ver medicamento actual");
            System.out.println("6. Ver alergias");
            System.out.println("7. Editar historial");
            System.out.println("8. Volver al menú medico");

            int opcion = obtenerOpcion(scanner);

            switch (opcion) {
                case 1:
                    System.out.println("Edad: " + paciente.getEdad());
                    break;
                case 2:
                    System.out.println("Sexo: " + paciente.getSexo());
                    break;
                case 3:
                    System.out.println("Grupo Sanguíneo: " + paciente.getGrupoSanguineo());
                    break;
                case 4:
                    System.out.println("Altura y Peso: " + paciente.getAlturaPeso());
                    break;
                case 5:
                    System.out.println("Medicamento Actual: " + paciente.getMedicamentoActual());
                    break;
                case 6:
                    System.out.println("Alergias: " + paciente.getAlergias());
                    break;
                case 7:
                    boolean hola = true;
                    while(hola){
                        System.out.println("Que desea editar?");
                        System.out.println("1. edad");
                        System.out.println("2. sexo");
                        System.out.println("3. grupo sanguíneo");
                        System.out.println("4. altura y peso");
                        System.out.println("5. medicamento actual");
                        System.out.println("6. alergias");
                        System.out.println("7. antecedentes medicos");
                        System.out.println("8. antecedentes quirurgicos");
                        System.out.println("9. Volver al menú medico");
                        int opt = obtenerOpcion(scanner);

                        switch (opt) {
                            case 1:
                                System.out.println("Ingrese el nuevo dato:");
                                String Modificar = scanner.next();
                                List<historial> listaHistorial = leerCSV("historialm.csv");
                                for (historial historialM : listaHistorial) {
                                    if (historialM.getId().equals(paciente.getId())) {
                                        historialM.setSexo(Modificar);
                                        break; // No es necesario seguir buscando
                                    }
                                }
                                escribirCSV("historialm.csv", listaHistorial);
                                break;
                            case 2:
                                System.out.println("Ingrese el nuevo dato:");
                                String Modificar2 = scanner.next();
                                List<historial> listaHistorial2 = leerCSV("historialm.csv");
                                for (historial historialM : listaHistorial2) {
                                    if (historialM.getId().equals(paciente.getId())) {
                                        historialM.setSexo(Modificar2);
                                        break; // No es necesario seguir buscando
                                    }
                                }
                                escribirCSV("historialm.csv", listaHistorial2);
                                break;

                            case 3:
                                System.out.println("Ingrese el nuevo dato:");
                                String Modificar3 = scanner.next();
                                List<historial> listaHistorial3 = leerCSV("historialm.csv");
                                for (historial historialM : listaHistorial3) {
                                    if (historialM.getId().equals(paciente.getId())) {
                                        historialM.setGrupoSanguineo(Modificar3);
                                        break; // No es necesario seguir buscando
                                    }
                                }
                                escribirCSV("historialm.csv", listaHistorial3);
                                break;
                            case 4:
                                System.out.println("Ingrese el nuevo dato:");
                                String Modificar4 = scanner.next();
                                List<historial> listaHistorial4 = leerCSV("historialm.csv");
                                for (historial historialM : listaHistorial4) {
                                    if (historialM.getId().equals(paciente.getId())) {
                                        historialM.setAlturaPeso(Modificar4);
                                        break; // No es necesario seguir buscando
                                    }
                                }
                                escribirCSV("historialm.csv", listaHistorial4);
                                break;

                            case 5:
                                System.out.println("Ingrese el nuevo dato:");
                                String Modificar5 = scanner.next();
                                List<historial> listaHistorial5 = leerCSV("historialm.csv");
                                for (historial historialM : listaHistorial5) {
                                    if (historialM.getId().equals(paciente.getId())) {
                                        historialM.setMedicamentoActual(Modificar5);
                                        break; // No es necesario seguir buscando
                                    }
                                }
                                escribirCSV("historialm.csv", listaHistorial5);
                                break;

                            case 6:
                                System.out.println("Ingrese el nuevo dato:");
                                String Modificar6 = scanner.next();
                                List<historial> listaHistorial6 = leerCSV("historialm.csv");
                                for (historial historialM : listaHistorial6) {
                                    if (historialM.getId().equals(paciente.getId())) {
                                        historialM.setAlergias(Modificar6);
                                        break; // No es necesario seguir buscando
                                    }
                                }
                                escribirCSV("historialm.csv", listaHistorial6);
                                break;

                            case 7:
                                System.out.println("Ingrese el nuevo dato:");
                                String Modificar7 = scanner.next();
                                List<historial> listaHistorial7 = leerCSV("historialm.csv");
                                for (historial historialM : listaHistorial7) {
                                    if (historialM.getId().equals(paciente.getId())) {
                                        historialM.setAntecedentesmed(Modificar7);
                                        break; // No es necesario seguir buscando
                                    }
                                }
                                escribirCSV("historialm.csv", listaHistorial7);
                                break;
                            
                            case 8:
                                System.out.println("Ingrese el nuevo dato:");
                                String Modificar8 = scanner.next();
                                List<historial> listaHistorial8 = leerCSV("historialm.csv");
                                for (historial historialM : listaHistorial8) {
                                    if (historialM.getId().equals(paciente.getId())) {
                                        historialM.setAntecedentesqui(Modificar8);
                                        break; // No es necesario seguir buscando
                                    }
                                }
                                escribirCSV("historialm.csv", listaHistorial8);
                                break;

                            case 9:
                                hola = false;
                            }   
                    }                    
                    break;
                case 8:
                    return; // Salir y volver al menú paciente
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }

            System.out.println("¿Quieres ver más información? (si/no)");
            String respuesta = scanner.next().toLowerCase();

            if (!respuesta.equals("si")) {
                return; // Salir si la respuesta no es "si"
            }

        } while (true); // Repetir el bucle mientras el usuario quiera ver más información
    }


//leer archivo .csv de historial
    private static List<historial> leerCSV(String archivo) {
    List<historial> listaHistorial = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
        // Leer la fila de encabezado (ignorarla)
        br.readLine();

        String linea;
        while ((linea = br.readLine()) != null) {
            String[] datos = linea.split(",");

            String id = datos[0].trim();
            String nombre = datos[1].trim();
            String edad = datos[2].trim();
            String sexo = datos[3].trim();
            String grupo = datos[4].trim();
            String akturapeso = datos[5].trim();
            String ant_medicos = datos[6].trim();
            String ant_quir = datos[7].trim();
            String medActual = datos[8].trim();
            String alergia = datos[9].trim();

            listaHistorial.add(new historial(id, nombre, edad, sexo, grupo, akturapeso, ant_medicos, ant_quir, medActual, alergia));
        }
    } catch (IOException e) {
        e.printStackTrace();
    }

    return listaHistorial;
    }

    // Método para escribir datos en un archivo CSV
    private static void escribirCSV(String archivo, List<historial> listaHistorial) {
        try (FileWriter writer = new FileWriter(archivo)) {
            // Escribir la fila de encabezado
            writer.write("id,nombre,edad,sexo,grupo sanguineo,altura y peso,antecedentes medicos,antecedentes quirurgicos,medicamento actual,alergias\n");

            // Escribir los datos
            for (historial persona : listaHistorial) {
                writer.write(persona.getId() + "," + persona.getNombre() + "," + persona.getEdad() + "," + persona.getSexo() + "," + persona.getGrupoSanguineo() + "," + persona.getAlturaPeso() + "," + persona.getantecedentesmed() + "," + persona.getantecedentesqui() + "," + persona.getMedicamentoActual() + "," + persona.getAlergias() + "," + persona.getId() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void modificarCSV(String idModificr, String mod) {
        List<historial> listaHistorial = leerCSV("historialm.csv");
        for (historial historialM : listaHistorial) {
            if (historialM.getId().equals(idModificr)) {
                historialM.setEdad(mod);
                break; // No es necesario seguir buscando
            }
        }

        escribirCSV("historialm.csv", listaHistorial);
        
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

