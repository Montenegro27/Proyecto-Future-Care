class Medico {
    private int id;
    private String nombre;
    private String especialidad;

    public Medico(int id, String nombre, String especialidad) {
        this.id = id;
        this.nombre = nombre;
        this.especialidad = especialidad;
    }

    // Getters y setters

    public String toString() {
        return "ID: " + id + "\nNombre: " + nombre + "\nEspecialidad: " + especialidad;
    }
}
