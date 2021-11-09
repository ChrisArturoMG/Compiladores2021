import java.rmi.server.ExportException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.StyledEditorKit;

public class Thomson {
    public List<AFD> automatas = new ArrayList<>();
    List<String> ordenJerarquico = new ArrayList<>();
    
    public boolean esNumero(char numero){
        try {
            int estado = Integer.parseInt(Character.toString(numero));
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public void ordenarJerarquia(String expresion){
        List<AFD> listaAFD= new ArrayList<AFD>();
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
                i=0;
            }  

            // ab
            if(!expresion.contains("*") && !expresion.contains("|") && ultimoParentesis==0){
                ordenJerarquico.add(expresion);
                expresion = expresion.replace(expresion, String.valueOf( ordenJerarquico.size()-1));
                i=0;
                /*
                for (int j = 0; j < expresion.length(); j++) {
                    if(expresion.length()>1){
                        for (int j2 = 0; j2 < expresion.length(); j2++) {
                            if(esNumero(expresion.charAt(j2)) &&  esNumero(expresion.charAt(j2+1))){
                                concatenacion(expresion.substring(j2), expresion.substring(j2+1));

                                expresion = expresion.replace(expresion.substring(i-1, i+1), String.valueOf( ordenJerarquico.size()-1));
                                System.out.println("expresion : " + expresion);
                                i=0;
                            }else{

                            }
                        }
                    }else{
                        if(esNumero(expresion.charAt(i))){
                            System.out.println("subcadena: " + expresion.substring(i-1, i+1));
                            ordenJerarquico.add(expresion.substring(i));
                            expresion = expresion.replace(expresion.substring(i), String.valueOf( ordenJerarquico.size()-1));
                            System.out.println("expresion : " + expresion);
                            i=0;
                        }
                    }

                }*/
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
                
                Boolean cadenaConNumeros = false;

                for (int j = 0; j < ordenJerarquico.get(i).length()-1; j++) {
                    System.out.println(ordenJerarquico.get(i).charAt(j));
                    if(esNumero(ordenJerarquico.get(i).charAt(j))){
                        cadenaConNumeros = true;
                    }
                }

                if(cadenaConNumeros){
                    for (int j = 0; j < ordenJerarquico.get(i).length(); j++) {
                        int indiceAutomata = Character.getNumericValue(ordenJerarquico.get(i).charAt(j));
    
                        if(esNumero(ordenJerarquico.get(i).charAt(j)) && esNumero(ordenJerarquico.get(i).charAt(j+1))){
                            System.out.print("concatenacion ambos " + ordenJerarquico.get(i).charAt(j));
                            concatenacion(automatas.get(j), automatas.get(j+1) );
    
                        }else if(!esNumero(ordenJerarquico.get(i).charAt(j))  && esNumero(ordenJerarquico.get(i).charAt(j+1)) ){
                            System.out.print("concatenacion segundo " + ordenJerarquico.get(i).charAt(j));
                            concatenacion(automatas.get(j), generarAFD(ordenJerarquico.get(j).charAt(j+1)));
    
                        }else if(esNumero(ordenJerarquico.get(i).charAt(j))  && !esNumero(ordenJerarquico.get(i).charAt(j+1))){
                            System.out.println("concatenacion primero " + ordenJerarquico.get(i).charAt(j));
                            concatenacion(automatas.get(j), generarAFD(ordenJerarquico.get(j).charAt(j+1)));
    
                        }else if(!esNumero(ordenJerarquico.get(i).charAt(j))  && !esNumero(ordenJerarquico.get(i).charAt(j+1))){
                        
                        }
                    }

                }else{
                    concatenacion(ordenJerarquico.get(i));
                }

            }else if(ordenJerarquico.get(i).contains("|")) {
                System.out.print("Union  ");
                System.out.println(ordenJerarquico.get(i));
                union(generarAFD(ordenJerarquico.get(i).charAt(0)), generarAFD(ordenJerarquico.get(i).charAt(2)));
            }
        }
    }

    public AFD generarAFD(char simbolo){
        AFD automata = new AFD();
        automata.insertar_transicion(0, 1, simbolo);
        automata.establecer_inicial(0);
        automata.establecer_final(1);
        return automata;
    }

    public void concatenacion(String expresion){
        AFD automataConcatenacion = new AFD();

        for (int i = 0; i < expresion.length(); i++) {
            automataConcatenacion.insertar_transicion(i, i+1, expresion.charAt(i));
        }
        automataConcatenacion.establecer_inicial(0);
        automataConcatenacion.establecer_final(expresion.length());

        automatas.add(automataConcatenacion);

        for (int i = 0; i <automataConcatenacion.obtener_transiciones().size(); i++) {
            System.out.println("___________________________________________");
            System.out.print(automataConcatenacion.obtener_transiciones().get(i).estadoInicial );
            System.out.print(automataConcatenacion.obtener_transiciones().get(i).siguienteEstado);
            System.out.print(automataConcatenacion.obtener_transiciones().get(i).simbolo);
            System.out.println("");

        }

    }

