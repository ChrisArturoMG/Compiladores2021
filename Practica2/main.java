import javax.naming.directory.AttributeModificationException;

public class main {
    public static void main (String [] arg){
        Automata a = new Automata();
        //a.cargar_automata("automata.txt");
        
        a.insertar_transicion(1, 2, '0');
        a.insertar_transicion(2, 3, '1');
        a.insertar_transicion(3, 4, '0');       

        System.out.println(a.obtener_transiciones().get(2).estadoInicial);
        a.establecer_inicial(1);
        a.establecer_final(4);

        System.out.println(a.obtener_inicial());
        System.out.println(a.obtener_finales());
        System.out.println(a.ValidarAFD());
        
    }    
}
