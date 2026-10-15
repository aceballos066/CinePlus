package uniquindio.edu.co.model;


import java.util.Locale;

public class Sala {

    private String codigo;
    private String nombre;
    private int capacidadMax;
    private int filas;
    private int columnas;
    private TipoSala tipo;

    /**
     * @param codigo identificador unico
     * @param nombre nombre de sala
     * @param capacidadMax debe ser igual a filas por columnas
     * @param filas numero de filas, identificadas como A, B, ..., Z, AA...
     * @param columnas asientos por fila, numerados desde 1
     * @param tipo tipo de sala
     */
    public Sala(String codigo, String nombre, int capacidadMax,
                int filas, int columnas, TipoSala tipo) {
        if (codigo == null || codigo.isBlank() || nombre == null || nombre.isBlank()
                || filas <= 0 || columnas <= 0 || capacidadMax <= 0 || tipo == null
                || (long) filas * columnas != capacidadMax) {
            throw new IllegalArgumentException("Datos invalidos: capacidad debe ser filas por columnas.");
        }
        this.codigo = codigo.trim();
        this.nombre = nombre.trim();
        this.capacidadMax = capacidadMax;
        this.filas = filas;
        this.columnas = columnas;
        this.tipo = tipo;
    }

    /**
     * Obtiene el porcentaje de recargo expresado como decimal.
     * @return 0, 0.30 o 0.20
     */
    public double obtenerRecargo() {
        double recargo = 0;
        if (tipo == TipoSala.VIP) {
            recargo = 0.30;
        } else if (tipo == TipoSala.TRES_D) {
            recargo = 0.20;
        }
        return recargo;
    }

    /**
     * Verifica que la fila y el numero existan en la sala.
     * @param fila letra de fila, por ejemplo A
     * @param asiento numero desde 1 hasta columnas
     * @return true si la posicion es valida
     */
    public boolean esAsientoValido(String fila, int asiento) {
        if (fila == null || fila.isBlank() || asiento < 1 || asiento > columnas) {
            return false;
        }
        String letras = fila.trim().toUpperCase(Locale.ROOT);
        long numeroFila = 0;
        for (int i = 0; i < letras.length(); i++) {
            char letra = letras.charAt(i);
            if (letra < 'A' || letra > 'Z') {
                return false;
            }
            numeroFila = numeroFila * 26 + letra - 'A' + 1;
            if (numeroFila > filas) {
                return false;
            }
        }
        return numeroFila >= 1;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCapacidadMax() {
        return capacidadMax;
    }

    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public TipoSala getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        return codigo + " - " + nombre + " - " + tipo + " - capacidad=" + capacidadMax
                + " - filas=" + filas + " - columnas=" + columnas;
    }
}

