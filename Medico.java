class Medico {
    private int idm;
    private String nombre;
    private String especialidad;

    public Medico(int idm, String nombre, String especialidad) {
        this.idm = idm;
        this.nombre = nombre;
        this.especialidad = especialidad;
    }

    // Getters y setters

    public String toString() {
        return "ID: " + idm + "\nNombre: " + nombre + "\nEspecialidad: " + especialidad;
    }
}
