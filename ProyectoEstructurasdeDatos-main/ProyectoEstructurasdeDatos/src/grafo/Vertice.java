package grafo;


public class Vertice {


    private String nombre;
    private Arista primeraArista;
    private Vertice siguiente;


    public Vertice(String nombre) {
        this.nombre = nombre;
        this.primeraArista = null;
        this.siguiente = null;
    }


    public String getNombre() {
        return nombre;
    }

    public Arista getPrimeraArista() {
        return primeraArista;
    }

    public Vertice getSiguiente() {
        return siguiente;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrimeraArista(Arista primeraArista) {
        this.primeraArista = primeraArista;
    }

    public void setSiguiente(Vertice siguiente) {
        this.siguiente = siguiente;
    }


    public void agregarArista(String destino, int peso) {
        Arista nueva = new Arista(destino, peso);
        nueva.setSiguiente(primeraArista);
        primeraArista = nueva;
    }


    public boolean existeAristaHacia(String destino) {
        Arista temp = primeraArista;
        while (temp != null) {
            if (temp.getDestino().equalsIgnoreCase(destino)) {
                return true;
            }
            temp = temp.getSiguiente();
        }
        return false;
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(nombre).append("] ");

        Arista temp = primeraArista;
        if (temp == null) {
            sb.append("(sin conexiones)");
        } else {
            while (temp != null) {
                sb.append(temp);
                if (temp.getSiguiente() != null) sb.append("  ");
                temp = temp.getSiguiente();
            }
        }
        return sb.toString();
    }
}
