
package uniquindio.edu.co.model;

import java.time.LocalDate;

public class Operario extends Persona {

    private String codEmpleado;
    private LocalDate fechaContrato;
    private double salario;

    /**
     * @param documento identificacion
     * @param nombre nombre completo
     * @param correo correo electronico
     * @param telefono telefono de contacto
     * @param codEmpleado codigo de empleado
     * @param fechaContrato fecha de contratacion
     * @param salario salario basico
     */
    public Operario(String documento, String nombre, String correo, String telefono,
                    String codEmpleado, LocalDate fechaContrato, double salario) {
        super(documento, nombre, correo, telefono);
        if (codEmpleado == null || codEmpleado.isBlank() || fechaContrato == null
                || !Double.isFinite(salario) || salario < 0) {
            throw new IllegalArgumentException("Datos del operario invalidos.");
        }
        this.codEmpleado = codEmpleado.trim();
        this.fechaContrato = fechaContrato;
        this.salario = salario;
    }

    public String getCodEmpleado() {
        return codEmpleado;
    }

    public LocalDate getFechaContrato() {
        return fechaContrato;
    }

    public double getSalario() {
        return salario;
    }

    @Override
    public String toString() {
        return "Operario{" + super.toString() + ", empleado=" + codEmpleado
                + ", fechaContrato=" + fechaContrato + ", salario=" + salario + "}";
    }
}
