package uniquindio.edu.co.model;

import java.util.ArrayList;
import java.util.List;

public class Cine {

    private String nombreComercial;
    private String nit;
    private String direccion;

    // Listas que representan las relaciones del cine.
    private List<Pelicula> listPeliculas;
    private List<Sala> listSalas;
    private List<Funcion> listFunciones;
    private List<Persona> listPersonas;
    private List<Venta> listVentas;

    /**
     * @param nombreComercial nombre del cine
     * @param nit identificacion tributaria
     * @param direccion direccion del cine
     */
    public Cine(String nombreComercial, String nit, String direccion) {
        if (nombreComercial == null || nombreComercial.isBlank()
                || nit == null || nit.isBlank() || direccion == null || direccion.isBlank()) {
            throw new IllegalArgumentException("Complete los datos del cine.");
        }
        this.nombreComercial = nombreComercial.trim();
        this.nit = nit.trim();
        this.direccion = direccion.trim();
        this.listPeliculas = new ArrayList<>();
        this.listSalas = new ArrayList<>();
        this.listFunciones = new ArrayList<>();
        this.listPersonas = new ArrayList<>();
        this.listVentas = new ArrayList<>();
    }

    /**
     * @param codigo identificador a consultar
     * @return objeto encontrado o null
     */
    public Pelicula buscarPelicula(String codigo) {
        Pelicula encontrado = null;
        if (codigo != null) {
            for (Pelicula elemento : listPeliculas) {
                if (elemento.getCodigo().equalsIgnoreCase(codigo.trim())) {
                    encontrado = elemento;
                    break;
                }
            }
        }
        return encontrado;
    }

    /**
     * @param codigo identificador a consultar
     * @return objeto encontrado o null
     */
    public Sala buscarSala(String codigo) {
        Sala encontrado = null;
        if (codigo != null) {
            for (Sala elemento : listSalas) {
                if (elemento.getCodigo().equalsIgnoreCase(codigo.trim())) {
                    encontrado = elemento;
                    break;
                }
            }
        }
        return encontrado;
    }

    /**
     * @param codigo identificador a consultar
     * @return objeto encontrado o null
     */
    public Funcion buscarFuncion(String codigo) {
        Funcion encontrado = null;
        if (codigo != null) {
            for (Funcion elemento : listFunciones) {
                if (elemento.getCodigo().equalsIgnoreCase(codigo.trim())) {
                    encontrado = elemento;
                    break;
                }
            }
        }
        return encontrado;
    }

    /**
     * @param codigo identificador a consultar
     * @return objeto encontrado o null
     */
    public Venta buscarVenta(String codigo) {
        Venta encontrado = null;
        if (codigo != null) {
            for (Venta elemento : listVentas) {
                if (elemento.getCodigo().equalsIgnoreCase(codigo.trim())) {
                    encontrado = elemento;
                    break;
                }
            }
        }
        return encontrado;
    }

    /**
      * @param documento identificacion
     * @return persona encontrada o null
     */
    public Persona buscarPersona(String documento) {
        Persona encontrado = null;
        if (documento != null) {
            for (Persona persona : listPersonas) {
                if (persona.getDocumento().equals(documento.trim())) {
                    encontrado = persona;
                    break;
                }
            }
        }
        return encontrado;
    }

    /**
     * @param documento identificacion
     * @return cliente encontrado o null si no existe o es operario
     */
    public Cliente buscarCliente(String documento) {
        Persona persona = buscarPersona(documento);
        Cliente encontrado = null;
        if (persona instanceof Cliente) {
            encontrado = (Cliente) persona;
        }
        return encontrado;
    }

    /**
     * @param documento identificacion
     * @return operario encontrado o null si no existe o es cliente
     */
    public Operario buscarOperario(String documento) {
        Persona persona = buscarPersona(documento);
        Operario encontrado = null;
        if (persona instanceof Operario) {
            encontrado = (Operario) persona;
        }
        return encontrado;
    }

    /**
     * @param pelicula objeto que se registra
     */
    public void agregarPelicula(Pelicula pelicula) {
        if (pelicula == null || buscarPelicula(pelicula.getCodigo()) != null) {
            throw new IllegalArgumentException("Pelicula inexistente o codigo repetido.");
        }
        listPeliculas.add(pelicula);
    }

    /**
     * @param sala sala del complejo
     */
    public void agregarSala(Sala sala) {
        if (sala == null || buscarSala(sala.getCodigo()) != null) {
            throw new IllegalArgumentException("Sala inexistente o codigo repetido.");
        }
        listSalas.add(sala);
    }

