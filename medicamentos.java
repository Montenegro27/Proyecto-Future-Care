import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class medicamentos {

    public static void main(String[] args) {
        // Crear una lista de medicamentos
        List<Medicamento> medicamentos = new ArrayList<>();
        medicamentos.add(new Medicamento("Paracetamol", "Analgésico", "Disponible", 100));
        medicamentos.add(new Medicamento("Ibuprofeno", "Antiinflamatorio", "Disponible", 50));
        medicamentos.add(new Medicamento("Amoxicilina", "Antibiótico", "Agotado", 0));
        medicamentos.add(new Medicamento("Omeprazol", "Antiácido", "Disponible", 75));

        // Especificar el nombre del archivo CSV
        String archivoCSV = "medicamentos.csv";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoCSV))) {
            // Escribir el encabezado del archivo CSV
            writer.write("Nombre,Tipo de Medicamento,Disponibilidad,Cantidad\n");

            // Escribir la información de los medicamentos en el archivo CSV
            for (Medicamento medicamento : medicamentos) {
                String linea = String.format("%s,%s,%s,%d\n",
                        medicamento.getNombre(),
                        medicamento.getTipo(),
                        medicamento.getDisponibilidad(),
                        medicamento.getCantidad());
                writer.write(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Los medicamentos se han guardado en el archivo CSV: " + archivoCSV);
    }
}

class Medicamento {
    private String nombre;
    private String tipo;
    private String disponibilidad;
    private int cantidad;

    public Medicamento(String nombre, String tipo, String disponibilidad, int cantidad) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.disponibilidad = disponibilidad;
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDisponibilidad() {
        return disponibilidad;
    }

    public int getCantidad() {
        return cantidad;
    }
}
