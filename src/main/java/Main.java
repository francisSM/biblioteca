import java.util.*;

public class Main {
    private static Biblioteca biblioteca = new Biblioteca();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        MenuInteractivo.mostrarMenu();
    }
    public static void agregarSala() {
        int ID = Integer.parseInt(solicitarEntrada("Ingrese el ID de la sala:"));
        int Capacidad = Integer.parseInt(solicitarEntrada("Ingrese la capacidad maxima de la sala:"));
        boolean disponible= true;
        SalaEstudio sala = new SalaEstudio(ID, Capacidad, disponible);
        biblioteca.agregarSala(sala);
        System.out.println("Sala agregada con éxito.");
    }
    public static void eliminarSala() {
        int ID = Integer.parseInt(solicitarEntrada("Ingrese el ID de la sala a eliminar:"));
        biblioteca.eliminarSala(ID);
    }
    public static void Prestamosala(){
        String rutUsuario = solicitarRut();
        Usuario usuario = buscarUsuarioPorRut(rutUsuario);
        if (usuario == null) {
            System.out.println("Usuario no encontrado.");
            return;
        }
        int ID = Integer.parseInt(solicitarEntrada("Ingrese el ID de la sala:"));
        SalaEstudio sala = biblioteca.obtenerSalaPorID(ID);
        biblioteca.PrestarSala(usuario ,sala);
    }

    public static void agregarUsuario() {
        String nombre = solicitarEntrada("Ingrese el nombre del usuario:");
        String rut = solicitarRut();
        String tipo = solicitarEntrada("Ingrese el tipo de usuario (Estudiante/Profesor/Personal de la Biblioteca):");
        Usuario usuario = new Usuario(nombre, rut, tipo);
        biblioteca.agregarUsuario(usuario);
        System.out.println("Usuario agregado con éxito.");
    }

    public static void eliminarUsuario() {
        String rut = solicitarRut();
        Usuario usuario = buscarUsuarioPorRut(rut);
        if (usuario != null) {
            biblioteca.eliminarUsuario(usuario);
            System.out.println("Usuario eliminado con éxito.");
        } else {
            System.out.println("Usuario no encontrado.");
        }
    }

    public static void agregarLibro() {
        String titulo = solicitarEntrada("Ingrese el título del libro:");
        String autor = solicitarEntrada("Ingrese el autor del libro:");
        String categoria = solicitarEntrada("Ingrese la categoría del libro:");
        int ejemplaresDisponibles = Integer.parseInt(solicitarEntrada("Ingrese la cantidad de ejemplares disponibles del libro:"));
        Libro libro = new Libro(titulo, autor, categoria, ejemplaresDisponibles);
        biblioteca.agregarLibro(libro);
        System.out.println("Libro agregado con éxito.");
    }

    public static void eliminarLibro() {
        String titulo = solicitarEntrada("Ingrese el título del libro que desea eliminar:");
        Libro libro = buscarLibroPorTitulo(titulo);
        if (libro != null) {
            biblioteca.eliminarLibro(libro);
            System.out.println("Libro eliminado con éxito.");
        } else {
            System.out.println("Libro no encontrado.");
        }
    }

    public static void realizarPrestamo() {
        String rutUsuario = solicitarRut();
        Usuario usuario = buscarUsuarioPorRut(rutUsuario);
        if (usuario == null) {
            System.out.println("Usuario no encontrado.");
            return;
        }

        String tituloLibro = solicitarEntrada("Ingrese el título del libro que se prestará:");
        Libro libro = buscarLibroPorTitulo(tituloLibro);
        if (libro == null) {
            System.out.println("Libro no encontrado.");
            return;
        }

        if (!validarPrestamo(usuario, libro)) {
            System.out.println("No se puede realizar el préstamo. El libro no está disponible.");
            return;
        }

        biblioteca.realizarPrestamo(usuario, libro);
        System.out.println("Préstamo realizado con éxito.");
    }

    public static void realizarDevolucion() {
        String rutUsuario = solicitarRut();
        Usuario usuario = buscarUsuarioPorRut(rutUsuario);
        if (usuario == null) {
            System.out.println("Usuario no encontrado.");
            return;
        }

        String tituloLibro = solicitarEntrada("Ingrese el título del libro que se devolverá:");
        Libro libro = buscarLibroPrestadoPorUsuario(usuario, tituloLibro);
        if (libro == null) {
            System.out.println("Libro no encontrado o no prestado a este usuario.");
            return;
        }

        biblioteca.realizarDevolucion(usuario, libro);
        System.out.println("Devolución realizada con éxito.");
    }

    public static void mostrarInformacion() {
        System.out.println("------ Información ------");
        System.out.println("Libros en el catálogo:");
        for (Libro libro : biblioteca.getCatalogo()) {
            System.out.println(libro);
        }
        System.out.println("\nUsuarios registrados:");
        for (Usuario usuario : biblioteca.getUsuarios()) {
            System.out.println(usuario);
        }
        System.out.println("\nPréstamos registrados:");
        for (Map.Entry<Usuario, List<Libro>> entry : biblioteca.getPrestamos().entrySet()) {
            Usuario usuario = entry.getKey();
            List<Libro> librosPrestados = entry.getValue();
            System.out.println("Usuario: " + usuario.getNombre());
            System.out.println("Libros prestados:");
            for (Libro libro : librosPrestados) {
                System.out.println("- " + libro.getTitulo());
            }
        }
    }

    private static Usuario buscarUsuarioPorRut(String rut) {
        for (Usuario u : biblioteca.getUsuarios()) {
            if (u.getRut().equals(rut)) {
                return u;
            }
        }
        return null;
    }

    private static String solicitarRut() {
        String rut;
        while (true) {
            try {
                rut = solicitarEntrada("Ingrese el RUT del usuario (sin puntos ni guión):");
                if (!validarRut(rut)) {
                    throw new IllegalArgumentException("El formato del RUT es inválido. Por favor, ingrese nuevamente.");
                }
                if (existeUsuarioConRut(rut)) {
                    throw new IllegalArgumentException("El RUT ingresado ya está registrado. Por favor, ingrese otro RUT.");
                }
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return rut;
    }

    private static boolean existeUsuarioConRut(String rut) {
        for (Usuario usuario : biblioteca.getUsuarios()) {
            if (usuario.getRut().equals(rut)) {
                return true;
            }
        }
        return false;
    }


    private static boolean validarRut(String rut) {
        String rutRegex = "\\d{8}[0-9kK]";
        if (rut.toLowerCase().endsWith("k")) {
            rut = rut.substring(0, rut.length() - 1) + "0";
        }
        return rut.matches(rutRegex);
    }

    private static String solicitarEntrada(String mensaje) {
        System.out.println(mensaje);
        return scanner.nextLine();
    }

    private static Libro buscarLibroPorTitulo(String titulo) {
        for (Libro l : biblioteca.getCatalogo()) {
            if (l.getTitulo().equals(titulo)) {
                return l;
            }
        }
        return null;
    }

    private static Libro buscarLibroPrestadoPorUsuario(Usuario usuario, String titulo) {
        for (Libro l : biblioteca.getPrestamos().get(usuario)) {
            if (l.getTitulo().equals(titulo)) {
                return l;
            }
        }
        return null;
    }

    private static boolean validarPrestamo(Usuario usuario, Libro libro) {
        return libro.getEjemplaresDisponibles() > 0 && !biblioteca.getPrestamos().get(usuario).contains(libro);
    }
}
