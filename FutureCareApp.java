import java.util.ArrayList;
import java.util.List;

public class FutureCareApp {
    private List<Paciente> pacientes;
    private List<Medico> medicos;
    private List<CitaMedica> citasMedicas;

    public FutureCareApp() {
        pacientes = new ArrayList<>();
        medicos = new ArrayList<>();
        citasMedicas = new ArrayList<>();
    }

    public void agregarPaciente(Paciente paciente) {
        pacientes.add(paciente);
    }

    public void agregarMedico(Medico medico) {
        medicos.add(medico);
    }

    public void programarCita(Paciente paciente, Medico medico, String fecha, String hora) {
        int citaId = citasMedicas.size() + 1;
        CitaMedica cita = new CitaMedica(citaId, paciente, medico, fecha, hora);
        citasMedicas.add(cita);
    }

    public List<CitaMedica> obtenerCitasMedicas() {
        return citasMedicas;
    }

    public static void main(String[] args) {
        FutureCareApp app = new FutureCareApp();

        Paciente paciente1 = new Paciente(1, "Juan Perez", "12345");
        Paciente paciente2 = new Paciente(2, "Maria Rodriguez", "67890");
        app.agregarPaciente(paciente1);
        app.agregarPaciente(paciente2);

        Medico medico1 = new Medico(1, "Dr. Lopez", "Cardiología");
        Medico medico2 = new Medico(2, "Dra. Martinez", "Pediatría");
        app.agregarMedico(medico1);
        app.agregarMedico(medico2);

        app.programarCita(paciente1, medico1, "2023-10-15", "10:00 AM");
        app.programarCita(paciente2, medico2, "2023-10-16", "2:30 PM");

        List<CitaMedica> citas = app.obtenerCitasMedicas();
        for (CitaMedica cita : citas) {
            System.out.println(cita);
        }
    }
}
