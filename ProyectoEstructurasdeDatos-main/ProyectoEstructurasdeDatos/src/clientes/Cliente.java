package clientes;

import listaProductos.ListaProductos;


public class Cliente {
    
    // Atributos
    private String nombre;
    private String idCliente;
    private int prioridad;
    private ListaProductos carrito;
    private Cliente siguiente;
    private String ubicacion;

    // Metodos
    // Constructor
    public Cliente(String nombre, String idCliente, int prioridad, String ubicacion) {
        this.nombre = nombre;
        this.idCliente = idCliente;
        this.prioridad = prioridad;
        carrito = new ListaProductos();
        this.siguiente = null;
        this.ubicacion = ubicacion;
        
    }
    
    
    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public ListaProductos getCarrito() {
        return carrito;
    }

    public Cliente getSiguiente() {
        return siguiente;
    }
    
    public String getUbicacion() {
        return ubicacion;
    }
    
    // Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public void setSiguiente(Cliente siguiente) {
        this.siguiente = siguiente;
    }
    
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
    
    // toString
    @Override
    public String toString() {
        String msjPrioridad;
                
        if(prioridad == 1){
            msjPrioridad = "Básico";
        } else if (prioridad == 2){
            msjPrioridad = "Afiliado";
        } else{
            msjPrioridad = "Premium";
        }

        return "Cliente: " + nombre + "\n" +
           "ID: " + idCliente + "\n" +
           "Ubicación: " + ubicacion + "\n" +
           "Prioridad: " + msjPrioridad + "\n" +
           "Carrito:\n" + carrito + "\n";
    }
}
