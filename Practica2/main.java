import javax.naming.directory.AttributeModificationException;
import javax.print.event.PrintEvent;

public class main {
    public static void main (String [] arg){

        AFD a1 = new AFD();
        AFD a2 = new AFD();

        a1.insertar_transicion(0, 1, 'a');
        a2.insertar_transicion(2, 3, 'b');


        a1.establecer_inicial(0);
        a2.establecer_inicial(2);

        a1.establecer_final(1);
        a2.establecer_final(3);


        Thomson t1 = new Thomson();
        
        t1.cerradura(a1);

        /*for (int i = 0; i < t1.automatas.get(0).obtener_transiciones().size(); i++) {
            System.out.println("___________________________________________");
            System.out.print(t1.automatas.get(0).obtener_transiciones().get(i).estadoInicial);
            System.out.print(t1.automatas.get(0).obtener_transiciones().get(i).siguienteEstado);
            System.out.print(t1.automatas.get(0).obtener_transiciones().get(i).simbolo);
            System.out.println("");
        }*/
        
        
    

    }    
}
