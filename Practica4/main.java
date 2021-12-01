import javax.naming.directory.AttributeModificationException;
import javax.print.event.PrintEvent;

public class main {
    public static void main (String [] arg){
        Arbol a = new Arbol();
        a.ordenarJerarquia("(a|b)*abb");
    }    
}