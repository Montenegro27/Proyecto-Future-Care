class CitaMedica {
    private int id;
    private Paciente paciente;
    private Medico medico;
    private String fecha;
    private String hora;

    public CitaMedica(int id, Paciente paciente, Medico medico, String fecha, String hora) {
        this.id = id;
        this.paciente = paciente;
        this.medico = medico;
        this.fecha = fecha;
        this.hora = hora;
    }

    // Getters y setters

    public String toString() {
        return "ID: " + id + "\nFecha: " + fecha + "\nHora: " + hora + "\nPaciente: \n" + paciente + "\nMedico: \n" + medico;
    }
}

