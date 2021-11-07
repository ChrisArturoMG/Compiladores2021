import java.rmi.server.ExportException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.StyledEditorKit;

public class Thomson {
    public List<AFD> automatas = new ArrayList<>();
    List<String> ordenJerarquico = new ArrayList<>();


    public void ordenarJerarquia(String expresion){
        List<AFD> listaAFD= new ArrayList<AFD>();
        int ultimoParentesis = 0; 
        System.out.println("expresion: " + expresion);
        for (int i = 0; i < expresion.length(); i++) {
            if(expresion.charAt(i)=='('){
                ultimoParentesis = i;
            }if(expresion.charAt(i)==')'){
                //System.out.println("subcadena: " + expresion.substring(ultimoParentesis+1, i));
                ordenJerarquico.add(expresion.substring(ultimoParentesis+1, i));
                expresion = expresion.replace(expresion.substring(ultimoParentesis, i+1), String.valueOf( ordenJerarquico.size()-1));
                //System.out.println("expresion : " + expresion);
                i=0;
                ultimoParentesis=0;
            }
        }
        ordenJerarquico.add(expresion);
        /*for (int i = 0; i < ordenJerarquico.size(); i++) {
            System.out.println(ordenJerarquico.get(i));
        }*/

        realizarOperaciones();
    }
    public void realizarOperaciones(){
        for (int i = 0; i < ordenJerarquico.size(); i++) {
            if(ordenJerarquico.get(i).contains("*")){
                System.out.println("Cerradura");
                System.out.println(ordenJerarquico.get(i));

                cerradura(automatas.get(0));//ordenJerarquico.get(i).charAt(i)));
                
            }else if (ordenJerarquico.get(i).length() > 1 &&  !ordenJerarquico.get(i).contains("*") && !ordenJerarquico.get(i).contains("|") ) {
                System.out.println("concatenacion");
                System.out.println(ordenJerarquico.get(i));
            }else if(ordenJerarquico.get(i).contains("|")) {
                System.out.println("Union");
                System.out.println(ordenJerarquico.get(i));
                union(generarAFD(ordenJerarquico.get(i).charAt(0)), generarAFD(ordenJerarquico.get(i).charAt(2)));
            }
        }
    }

    public AFD generarAFD(char simbolo){
        AFD automata = new AFD();
        automata.insertar_transicion(0, 1, simbolo);
        return automata;
    }

    public void concatenacion ( String expresion){
        AFD automataConcatenacion = new AFD();

        for (int i = 0; i < expresion.length(); i++) {
            automataConcatenacion.insertar_transicion(i, i+1 , expresion.charAt(i));
        }

        for (int i = 0; i <automataConcatenacion.obtener_transiciones().size(); i++) {
            System.out.println("___________________________________________");
            System.out.print(automataConcatenacion.obtener_transiciones().get(i).estadoInicial );
            System.out.print(automataConcatenacion.obtener_transiciones().get(i).siguienteEstado);
            System.out.print(automataConcatenacion.obtener_transiciones().get(i).simbolo);
            System.out.println("");

        }
        
        /*List<Transicion> transicionesA1= new ArrayList<Transicion>(a1.obtener_transiciones());
        List<Transicion> transicionesA2= new ArrayList<Transicion>(a2.obtener_transiciones());
        int contadorEstado = -1;

        for (int i = 0; i < transicionesA1.size(); i++) {
            contadorEstado++;
            automataConcatenacion.insertar_transicion(contadorEstado, contadorEstado+1, transicionesA1.get(i).simbolo);
        }    
        automataConcatenacion.establecer_inicial(a1.obtener_inicial());

        for (int j = 0; j < transicionesA2.size(); j++) {
            contadorEstado++;
            automataConcatenacion.insertar_transicion(contadorEstado, contadorEstado+1, transicionesA2.get(j).simbolo);
        }

        automataConcatenacion.establecer_final(a2.obtener_finales().get(0));
        this.automatas.add(automataConcatenacion);*/
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
        int contadorEstado = a1.obtener_transiciones().size();
        
        automataCerradura.insertar_transicion(contadorEstado, transicionesA1.get(0).estadoInicial, 'E');
        for (int i = 0; i <transicionesA1.size(); i++) {
            automataCerradura.insertar_transicion(transicionesA1.get(i).estadoInicial, transicionesA1.get(i).siguienteEstado, transicionesA1.get(i).simbolo);
        }        
        automataCerradura.insertar_transicion(a1.obtener_finales().get(0), contadorEstado+1, 'E');
        automataCerradura.insertar_transicion(contadorEstado+1,contadorEstado, 'E');

        for (int i = 0; i <automataCerradura.obtener_transiciones().size(); i++) {
            System.out.println("___________________________________________");
            System.out.print(automataCerradura.obtener_transiciones().get(i).estadoInicial );
            System.out.print(automataCerradura.obtener_transiciones().get(i).siguienteEstado);
            System.out.print(automataCerradura.obtener_transiciones().get(i).simbolo);
            System.out.println("");

        }

    }
    
}