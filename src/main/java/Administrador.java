import java.util.List;

public class Administrador {
    private Biblioteca biblioteca;

    public Administrador(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }

    public void agregarLibro(Libro libro) {
        biblioteca.agregarLibro(libro);
    }

    public void eliminarLibro(Libro libro) {
        biblioteca.eliminarLibro(libro);
    }

    public void actualizarLibro(Libro libro) {
        biblioteca.actualizarLibro(libro);
    }

    public void agregarUsuario(Usuario usuario) {
        biblioteca.agregarUsuario(usuario);
    }

    public void eliminarUsuario(Usuario usuario) {
        biblioteca.eliminarUsuario(usuario);
    }

    public void actualizarUsuario(Usuario usuario) {
        biblioteca.actualizarUsuario(usuario);
    }

    public void realizarPrestamo(Usuario usuario, Libro libro) {
        biblioteca.realizarPrestamo(usuario, libro);
    }

    public void realizarDevolucion(Usuario usuario, Libro libro) {
        biblioteca.realizarDevolucion(usuario, libro);
    }
    public void agregarSala(SalaEstudio salaEstudio){
        biblioteca.agregarSala(salaEstudio);
    }
    public void eliminarSala(int ID){
        biblioteca.eliminarSala(ID);
    }
}
