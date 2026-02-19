
package listaProductos;


import java.util.ArrayList;

public class ListaProductos {

    //atributos

    private NodoProducto primero;

    //metodos/constructor

    public ListaProductos(){
        this.primero = null;
    }

    //geteers


    public NodoProducto getPrimero() {
        return primero;
    }

    //Setter

    public void setPrimero(NodoProducto primero) {
        this.primero = primero;
    }

    //Operaciones

    public void insertarNodoInicio(String nombre, String categoria, String id, double precio, int cantidad, ArrayList<String> listaImagenes){
        NodoProducto nuevo = new NodoProducto(nombre, categoria, id, precio, cantidad, listaImagenes);

        nuevo.setSiguiente(primero);
        setPrimero(nuevo);
    }

    public boolean estaVacia(){
        return primero == null;
    }

    public void insertarNodoFInal(String nombre, String categoria, String id, double precio, int cantidad, ArrayList<String> listaImagenes){
        NodoProducto nuevo = new NodoProducto(nombre, categoria, id, precio, cantidad, listaImagenes);

        if (estaVacia()){
            setPrimero(nuevo);
            return;

        }

        NodoProducto temp = primero;
        while (temp.getSiguiente() != null){
            temp = temp.getSiguiente();
        }

        temp.setSiguiente((nuevo));
    }

    public NodoProducto buscar(String id){
        if (estaVacia()) {
            System.out.println("La lista esta vacia");
            return null;
        }

        NodoProducto temp = primero;
        while (temp != null && !temp.getId().equalsIgnoreCase(id)){
            temp = temp.getSiguiente();
        }

        if (temp == null){
            System.out.println("No se encontró ningun libro con la ID: " + id);
            return temp;
        }else{
            System.out.println("Libro encontrado:");
            System.out.println(temp);
        }
        return temp;
    }

    public void mostrarLista(){
        if (estaVacia()) {
            System.out.println("La lista esta vacia");
            return;
        }

        NodoProducto temp = primero;

        while (temp != null){
            System.out.println(temp);
            temp = temp.getSiguiente();
        }

    }

    public NodoProducto eliminar(String id){
        if (estaVacia()) {
            System.out.println("La lista esta vacia");
            return null;
        }
        
        if (primero.getId().equalsIgnoreCase(id)) {
        NodoProducto eliminado = primero;
        primero = primero.getSiguiente(); // El segundo pasa a ser primero
        System.out.println("El libro se eliminó correctamente");
        return eliminado;
    }

        NodoProducto temp = primero;
        NodoProducto anteriorTemp = temp;

        while (temp != null && !temp.getId().equalsIgnoreCase(id)){
            anteriorTemp = temp;
            temp = temp.getSiguiente();
        }

        if (temp == null){
            System.out.println("EL id del libro no se encontró");
        }else{
            System.out.println("El libro se eliminó correctamente");
            anteriorTemp.setSiguiente(temp.getSiguiente());
        }
        return temp;


    }
    public void modificarNombre(String id, String nuevoNombre){

    NodoProducto temp = buscar(id);  // de esta forma reutilizamos el metodo buscar

        if (temp != null){
            temp.setNombre(nuevoNombre);
        }
    }
    
    public void modificarCategoria(String id, String nuevaCategoria){

    NodoProducto temp = buscar(id);

        if (temp != null){
            temp.setCategoria(nuevaCategoria);
        }
    }
    
    public void modificarPrecio(String id, double nuevoPrecio){

    NodoProducto temp = buscar(id);

        if (temp != null){
            temp.setPrecio(nuevoPrecio);
        }
    }
    
    public void modificarCantidad(String id, int nuevaCantidad){

    NodoProducto temp = buscar(id); 

        if (temp != null){
            temp.setCantidad(nuevaCantidad);
        }
    }
}
