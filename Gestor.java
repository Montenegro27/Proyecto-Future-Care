import java.util.Scanner;

public class Gestor {
    private Calendario calendario;
    private Scanner scanner;

    public Gestor() {
        calendario = new Calendario();
        scanner = new Scanner(System.in);
    }

    public void agendarCita() {

        System.out.println("Ingrese el ID del paciente: ");
        int idp = scanner.nextInt();

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

}