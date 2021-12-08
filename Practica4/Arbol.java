import java.nio.channels.AcceptPendingException;
import java.rmi.server.ExportException;
import java.security.SignatureSpi;
import java.util.ArrayList;
import java.util.List;


public class Arbol {
    
    List <Nodo> nodos = new ArrayList<>();
    int total_estados=0;
    int indice_estados=0;
    List<String> ordenJerarquico = new ArrayList<>();

    public void ordenarJerarquia(String expresion){
        int ultimoParentesis = 0; 
        System.out.println("expresion: " + expresion);
        for (int i = 0; i < expresion.length(); i++) {
            if(expresion.charAt(i)=='('){
                ultimoParentesis = i;
            }
            if(expresion.charAt(i)==')'){
                System.out.println("valor " + expresion.substring(ultimoParentesis+1, i));
                ordenJerarquico.add(expresion.substring(ultimoParentesis+1, i));
                expresion = expresion.replace(expresion.substring(ultimoParentesis, i), String.valueOf( ordenJerarquico.size()-1));
                i=0;
                ultimoParentesis=0;
            }
            if(expresion.charAt(i) == '*' && ultimoParentesis==0){
                ordenJerarquico.add(expresion.substring(i-1, i+1));
                expresion = expresion.replace(expresion.substring(i-1, i+1), String.valueOf( ordenJerarquico.size()-1));
                i=0;
            }  

            if(!expresion.contains("*") && !expresion.contains("|") && ultimoParentesis==0){
                ordenJerarquico.add(expresion);
                expresion = expresion.replace(expresion, String.valueOf( ordenJerarquico.size()-1));
                i=0;

            }  
            if(expresion.charAt(i) == '|' && ultimoParentesis==0){
                ordenJerarquico.add(expresion.substring(1, expresion.length()-2));
                expresion = expresion.replace(expresion.substring(0, expresion.length()-1), String.valueOf( ordenJerarquico.size()-1));
                i=0;
            }  
            
        }
    }
}
