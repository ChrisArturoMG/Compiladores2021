import java.util.ArrayList;
import java.util.List;

import javax.naming.directory.InitialDirContext;
import javax.xml.validation.Validator;

public class SubConjuntos {

    Automata automata = new Automata();
    List <EstadoCandidato> estados_candidatos = new ArrayList<>();

    public void establecerAutomata(Automata automata){
        this.automata = automata;
    }
    
    public ArrayList cerraduraEpsilon(Estado estado){
        
        ArrayList<Integer> valores_cerradura = new ArrayList<>(); 
        valores_cerradura.add(estado.numeroEstado);

        //Recorrido en el Automata
        for (int i = 0; i < valores_cerradura.size(); i++) {
            List<Transicion> lista_transiciones = new ArrayList<>(automata.obtener_estados().get(valores_cerradura.get(i)).obtener_transiciones()); 
            for (int j = 0; j <lista_transiciones.size(); j++) {
                if(lista_transiciones.get(j).simbolo == 'E'){
                    if(!valores_cerradura.contains(lista_transiciones.get(j).estadoSiguiente)){
                        valores_cerradura.add(lista_transiciones.get(j).estadoSiguiente);   
                    }
                }
            }           
        }   
        
        System.out.println(" Cerradura ");
        for (int i = 0; i < valores_cerradura.size(); i++) {
            System.out.print(" " + valores_cerradura.get(i));
        }
        System.out.println("");
        estados_candidatos.add(new EstadoCandidato(valores_cerradura));

        mover(estados_candidatos.get(0), 'a');

        return valores_cerradura;
    }

    public void mover(EstadoCandidato estado, char simbolo){
        ArrayList<Integer> valores_mover = new ArrayList<>(); 
        
        ArrayList<Integer> lista_estado= new ArrayList<>(estado.obtener_estados()); 
        //Recorrido en el Automata

        

        for (int i = 0; i < estado.obtener_estados().size(); i++) {
            List<Transicion> lista_transiciones = new ArrayList<>(automata.obtener_estados().get(lista_estado.get(i)).obtener_transiciones()); 
            for (int j = 0; j < lista_transiciones.size(); j++) {
                if(lista_transiciones.get(j).simbolo == simbolo){
                    if(!valores_mover.contains(lista_transiciones.get(j).estadoSiguiente)){
                        valores_mover.add(lista_transiciones.get(j).estadoSiguiente);   
                    }
                }
            }           
        } 
        
        System.out.println(" Mover ");
        for (int i = 0; i < valores_mover.size(); i++) {
            System.out.print(" " + valores_mover.get(i));
        }
        System.out.println("");

    }



}
