package uniquindio.edu.co.model;
public class Pelicula {

    private String codigo;
    private String titulo;
    private int duracion;
    private Genero genero;
    private String clasificacion;

    /**
     * Crea una pelicula del catalogo.
     * @param codigo identificador unico
     * @param titulo titulo de la pelicula
     * @param duracion duracion positiva en minutos
     * @param genero genero cinematografico
     * @param clasificacion clasificacion por edad
     */
    public Pelicula(String codigo, String titulo, int duracion,
                    Genero genero, String clasificacion) {
        if (codigo == null || codigo.isBlank() || titulo == null || titulo.isBlank()
                || duracion <= 0 || genero == null || clasificacion == null || clasificacion.isBlank()) {
            throw new IllegalArgumentException("Datos de la pelicula invalidos.");
        }
        this.codigo = codigo.trim();
        this.titulo = titulo.trim();
        this.duracion = duracion;
        this.genero = genero;
        this.clasificacion = clasificacion.trim();
    }

    public String getCodigo() {
        return codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getDuracion() {
        return duracion;
    }

    public Genero getGenero() {
        return genero;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    @Override
    public String toString() {
        return codigo + " - " + titulo + " - " + duracion + " minutos - "
                + genero + " - " + clasificacion;
    }
}
