import java.rmi.server.ExportException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.StyledEditorKit;
import javax.swing.text.DefaultEditorKit.InsertBreakAction;

public class Thomson {


    public void cerradura(Automata ACerradura){
        
        int ultimo_estado = ACerradura.obtener_numero_estados();
        List<Estado> lista_estados = ACerradura.obtener_estados();
        int estado_inicial = ACerradura.obtener_inicial();
        int estado_final = ACerradura.obtener_finales().get(0);

        //ACerradura.insertar_estado();
  //      lista_estados.get(ultimo_estado).insertar_transicion(estado_inicial, 'E');;
        /*
        ultimo_estado = ACerradura.obtener_numero_estados();
        ACerradura.insertar_estado();
        lista_estados.get(estado_final).insertar_transicion(ultimo_estado, 'E');

        lista_estados.get(estado_final).insertar_transicion(estado_inicial, 'E');
*/
        /*
        for (int i = 0; i < lista_estados.size(); i++) {
            for (int j = 0; j < lista_estados.get(i).obtener_transiciones().size(); j++) {
                System.out.print("************************************************************************");
                System.out.print(lista_estados.get(i).numeroEstado);
                System.out.print(lista_estados.get(i).obtener_transiciones().get(j).estadoSiguiente);
                System.out.print(lista_estados.get(i).obtener_transiciones().get(j).simbolo);
                System.out.print("************************************************************************");
                
            }
        }*/

    }


}