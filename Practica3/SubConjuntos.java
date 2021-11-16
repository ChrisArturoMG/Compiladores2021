import java.util.ArrayList;
import java.util.List;

import javax.naming.directory.InitialDirContext;

public class SubConjuntos {

    public ArrayList cerraduraEpsilon(Estado estado){
        List<Transicion> transiciones = new ArrayList<>(estado.obtener_transiciones());
        
        ArrayList<Integer> valores_cerradura = new ArrayList<>(); 

        int indice = 0;
        for (int i = 0; i < transiciones.size(); i++) {
            if(transiciones.get(i).simbolo== 'E'){
                valores_cerradura.add(transiciones.get(i).estadoSiguiente);
                indice++;
            }
        }

        for (int i = 0; i < valores_cerradura.size(); i++) {
            System.out.print(" " + valores_cerradura.get(i));
        }

        return valores_cerradura;
    }


}
