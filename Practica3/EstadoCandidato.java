import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.events.EventTarget;

public class EstadoCandidato {
   List <Integer> estados = new ArrayList<>();
   
    public void establecer_estados(List<Integer> estados){
        this.estados = estados;
    }   
}
