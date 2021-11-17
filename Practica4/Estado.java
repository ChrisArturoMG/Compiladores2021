import java.util.ArrayList;
import java.util.List;

import javax.swing.JSpinner.NumberEditor;

public class Estado {
    int numeroEstado;
    protected List<Transicion> transiciones = new ArrayList<>();

    public Estado( int numeroEstado){
        this.numeroEstado = numeroEstado;
    }   
    
    public void insertar_transicion(int siguiente, char simbolo){
        Transicion a = new Transicion(siguiente, simbolo);
        transiciones.add(a);
    }
    public void clonar_transiciones(List<Transicion> transiciones_nuevas){
        this.transiciones = new ArrayList<>(transiciones_nuevas);
    }
    public List<Transicion> obtener_transiciones(){
        return transiciones;
    }
    public void reenumerar(int numeroEstado){
        this.numeroEstado = numeroEstado;
    }


}
