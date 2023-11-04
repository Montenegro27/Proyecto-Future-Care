import java.util.*;
import java.io.*;


public class registroPaciente{

    public static void main (String[] args){
        Scanner teclado = new Scanner(System.in);
        ArrayList<Paciente> lista_paciente = new ArrayList<Paciente>();

        //CARGAR ARCHIVO .CSV
        try{
            File archivo = new File("pacientes.csv");
            Scanner scanner = new Scanner(archivo);

            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] campos = linea.split(",");

                int id_paciente = Integer.parseInt(campos[0]);
                String nombre_paciente = campos[1];
                String seguro_paciente = campos[2];

                lista_paciente.add(new Paciente(id_paciente, nombre_paciente, seguro_paciente));

            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for(Paciente paciente : lista_paciente){
            System.out.println(paciente);
        }

        boolean hola = true;
        while(hola){

            System.out.println("Ingrese el id del paciente: ");
            int id = teclado.nextInt();
            teclado.nextLine();

            System.out.println("Coloque el nombre del paciente:");
            String nameP = teclado.nextLine();

            System.out.println("Ingrese el seguro medico del paciente: ");
            String seguro = teclado.nextLine();

            lista_paciente.add(new Paciente(id, nameP, seguro));

            System.out.println("Desea registrar otro paciente?");
            String decicion = teclado.nextLine();

            if(decicion.equals("no")){
                hola = false;
            }else{
                hola = true;
            }
        }
        System.out.println("Guardando datos....");
                    //guardar en el .csv
        String nombreArchivo = "pacientes.csv";
        String encabezado = "id,nombre,seguro";

        try{
            FileWriter escritor = new FileWriter(nombreArchivo, false);
            escritor.write(encabezado + "\n");

            for(Paciente paciente : lista_paciente){
                escritor.write(paciente.getID() + ","+ paciente.getNombre() + "," + paciente.getSeguro() + "\n");
            }
            escritor.close();

        }catch (IOException e) {
            e.printStackTrace();
        }
//YA SE GUARDO EL ARCHIVO                    

    }
}


class Paciente {
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
