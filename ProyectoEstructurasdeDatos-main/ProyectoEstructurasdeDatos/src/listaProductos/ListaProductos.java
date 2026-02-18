
package listaProductos;


import java.util.ArrayList;

public class ListaProductos {

    //atributos

    private  NodoProducto primero;

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
        while (temp != null && !temp.getId().equals(id)){
            temp = temp.getSiguiente();
        }

        if (temp == null){
            System.out.println("No se encontró ningun libro con la ID: "+ id);
            return temp;
        }else{
            System.out.println("El Libro si se encontro");
            return temp;
        }

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

        NodoProducto temp = primero;
        NodoProducto anteriorTemp = temp;

        while (temp != null && !temp.getId().equals(id)){
            anteriorTemp = temp;
            temp = temp.getSiguiente();
        }

        if (temp == null){
            System.out.println("EL id del libro no se encontró");
        }else{
            System.out.println("El nombre se encontro");
            anteriorTemp.setSiguiente(temp.getSiguiente());
        }
        return temp;


    }

    public void modificar(String nuevoNombre, String nuevaCategoria, String id, double nuevoPrecio, int nuevaCantidad){
        if (estaVacia()) {
            System.out.println("La lista esta vacia");
            return;
        }

        NodoProducto temp = primero;
        while (temp != null && !temp.getId().equals(id)){
            temp = temp.getSiguiente();
        }

        if (temp==null){
            System.out.println("No se encontro ningun libro con esa id");
        }else{
            temp.setCantidad(nuevaCantidad);
            temp.setNombre(nuevoNombre);
            temp.setCategoria(nuevaCategoria);
            temp.setPrecio(nuevoPrecio);
            
        }

    }
}
