
package uniquindio.edu.co.model;

public class Cliente extends Persona {

    private String codAfiliacion;
    private double descuento;

    /**
     * Crea un cliente e inicializa su parte heredada con super.
     * @param documento identificacion
     * @param nombre nombre completo
     * @param correo correo electronico
     * @param telefono telefono de contacto
     * @param codAfiliacion codigo de afiliacion
     * @param descuento porcentaje entre 0 y 100; 10 significa 10 por ciento
     */
    public Cliente(String documento, String nombre, String correo, String telefono,
                   String codAfiliacion, double descuento) {
        super(documento, nombre, correo, telefono);
        if (codAfiliacion == null || codAfiliacion.isBlank()) {
            throw new IllegalArgumentException("Ingrese el codigo de afiliacion.");
        }
        if (!Double.isFinite(descuento) || descuento < 0 || descuento > 100) {
            throw new IllegalArgumentException("El descuento debe estar entre 0 y 100.");
        }
        this.codAfiliacion = codAfiliacion.trim();
        this.descuento = descuento;
    }

    public String getCodAfiliacion() {
        return codAfiliacion;
    }

    public double getDescuento() {
        return descuento;
    }

    @Override
    public String toString() {
        return "Cliente{" + super.toString() + ", afiliacion=" + codAfiliacion
                + ", descuento=" + descuento + "}";
    }
}
