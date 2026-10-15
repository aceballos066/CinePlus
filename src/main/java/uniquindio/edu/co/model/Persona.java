package uniquindio.edu.co.model;

public abstract class Persona {

    // Atributos comunes a clientes y operarios.
    private String documento;
    private String nombre;
    private String correo;
    private String telefono;

    /**
     * Inicializa los datos comunes de una persona.
     *
     * @param documento identificacion
     * @param nombre    nombre completo
     * @param correo    correo electronico
     * @param telefono  telefono de contacto
     */
    public Persona(String documento, String nombre, String correo, String telefono) {
        if (documento == null || documento.isBlank() || nombre == null || nombre.isBlank()
                || correo == null || correo.isBlank() || telefono == null || telefono.isBlank()) {
            throw new IllegalArgumentException("Complete los datos de la persona.");
        }
        this.documento = documento.trim();
        this.nombre = nombre.trim();
        this.correo = correo.trim();
        this.telefono = telefono.trim();
    }

    public String getDocumento() {
        return documento;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public String getTelefono() {
        return telefono;
    }

    @Override
    public String toString() {
        return "documento=" + documento + ", nombre=" + nombre
                + ", correo=" + correo + ", telefono=" + telefono;
    }
}
