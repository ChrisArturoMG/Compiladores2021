import java.util.ArrayList;
import java.util.List;

public abstract class Automata {
    protected int estadoInicial;
    protected List<Integer> estadosFinales;
    protected List<Transicion> transiciones;
    
    public void insertar_transicion(int inicio, int fin, char simbolo){
        this.transiciones.add(new Transicion(inicio,fin,simbolo));
    }
    public int dame_inicial() {
        return estadoInicial;
    }
    public List<Integer> dame_finales() {
        return estadosFinales;
    }
    public List<Transicion> dame_transicion(){
        return transiciones;
    }

    
}