
package clientes;


public class ColaClientes {
    
    // Atributos
    private Cliente frente;
    
    // Constructor
    public ColaClientes() {
        this.frente = null;
    }
    
    // Metodo para verificar si está vacía
    public boolean estaVacia() {
        return frente == null;
    }
    
    // Ver el cliente al frente
    public Cliente verFrente() {
        if (estaVacia()) {
            System.out.println("No hay clientes en la cola");
            return null;
        }
        return frente;
    }
    
    // Atender Cliente del frente (sacarlo de la cola)
    public Cliente desencolar(){
        if(estaVacia()){
            System.out.println("No hay clientes en la cola");
            return null;
        }
        
        Cliente cliente = frente;
        frente = frente.getSiguiente();
        return cliente;
    }
    
    // Insertar Cliente según su prioridad
    public void encolar(Cliente nuevoCliente){
        
        // Validar que el id no se repita
        if (buscarPorId(nuevoCliente.getIdCliente()) != null) {
            System.out.println("Ya existe un cliente con ese ID");
            return;
        }
        
        // Caso 1: cola vacía
        if(estaVacia()){
            frente = nuevoCliente;
            return;
        }
        
        // Caso 2: mayor prioridad que el frente
        if(nuevoCliente.getPrioridad()> frente.getPrioridad()){
            nuevoCliente.setSiguiente(frente);
            frente = nuevoCliente;
            return;
        }
        // jose 3
        //carlos 2 ingresando
        // Caso 3: buscar posición correcta
        Cliente temp = frente;
        
        while(temp.getSiguiente() != null && temp.getSiguiente().getPrioridad()>= nuevoCliente.getPrioridad()){
            
            temp = temp.getSiguiente();
        }
        
        // Insertar en la posición encontrada
        nuevoCliente.setSiguiente(temp.getSiguiente());
        temp.setSiguiente(nuevoCliente);
        
        System.out.println("Cliente agregado a la cola");
    }
    
    // Método para buscar cliente según ID
    public Cliente buscarPorId(String id) {

        Cliente temp = frente;

        while (temp != null) {
            if (temp.getIdCliente().equalsIgnoreCase(id)) {
                return temp; // encontrado
            }
            temp = temp.getSiguiente();
        }
        return null; // no existe
    }
}
