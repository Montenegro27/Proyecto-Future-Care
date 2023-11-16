import java.util.Random;

public class CitaMedica {
    private int idc;
    private int idp;
    private int idm;
    private String fecha;
    private String hora;

    public CitaMedica(int idp, int idm, String fecha, String hora) {
        this.idc = generarID();
        this.idp = idp;
        this.idm = idm;
        this.fecha = fecha;
        this.hora = hora;
    }

    public int getId() {
        return idc;
    }

    public int generarID() {
        Random random = new Random();

        return random.nextInt(9999);

    }

    // Getters y setters

    public String toString() {
        return "ID: " + idc + "\nFecha: " + fecha + "\nHora: " + hora + "\nPaciente: \n" + idp + "\nMedico: \n" + idm;
    }
}

