
package uniquindio.edu.co;

import uniquindio.edu.co.model.Boleta;
import uniquindio.edu.co.model.Cine;
import uniquindio.edu.co.model.Cliente;
import uniquindio.edu.co.model.Funcion;
import uniquindio.edu.co.model.Genero;
import uniquindio.edu.co.model.Operario;
import uniquindio.edu.co.model.Pelicula;
import uniquindio.edu.co.model.Persona;
import uniquindio.edu.co.model.Sala;
import uniquindio.edu.co.model.TipoSala;
import uniquindio.edu.co.model.Venta;
import javax.swing.JOptionPane;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class Main {

    private static String leer(String mensaje) {
        String dato = JOptionPane.showInputDialog(mensaje);
        if (dato == null || dato.isBlank()) {
            throw new IllegalArgumentException("Operacion cancelada o dato vacio. No se guardo la operacion.");
        }
        return dato.trim();
    }

    public static void main(String[] args) {
        Cine cine = new Cine("CinePlus", "900123456", "Armenia, Quindio");
        int opcion = -1;
        while (opcion != 0) {
            try {
                String entrada = JOptionPane.showInputDialog("CINEPLUS\n"
                        + "1. Registrar pelicula\n"
                        + "2. Consultar peliculas\n"
                        + "3. Registrar sala\n"
                        + "4. Registrar cliente\n"
                        + "5. Registrar operario\n"
                        + "6. Programar funcion\n"
                        + "7. Realizar venta de boletas\n"
                        + "8. Consultar venta y total\n"
                        + "9. Consultar ocupacion de funcion\n"
                        + "10. Consultar funciones por pelicula\n"
                        + "11. Consultar ingresos de funcion\n"
                        + "12. Consultar personas, salas y funciones\n"
                        + "0. Salir");
                if (entrada == null) {
                    opcion = 0;
                } else {
                    opcion = Integer.parseInt(entrada.trim());
                }

                if (opcion == 1) {
                    String codigo = leer("Codigo de pelicula:");
                    String titulo = leer("Titulo:");
                    int duracion = Integer.parseInt(leer("Duracion en minutos:"));
                    int seleccion = Integer.parseInt(leer(
                            "Genero: 1. Accion  2. Comedia  3. Drama  4. Terror  5. Animacion"));
                    Genero genero;
                    if (seleccion == 1) {
                        genero = Genero.ACCION;
                    } else if (seleccion == 2) {
                        genero = Genero.COMEDIA;
                    } else if (seleccion == 3) {
                        genero = Genero.DRAMA;
                    } else if (seleccion == 4) {
                        genero = Genero.TERROR;
                    } else if (seleccion == 5) {
                        genero = Genero.ANIMACION;
                    } else {
                        throw new IllegalArgumentException("Genero incorrecto.");
                    }
                    String clasificacion = leer("Clasificacion por edad, por ejemplo: Mayores de 12");
                    Pelicula pelicula = new Pelicula(codigo, titulo, duracion, genero, clasificacion);
                    cine.agregarPelicula(pelicula);
                    JOptionPane.showMessageDialog(null, "Pelicula registrada.");
                } else if (opcion == 2) {
                    String mensaje = "CATALOGO DE PELICULAS\n";
                    for (Pelicula pelicula : cine.getListPeliculas()) {
                        mensaje += pelicula.toString() + "\n";
                    }
                    if (cine.getListPeliculas().isEmpty()) {
                        mensaje += "Todavia no hay peliculas.";
                    }
                    JOptionPane.showMessageDialog(null, mensaje);
                } else if (opcion == 3) {
                    String codigo = leer("Codigo de sala:");
                    String nombre = leer("Nombre de sala:");
                    int filas = Integer.parseInt(leer("Cantidad de filas (A, B, C...):"));
                    int columnas = Integer.parseInt(leer("Asientos por fila:"));
                    int capacidad = Integer.parseInt(leer("Capacidad maxima (filas por columnas):"));
                    int seleccion = Integer.parseInt(leer("Tipo: 1. Tradicional  2. VIP  3. 3D"));
                    TipoSala tipo;
                    if (seleccion == 1) {
                        tipo = TipoSala.TRADICIONAL;
                    } else if (seleccion == 2) {
                        tipo = TipoSala.VIP;
                    } else if (seleccion == 3) {
                        tipo = TipoSala.TRES_D;
                    } else {
                        throw new IllegalArgumentException("Tipo de sala incorrecto.");
                    }
                    Sala sala = new Sala(codigo, nombre, capacidad, filas, columnas, tipo);
                    cine.agregarSala(sala);
                    JOptionPane.showMessageDialog(null, "Sala registrada.");
                } else if (opcion == 4) {
                    String documento = leer("Documento del cliente:");
                    String nombre = leer("Nombre completo:");
                    String correo = leer("Correo:");
                    String telefono = leer("Telefono:");
                    String afiliacion = leer("Codigo de afiliacion:");
                    double descuento = Double.parseDouble(leer("Descuento de 0 a 100 (10 equivale a 10%):"));
                    Cliente cliente = new Cliente(documento, nombre, correo, telefono, afiliacion, descuento);
                    cine.registrarCliente(cliente);
                    JOptionPane.showMessageDialog(null, "Cliente registrado.");
                } else if (opcion == 5) {
                    String documento = leer("Documento del operario:");
                    String nombre = leer("Nombre completo:");
                    String correo = leer("Correo:");
                    String telefono = leer("Telefono:");
                    String empleado = leer("Codigo de empleado:");
                    LocalDate fecha = LocalDate.parse(leer("Fecha de contratacion AAAA-MM-DD:"));
                    double salario = Double.parseDouble(leer("Salario basico:"));
                    Operario operario = new Operario(documento, nombre, correo, telefono, empleado, fecha, salario);
                    cine.registrarOperario(operario);
                    JOptionPane.showMessageDialog(null, "Operario registrado.");
                } else if (opcion == 6) {
                    String codigo = leer("Codigo de funcion:");
                    Pelicula pelicula = cine.buscarPelicula(leer("Codigo de pelicula registrada:"));
                    Sala sala = cine.buscarSala(leer("Codigo de sala registrada:"));
                    if (pelicula == null || sala == null) {
                        throw new IllegalArgumentException("La pelicula o la sala no existe.");
                    }
                    LocalDate fecha = LocalDate.parse(leer("Fecha de presentacion AAAA-MM-DD:"));
                    LocalTime hora = LocalTime.parse(leer("Hora de inicio HH:MM, formato 24 horas:"));
                    double precio = Double.parseDouble(leer("Precio base de la boleta:"));
                    Funcion funcion = new Funcion(codigo, fecha, hora, precio, pelicula, sala);
                    cine.agregarFuncion(funcion);
                    JOptionPane.showMessageDialog(null, "Funcion programada.");
                } else if (opcion == 7) {
                    String codigo = leer("Codigo de venta:");
                    LocalDate fecha = LocalDate.parse(leer("Fecha de venta AAAA-MM-DD:"));
                    Cliente cliente = cine.buscarCliente(leer("Documento del cliente:"));
                    Operario operario = cine.buscarOperario(leer("Documento del operario:"));
                    Funcion funcion = cine.buscarFuncion(leer("Codigo de funcion:"));
                    if (cliente == null || operario == null || funcion == null) {
                        throw new IllegalArgumentException("Cliente, operario o funcion no registrados.");
                    }
                    int cantidad = Integer.parseInt(leer("Cuantas boletas va a comprar:"));
                    if (cantidad < 1 || cantidad > funcion.getSala().getCapacidadMax()
                            - funcion.cantidadBoletasVendidas()) {
                        throw new IllegalArgumentException("Cantidad invalida o superior al cupo disponible.");
                    }
                    Venta venta = new Venta(codigo, fecha, cliente, operario, funcion);
                    double valor = funcion.valorBoleta(cliente);
                    for (int i = 0; i < cantidad; i++) {
                        String codigoBoleta = leer("Codigo unico de la boleta " + (i + 1) + ":");
                        String fila = leer("Fila del asiento (A, B, C...):");
                        int asiento = Integer.parseInt(leer("Numero del asiento:"));
                        Boleta boleta = new Boleta(codigoBoleta, fila, asiento, valor);
                        venta.agregarBoleta(boleta);
                    }
                    cine.registrarVenta(venta);
                    JOptionPane.showMessageDialog(null, "Venta registrada.\n" + venta.toString());
                } else if (opcion == 8) {
                    Venta venta = cine.buscarVenta(leer("Codigo de venta:"));
                    if (venta == null) {
                        JOptionPane.showMessageDialog(null, "La venta no existe.");
                    } else {
                        JOptionPane.showMessageDialog(null, venta.toString());
                    }
                } else if (opcion == 9) {
                    Funcion funcion = cine.buscarFuncion(leer("Codigo de funcion:"));
                    if (funcion == null) {
                        JOptionPane.showMessageDialog(null, "La funcion no existe.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Boletas vendidas: " + funcion.cantidadBoletasVendidas()
                                + "\nCapacidad: " + funcion.getSala().getCapacidadMax()
                                + "\nOcupacion: " + funcion.calcularOcupacion() + "%");
                    }
                } else if (opcion == 10) {
                    String titulo = leer("Titulo de la pelicula:");
                    String mensaje = "FUNCIONES\n";
                    for (Funcion funcion : cine.obtenerFuncionesPorPelicula(titulo)) {
                        mensaje += funcion.toString() + "\n";
                    }
                    if (cine.obtenerFuncionesPorPelicula(titulo).isEmpty()) {
                        mensaje += "No hay funciones para ese titulo.";
                    }
                    JOptionPane.showMessageDialog(null, mensaje);
                } else if (opcion == 11) {
                    Funcion funcion = cine.buscarFuncion(leer("Codigo de funcion:"));
                    if (funcion == null) {
                        JOptionPane.showMessageDialog(null, "La funcion no existe.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Ingresos: " + funcion.calcularIngresos());
                    }
                } else if (opcion == 12) {
                    String mensaje = cine.toString() + "\n\nPERSONAS\n";
                    for (Persona persona : cine.getListPersonas()) {
                        mensaje += persona.toString() + "\n";
                    }
                    mensaje += "\nSALAS\n";
                    for (Sala sala : cine.getListSalas()) {
                        mensaje += sala.toString() + "\n";
                    }
                    mensaje += "\nFUNCIONES\n";
                    for (Funcion funcion : cine.getListFunciones()) {
                        mensaje += funcion.toString() + "\n";
                    }
                    JOptionPane.showMessageDialog(null, mensaje);
                } else if (opcion != 0) {
                    JOptionPane.showMessageDialog(null, "Seleccione una opcion del menu.");
                }
            } catch (DateTimeParseException error) {
                JOptionPane.showMessageDialog(null, "Fecha u hora invalida. Use AAAA-MM-DD y HH:MM.");
            } catch (IllegalArgumentException error) {
                JOptionPane.showMessageDialog(null, error.getMessage());
            }
        }
    }
}
