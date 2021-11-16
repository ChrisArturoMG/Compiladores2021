import java.util.ArrayList;
import java.util.List;

public class Automata {
    protected int estadoInicial;
    protected int estadoFinal;
    protected int numeroDeEstados=0;
    protected List<Estado> estados = new ArrayList<>();

    
    public void insertar_estado(){
        this.estados.add(new Estado(numeroDeEstados));
        numeroDeEstados = estados.size();
    }

    public void establecer_inicial(int estado){        
        estadoInicial = estado;
    }
    
    public void establecer_final(int estado){
        this.estadoFinal = estado;
    }
    
    public int obtener_inicial() {
        return estadoInicial;
    }
    public int obtener_final() {
        return estadoFinal;
    }
    public List<Estado> obtener_estados(){
        numeroDeEstados = estados.size();
        return estados;
    }
    public int obtener_numero_estados(){
        return numeroDeEstados;
    }

    public void mostrar_automata(){
        for (int i = 0; i < estados.size(); i++) {
            System.out.print(estados.get(i).numeroEstado + " --> | ");
            for (int j = 0; j <estados.get(i).obtener_transiciones().size(); j++) {
                System.out.print(estados.get(i).obtener_transiciones().get(j).estadoSiguiente+", ");
                System.out.print(estados.get(i).obtener_transiciones().get(j).simbolo+" | ");
            }
            System.out.println(" ");
        }
    }
}