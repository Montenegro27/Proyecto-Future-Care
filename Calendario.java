import java.util.ArrayList;
import java.util.List;

public class Calendario {
   
    private List<CitaMedica> citas;

    public Calendario() {
        citas = new ArrayList<>();

    }

    public void agregarCita(CitaMedica cita) {
        citas.add(cita);

    }

    public List<CitaMedica> obtenerCitas() {
        return citas;
    }
}
