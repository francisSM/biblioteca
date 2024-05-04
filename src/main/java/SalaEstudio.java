import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SalaEstudio {
    private int ID_Sala;
    private int capacidad;
    private boolean disponible;
    private String usuarioPrestado;
    private List<String> historialprestamo;

    public SalaEstudio(int numero, int capacidad, boolean disponible) {
        this.ID_Sala = numero;
        this.capacidad = capacidad;
        this.disponible = disponible;
        this.historialprestamo = new ArrayList<>();
    }

    public static void Prestamo(Usuario usuario, SalaEstudio sala) {
        sala.disponible = false;
        sala.usuarioPrestado = usuario.getRut();
        sala.agregarHistorial(usuario.getRut());
    }
    public void Prestamo(SalaEstudio sala) {

    }

    public int getNumero() {
        return ID_Sala;
    }
    public int getCapacidad() {
        return capacidad;
    }
    public boolean isDisponible() {
        return disponible;
    }
    public String getUsuarioPrestado() {
        return usuarioPrestado;
    }
    public void agregarHistorial(String rut) {
        historialprestamo.add(rut);
    }
    public void DevolverSala() {
        if (!disponible) {
            disponible = true;
            usuarioPrestado = null;
            System.out.println("Sala devuelta correctamente");
        } else {
            System.out.println("La sala ya está disponible");
        }
    }
    public void ModificiarCapacidad(){
        System.out.println("Ingrese la nueva capacidad");
        Scanner Scanner = new Scanner(System.in);
        int newcapacidad = Scanner.nextInt();
        capacidad = newcapacidad;
    }
}
