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

    public void PrestarSala(Usuario usuario) {
        Scanner scanner = new Scanner(System.in);
        if (disponible) {
            System.out.println("Ingrese el número de usuarios que ocuparán la sala");
            int numUsuarios = scanner.nextInt();
            if (numUsuarios <= capacidad) {
                disponible = false;
                usuarioPrestado = usuario.getRut();
                agregarHistorial(usuario.getRut());
                System.out.println("Sala prestada correctamente a: " + usuario.getNombre() + " (RUT: " + usuarioPrestado + ")");
            } else {
                System.out.println("Capacidad de la sala superada");
            }
        } else {
            System.out.println("Sala no disponible");
        }
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
