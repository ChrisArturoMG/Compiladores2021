import java.util.ArrayList;
import java.util.List;

public abstract class Automata {
    protected int estadoInicial;
    protected List<Integer> estadosFinales = new ArrayList<>();
    protected List<Transicion> transiciones = new ArrayList<>();
    protected int numeroDeEstados;
    
    public void insertar_transicion(int inicio, int sig, char simbolo){
        this.transiciones.add(new Transicion(inicio,sig,simbolo));
    }
    public void eliminar_transicion(int inicio, int sig, char simbolo){
        for (int i = 0; i < transiciones.size(); i++) {
            if (transiciones.get(i).estadoInicial == inicio 
                    && transiciones.get(i).siguienteEstado == sig
                       && transiciones.get(i).simbolo == simbolo) {
                transiciones.remove(i);
            }
        }
    }
    
    public void establecer_inicial(int estado){        
        for (int i = 0; i < transiciones.size(); i++) {
            if (transiciones.get(i).estadoInicial == estado || transiciones.get(i).siguienteEstado == estado) {
                this.estadoInicial = estado;
            }
        }
    }

    public void establecer_final(int estado){
        for (int i = 0; i < transiciones.size(); i++) {
            if (transiciones.get(i).estadoInicial == estado || transiciones.get(i).siguienteEstado == estado) {
                this.estadosFinales.add(estado);
            }
        }
    }

    public boolean ValidarAFD(){
        Transicion temp;
        boolean esAFD = true;
        for (int i = 0; i < transiciones.size(); i++) {
            for (int j = i+1; j < transiciones.size(); j++) {
                if(transiciones.get(i).estadoInicial == transiciones.get(j).estadoInicial
                        && transiciones.get(i).simbolo == transiciones.get(j).simbolo){
                    esAFD = false;
                    return esAFD;
                }
            }
        }
        return esAFD;
    }
    
    public boolean AFN(){
        return !ValidarAFD();
    }

    public int obtener_inicial() {
        return estadoInicial;
    }
    public List<Integer> obtener_finales() {
        return estadosFinales;
    }
    public List<Transicion> obtener_transiciones(){
        return transiciones;
    }


    public abstract int evaluaSimbolo(char simbolo, int estadoActual);
    
    
}