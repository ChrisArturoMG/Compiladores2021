import java.nio.channels.AcceptPendingException;
import java.rmi.server.ExportException;
import java.security.SignatureSpi;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.StyledEditorKit;
import javax.swing.text.DefaultEditorKit.InsertBreakAction;

public class Thomson {

    List <Automata>automatas = new  ArrayList<>();
    int total_estados=0;
    int indice_estados=0;

    public void cerradura(Automata ACerradura){
        

        int indice_estado_final = ACerradura.obtener_numero_estados()-1;
        List<Estado> lista_estados = ACerradura.obtener_estados();
        int estado_inicial = ACerradura.obtener_inicial();
        int estado_final = ACerradura.obtener_final();
        
        //CONEXION DE NUEVO INICIAL A AUTOMATA
        lista_estados.get(indice_estado_final).insertar_transicion(estado_inicial, 'E');;
        
        //CREACION DE NUEVO INICIAL
        ACerradura.insertar_estado();
        indice_estado_final = ACerradura.obtener_numero_estados()-1;
        ACerradura.establecer_inicial(indice_estado_final);
        lista_estados.get(indice_estado_final).insertar_transicion(estado_inicial, 'E');
        
        // CONEXION A ESTADO FINAL A FINAL
        ACerradura.insertar_estado();
        indice_estado_final = ACerradura.obtener_numero_estados()-1;
        System.out.println("esto importa" + estado_final + " " + indice_estado_final);
        ACerradura.establecer_final(indice_estado_final);
        lista_estados.get(estado_final).insertar_transicion(indice_estado_final, 'E');
        lista_estados.get(indice_estado_final).insertar_transicion(indice_estado_final-1, 'E');
        
        
        System.out.println(" ->" + ACerradura.obtener_inicial() );
        System.out.println(ACerradura.obtener_final() + "*");

        automatas.add(ACerradura);
        System.out.println("Cerradura    ");
        ACerradura.mostrar_automata();

    }

    public boolean esNumero(char numero){
        try {
            int estado = Integer.parseInt(Character.toString(numero));
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public Automata generarAFD(char simbolo){//, int inicial, int fin){
        Automata a = new Automata();
        a.insertar_estado();
        a.insertar_estado();
        indice_estados++;
        //a.establecer_inicial(inicial);
        //a.establecer_final(fin);
        a.establecer_inicial(0);
        a.establecer_final(1);

        //a.obtener_estados().get(inicial).insertar_transicion(fin, simbolo);
        a.obtener_estados().get(0).insertar_transicion(1, simbolo);
        return a;
    }

    public void concatenacion(String expresion){
        Automata raiz;
        if(esNumero(expresion.charAt(0))){
            raiz = automatas.get(Integer.parseInt(Character.toString(expresion.charAt(0))));
        }else{            
            raiz = generarAFD(expresion.charAt(0));      
        }


        // RECORRIDO DE EXPRESION MENOS UNO PORQUE SE HARA LA CREACION REVISANDO DOS ELEMENTOS SEGUIDOS
        for (int i = 1; i < expresion.length(); i++) {

            Automata automata_siguiente = generarAFD(expresion.charAt(i));
            List<Estado> lista_estados = new ArrayList<>(automata_siguiente.obtener_estados());
            
            int indice_estados = raiz.obtener_estados().size()-1;
            
            
            for (int j = 0; j <lista_estados.size(); j++) {
                lista_estados.get(j).reenumerar(lista_estados.get(j).numeroEstado + indice_estados );;
                
                List<Transicion> lista_transiciones= new ArrayList<>(lista_estados.get(j).obtener_transiciones());
                for (int k = 0; k < lista_transiciones.size() ; k++) {
                    lista_transiciones.get(k).renumerar( lista_transiciones.get(k).estadoSiguiente + indice_estados);
                }   
                
                if(lista_estados.size()-2 == j){
                    System.out.println("REspert");
                    
                    //lista_transiciones.addAll(new ArrayList<>(raiz.obtener_estados().get(raiz.obtener_final()).obtener_transiciones()) );
                    //lista_transiciones.addAll(lista_transiciones );
                    raiz.obtener_estados().get( raiz.obtener_final()).obtener_transiciones().addAll(new ArrayList<>(lista_transiciones) );
                    //raiz.obtener_estados().remove(indice_estados);
                }    
                 
            }
            

            
            
            raiz.obtener_estados().addAll(lista_estados);
        }
        System.out.println("Concatenacion    ");
        raiz.mostrar_automata();

    }


}