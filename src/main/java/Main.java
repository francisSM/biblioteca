import java.util.*;

public class Main {
    private static Biblioteca biblioteca = new Biblioteca();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        SalaEstudio salaEstudio = new SalaEstudio(1,5,true);
        biblioteca.agregarSala(salaEstudio);
        Libro libro = new Libro("Test", "Test", "Test", 5);
        biblioteca.agregarLibro(libro);
        Usuario usuario = new Usuario("Pedro", "208181203", "Estudiante");
        biblioteca.agregarUsuario(usuario);
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
        if (sala != null){
        biblioteca.PrestarSala(usuario ,sala);}
        else {
            System.out.println("Sala No Encontrada");
        }
    }
    public static void devolversala(){
        String rutUsuario = solicitarRut();
        Usuario usuario = buscarUsuarioPorRut(rutUsuario);
        if (usuario == null) {
            System.out.println("Usuario no encontrado.");
            return;
        }
        int ID = Integer.parseInt(solicitarEntrada("Ingrese el ID de la sala:"));
        SalaEstudio sala = biblioteca.obtenerSalaPorID(ID);
        if (sala != null){
            biblioteca.DevolverSala(usuario ,sala);}
        else {
            System.out.println("Sala No Encontrada");
        }
    }

    public static void agregarUsuario() {
        String nombre = solicitarEntrada("Ingrese el nombre del usuario:");
        String rut = solicitarRutRegistro();
        int tipoUsuario = solicitarTipoUsuario();
        String tipo = switch (tipoUsuario) {
            case 1 -> "Estudiante";
            case 2 -> "Profesor";
            case 3 -> "Personal de la Biblioteca";
            default -> "Tipo de usuario no especificado";
        };

        // Solicitar confirmación al usuario
        boolean confirmado = confirmarDatos(nombre, rut, tipo);

        if (confirmado) {
            Usuario usuario = new Usuario(nombre, rut, tipo);
            biblioteca.agregarUsuario(usuario);
            System.out.println("Usuario agregado con éxito.");
        } else {
            System.out.println("Registro de usuario cancelado. No se han realizado cambios.");
        }
    }


    private static boolean confirmarDatos(String nombre, String rut, String tipo) {
        System.out.println("Datos ingresados:");
        System.out.println("Nombre: " + nombre);
        System.out.println("RUT: " + rut);
        System.out.println("Tipo de usuario: " + tipo);

        Scanner scanner = new Scanner(System.in);
        int respuesta;

        while (true) {
            try {
                System.out.println("¿Desea confirmar los datos ingresados?");
                System.out.println("1. Sí");
                System.out.println("2. No");
                System.out.print("Ingrese su respuesta (1/2): ");
                respuesta = Integer.parseInt(scanner.nextLine());

                if (respuesta != 1 && respuesta != 2) {
                    throw new IllegalArgumentException("Respuesta inválida. Por favor, ingrese 1 o 2.");
                }

                break;
            } catch (NumberFormatException e) {
                System.out.println("Error: Respuesta no válida. Ingrese un número (1 o 2).");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        return respuesta == 1;
    }

    private static int solicitarTipoUsuario() {
        int tipoUsuario;
        while (true) {
            try {
                tipoUsuario = Integer.parseInt(solicitarEntrada("Seleccione el tipo de usuario:\n1. Estudiante\n2. Profesor\n3. Personal de la Biblioteca"));
                if (tipoUsuario < 1 || tipoUsuario > 3) {
                    throw new IllegalArgumentException("Opción inválida. Por favor, seleccione un número entre 1 y 3.");
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Opción inválida. Por favor, ingrese un número.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return tipoUsuario;
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
    public static void editarusuario(){
        String rutUsuario = solicitarRut();
        biblioteca.actualizarUsuario(buscarUsuarioPorRut(rutUsuario));
    }

    public static void agregarLibro() {
        String titulo = solicitarEntrada("Ingrese el título del libro:");
        String autor = solicitarEntrada("Ingrese el autor del libro:");
        String categoria = solicitarEntrada("Ingrese la categoría del libro:");
        int ejemplaresDisponibles = solicitarCantidadEjemplares();
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

    public static int solicitarCantidadEjemplares() {
        int ejemplares = 0;
        while (true) {
            try {
                ejemplares = Integer.parseInt(solicitarEntrada("Ingrese la cantidad de ejemplares disponibles del libro:"));
                if (ejemplares <= 0) {
                    throw new IllegalArgumentException("La cantidad de ejemplares debe ser mayor a cero.");
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un número válido.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return ejemplares;
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

    public static void mostrarHistorialPrestamosLibros() {
        List<Map.Entry<Usuario, Libro>> historial = biblioteca.getHistorialPrestamos();
        if (historial == null || historial.isEmpty()) {
            System.out.println("No hay historial de préstamos de libros.");
        } else {
            System.out.println("Historial de Préstamos de Libros");
            for (Map.Entry<Usuario, Libro> entry : historial) {
                System.out.println("Usuario: " + entry.getKey().getNombre() + " - Libro: " + entry.getValue().getTitulo());
            }
        }
    }

    public static void mostrarInformacionUsuarios() {
        List<Usuario> usuarios = biblioteca.getUsuarios();
        if (usuarios == null || usuarios.isEmpty()) {
            System.out.println("No hay información registrada de usuarios.");
        } else {
            System.out.println("Información de Usuarios");
            for (Usuario usuario : usuarios) {
                System.out.println(usuario);
            }
        }
    }

    public static void mostrarInformacionLibros() {
        List<Libro> libros = biblioteca.getCatalogo();
        if (libros == null || libros.isEmpty()) {
            System.out.println("No hay información registrada de libros.");
        } else {
            System.out.println("Información de Libros");
            for (Libro libro : libros) {
                System.out.println(libro);
            }
        }
    }

    public static void mostrarInformacionSalas() {
        List<SalaEstudio> salas = biblioteca.getSalas();
        if (salas == null || salas.isEmpty()) {
            System.out.println("No hay información registrada de salas.");
        } else {
            System.out.println("Información de Salas");
            for (SalaEstudio sala : salas) {
                System.out.println("ID: " + sala.getNumero());
                System.out.println("Capacidad: " + sala.getCapacidad());
                System.out.println("Disponible: " + (sala.isDisponible() ? "Sí" : "No"));
                System.out.println("Usuario prestado: " + (sala.isDisponible() ? "N/A" : sala.getUsuarioPrestado()));
                System.out.println();
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

                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return rut;
    }
    private static String solicitarRutRegistro() {
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
        List<Libro> prestamosUsuario = biblioteca.getPrestamos().getOrDefault(usuario, new ArrayList<>());
        boolean libroDisponible = biblioteca.getCatalogo().contains(libro);
        return !prestamosUsuario.contains(libro) && libroDisponible && libro.getEjemplaresDisponibles() > 0;
    }
}
