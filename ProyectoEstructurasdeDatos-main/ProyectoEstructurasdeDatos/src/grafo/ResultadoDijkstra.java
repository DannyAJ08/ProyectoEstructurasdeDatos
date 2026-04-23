package grafo;

import java.util.ArrayList;


public class ResultadoDijkstra {


    private ArrayList<String> camino;
    private int distanciaTotal;


    public ResultadoDijkstra(ArrayList<String> camino, int distanciaTotal) {
        this.camino = camino;
        this.distanciaTotal = distanciaTotal;
    }


    public ArrayList<String> getCamino() {
        return camino;
    }

    public int getDistanciaTotal() {
        return distanciaTotal;
    }


    public String toString() {
        if (camino == null || camino.isEmpty()) {
            return "Sin camino disponible";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < camino.size(); i++) {
            sb.append(camino.get(i));
            if (i < camino.size() - 1) {
                sb.append(" -> ");
            }
        }
        sb.append("\nDistancia total: ").append(distanciaTotal).append(" km");
        return sb.toString();
    }
}
