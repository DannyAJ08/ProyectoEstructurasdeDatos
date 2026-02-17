package listaProductos;
import java.util.ArrayList;

public class NodoProducto {
    
    // Atributos
    private String nombre;
    private String categoria;
    private String id;
    private double precio;
    private int cantidad;
    private ArrayList<String> listaImagenes;
    private NodoProducto siguiente;
    
    // Constructor

    public NodoProducto(String nombre, String categoria, String id, double precio, int cantidad) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.id = id;
        this.precio = precio;
        this.cantidad = cantidad;
        this.listaImagenes = new ArrayList<>(); // Lista para guardar las imágenes de cada libro
        this.siguiente = null;
    }
    
    // Getters

    public String getNombre() {
        return nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getId() {
        return id;
    }

    public double getPrecio() {
        return precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public ArrayList<String> getListaImagenes() {
        return listaImagenes;
    }

    public NodoProducto getSiguiente() {
        return siguiente;
    }
    
    // Setters

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setSiguiente(NodoProducto siguiente) {
        this.siguiente = siguiente;
    }
    
    // Métodos
    public void agregarImagen(String imagen){
        listaImagenes.add(imagen);
    }
    
    public void eliminarImagen(String imagen){
        listaImagenes.remove(imagen);
    }
    
    // toString

    @Override
    public String toString() {
        return "Nombre: " + nombre + "\n" +
                "Categoría: " + categoria + "\n" +
                "Id: " + id + "\n" +
                "Precio: ₡" + precio + "\n" +
                "Cantidad: " + cantidad + "\n" +
                "Imagenes: " + listaImagenes + "\n";
    }
    
}
