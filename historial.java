class historial {
    private String id;
    private String nombre;
    private String edad;
    private String sexo;
    private String grupoSanguineo;
    private String alturaPeso;
    private String medicamentoActual;
    private String alergias;

    public historial(String id, String nombre, String edad, String sexo, String grupoSanguineo, String alturaPeso, String medicamentoActual, String alergias) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.sexo = sexo;
        this.grupoSanguineo = grupoSanguineo;
        this.alturaPeso = alturaPeso;
        this.medicamentoActual = medicamentoActual;
        this.alergias = alergias;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEdad() {
        return edad;
    }

    public String getSexo() {
        return sexo;
    }

    public String getGrupoSanguineo() {
        return grupoSanguineo;
    }

    public String getAlturaPeso() {
        return alturaPeso;
    }

    public String getMedicamentoActual() {
        return medicamentoActual;
    }

    public String getAlergias() {
        return alergias;
    }
}