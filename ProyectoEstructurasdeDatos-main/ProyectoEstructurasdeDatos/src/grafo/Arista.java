package grafo;


public class Arista {


    private String destino;
    private int peso;
    private Arista siguiente;

    public Arista(String destino, int peso) {
        this.destino = destino;
        this.peso = peso;
        this.siguiente = null;
    }


    public String getDestino() {
        return destino;
    }

    public int getPeso() {
        return peso;
    }

    public Arista getSiguiente() {
        return siguiente;
    }


    public void setDestino(String destino) {
        this.destino = destino;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public void setSiguiente(Arista siguiente) {
        this.siguiente = siguiente;
    }

    public String toString() {
        return "-> " + destino + " (distancia: " + peso + " km)";
    }
}
