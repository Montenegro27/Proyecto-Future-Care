import java.util.Random;

public class CitaMedica {
    private int idc;
    private String idp;
    private int idm;
    private String fecha;
    private String hora;

    public CitaMedica(String idp, int idm, String fecha, String hora) {
        this.idc = generarID();
        this.idp = idp;
        this.idm = idm;
        this.fecha = fecha;
        this.hora = hora;
    }

    public int getIdC() {
        return idc;
    }

    public String getIdP() {
        return idp;
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

