import javax.naming.directory.AttributeModificationException;
import javax.print.event.PrintEvent;

public class main {
    public static void main (String [] arg){
        
        Automata a = new Automata();

        for (int i = 0; i < 10; i++) {
            a.insertar_estado();    
        }

        a.obtener_estados().get(0).insertar_transicion(1, 'E');
        a.obtener_estados().get(0).insertar_transicion(7, 'E');

        a.obtener_estados().get(1).insertar_transicion(2, 'E');
        a.obtener_estados().get(1).insertar_transicion(4, 'E');

        a.obtener_estados().get(2).insertar_transicion(3, 'a');
        a.obtener_estados().get(3).insertar_transicion(6, 'E');

        a.obtener_estados().get(4).insertar_transicion(5, 'b');
        a.obtener_estados().get(5).insertar_transicion(6, 'E');

        a.obtener_estados().get(6).insertar_transicion(7, 'E');
        a.obtener_estados().get(6).insertar_transicion(1, 'E');

        a.obtener_estados().get(7).insertar_transicion(8, 'a');
        a.obtener_estados().get(8).insertar_transicion(9, 'b');
        a.obtener_estados().get(9).insertar_transicion(10, 'b');

        a.establecer_inicial(0);
        a.establecer_final(10);

        a.insertar_alfabeto(new char []{'a', 'b'});

        SubConjuntos s = new SubConjuntos();
        s.establecerAutomata(a);
        s.cerraduraEpsilon(a.obtener_estados().get(1));



    }    
}