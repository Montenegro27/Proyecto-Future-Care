import java.util.Random;

public class CitaMedica {
    private int id;
    private Paciente paciente;
    private Medico medico;
    private String fecha;
    private String hora;

    public CitaMedica(int id, Paciente paciente, Medico medico, String fecha, String hora) {
        this.id = generarID();
        this.paciente = paciente;
        this.medico = medico;
        this.fecha = fecha;
        this.hora = hora;
    }

    public int generarID() {
        Random random = new Random();

        return random.nextInt(9999);

    }

    // Getters y setters

    public String toString() {
        return "ID: " + id + "\nFecha: " + fecha + "\nHora: " + hora + "\nPaciente: \n" + paciente + "\nMedico: \n" + medico;
    }
}

