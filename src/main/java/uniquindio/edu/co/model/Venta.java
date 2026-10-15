
package uniquindio.edu.co.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Venta {

    private String codigo;
    private LocalDate fecha;

    private Cliente cliente;
    private Operario operario;
    private Funcion funcion;
    private List<Boleta> listBoletas;

    public Venta(String codigo, LocalDate fecha, Cliente cliente,
                 Operario operario, Funcion funcion) {
        if (codigo == null || codigo.isBlank() || fecha == null || cliente == null
                || operario == null || funcion == null) {
            throw new IllegalArgumentException("Datos de la venta incompletos.");
        }
        if (fecha.isAfter(funcion.getFecha())) {
            throw new IllegalArgumentException("La venta no puede ser posterior a la funcion.");
        }
        this.codigo = codigo.trim();
        this.fecha = fecha;
        this.cliente = cliente;
        this.operario = operario;
        this.funcion = funcion;
        this.listBoletas = new ArrayList<>();
    }

    public void agregarBoleta(Boleta boleta) {
        if (funcion.getListVentas().contains(this)) {
            throw new IllegalArgumentException("La venta ya fue registrada y no puede modificarse.");
        }
        if (boleta == null || !funcion.getSala().esAsientoValido(boleta.fila(), boleta.asiento())) {
            throw new IllegalArgumentException("El asiento no existe en la sala.");
        }
        if (funcion.asientoOcupado(boleta.fila(), boleta.asiento())) {
            throw new IllegalArgumentException("El asiento ya esta vendido para esta funcion.");
        }
        for (Boleta existente : listBoletas) {
            if (existente.codigo().equalsIgnoreCase(boleta.codigo())) {
                throw new IllegalArgumentException("Codigo de boleta repetido en la venta.");
            }
            if (existente.fila().equals(boleta.fila()) && existente.asiento() == boleta.asiento()) {
                throw new IllegalArgumentException("El asiento esta repetido dentro de la venta.");
            }
        }
        if (funcion.cantidadBoletasVendidas() + listBoletas.size() >= funcion.getSala().getCapacidadMax()) {
            throw new IllegalArgumentException("No hay capacidad disponible.");
        }
        if (Double.compare(boleta.valorPagado(), funcion.valorBoleta(cliente)) != 0) {
            throw new IllegalArgumentException("El valor pagado no coincide con la tarifa del cliente.");
        }
        listBoletas.add(boleta);
    }

    public double calcularTotal() {
        double total = 0;
        for (Boleta boleta : listBoletas) {
            total += boleta.valorPagado();
        }
        return Math.round(total * 100) / 100.0;
    }

    public String getCodigo() {
        return codigo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Operario getOperario() {
        return operario;
    }

    public Funcion getFuncion() {
        return funcion;
    }

    public List<Boleta> getListBoletas() {
        return new ArrayList<>(listBoletas);
    }

    @Override
    public String toString() {
        return "Venta " + codigo + " - " + fecha + " - cliente=" + cliente.getNombre()
                + " - operario=" + operario.getNombre() + " - funcion=" + funcion.getCodigo()
                + "\nBoletas: " + listBoletas + "\nTotal: " + calcularTotal();
    }
}
