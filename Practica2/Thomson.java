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
    List<String> ordenJerarquico = new ArrayList<>();


    public void ordenarJerarquia(String expresion){
        List<Automata> listaAFD= new ArrayList<Automata>();
        int ultimoParentesis = 0; 
        System.out.println("expresion: " + expresion);
        for (int i = 0; i < expresion.length(); i++) {
            if(expresion.charAt(i)=='('){
                ultimoParentesis = i;
            }if(expresion.charAt(i)==')'){
                System.out.println("subcadena: " + expresion.substring(ultimoParentesis+1, i));
                ordenJerarquico.add(expresion.substring(ultimoParentesis+1, i));
                expresion = expresion.replace(expresion.substring(ultimoParentesis, i+1), String.valueOf( ordenJerarquico.size()-1));
                System.out.println("expresion : " + expresion);
                i=0;
                ultimoParentesis=0;
            }
            if(expresion.charAt(i) == '*' && ultimoParentesis==0){
                System.out.println("subcadena: " + expresion.substring(i-1, i+1));
                ordenJerarquico.add(expresion.substring(i-1, i+1));
                expresion = expresion.replace(expresion.substring(i-1, i+1), String.valueOf( ordenJerarquico.size()-1));
                System.out.println("expresion : " + expresion);
                System.out.println("");
                i=0;
            }  

            
            // ab
            if(!expresion.contains("*") && !expresion.contains("|") && ultimoParentesis==0){
                ordenJerarquico.add(expresion);
                expresion = expresion.replace(expresion, String.valueOf( ordenJerarquico.size()-1));
                i=0;

            }  
            if(expresion.charAt(i) == '|' && ultimoParentesis==0){
                System.out.println("union");

                //System.out.println("subcadena: " + expresion.substring(i-2, i));
                System.out.println("subcadena: " + expresion.substring(1, expresion.length()-1));
                //ordenJerarquico.add(expresion.substring(i-2, i));
                ordenJerarquico.add(expresion.substring(1, expresion.length()-1));
                expresion = expresion.replace(expresion.substring(0, expresion.length()), String.valueOf( ordenJerarquico.size()-1));
                System.out.println("expresion : " + expresion);
                System.out.println("");
                i=0;
            }  
            
        }
        
        //ordenJerarquico.add(expresion);
        System.out.println("___________________________________________");
        System.out.println("Orden Jerarquico");
        for (int i = 0; i < ordenJerarquico.size(); i++) {
            System.out.println(ordenJerarquico.get(i));
        }
        System.out.println("___________________________________________");

        realizarOperaciones();

    }
    public void realizarOperaciones(){
        for (int i = 0; i < ordenJerarquico.size(); i++) {
            if(ordenJerarquico.get(i).contains("*")){
                System.out.print("Cerradura  ");
                System.out.println(ordenJerarquico.get(i));
                if(esNumero(ordenJerarquico.get(i).charAt(0))){
                    cerradura(automatas.get(i-1));//ordenJerarquico.get(i).charAt(i)));
                }else{
                    cerradura(generarAFD(ordenJerarquico.get(i).charAt(0)));//ordenJerarquico.get(i).charAt(i)));
                }

            }else if (ordenJerarquico.get(i).length() > 1 &&  !ordenJerarquico.get(i).contains("*") && !ordenJerarquico.get(i).contains("|") ) {
                System.out.print("concatenacion  ");
                System.out.println(ordenJerarquico.get(i));
                concatenacion(ordenJerarquico.get(i));
                

            }else if(ordenJerarquico.get(i).contains("|")) {
                System.out.print("Union  ");
                System.out.println(ordenJerarquico.get(i));
                if(esNumero(ordenJerarquico.get(i).charAt(0))){
                    union(automatas.get(Integer.parseInt(String.valueOf(ordenJerarquico.get(i).charAt(0)))),   generarAFD(ordenJerarquico.get(i).charAt(2)));

                }
            }
        }
    }


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
        //System.out.println("esto importa" + estado_final + " " + indice_estado_final);
        ACerradura.establecer_final(indice_estado_final);
        lista_estados.get(estado_final).insertar_transicion(indice_estado_final, 'E');
        lista_estados.get(indice_estado_final).insertar_transicion(indice_estado_final-1, 'E');
                
        //System.out.println(" ->" + ACerradura.obtener_inicial() );
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
            Automata automata_siguiente;
            if(esNumero(expresion.charAt(i))){
                automata_siguiente = automatas.get(Integer.parseInt(Character.toString(expresion.charAt(i))));
            }else{            
                automata_siguiente = generarAFD(expresion.charAt(i));
            }
            
            List<Estado> lista_estados = new ArrayList<>(automata_siguiente.obtener_estados());
            int indice_estados = raiz.obtener_estados().size()-1;
            
            for (int j = 0; j <lista_estados.size(); j++) {
                indice_estados = raiz.obtener_estados().size()-1;
                lista_estados.get(j).reenumerar(lista_estados.get(j).numeroEstado + indice_estados );;
                
                List<Transicion> lista_transiciones= new ArrayList<>(lista_estados.get(j).obtener_transiciones());
                
                if(j == lista_estados.size()-1){
                    raiz.obtener_estados().add(lista_estados.get(j));
                    raiz.obtener_estados().get(indice_estados).obtener_transiciones().addAll(new ArrayList<>(lista_transiciones));
                }
                for (int k = 0; k < lista_transiciones.size() ; k++) {
                    lista_transiciones.get(k).renumerar( lista_transiciones.get(k).estadoSiguiente + indice_estados);
                    if(j == 0){
                        raiz.obtener_estados().get( indice_estados).obtener_transiciones().addAll(new ArrayList<>(lista_transiciones));
                    }else{
                        raiz.obtener_estados().add(lista_estados.get(j));
                    }    
                }
            }
            indice_estados = raiz.obtener_estados().size()-1;
            raiz.establecer_final(indice_estados);
            raiz.mostrar_automata();
        }
        System.out.println("Concatenacion    ");
        raiz.mostrar_automata();
        System.out.println("Este es el final " + raiz.obtener_final());
        automatas.add(raiz);

    }
    public void union(Automata AUnion1, Automata AUnion2){
        
        Automata union = new Automata();
        
        //AUTOMATA 1 
        int inicial1 = AUnion1.obtener_inicial();
        List<Estado> lista_estados1 = new ArrayList<>(AUnion1.obtener_estados());
        
        int indice_estado = AUnion1.obtener_estados().size();
        
        //AUTOMATA 2
        List<Estado> lista_estados2 = new ArrayList<>(AUnion2.obtener_estados());
        
        //AJUSTAR AUTOMATA
        for (int i = 0; i < lista_estados2.size(); i++) {   
            lista_estados2.get(i).reenumerar( lista_estados2.get(i).numeroEstado + indice_estado);
            List<Transicion> lista_transiciones= new ArrayList<>(AUnion2.obtener_estados().get(i).obtener_transiciones());
            
            for (int j = 0; j < lista_transiciones.size(); j++) {
                lista_transiciones.get(j).renumerar(lista_transiciones.get(j).estadoSiguiente + indice_estado);
            }
        }
        AUnion2.establecer_inicial(AUnion2.obtener_inicial() + indice_estado );
        AUnion2.establecer_final(AUnion2.obtener_final() + indice_estado);
        
        union.obtener_estados().addAll(lista_estados1);
        union.obtener_estados().addAll(lista_estados2);
        indice_estado = union.obtener_estados().size();
        union.insertar_estado();
        union.obtener_estados().get(indice_estado).insertar_transicion(AUnion1.obtener_inicial(), 'E');
        union.obtener_estados().get(indice_estado).insertar_transicion(AUnion2.obtener_inicial(), 'E');
        indice_estado = union.obtener_estados().size();
        union.insertar_estado();
        union.obtener_estados().get(AUnion1.estadoFinal).insertar_transicion(indice_estado, 'E');
        union.obtener_estados().get(AUnion2.estadoFinal).insertar_transicion(indice_estado, 'E');
        union.mostrar_automata();
        
        System.out.println("este es la union");
        automatas.add(union);


    }  


}