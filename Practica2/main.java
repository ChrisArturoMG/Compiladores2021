import javax.naming.directory.AttributeModificationException;
import javax.print.event.PrintEvent;

public class main {
    public static void main (String [] arg){
        
        Automata a = new Automata();
        Automata b = new Automata();

        b.insertar_estado();
        b.insertar_estado();

        a.insertar_estado();
        a.insertar_estado();
        a.insertar_estado();
        a.insertar_estado();

        Thomson t1 = new Thomson();
        
        a.obtener_estados().get(0).insertar_transicion(1, 'a');
        a.obtener_estados().get(1).insertar_transicion(2, 'b');
        a.obtener_estados().get(2).insertar_transicion(3, 'c');

        b.obtener_estados().get(0).insertar_transicion(1, 'c');
        b.establecer_inicial(0);
        b.establecer_final(1);

        a.establecer_inicial(0);
        a.establecer_final(3);

        t1.ordenarJerarquia("((ab)|c)*");
    }    
}