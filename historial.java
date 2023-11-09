class historial {
    private String id;
    private String nombre;
    private String edad;
    private String sexo;
    private String grupoSanguineo;
    private String alturaPeso;
    private String medicamentoActual;
    private String alergias;
    private String antecedentesMedicos;
    private String antecedentesQuirurgicos;

    public historial(String id, String nombre, String edad, String sexo, String grupoSanguineo, String alturaPeso, String medicamentoActual, String alergias, String antecedentesMedicos, String antecedentesQuirurgicos) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.sexo = sexo;
        this.grupoSanguineo = grupoSanguineo;
        this.alturaPeso = alturaPeso;
        this.medicamentoActual = medicamentoActual;
        this.alergias = alergias;
        this.antecedentesMedicos = antecedentesMedicos;
        this.antecedentesQuirurgicos = antecedentesQuirurgicos;
    }

    public historial(String idCSV, String antecedentesMedicos2, String antecedentesQuirurgicos2) {
    }

    public historial(String idCSV, String nombre2, String edad2, String sexo2, String grupoSanguineo2,
            String alturaPeso2, String medicamentoActual2, String alergias2) {
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

    public String getAntecedentesMedicos() {
        return antecedentesMedicos;
    }

    public String getAntecedentesQuirurgicos() {
        return antecedentesQuirurgicos;
    }
}