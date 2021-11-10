import java.rmi.server.ExportException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.StyledEditorKit;
import javax.swing.text.DefaultEditorKit.InsertBreakAction;

public class Thomson {

    List <Automata>automatas = new  ArrayList<>();
    int total_estados=0;

    public void cerradura(Automata ACerradura){
        
        int total_estados = ACerradura.obtener_numero_estados()-1;
        List<Estado> lista_estados = ACerradura.obtener_estados();
        int estado_inicial = ACerradura.obtener_inicial();
        int estado_final = ACerradura.obtener_finales().get(0);
        
        //CONEXION DE NUEVO INICIAL A AUTOMATA
        ACerradura.insertar_estado();
        total_estados++;
        lista_estados.get(total_estados).insertar_transicion(estado_inicial, 'E');;
        
        // CONEXION DE ESTADO FINAL A ULTIMO ESTA CREADO
        ACerradura.insertar_estado();
        total_estados++;
        lista_estados.get(estado_final).insertar_transicion(total_estados, 'E');

        //CONEXION DE INICIAL A FINAL DE AUTOMATA
        lista_estados.get(estado_final).insertar_transicion(estado_inicial, 'E');
        
        //CONEXION DE FINAL DE AUTOMATA CERRADURA A INICIAL
        lista_estados.get(total_estados).insertar_transicion(total_estados-1, 'E');

        System.out.println(total_estados);

        for (int i = 0; i < lista_estados.size(); i++) {
            for (int j = 0; j < lista_estados.get(i).obtener_transiciones().size(); j++) {
                System.out.println("_________");
                System.out.print(lista_estados.get(i).numeroEstado);
                System.out.print(lista_estados.get(i).obtener_transiciones().get(j).estadoSiguiente);
                System.out.print(lista_estados.get(i).obtener_transiciones().get(j).simbolo);
                System.out.println("");
                
            }
        }
        automatas.add(ACerradura);

    }

    public boolean esNumero(char numero){
        try {
            int estado = Integer.parseInt(Character.toString(numero));
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public Automata generarAFD(char simbolo, int inicial, int fin){
        Automata a = new Automata();
        a.insertar_estado();
        total_estados++;
        a.insertar_estado();
        total_estados++;
        a.establecer_inicial(inicial);
        a.establecer_final(fin);

        a.obtener_estados().get(0).insertar_transicion(1, simbolo);
        return a;
    }

    public void concatenacion(String expresion){
        Automata automataConcatenacion = new Automata();
        List <Automata>automatasConcatenacion = new  ArrayList<>();

        for (int i = 0; i < expresion.length()-1; i++) {
            if(esNumero(expresion.charAt(i)) && esNumero(expresion.charAt(i+1)) ){
                Automata automata_raiz = automatas.get(i);
                List <Estado>estados =  new ArrayList<>(automata_raiz.obtener_estados());
                int estado_final = automata_raiz.obtener_finales().get(0);
                int estado_inicial = automata_raiz.obtener_inicial();

                Automata evaluacion_automata_siguiente = automatas.get(i);
                List <Estado>estados_siguiente =  new ArrayList<>(evaluacion_automata.obtener_estados());
                int estado_final_siguiente = evaluacion_automata.obtener_finales().get(0);
                int estado_inicial_siguiente = evaluacion_automata.obtener_inicial();
                
                estados.get(estado_final).insertar_transicion(estado_inicial_siguiente);

            }else{
                automatasConcatenacion.add(generarAFD(expresion.charAt(i)));
            }
        }

        for (int i = 0; i < automatasConcatenacion.size()-1; i++) {

            int ultimo_estado = automatasConcatenacion.get(i).obtener_numero_estados()-1;
            List<Estado> lista_estados = automatasConcatenacion.get(i).obtener_estados();
            int estado_inicial = automatasConcatenacion.get(i).obtener_inicial();
            int estado_final = automatasConcatenacion.get(i).obtener_finales().get(0);

            List<Estado> lista_estados_siguiente = automatasConcatenacion.get(i+1).obtener_estados();
            int estado_inicial_siguiente = automatasConcatenacion.get(i+1).obtener_inicial();
            

            
        }

    }


}