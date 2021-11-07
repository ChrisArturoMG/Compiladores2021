import javax.naming.directory.AttributeModificationException;
import javax.print.event.PrintEvent;

public class main {
    public static void main (String [] arg){

        AFD a1 = new AFD();
        AFD a2 = new AFD();

        Thomson t1 = new Thomson();

        t1.ordenarJerarquia("((abc)|b)*abcd");
        //t1.concatenacion("abc");

        /*for (int i = 0; i < t1.automatas.get(0).obtener_transiciones().size(); i++) {
            System.out.println("___________________________________________");
            System.out.print(t1.automatas.get(0).obtener_transiciones().get(i).estadoInicial);
            System.out.print(t1.automatas.get(0).obtener_transiciones().get(i).siguienteEstado);
            System.out.print(t1.automatas.get(0).obtener_transiciones().get(i).simbolo);
            System.out.println("");
        }*/
        
        
    

    }    
}
