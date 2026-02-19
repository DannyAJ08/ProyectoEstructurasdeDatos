package proyectoestructurasdedatos;
import listaProductos.ListaProductos;
import listaProductos.NodoProducto;
import java.util.ArrayList;
import java.util.Scanner;

public class ProyectoEstructurasdeDatos {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // Scanner permite leer lo que escribe el usuario en consola
        ListaProductos lista = new ListaProductos(); // Creamos la lista enlazada donde se guardarán los libros
        int opcion; // Variable para el switch del menú principal
        boolean continuar = true; // Variable para controlar el ciclo while principal
        
        // Bucle principal del menú
        while(continuar){
            System.out.println("***** MENÚ LIBRERÍA *****\n" +
                                "1. Insertar libro al inicio\n" +
                                "2. Insertar libro al final\n" +
                                "3. Mostrar libros\n" +
                                "4. Buscar libro por ID\n" +
                                "5. Eliminar libro\n" +
                                "6. Modificar libro\n" +
                                "0. Salir\n");

            System.out.print("Seleccione una opción: \n");
            opcion = sc.nextInt();
            sc.nextLine(); //Borrar el buffer
            
            switch(opcion){
                
                // Insertar al inicio
                case 1:
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();

                    System.out.print("Categoria: ");
                    String categoria = sc.nextLine();

                    System.out.print("ID: ");
                    String id = sc.nextLine();

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
                    
                    lista.insertarNodoInicio(nombre, categoria, id, precio, cantidad, imagenes);
                    break;
                    
                // Insertar al final
                case 2:
                    System.out.print("Nombre: ");
                    String nombreF = sc.nextLine();

                    System.out.print("Categoria: ");
                    String categoriaF = sc.nextLine();

                    System.out.print("ID: ");
                    String idF = sc.nextLine();

                    System.out.print("Precio: ");
                    double precioF = sc.nextDouble();

                    System.out.print("Cantidad: ");
                    int cantidadF = sc.nextInt();
                    sc.nextLine();

                    ArrayList<String> imagenesF = new ArrayList<>();

                    System.out.print("Cuántas imágenes desea agregar? ");
                    int numImgF = sc.nextInt();
                    sc.nextLine();

                    for (int i = 0; i < numImgF; i++) {
                        System.out.print("Imagen " + (i+1) + ": ");
                        imagenesF.add(sc.nextLine());
                    }
                    
                    lista.insertarNodoFInal(nombreF, categoriaF, idF, precioF, cantidadF, imagenesF);
                    break;
                  
                // Mostrar lista
                case 3: 
                    lista.mostrarLista();
                    break;
                
                // Buscar por ID
                case 4:
                    System.out.println("Ingrese el ID que desea buscar: ");
                    id = sc.nextLine();
                    lista.buscar(id);
                    break;
                
                // Eliminar libro
                case 5:
                    System.out.println("Ingrese el ID que desea eliminar: ");
                    id = sc.nextLine();
                    lista.eliminar(id);
                    break;
                
                // Modificar libro
                case 6:
                    System.out.println("Ingrese el ID que desea modificar: ");
                    id = sc.nextLine();
                    
                    // Verificamos si el libro existe
                    NodoProducto producto = lista.buscar(id);

                    if (producto == null) {
                        break; // volver al menú principal
                    }
                    boolean seguir = true; // Variable para controlar el ciclo while de modificar
                    
                    // Bucle del menú para modificar
                    while (seguir){
                        System.out.println("Cuál dato desea modificar? \n" +
                                            "1. Nombre \n" +
                                            "2. Categoría \n" +
                                            "3. Precio \n" +
                                            "4. Cantidad \n" +
                                            "5. Volver al menú principal \n");
                        int opc = sc.nextInt();
                        sc.nextLine();
                        
                        switch(opc){
                        
                            case 1:
                                System.out.print("Nuevo nombre: ");
                                String nuevoNombre = sc.nextLine();
                                
                                lista.modificarNombre(id,nuevoNombre);
                                break;
                            case 2:
                                System.out.print("Nueva categoría: ");
                                String nuevaCategoria = sc.nextLine();
                                
                                lista.modificarCategoria(id, nuevaCategoria);
                                break;
                            case 3:
                                System.out.print("Nuevo precio: ");
                                double nuevoPrecio = sc.nextDouble();
                                sc.nextLine();
                                
                                lista.modificarPrecio(id, nuevoPrecio);
                                break;
                            case 4:
                                System.out.print("Nueva cantidad: ");
                                int nuevaCantidad= sc.nextInt();
                                sc.nextLine();

                                lista.modificarCantidad(id, nuevaCantidad);
                                break;
                            case 5:
                                System.out.print("Volviendo al menú principal...\n");
                                seguir = false; // Para salir del bucle de modificar
                                break;
                            default:
                                System.out.print("Opción inválida, intente nuevamente. \n");
                                break;
                        }
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
