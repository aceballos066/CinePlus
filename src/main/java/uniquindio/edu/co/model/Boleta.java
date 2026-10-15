package uniquindio.edu.co.model;

import java.util.Locale;

/**
 * @param codigo identificador unico
 * @param fila letra de la fila
 * @param asiento numero del asiento
 * @param valorPagado precio final individual
 */
public record Boleta(String codigo, String fila, int asiento, double valorPagado) {

    /** Valida y normaliza los datos antes de construir el record. */
    public Boleta {
        if (codigo == null || codigo.isBlank() || fila == null || fila.isBlank()
                || asiento <= 0 || !Double.isFinite(valorPagado) || valorPagado < 0) {
            throw new IllegalArgumentException("Datos de la boleta invalidos.");
        }
        codigo = codigo.trim();
        fila = fila.trim().toUpperCase(Locale.ROOT);
    }
}
