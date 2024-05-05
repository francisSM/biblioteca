import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String nombre;
    private String rut;
    private String tipo; // Estudiante, Profesor, Personal de la Biblioteca
    private List<Libro> historialPrestamos;
    private List<Libro> librosReservados;
    private List<Integer> calificaciones;

    public Usuario(String nombre, String rut, String tipo) {
        this.nombre = nombre;
        this.rut = rut;
        this.tipo = tipo;
        this.historialPrestamos = new ArrayList<>();
        this.librosReservados = new ArrayList<>();
        this.calificaciones = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public String getRut() {
        return rut;
    }

    public String getTipo() {
        return tipo;
    }

    public List<Libro> getHistorialPrestamos() {
        return historialPrestamos;
    }

    public List<Libro> getLibrosReservados() {
        return librosReservados;
    }

    public List<Integer> getCalificaciones() {
        return calificaciones;
    }

    public void agregarPrestamo(Libro libro) {
        historialPrestamos.add(libro);
    }

    public void realizarReserva(Libro libro) {
        librosReservados.add(libro);
    }

    public void agregarCalificacion(int calificacion) {
        calificaciones.add(calificacion);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                ", rut='" + rut + '\'' +
                ", tipo='" + tipo + '\'' +
                ", historialPrestamos=" + historialPrestamos +
                ", librosReservados=" + librosReservados +
                ", calificaciones=" + calificaciones +
                '}';
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
