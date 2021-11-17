import java.lang.runtime.ObjectMethods;
import java.util.ArrayList;
import java.util.List;

import javax.naming.directory.InitialDirContext;
import javax.xml.validation.Validator;

public class SubConjuntos {

    // Construccion de subconjuntos de un AFD a partir de un AFN    
    // Entrada un AFN N
    // Salida AFD D que acepta el mismo lenguaje que N

    Automata automata = new Automata();
    List <EstadoCandidato> estados_AFD = new ArrayList<>();

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
        /*
        System.out.println(" Cerradura " + estado.numeroEstado);
        for (int i = 0; i < valores_cerradura.size(); i++) {
            System.out.print(" " + valores_cerradura.get(i));
        }
        System.out.println("");
*/
        return valores_cerradura;
    }

    public ArrayList mover(EstadoCandidato estado, char simbolo){
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
        /*
        System.out.println(" Mover ");
        for (int i = 0; i < valores_mover.size(); i++) {
            System.out.print(" " + valores_mover.get(i));
        }
        System.out.println("");
*/
        return valores_mover;
    }

    // Cerradura de estado inicial -> primer estado del automata
    // Iterar con los simbolos del alfabeto el estado obtenido

    public void generarAFD(){

        //Cerradura de primer estado del automata

        ArrayList<Integer> resultados_cerradura = cerraduraEpsilon(automata.obtener_estados().get(automata.obtener_inicial()));

        estados_AFD.add(new EstadoCandidato(resultados_cerradura)) ;
        
        //mover(estados_AFD.get(0), 'a');
        resultados_cerradura.clear();
        
        for (int i = 0; i < estados_AFD.size() ; i++) {
            for (int j = 0; j < automata.obtener_alfabeto().length; j++) {
                ArrayList<Integer> resultados_mover = new ArrayList<>(mover(estados_AFD.get(i), automata.obtener_alfabeto()[j]));

                for (int k = 0; k < resultados_mover.size(); k++) {
                    resultados_cerradura = cerraduraEpsilon(automata.obtener_estados().get(resultados_mover.get(k)));
                    if(k == 0){
                        estados_AFD.add(new EstadoCandidato(resultados_cerradura));
                    }else{
                        estados_AFD.get(estados_AFD.size()-1).obtener_estados().addAll(resultados_cerradura);
                    }
                }

                System.out.println(" Candidato a nuevo estado ");
                for (int a = 0; a < estados_AFD.get(estados_AFD.size()-1).obtener_estados().size(); a++) {
                    System.out.print(" " + estados_AFD.get(estados_AFD.size()-1).obtener_estados().get(a));
                }
                System.out.println("");
            }
        }

        //Operacion mover con los valores del alfabeto
        /*
        for (int i = 0; i < estados_AFD.size(); i++) {
            estados_AFD.get(i).obtener_estados();
        }
        */
        

        
    }
}
