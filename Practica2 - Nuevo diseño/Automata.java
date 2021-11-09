import java.util.ArrayList;
import java.util.List;

public class Automata {
    protected int estadoInicial;
    protected List<Integer> estadosFinales = new ArrayList<>();
    protected int numeroDeEstados=0;
    protected List<Estado> estados = new ArrayList<>();

    
    public void insertar_estado(){
        this.estados.add(new Estado(numeroDeEstados));
        numeroDeEstados++;
    }

    public void insertar_transicion(){

    }

    public void establecer_inicial(int estado){        
        estadoInicial = estado;
    }
    
    public void establecer_final(int estado){
        estadosFinales.add(estado);
    }
    
    public int obtener_inicial() {
        return estadoInicial;
    }
    public List<Integer> obtener_finales() {
        return estadosFinales;
    }
    public List<Estado> obtener_estados(){
        return estados;
    }
    public int obtener_numero_estados(){
        return numeroDeEstados;
    }


    /*
    

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


    public abstract int evaluaSimbolo(char simbolo, int estadoActual);
    
    */
}