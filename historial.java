class historial {
    private String id;
    private String nombre;
    private String edad;
    private String sexo;
    private String grupoSanguineo;
    private String alturaPeso;
    private String antecedentesmed;
    private String antecedentesqui;
    private String medicamentoActual;
    private String alergias;

    public historial(String id, String nombre, String edad, String sexo, String grupoSanguineo, String alturaPeso, String antecedentesmed, String antecedentesqui, String medicamentoActual, String alergias) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.sexo = sexo;
        this.grupoSanguineo = grupoSanguineo;
        this.alturaPeso = alturaPeso;
        this.antecedentesmed = antecedentesmed;
        this.antecedentesqui = antecedentesqui;
        this.medicamentoActual = medicamentoActual;
        this.alergias = alergias;
    }

    public historial(String idCSV, String string, String string2, String string3, String string4, String string5, String string6, String string7, String string8) {
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

    public String getantecedentesmed() {
        return antecedentesmed;
    }

    public String getantecedentesqui() {
        return antecedentesqui;
    }

    public String getMedicamentoActual() {
        return medicamentoActual;
    }

    public String getAlergias() {
        return alergias;
    }
}