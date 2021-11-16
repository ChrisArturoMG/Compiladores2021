import java.util.ArrayList;
import java.util.List;

import javax.naming.directory.InitialDirContext;
import javax.xml.validation.Validator;

public class SubConjuntos {

    Automata AFD = new Automata();

    public void establecerAutomata(Automata automata){
        AFD = automata;
    }
    
    public ArrayList cerraduraEpsilon(Estado estado){
        
        ArrayList<Integer> valores_cerradura = new ArrayList<>(); 
        valores_cerradura.add(estado.numeroEstado);

        //Recorrido en el Automata
        for (int i = 0; i < valores_cerradura.size(); i++) {
            List<Transicion> lista_transiciones = new ArrayList<>(AFD.obtener_estados().get(valores_cerradura.get(i)).obtener_transiciones()); 
            for (int j = 0; j <lista_transiciones.size(); j++) {
                if(lista_transiciones.get(j).simbolo == 'E'){
                    if(!valores_cerradura.contains(lista_transiciones.get(j).estadoSiguiente)){
                        valores_cerradura.add(lista_transiciones.get(j).estadoSiguiente);   
                    }
                }
            }           
        }   
        
        for (int i = 0; i < valores_cerradura.size(); i++) {
            System.out.print(" " + valores_cerradura.get(i));
        }

        return valores_cerradura;
    }


}
