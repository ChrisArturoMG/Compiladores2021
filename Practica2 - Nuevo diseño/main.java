import javax.naming.directory.AttributeModificationException;
import javax.print.event.PrintEvent;

public class main {
    public static void main (String [] arg){
        
        Automata a = new Automata();
        a.insertar_estado();
        a.insertar_estado();
        a.insertar_estado();

        a.

        System.out.println(a.obtener_estados().get(1));

        Thomson t1 = new Thomson();
        t1.cerradura(a);

        a.establecer_inicial(0);
        a.establecer_final(3);


    }    
}