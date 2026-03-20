package tienda;

import listaProductos.NodoProducto;

public class ArbolProductos {

    private NodoProducto raiz;

    // Constructor
    public ArbolProductos() {
        this.raiz = null;
    }

    // Verificar si el árbol está vacío
    public boolean estaVacio() {
        return raiz == null;
    }

    // Obtener la raíz
    public NodoProducto getRaiz() {
        return raiz;
    }

    // Método público para insertar un producto
    public void insertar(NodoProducto producto) {
        if (producto == null) {
            System.out.println("Error: No se puede insertar un producto nulo");
            return;
        }

        // Verificar si ya existe un producto con el mismo ID
        if (buscarPorId(producto.getId()) != null) {
            System.out.println("Error: Ya existe un producto con el ID: " + producto.getId());
            return;
        }

        raiz = insertarRecursivo(raiz, producto);
        System.out.println("Producto insertado correctamente en el inventario");
    }

    // Método recursivo para insertar en el ABB
    private NodoProducto insertarRecursivo(NodoProducto actual, NodoProducto nuevo) {
        if (actual == null) {
            return nuevo;
        }

        // Comparar por ID (String) para determinar la posición en el árbol
        if (nuevo.getId().compareToIgnoreCase(actual.getId()) < 0) {
            actual.setIzquierdo(insertarRecursivo(actual.getIzquierdo(), nuevo));
        } else if (nuevo.getId().compareToIgnoreCase(actual.getId()) > 0) {
            actual.setDerecho(insertarRecursivo(actual.getDerecho(), nuevo));
        }

        return actual;
    }

    // Método público para buscar un producto por ID
    public NodoProducto buscarPorId(String id) {
        if (id == null || id.trim().isEmpty()) {
            return null;
        }
        return buscarRecursivo(raiz, id);
    }

    // Método recursivo para buscar en el ABB
    private NodoProducto buscarRecursivo(NodoProducto actual, String id) {
        if (actual == null) {
            return null;
        }

        int comparacion = id.compareToIgnoreCase(actual.getId());

        if (comparacion == 0) {
            return actual;
        } else if (comparacion < 0) {
            return buscarRecursivo(actual.getIzquierdo(), id);
        } else {
            return buscarRecursivo(actual.getDerecho(), id);
        }
    }

    // Mostrar todos los productos en orden (inorden)
    public void mostrarInorden() {
        if (estaVacio()) {
            System.out.println("El inventario está vacío");
            return;
        }
        System.out.println("===== INVENTARIO DE PRODUCTOS =====");
        mostrarInordenRecursivo(raiz);
        System.out.println("===================================");
    }

    private void mostrarInordenRecursivo(NodoProducto actual) {
        if (actual != null) {
            mostrarInordenRecursivo(actual.getIzquierdo());
            System.out.println(actual);
            mostrarInordenRecursivo(actual.getDerecho());
        }
    }

    // Mostrar productos en preorden
    public void mostrarPreorden() {
        if (estaVacio()) {
            System.out.println("El inventario está vacío");
            return;
        }
        System.out.println("===== INVENTARIO DE PRODUCTOS =====");
        mostrarPreordenRecursivo(raiz);
        System.out.println("===================================");
    }

    private void mostrarPreordenRecursivo(NodoProducto actual) {
        if (actual != null) {
            System.out.println(actual);
            mostrarPreordenRecursivo(actual.getIzquierdo());
            mostrarPreordenRecursivo(actual.getDerecho());
        }
    }

    // Mostrar productos en postorden
    public void mostrarPostorden() {
        if (estaVacio()) {
            System.out.println("El inventario está vacío");
            return;
        }
        System.out.println("===== INVENTARIO DE PRODUCTOS =====");
        mostrarPostordenRecursivo(raiz);
        System.out.println("===================================");
    }

    private void mostrarPostordenRecursivo(NodoProducto actual) {
        if (actual != null) {
            mostrarPostordenRecursivo(actual.getIzquierdo());
            mostrarPostordenRecursivo(actual.getDerecho());
            System.out.println(actual);
        }
    }

