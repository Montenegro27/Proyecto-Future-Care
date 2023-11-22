import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Gestor {
    private static Calendario calendario;
    private static Scanner scanner;

    static {
        calendario = new Calendario();
        scanner = new Scanner(System.in);
    }

    public Gestor() {

    }

    public static void agendarCita() {

        System.out.println("Ingrese el ID del paciente: ");
        String idp = scanner.next();

        System.out.println("Ingrese el ID del médico: ");
        int idm = scanner.nextInt();

        System.out.println("Ingrese la fecha (formato YYYY-MM-DD): ");
        String fecha = scanner.next();

        System.out.println("Ingrese la hora (formato HH:mm): ");
        String hora = scanner.next();

        CitaMedica nuevaCita = new CitaMedica( idp, idm, fecha, hora);

        calendario.agregarCita(nuevaCita);

        System.out.println("Cita agendada con éxito.");
    }


// public static List<CitaMedica> obtenerCitasPaciente(String numSeguro) {
//     List<CitaMedica> citasPaciente = new ArrayList<>();

//     for (CitaMedica cita : calendario.obtenerCitas()) {
//         if (cita.getIdP().equals(numSeguro)) {
//             citasPaciente.add(cita);
//         }
//     }

//     return citasPaciente;
// }

// public static void mostrarCitasPaciente(String numSeguro) {
//     List<CitaMedica> citasPaciente = obtenerCitasPaciente(numSeguro);

//     if (citasPaciente.isEmpty()) {
//         System.out.println("El paciente no tiene citas programadas.");
//     } else {
//         System.out.println("Citas del paciente con ID " + numSeguro + ":");
//         for (CitaMedica cita : citasPaciente) {
//             System.out.println(cita);
//         }
//     }
// }
public static void mostrarCitasPaciente(String numSeguro) {
    List<CitaMedica> citasPaciente = obtenerCitasPaciente(numSeguro);

    if (citasPaciente.isEmpty()) {
        System.out.println("El paciente no tiene citas programadas.");
    } else {
        System.out.println("Citas del paciente con ID " + numSeguro + ":");
        for (CitaMedica cita : citasPaciente) {
            System.out.println(cita);
        }
    }
}

public static List<CitaMedica> obtenerCitasPaciente(String numSeguro) {
    List<CitaMedica> citasPaciente = new ArrayList<>();

    for (CitaMedica cita : calendario.obtenerCitas()) {
        if (cita.getIdP().equals(numSeguro)) {
            citasPaciente.add(cita);
        }
    }

    return citasPaciente;
}


}
