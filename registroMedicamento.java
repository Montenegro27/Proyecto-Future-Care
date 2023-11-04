import java.util.*;
import java.io.*;


public class registroMedicamento{

    public static void main (String[] args){
        Scanner teclado = new Scanner(System.in);
        ArrayList<Medicamento> lista_medicamentos = new ArrayList<Medicamento>();

        //CARGAR ARCHIVO .CSV
        try{
            File archivo = new File("medicamentos.csv");
            Scanner scanner = new Scanner(archivo);

            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] campos = linea.split(",");

                String nombre_medicamento = campos[0];
                String tipo_medicamento = campos[1];
                String disponibilidad_medicamento = campos[2];
                int cantidad_medicamento = Integer.parseInt(campos[3]);

                lista_medicamentos.add(new Medicamento(nombre_medicamento, tipo_medicamento, disponibilidad_medicamento, cantidad_medicamento));

            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for(Medicamento medicamento : lista_medicamentos){
            System.out.println(medicamento.getNombre());
        }

        boolean hola = true;
        while(hola){
            System.out.println("Coloque el nombre del medicamento:");
            String name = teclado.nextLine();

            System.out.println("El tipo de medicamento: ");
            String type = teclado.nextLine();

            System.out.println("Ingrese si el medicamento esta Disponible/Agotado");
            String disponivilidad = teclado.nextLine();

            System.out.println("Ingrese la cantidad del medicamento: ");
            int cant = teclado.nextInt();
            teclado.nextLine();

            lista_medicamentos.add(new Medicamento(name, type, disponivilidad, cant));

            System.out.println("Desea registrar otro medicamento?");
            String decicion = teclado.nextLine();

            if(decicion.equals("no")){
                hola = false;
            }else{
                hola = true;
            }
        }
        System.out.println("Guardando datos....");
                    //guardar en el .csv
        String nombreArchivo = "medicamentos.csv";
        String encabezado = "Nombre,Tipo de Medicamento,Disponibilidad,Cantidad";

        try{
            FileWriter escritor = new FileWriter(nombreArchivo, false);
            escritor.write(encabezado + "\n");

            for(Medicamento medicamento : lista_medicamentos){
                escritor.write(medicamento.getNombre() + ","+ medicamento.getTipo() + "," + medicamento.getDisponibilidad() + "," + medicamento.getCantidad() + "\n");
            }
            escritor.close();

        }catch (IOException e) {
            e.printStackTrace();
        }
//YA SE GUARDO EL ARCHIVO                    

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
