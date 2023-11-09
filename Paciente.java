public class Paciente {
    private int id;
    private String nombre;
    private String seguroMedico;

    public Paciente(int id, String nombre, String seguroMedico) {
        this.id = id;
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
        return id;
    }

    public String toString() {
        return "ID: " + id + "\nNombre: " + nombre + "\nSeguro Médico: " + seguroMedico;
    }

}


