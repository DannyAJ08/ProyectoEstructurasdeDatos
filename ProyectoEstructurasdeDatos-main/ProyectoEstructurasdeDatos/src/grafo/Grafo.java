package grafo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class Grafo {


    private Vertice primero;   // Primer vértice de la lista de vértices
    private int totalVertices; // Contador de vértices


    public Grafo() {
        this.primero = null;
        this.totalVertices = 0;
    }


    public Vertice getPrimero() {
        return primero;
    }

    public int getTotalVertices() {
        return totalVertices;
    }


    public boolean estaVacio() {
        return primero == null;
    }


    public boolean existeVertice(String nombre) {
        return buscarVertice(nombre) != null;
    }


    public Vertice buscarVertice(String nombre) {
        Vertice temp = primero;
        while (temp != null) {
            if (temp.getNombre().equalsIgnoreCase(nombre)) {
                return temp;
            }
            temp = temp.getSiguiente();
        }
        return null;
    }



    public void agregarVertice(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            System.out.println("Error: el nombre del vértice no puede estar vacío");
            return;
        }
        if (existeVertice(nombre)) {
            System.out.println("Ya existe un vértice con el nombre: " + nombre);
            return;
        }

        Vertice nuevo = new Vertice(nombre);


        if (primero == null) {
            primero = nuevo;
        } else {
            Vertice temp = primero;
            while (temp.getSiguiente() != null) {
                temp = temp.getSiguiente();
            }
            temp.setSiguiente(nuevo);
        }

        totalVertices++;
        System.out.println("Vértice '" + nombre + "' agregado al mapa");
    }


    public void agregarArista(String origen, String destino, int peso) {
        if (origen == null || destino == null || origen.trim().isEmpty() || destino.trim().isEmpty()) {
            System.out.println("Error: los nombres de los vértices no pueden estar vacíos");
            return;
        }
        if (peso <= 0) {
            System.out.println("Error: el peso de la arista debe ser mayor a 0");
            return;
        }
        if (origen.equalsIgnoreCase(destino)) {
            System.out.println("Error: el origen y destino no pueden ser el mismo vértice");
            return;
        }

        Vertice vOrigen = buscarVertice(origen);
        Vertice vDestino = buscarVertice(destino);

        if (vOrigen == null) {
            System.out.println("Error: no existe el vértice origen '" + origen + "'");
            return;
        }
        if (vDestino == null) {
            System.out.println("Error: no existe el vértice destino '" + destino + "'");
            return;
        }


        if (vOrigen.existeAristaHacia(destino)) {
            System.out.println("Ya existe una arista entre '" + origen + "' y '" + destino + "'");
            return;
        }


        vOrigen.agregarArista(destino, peso);
        vDestino.agregarArista(origen, peso);

        System.out.println("Arista agregada: " + origen + " <-> " + destino + " (distancia: " + peso + " km)");
    }



    public void mostrarMapa() {
        if (estaVacio()) {
            System.out.println("El mapa está vacío");
            return;
        }
        System.out.println("===== MAPA DE UBICACIONES =====");
        Vertice temp = primero;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.getSiguiente();
        }
        System.out.println("================================");
    }



    public boolean estaConectado(String origen, String destino) {
        if (!existeVertice(origen) || !existeVertice(destino)) {
            return false;
        }
        if (origen.equalsIgnoreCase(destino)) {
            return true;
        }

        ArrayList<String> visitados = new ArrayList<>();
        ArrayList<String> cola = new ArrayList<>();

        cola.add(origen);
        visitados.add(origen);

        while (!cola.isEmpty()) {
            String actual = cola.remove(0);
            Vertice v = buscarVertice(actual);

            Arista arista = v.getPrimeraArista();
            while (arista != null) {
                String vecino = arista.getDestino();
                if (vecino.equalsIgnoreCase(destino)) {
                    return true; // Encontrado
                }
                if (!visitados.contains(vecino)) {
                    visitados.add(vecino);
                    cola.add(vecino);
                }
                arista = arista.getSiguiente();
            }
        }
        return false;
    }


    public ResultadoDijkstra caminoMasCorto(String origen, String destino) {
        if (!existeVertice(origen) || !existeVertice(destino)) {
            System.out.println("Error: uno o ambos vértices no existen en el mapa");
            return null;
        }


        Map<String, Integer> distancias = new HashMap<>();
        Map<String, String> predecesores = new HashMap<>();
        ArrayList<String> noVisitados = new ArrayList<>();


        Vertice temp = primero;
        while (temp != null) {
            distancias.put(temp.getNombre(), Integer.MAX_VALUE);
            predecesores.put(temp.getNombre(), null);
            noVisitados.add(temp.getNombre());
            temp = temp.getSiguiente();
        }


        distancias.put(origen, 0);

        while (!noVisitados.isEmpty()) {

            String actual = null;
            int menorDist = Integer.MAX_VALUE;
            for (String nombre : noVisitados) {
                if (distancias.get(nombre) < menorDist) {
                    menorDist = distancias.get(nombre);
                    actual = nombre;
                }
            }


            if (actual == null) break;


            if (actual.equalsIgnoreCase(destino)) break;

            noVisitados.remove(actual);


            Vertice vActual = buscarVertice(actual);
            Arista arista = vActual.getPrimeraArista();
            while (arista != null) {
                String vecino = arista.getDestino();
                if (noVisitados.contains(vecino)) {
                    int nuevaDist = distancias.get(actual) + arista.getPeso();
                    if (nuevaDist < distancias.get(vecino)) {
                        distancias.put(vecino, nuevaDist);
                        predecesores.put(vecino, actual);
                    }
                }
                arista = arista.getSiguiente();
            }
        }


        if (distancias.get(destino) == Integer.MAX_VALUE) {
            return null; // No hay camino
        }

        ArrayList<String> camino = new ArrayList<>();
        String paso = destino;
        while (paso != null) {
            camino.add(paso);
            paso = predecesores.get(paso);
        }
        Collections.reverse(camino);

        return new ResultadoDijkstra(camino, distancias.get(destino));
    }


    public int obtenerDistancia(String origen, String destino) {
        ResultadoDijkstra resultado = caminoMasCorto(origen, destino);
        if (resultado == null) return -1;
        return resultado.getDistanciaTotal();
    }
}
