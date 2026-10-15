
package uniquindio.edu.co.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Funcion {

    private String codigo;
    private LocalDate fecha;
    private LocalTime horaInicio;
    private double precioBase;

    private Pelicula pelicula;
    private Sala sala;
    private List<Venta> listVentas;

    public Funcion(String codigo, LocalDate fecha, LocalTime horaInicio,
                   double precioBase, Pelicula pelicula, Sala sala) {
        if (codigo == null || codigo.isBlank() || fecha == null || horaInicio == null
                || !Double.isFinite(precioBase) || precioBase <= 0 || pelicula == null || sala == null) {
            throw new IllegalArgumentException("Datos de la funcion invalidos.");
        }
        this.codigo = codigo.trim();
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.precioBase = precioBase;
        this.pelicula = pelicula;
        this.sala = sala;
        this.listVentas = new ArrayList<>();
    }

    public double valorBoleta(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("Seleccione un cliente.");
        }
        double precioConRecargo = precioBase + precioBase * sala.obtenerRecargo();
        double valor = precioConRecargo - precioConRecargo * cliente.getDescuento() / 100;
        return Math.round(valor * 100) / 100.0;
    }

    public boolean seSuperpone(Funcion otra) {
        if (otra == null || !sala.getCodigo().equalsIgnoreCase(otra.getSala().getCodigo())) {
            return false;
        }
        LocalDateTime inicio = LocalDateTime.of(fecha, horaInicio);
        LocalDateTime fin = inicio.plusMinutes(pelicula.getDuracion());
        LocalDateTime otroInicio = LocalDateTime.of(otra.getFecha(), otra.getHoraInicio());
        LocalDateTime otroFin = otroInicio.plusMinutes(otra.getPelicula().getDuracion());
        return inicio.isBefore(otroFin) && otroInicio.isBefore(fin);
    }

    public boolean asientoOcupado(String fila, int asiento) {
        for (Venta venta : listVentas) {
            for (Boleta boleta : venta.getListBoletas()) {
                if (boleta.fila().equalsIgnoreCase(fila.trim()) && boleta.asiento() == asiento) {
                    return true;
                }
            }
        }
        return false;
    }

    public int cantidadBoletasVendidas() {
        int cantidad = 0;
        for (Venta venta : listVentas) {
            cantidad += venta.getListBoletas().size();
        }
        return cantidad;
    }

    public double calcularOcupacion() {
        return cantidadBoletasVendidas() * 100.0 / sala.getCapacidadMax();
    }

    public double calcularIngresos() {
        double ingresos = 0;
        for (Venta venta : listVentas) {
            ingresos += venta.calcularTotal();
        }
        return Math.round(ingresos * 100) / 100.0;
    }

    void vincularVenta(Venta venta) {
        listVentas.add(venta);
    }

    public String getCodigo() {
        return codigo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public Sala getSala() {
        return sala;
    }

    public List<Venta> getListVentas() {
        return new ArrayList<>(listVentas);
    }

    @Override
    public String toString() {
        return codigo + " - " + pelicula.getTitulo() + " - sala=" + sala.getCodigo()
                + " - " + fecha + " " + horaInicio + " - precio base=" + precioBase;
    }
}
