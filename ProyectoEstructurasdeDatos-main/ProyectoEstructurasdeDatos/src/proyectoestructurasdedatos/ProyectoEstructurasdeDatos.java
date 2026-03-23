package proyectoestructurasdedatos;

import listaProductos.NodoProducto;
import clientes.Cliente;
import tienda.Tienda;

import java.util.ArrayList;
import java.util.Scanner;

public class ProyectoEstructurasdeDatos {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // Scanner permite leer lo que escribe el usuario en consola
        
        Tienda tienda = new Tienda("Librería Online", "Costa Rica");
        
        int opcion; // Variable para el switch del menú principal
        boolean continuar = true; // Variable para controlar el ciclo while principal
        
        // Bucle principal del menú
        while(continuar){
            System.out.println("***** MENÚ LIBRERÍA *****\n" +
                                "1. Agregar producto al inventario\n" +
                                "2. Mostrar inventario\n" +
                                "3. Agregar cliente a la cola y comprar productos\n" +
                                "4. Ver cliente al frente\n" +
                                "5. Atender siguiente cliente\n" +
                                "0. Salir\n");

            System.out.print("Seleccione una opción: \n");
            opcion = sc.nextInt();
            sc.nextLine(); //Borrar el buffer
            
            switch(opcion){
                
                // Agregar producto al inventario
                case 1:
                    System.out.print("Nombre del libro: ");
                    String nombre = sc.nextLine();

                    System.out.print("Categoría: ");
                    String categoria = sc.nextLine();

                    String id;

                    while (true) {
                        System.out.print("ID: ");
                        id = sc.nextLine();

                        if (tienda.buscarProducto(id) != null) {
                            System.out.println("Error: ya existe un producto con ese ID");
                        } else {
                            break; // Porque el ID ya sería válido
                        }
                    }

                    System.out.print("Precio: ");
                    double precio = sc.nextDouble();

                    System.out.print("Cantidad: ");
                    int cantidad = sc.nextInt();
                    sc.nextLine();
                  
                    ArrayList<String> imagenes = new ArrayList<>(); // ArrayList que guarda las imagenes que desee agregar a cada NodoProducto

                    System.out.print("Cuántas imágenes desea agregar? ");
                    int numImg = sc.nextInt();
                    sc.nextLine();
                    
                    // Recorremos el ArrayList para guardar la imagen en cada posicion, segun la cant de imagenes que el usuario desee agregar
                    for (int i = 0; i < numImg; i++) {
                        System.out.print("Imagen " + (i+1) + ": ");
                        imagenes.add(sc.nextLine());
                    }
                    
                    //Crear el producto y agregarlo al inventario
                    NodoProducto producto = new NodoProducto (nombre, categoria, id, precio, cantidad, imagenes);
                    tienda.agregarProducto(producto);
                    
                    break;
                    
                // Mostrar inventario
                case 2:
                    tienda.mostrarInventario();
                    break;
                  
                // Agregar cliente y llenar el carrito
                case 3: 
                    System.out.println("Nombre del cliente: ");
                    String nombreCliente = sc.nextLine();
                    
                    String idCliente;

                    while (true) {
                        System.out.print("ID del cliente: ");
                        idCliente = sc.nextLine();

                        if (tienda.buscarClienteEnCola(idCliente) != null) {
                            System.out.println("Error: ya existe un cliente con ese ID");
                        } else {
                            break; // ID válido
                        }
                    }
                    
                    System.out.println("Prioridad (1 Básico, 2 Afiliado, 3 Premium): ");
                    int prioridad = sc.nextInt();
                    sc.nextLine();
                    
                    // Crear el cliente
                    Cliente cliente = new Cliente(nombreCliente, idCliente, prioridad);
                    
                    // Llenar carrito
                    boolean agregarMas = true;
                    
                    while (agregarMas){
                        System.out.println("INVENTARIO DISPONIBLE\n");
                        tienda.mostrarInventario();
                        
                        System.out.print("Ingrese el ID del producto que desea agregar: ");
                        String idProducto = sc.nextLine();
                        
                        NodoProducto prod = tienda.buscarProducto(idProducto);
                        
                        if(prod == null){
                           System.out.println("Producto no encontrado\n");
                        } else {
                            System.out.print("Cantidad: ");
                            int cant = sc.nextInt();
                            sc.nextLine();
                        
                            // Validacion de stock
                            if (cant > prod.getCantidad()) {
                                System.out.println("Error: no hay suficiente stock disponible");
                                continue; // vuelve a pedir otro producto
                            }

                            // Crear copia del producto para el carrito
                            NodoProducto copia = new NodoProducto(
                                    prod.getNombre(),
                                    prod.getCategoria(),
                                    prod.getId(),
                                    prod.getPrecio(),
                                    cant,
                                    prod.getListaImagenes()
                            );

                            // Agregar al carrito del cliente
                            cliente.getCarrito().insertarNodoFInal(
                                    copia.getNombre(),
                                    copia.getCategoria(),
                                    copia.getId(),
                                    copia.getPrecio(),
                                    copia.getCantidad(),
                                    copia.getListaImagenes()
                            );

                            System.out.println("Producto agregado al carrito\n");
                        }

                        System.out.print("¿Desea agregar otro producto? (s/n): ");
                        String resp = sc.nextLine();

                        if (resp.equalsIgnoreCase("n")) {
                            agregarMas = false;
                        }
                    }
                    // Agregar cliente a la cola
                    tienda.agregarCliente(cliente);
                    
                    break;
                
                // Ver Cola
                case 4:
                    tienda.mostrarClienteFrente();
                    break;
                
                // Atender Cliente
                case 5:
                    Cliente atendido = tienda.atenderSiguienteCliente();
                    
                    if (atendido != null){
                        tienda.generarFactura(atendido);
                    }
                    break;
                
                // Salir del programa
                case 0:
                    System.out.println("Saliendo del sistema...");
                    continuar = false;
                    break;
                    
                // Opción inválida
                default:
                    System.out.println("Opción inválida. Intente nuevamente.\n");
                    break;
            }
        }
    }
}
