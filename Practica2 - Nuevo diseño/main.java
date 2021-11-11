import javax.naming.directory.AttributeModificationException;
import javax.print.event.PrintEvent;

public class main {
    public static void main (String [] arg){
        
        Automata a = new Automata();
        a.insertar_estado();
        a.insertar_estado();
        //a.insertar_estado();
        //a.insertar_estado();

        Thomson t1 = new Thomson();
        
        a.obtener_estados().get(0).insertar_transicion(1, 'a');
        //a.obtener_estados().get(1).insertar_transicion(2, 'b');
        //a.obtener_estados().get(2).insertar_transicion(3, 'c');

        a.establecer_inicial(0);
        a.establecer_final(1);
        //System.out.println(a.obtener_finales().size());
        
        t1.cerradura(a);
        t1.concatenacion("0b");

        



    }    
}