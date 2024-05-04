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
    public void Prestamo(Usuario usuario) {
        disponible = false;
        usuarioPrestado = usuario.getRut();
        agregarHistorial(usuario.getRut());
    }

    public void DevolverSala(Usuario usuario) {
        if (usuario.getRut().equals(usuarioPrestado)) {
            disponible = true;
            usuarioPrestado = null;
            System.out.println("Sala devuelta correctamente");
        } else {
            System.out.println("No puedes devolver la sala, tu no has solicitado el prestamo.");
        }
    }
}