    /**
     * @param cliente cliente nuevo
     */
    public void registrarCliente(Cliente cliente) {
        if (cliente == null || buscarPersona(cliente.getDocumento()) != null) {
            throw new IllegalArgumentException("Cliente inexistente o documento repetido.");
        }
        for (Persona persona : listPersonas) {
            if (persona instanceof Cliente) {
                Cliente existente = (Cliente) persona;
                if (existente.getCodAfiliacion().equalsIgnoreCase(cliente.getCodAfiliacion())) {
                    throw new IllegalArgumentException("Codigo de afiliacion repetido.");
                }
            }
        }
        listPersonas.add(cliente);
    }

    /**
     * @param operario operario nuevo
     */
    public void registrarOperario(Operario operario) {
        if (operario == null || buscarPersona(operario.getDocumento()) != null) {
            throw new IllegalArgumentException("Operario inexistente o documento repetido.");
        }
        for (Persona persona : listPersonas) {
            if (persona instanceof Operario) {
                Operario existente = (Operario) persona;
                if (existente.getCodEmpleado().equalsIgnoreCase(operario.getCodEmpleado())) {
                    throw new IllegalArgumentException("Codigo de empleado repetido.");
                }
            }
        }
        listPersonas.add(operario);
    }

    /**

     * @param funcion funcion nueva
     */
    public void agregarFuncion(Funcion funcion) {
        if (funcion == null || buscarFuncion(funcion.getCodigo()) != null) {
            throw new IllegalArgumentException("Funcion inexistente o codigo repetido.");
        }
        if (!listPeliculas.contains(funcion.getPelicula()) || !listSalas.contains(funcion.getSala())) {
            throw new IllegalArgumentException("Registre primero la pelicula y la sala.");
        }
        for (Funcion existente : listFunciones) {
            if (funcion.seSuperpone(existente)) {
                throw new IllegalArgumentException("Cruce de horario con la funcion " + existente.getCodigo());
            }
        }
        listFunciones.add(funcion);
    }

    /**.
     * @param titulo titulo que se desea consultar
     * @return lista de coincidencias, vacia si no existen
     */
    public List<Funcion> obtenerFuncionesPorPelicula(String titulo) {
        List<Funcion> encontradas = new ArrayList<>();
        if (titulo != null) {
            for (Funcion funcion : listFunciones) {
                if (funcion.getPelicula().getTitulo().equalsIgnoreCase(titulo.trim())) {
                    encontradas.add(funcion);
                }
            }
        }
        return encontradas;
    }

    /**
     * @param codigo identificador de boleta
     * @return true si ya fue usado
     */
    private boolean existeCodigoBoleta(String codigo) {
        for (Venta venta : listVentas) {
            for (Boleta boleta : venta.getListBoletas()) {
                if (boleta.codigo().equalsIgnoreCase(codigo)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @param venta venta preparada con una o mas boletas
     */
    public void registrarVenta(Venta venta) {
        if (venta == null || buscarVenta(venta.getCodigo()) != null) {
            throw new IllegalArgumentException("Venta inexistente o codigo repetido.");
        }
        if (buscarCliente(venta.getCliente().getDocumento()) != venta.getCliente()
                || buscarOperario(venta.getOperario().getDocumento()) != venta.getOperario()
                || buscarFuncion(venta.getFuncion().getCodigo()) != venta.getFuncion()) {
            throw new IllegalArgumentException("Registre primero al cliente, operario y funcion.");
        }
        List<Boleta> boletas = venta.getListBoletas();
        if (boletas.isEmpty()) {
            throw new IllegalArgumentException("La venta debe incluir al menos una boleta.");
        }
        Funcion funcion = venta.getFuncion();
        if (funcion.cantidadBoletasVendidas() + boletas.size() > funcion.getSala().getCapacidadMax()) {
            throw new IllegalArgumentException("La venta supera la capacidad disponible.");
        }
        for (Boleta boleta : boletas) {
            if (existeCodigoBoleta(boleta.codigo())) {
                throw new IllegalArgumentException("Codigo de boleta ya vendido: " + boleta.codigo());
            }
            if (funcion.asientoOcupado(boleta.fila(), boleta.asiento())) {
                throw new IllegalArgumentException("Asiento ya vendido: " + boleta.fila() + boleta.asiento());
            }
        }
        // Solo despues de todas las validaciones se modifican las listas.
        listVentas.add(venta);
        funcion.vincularVenta(venta);
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public String getNit() {
        return nit;
    }

    public String getDireccion() {
        return direccion;
    }

    public List<Pelicula> getListPeliculas() {
        return new ArrayList<>(listPeliculas);
    }

    public List<Sala> getListSalas() {
        return new ArrayList<>(listSalas);
    }

    public List<Funcion> getListFunciones() {
        return new ArrayList<>(listFunciones);
    }

    public List<Persona> getListPersonas() {
        return new ArrayList<>(listPersonas);
    }

    public List<Venta> getListVentas() {
        return new ArrayList<>(listVentas);
    }

    @Override
    public String toString() {
        return nombreComercial + " - NIT " + nit + " - " + direccion;
    }
}