    // Eliminar un producto por ID
    public NodoProducto eliminar(String id) {
        if (id == null || id.trim().isEmpty()) {
            System.out.println("ID inválido para eliminar");
            return null;
        }

        NodoProducto productoEliminado = buscarPorId(id);
        if (productoEliminado == null) {
            System.out.println("No se encontró producto con ID: " + id);
            return null;
        }

        raiz = eliminarRecursivo(raiz, id);
        System.out.println("Producto eliminado correctamente");
        return productoEliminado;
    }

    private NodoProducto eliminarRecursivo(NodoProducto actual, String id) {
        if (actual == null) {
            return null;
        }

        int comparacion = id.compareToIgnoreCase(actual.getId());

        if (comparacion < 0) {
            actual.setIzquierdo(eliminarRecursivo(actual.getIzquierdo(), id));
        } else if (comparacion > 0) {
            actual.setDerecho(eliminarRecursivo(actual.getDerecho(), id));
        } else {
            // Caso 1: Nodo hoja (sin hijos)
            if (actual.getIzquierdo() == null && actual.getDerecho() == null) {
                return null;
            }
            // Caso 2: Un solo hijo
            else if (actual.getIzquierdo() == null) {
                return actual.getDerecho();
            } else if (actual.getDerecho() == null) {
                return actual.getIzquierdo();
            }
            // Caso 3: Dos hijos - encontrar el sucesor inorden (mínimo del subárbol derecho)
            else {
                NodoProducto sucesor = encontrarMinimo(actual.getDerecho());
                // Copiar los datos del sucesor al nodo actual
                actual.setId(sucesor.getId());
                actual.setNombre(sucesor.getNombre());
                actual.setCategoria(sucesor.getCategoria());
                actual.setPrecio(sucesor.getPrecio());
                actual.setCantidad(sucesor.getCantidad());
                // Eliminar el sucesor del subárbol derecho
                actual.setDerecho(eliminarRecursivo(actual.getDerecho(), sucesor.getId()));
            }
        }

        return actual;
    }

    // Encontrar el nodo mínimo (más a la izquierda)
    private NodoProducto encontrarMinimo(NodoProducto actual) {
        while (actual.getIzquierdo() != null) {
            actual = actual.getIzquierdo();
        }
        return actual;
    }

    // Contar el número total de productos en el inventario
    public int contarProductos() {
        return contarProductosRecursivo(raiz);
    }

    private int contarProductosRecursivo(NodoProducto actual) {
        if (actual == null) {
            return 0;
        }
        return 1 + contarProductosRecursivo(actual.getIzquierdo()) + contarProductosRecursivo(actual.getDerecho());
    }

    // Verificar si un producto existe por ID
    public boolean existeProducto(String id) {
        return buscarPorId(id) != null;
    }

    // Actualizar cantidad de un producto
    public boolean actualizarCantidad(String id, int nuevaCantidad) {
        NodoProducto producto = buscarPorId(id);
        if (producto != null) {
            producto.setCantidad(nuevaCantidad);
            return true;
        }
        return false;
    }

    // Obtener lista de productos que están por debajo del stock mínimo
    public void mostrarProductosBajoStock(int stockMinimo) {
        if (estaVacio()) {
            System.out.println("El inventario está vacío");
            return;
        }
        System.out.println("===== PRODUCTOS CON STOCK BAJO (menor a " + stockMinimo + ") =====");
        mostrarProductosBajoStockRecursivo(raiz, stockMinimo);
        System.out.println("================================================================");
    }

    private void mostrarProductosBajoStockRecursivo(NodoProducto actual, int stockMinimo) {
        if (actual != null) {
            mostrarProductosBajoStockRecursivo(actual.getIzquierdo(), stockMinimo);
            if (actual.getCantidad() < stockMinimo) {
                System.out.println(actual);
            }
            mostrarProductosBajoStockRecursivo(actual.getDerecho(), stockMinimo);
        }
    }
}