    public void concatenacion ( Automata a1, Automata a2){
        AFD automataConcatenacion = new AFD();
        
        List<Transicion> transicionesA1= new ArrayList<Transicion>(a1.obtener_transiciones());
        List<Transicion> transicionesA2= new ArrayList<Transicion>(a2.obtener_transiciones());
        int contadorEstado = a1.obtener_transiciones().size();

        for (int i = 0; i < transicionesA1.size(); i++) {
            automataConcatenacion.insertar_transicion(transicionesA1.get(i).estadoInicial,transicionesA1.get(i).siguienteEstado, transicionesA1.get(i).simbolo);
        }    
        automataConcatenacion.establecer_inicial(a1.obtener_inicial());

        for (int j = 0; j < transicionesA2.size(); j++) {
            automataConcatenacion.insertar_transicion(transicionesA2.get(j).estadoInicial+contadorEstado, transicionesA2.get(j).siguienteEstado+contadorEstado, transicionesA2.get(j).simbolo);
        }

        automataConcatenacion.establecer_final(a2.obtener_finales().get(0)+contadorEstado);
        this.automatas.add(automataConcatenacion);

        for (int i = 0; i <automataConcatenacion.obtener_transiciones().size(); i++) {
            System.out.println("___________________________________________");
            System.out.print(automataConcatenacion.obtener_transiciones().get(i).estadoInicial );
            System.out.print(automataConcatenacion.obtener_transiciones().get(i).siguienteEstado);
            System.out.print(automataConcatenacion.obtener_transiciones().get(i).simbolo);
            System.out.println("");

        }

    }
     
    public void union ( AFD a1, AFD a2){
        AFD automataUnion = new AFD();

        List<Transicion> transicionesA1= a1.obtener_transiciones();
        List<Transicion> transicionesA2= a2.obtener_transiciones();

        int contadorEstado = 0;

        automataUnion.insertar_transicion(contadorEstado, contadorEstado+1, 'E');
        for (int i = 0; i <transicionesA1.size(); i++) {
            contadorEstado++;
            automataUnion.insertar_transicion(contadorEstado, contadorEstado+1, transicionesA1.get(i).simbolo);
        }
        contadorEstado++;
        automataUnion.insertar_transicion(contadorEstado, contadorEstado+1, 'E');
        automataUnion.establecer_final(contadorEstado+1);
        
        contadorEstado++;
        automataUnion.insertar_transicion(0, contadorEstado+1, 'E');
        for (int i = 0; i <transicionesA2.size(); i++) {
            contadorEstado++;
            automataUnion.insertar_transicion(contadorEstado, contadorEstado+1, transicionesA2.get(i).simbolo);
        }
        automataUnion.insertar_transicion(contadorEstado+1, automataUnion.obtener_finales().get(0), 'E');

        for (int i = 0; i <automataUnion.obtener_transiciones().size(); i++) {
            System.out.println("___________________________________________");
            System.out.print(automataUnion.obtener_transiciones().get(i).estadoInicial );
            System.out.print(automataUnion.obtener_transiciones().get(i).siguienteEstado);
            System.out.print(automataUnion.obtener_transiciones().get(i).simbolo);
            System.out.println("");

        }
        automatas.add(automataUnion);
    }

    public void cerradura(AFD a1){
        AFD automataCerradura = new AFD();
        List<Transicion> transicionesA1 = a1.obtener_transiciones();
        int contadorEstado = a1.obtener_transiciones().size()+1;
        
        automataCerradura.insertar_transicion(contadorEstado, transicionesA1.get(0).estadoInicial, 'E');
        for (int i = 0; i <transicionesA1.size(); i++) {
            automataCerradura.insertar_transicion(transicionesA1.get(i).estadoInicial, transicionesA1.get(i).siguienteEstado, transicionesA1.get(i).simbolo);
        }        
        automataCerradura.insertar_transicion(a1.obtener_finales().get(0), contadorEstado+1, 'E');
        automataCerradura.insertar_transicion(contadorEstado+1,contadorEstado, 'E');
        automataCerradura.insertar_transicion(a1.obtener_finales().get(0), a1.obtener_inicial(), 'E');

        for (int i = 0; i <automataCerradura.obtener_transiciones().size(); i++) {
            System.out.println("___________________________________________");
            System.out.print(automataCerradura.obtener_transiciones().get(i).estadoInicial );
            System.out.print(automataCerradura.obtener_transiciones().get(i).siguienteEstado);
            System.out.print(automataCerradura.obtener_transiciones().get(i).simbolo);
            System.out.println("");

        }
        automatas.add(automataCerradura);

    }
    
}