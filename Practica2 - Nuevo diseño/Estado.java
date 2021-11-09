import java.util.ArrayList;
import java.util.List;

public class Estado {
    int numeroEstado;
    protected List<Transicion> transiciones = new ArrayList<>();

    public Estado( int numeroEstado){
        this.numeroEstado = numeroEstado;
    }   

    public void insertar_transicion(int siguiente, char simbolo){
        transiciones.add(new Transicion(siguiente, simbolo));
    }
    public List<Transicion> obtener_transiciones(){
        return transiciones;
    }


}
