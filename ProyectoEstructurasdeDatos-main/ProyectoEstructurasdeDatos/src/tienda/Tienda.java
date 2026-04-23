package tienda;

import clientes.ColaClientes;
import clientes.Cliente;
import grafo.Grafo;
import grafo.ResultadoDijkstra;
import listaProductos.NodoProducto;

public class Tienda {


    private String nombre;
    private String direccion;
    private ArbolProductos inventario;
    private ColaClientes colaClientes;


    private String ubicacion;
    private Grafo grafo;


    public Tienda(String nombre, String direccion) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.inventario = new ArbolProductos();
        this.colaClientes = new ColaClientes();
        this.ubicacion = "Tienda Central";
        this.grafo = new Grafo();
        inicializarMapaBase();
    }


    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public ArbolProductos getInventario() {
        return inventario;
    }

    public ColaClientes getColaClientes() {
        return colaClientes;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public Grafo getGrafo() {
        return grafo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }



    public void agregarVertice(String nombre) {
        grafo.agregarVertice(nombre);
    }


    public void agregarArista(String origen, String destino, int peso) {
        grafo.agregarArista(origen, destino, peso);
    }


    public void mostrarMapa() {
        grafo.mostrarMapa();
    }


    public boolean hayConexionConTienda(String ubicacionCliente) {
        return grafo.estaConectado(this.ubicacion, ubicacionCliente);
    }


    public ResultadoDijkstra caminoMasCorto(String ubicacionDestino) {
        return grafo.caminoMasCorto(this.ubicacion, ubicacionDestino);
    }


    private void inicializarMapaBase() {
        System.out.println("Inicializando mapa base de entregas...");


        grafo.agregarVertice("Tienda Central");
        grafo.agregarVertice("San José");
        grafo.agregarVertice("Cartago");
        grafo.agregarVertice("Heredia");
        grafo.agregarVertice("Alajuela");
        grafo.agregarVertice("Tres Ríos");
        grafo.agregarVertice("Grecia");


        grafo.agregarArista("Tienda Central", "San José", 5);
        grafo.agregarArista("San José", "Cartago", 20);
        grafo.agregarArista("San José", "Heredia", 12);
        grafo.agregarArista("San José", "Alajuela", 18);
        grafo.agregarArista("Heredia", "Alajuela", 8);
        grafo.agregarArista("Cartago", "Tres Ríos", 7);
        grafo.agregarArista("Alajuela", "Grecia", 25);

        System.out.println("Mapa base cargado correctamente\n");
    }


    public void agregarProducto(NodoProducto producto) {
        if (producto == null) {
            System.out.println("Error: Producto nulo no puede ser agregado");
            return;
        }
        inventario.insertar(producto);
    }

    public NodoProducto buscarProducto(String id) {
        return inventario.buscarPorId(id);
    }

    public void mostrarInventario() {
        System.out.println(" TIENDA: " + nombre );
        System.out.println("Dirección: " + direccion);
        inventario.mostrarInorden();
    }

    public boolean eliminarProducto(String id) {
        NodoProducto producto = inventario.eliminar(id);
        return producto != null;
    }

    public boolean actualizarStockProducto(String id, int nuevaCantidad) {
        return inventario.actualizarCantidad(id, nuevaCantidad);
    }

    public void agregarCliente(Cliente cliente) {
        if (cliente == null) {
            System.out.println("Error: Cliente nulo no puede ser agregado");
            return;
        }
        int prioridad = cliente.getPrioridad();
        if (prioridad < 1 || prioridad > 3) {
            System.out.println("Error: Prioridad inválida. Debe ser 1 (Básico), 2 (Afiliado) o 3 (Premium)");
            return;
        }
        colaClientes.encolar(cliente);
    }

    public Cliente atenderSiguienteCliente() {
        if (colaClientes.estaVacia()) {
            System.out.println("No hay clientes en la cola para atender\n");
            return null;
        }

        Cliente clienteAtendido = colaClientes.desencolar();

        if (clienteAtendido != null) {
            System.out.println("\n ATENDIENDO CLIENTE ");
            System.out.println(clienteAtendido);
            System.out.println("==============================\n");
        }

        return clienteAtendido;
    }

    public void mostrarClienteFrente() {
        if (colaClientes.estaVacia()) {
            System.out.println("No hay clientes en espera");
            return;
        }

        System.out.println("===== COLA DE CLIENTES =====");
        Cliente clienteRegistro = colaClientes.verFrente();

        System.out.println("Nombre: " + clienteRegistro.getNombre() +
                " (ID: " + clienteRegistro.getIdCliente() + " | " +
                "Prioridad: " + colaClientes.prioridadTexto(clienteRegistro.getPrioridad()) +
                " | Productos en carrito: " + contarProductosEnCarrito(clienteRegistro) + ")");
    }

    private int contarProductosEnCarrito(Cliente cliente) {
        if (cliente.getCarrito() == null || cliente.getCarrito().estaVacia()) {
            return 0;
        }
        int contador = 0;
        listaProductos.NodoProducto actual = cliente.getCarrito().getPrimero();
        while (actual != null) {
            contador++;
            actual = actual.getSiguiente();
        }
        return contador;
    }

    public Cliente buscarClienteEnCola(String idCliente) {
        return colaClientes.buscarPorId(idCliente);
    }

    public boolean colaClientesVacia() {
        return colaClientes.estaVacia();
    }



    public void generarFactura(Cliente cliente) {
        if (cliente == null) {
            System.out.println("Error: Cliente inválido");
            return;
        }

        System.out.println("\n===== FACTURA =====");
        System.out.println("Tienda: " + nombre);
        System.out.println("Dirección: " + direccion);
        System.out.println("Fecha: " + java.time.LocalDate.now());
        System.out.println("------------------------");
        System.out.println("Cliente: " + cliente.getNombre());
        System.out.println("ID Cliente: " + cliente.getIdCliente());

        String prioridadTexto;
        if (cliente.getPrioridad() == 1) {
            prioridadTexto = "Básico";
        } else if (cliente.getPrioridad() == 2) {
            prioridadTexto = "Afiliado";
        } else {
            prioridadTexto = "Premium";
        }
        System.out.println("Tipo: " + prioridadTexto);
        System.out.println("------------------------");
        System.out.println("PRODUCTOS:");

        if (cliente.getCarrito() == null || cliente.getCarrito().estaVacia()) {
            System.out.println("El carrito está vacío");
        } else {
            listaProductos.NodoProducto actual = cliente.getCarrito().getPrimero();
            double total = 0;
            int item = 1;

            while (actual != null) {
                double subtotal = actual.getPrecio() * actual.getCantidad();
                System.out.println(item + ". " + actual.getNombre() +
                        " - ₡" + actual.getPrecio() +
                        " x " + actual.getCantidad() +
                        " = ₡" + subtotal);
                total += subtotal;
                actual = actual.getSiguiente();
                item++;
            }

            System.out.println("------------------------");
            System.out.println("TOTAL: ₡" + total);

            double descuento = 0;
            if (cliente.getPrioridad() == 2) {
                descuento = total * 0.05;
                System.out.println("Descuento afiliado (5%): -₡" + descuento);
            } else if (cliente.getPrioridad() == 3) {
                descuento = total * 0.10;
                System.out.println("Descuento premium (10%): -₡" + descuento);
            }

            if (descuento > 0) {
                System.out.println("TOTAL CON DESCUENTO: ₡" + (total - descuento));
            }
        }

        System.out.println("===== FIN FACTURA =====\n");
    }
    public String toString() {
        return "Tienda: " + nombre + "\n" +
                "Dirección: " + direccion + "\n" +
                "Ubicación en mapa: " + ubicacion + "\n" +
                "Productos en inventario: " + inventario.contarProductos() + "\n" +
                "Clientes en cola: " + (colaClientes.estaVacia() ? 0 : contarClientesEnCola());
    }

    private int contarClientesEnCola() {
        int contador = 0;
        Cliente temp = colaClientes.verFrente();
        while (temp != null) {
            contador++;
            temp = temp.getSiguiente();
        }
        return contador;
    }
}
