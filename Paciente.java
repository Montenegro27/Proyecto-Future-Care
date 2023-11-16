public class Paciente {
    private int idp;
    private String nombre;
    private String seguroMedico;

    public Paciente(int idp, String nombre, String seguroMedico) {
        this.idp = idp;
        this.nombre = nombre;
        this.seguroMedico = seguroMedico;
    }

    // Getters y setters

    public String getNombre() {
        return nombre;
    }

    public String getSeguro() {
        return seguroMedico;
    }

    public int getID() {
        return idp;
    }

    public String toString() {
        return "ID: " + idp + "\nNombre: " + nombre + "\nSeguro Médico: " + seguroMedico;
    }

}


