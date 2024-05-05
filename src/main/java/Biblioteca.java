import java.util.*;

public class Biblioteca {
    private List<Libro> catalogo;
    private List<Usuario> usuarios;
    private Map<Usuario, List<Libro>> prestamos;
    private List<SalaEstudio> salas;

    public Biblioteca() {
        this.catalogo = new ArrayList<>();
        this.usuarios = new ArrayList<>();
        this.prestamos = new HashMap<>();
        this.salas = new ArrayList<>();
    }
    public void agregarSala(SalaEstudio Sala) {
        salas.add(Sala);
    }
    public void eliminarSala(int ID) {
        SalaEstudio salaAEliminar = null;
        for (SalaEstudio sala : salas) {
            if (sala.getNumero() == ID) {
                salaAEliminar = sala;
                break;
            }
        }
        if (salaAEliminar != null) {
            salas.remove(salaAEliminar);
            System.out.println("Sala eliminada correctamente.");
        } else {
            System.out.println("No se encontró una sala con ese ID.");
        }
    }

    public void agregarLibro(Libro libro) {
        catalogo.add(libro);
    }

    public void eliminarLibro(Libro libro) {
        catalogo.remove(libro);
    }

    public void actualizarLibro(Libro libro) {
        for (int i = 0; i < catalogo.size(); i++) {
            if (catalogo.get(i).getTitulo().equals(libro.getTitulo())) {
                catalogo.set(i, libro);
                break;
            }
        }
    }

    public void agregarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public void eliminarUsuario(Usuario usuario) {
        usuarios.remove(usuario);
        prestamos.remove(usuario);
    }

   public void actualizarUsuario(Usuario usuario) {
        Scanner scanner = new Scanner(System.in);
        if (usuario != null) {
            System.out.println("Nombre Usuario: "+ usuario.getNombre()+"\nUsuario Registrado Actualmente como: " +usuario.getTipo());
            System.out.println("Seleccione el nuevo Rol del usuario:\n1. Estudiante\n2. Profesor\n3. Personal de la Biblioteca");
            int tipoUsuario = scanner.nextInt();
            String tipo = switch (tipoUsuario) {
                case 1 -> "Estudiante";
                case 2 -> "Profesor";
                case 3 -> "Personal de la Biblioteca";
                default -> "Tipo de usuario no especificado";
            };
            if (tipoUsuario >= 1 && tipoUsuario <= 3){
            usuario.setTipo(tipo);
            System.out.println("Tipo de usuario actualizado correctamente.");}
            else {
                System.out.println("Tipo de Usuario Invalido");
            }
        } else {
            System.out.println("Usuario no encontrado.");
        }
    }

    public void realizarPrestamo(Usuario usuario, Libro libro) {
        if (prestamos.containsKey(usuario)) {
            List<Libro> prestamosUsuario = prestamos.get(usuario);
            prestamosUsuario.add(libro);
            prestamos.put(usuario, prestamosUsuario);
        } else {
            List<Libro> prestamosUsuario = new ArrayList<>();
            prestamosUsuario.add(libro);
            prestamos.put(usuario, prestamosUsuario);
        }
        libro.prestamoRealizado();
    }

    public void realizarDevolucion(Usuario usuario, Libro libro) {
        if (prestamos.containsKey(usuario)) {
            List<Libro> prestamosUsuario = prestamos.get(usuario);
            prestamosUsuario.remove(libro);
            prestamos.put(usuario, prestamosUsuario);
            libro.devolucionRealizada();
        }
    }

    public List<Libro> getCatalogo() {
        return catalogo;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public List<SalaEstudio> getSalas() {
        return salas;
    }

    public Map<Usuario, List<Libro>> getPrestamos() {
        return prestamos;
    }
    public void PrestarSala(Usuario usuario, SalaEstudio sala) {
        Scanner scanner = new Scanner(System.in);
        if (sala.isDisponible()) {
            System.out.println("Ingrese el número de usuarios que ocuparán la sala");
            int numUsuarios = scanner.nextInt();
            if (numUsuarios <= sala.getCapacidad()) {
                sala.Prestamo(usuario);
                System.out.println("Sala prestada correctamente a: " + usuario.getNombre());
            } else {
                System.out.println("Capacidad de la sala superada");
            }
        } else {
            System.out.println("Sala no disponible");
        }
    }

    public void DevolverSala(Usuario usuario, SalaEstudio sala) {
        if (!sala.isDisponible()) {
            sala.DevolverSala(usuario);
        } else {
            System.out.println("La sala ya está disponible");
        }
    }
    public SalaEstudio obtenerSalaPorID(int ID) {
        for (SalaEstudio sala : salas) {
            if (sala.getNumero() == ID) {
                return sala;
            }
        }
        return null;
    }

}
