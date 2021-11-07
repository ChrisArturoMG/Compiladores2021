import java.util.ArrayList;
import java.util.List;

import javax.swing.text.StyledEditorKit;

public class Thomson {
    public List<AFD> automatas = new ArrayList<>();

    public void concatenacion ( AFD a1, AFD a2){
        AFD automataConcatenacion = new AFD();
        
        List<Transicion> transicionesA1= new ArrayList<Transicion>(a1.obtener_transiciones());
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
        this.automatas.add(automataConcatenacion);
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
        automataUnion.insertar_transicion(contadorEstado, automataUnion.obtener_finales().get(0), 'E');


        for (int i = 0; i <automataUnion.obtener_transiciones().size(); i++) {
            System.out.println("___________________________________________");
            System.out.print(automataUnion.obtener_transiciones().get(i).estadoInicial );
            System.out.print(automataUnion.obtener_transiciones().get(i).siguienteEstado);
            System.out.print(automataUnion.obtener_transiciones().get(i).simbolo);
            System.out.println("");

        }
    }

    public void cerradura(AFD a1){
        AFD automataCerradura = new AFD();
        List<Transicion> transicionesA1 = a1.obtener_transiciones();
        int contadorEstado = 0 ;

        automataCerradura.insertar_transicion(contadorEstado, contadorEstado+1, 'E');
        for (int i = 0; i <transicionesA1.size(); i++) {
            contadorEstado++;
            automataCerradura.insertar_transicion(contadorEstado, contadorEstado+1, transicionesA1.get(i).simbolo);
        }        
        contadorEstado++;
        automataCerradura.insertar_transicion(contadorEstado, contadorEstado+1, 'E');
        automataCerradura.insertar_transicion(0, contadorEstado+1, 'E');

        for (int i = 0; i <automataCerradura.obtener_transiciones().size(); i++) {
            System.out.println("___________________________________________");
            System.out.print(automataCerradura.obtener_transiciones().get(i).estadoInicial );
            System.out.print(automataCerradura.obtener_transiciones().get(i).siguienteEstado);
            System.out.print(automataCerradura.obtener_transiciones().get(i).simbolo);
            System.out.println("");

        }

    }
    
}