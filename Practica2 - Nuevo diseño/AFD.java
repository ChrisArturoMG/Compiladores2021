import java.util.ArrayList;
import java.util.List;

public class AFD extends Automata{
        
    public int evaluaSimbolo(char simbolo, int estadoActual){
        int siguienteEstado = -1;
        for (int i = 0; i < this.transiciones.size(); i++) {
            if (transiciones.get(i).estadoInicial == estadoActual
                    && transiciones.get(i).simbolo == simbolo) {
                siguienteEstado = transiciones.get(i).siguienteEstado;
            }
        }
        return siguienteEstado;
    }
    
}
