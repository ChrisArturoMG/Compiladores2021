import java.util.ArrayList;
import java.util.List;

import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;

import org.w3c.dom.events.EventTarget;

public class EstadoCandidato {
   List <Integer> estados = new ArrayList<>();
   protected int numero_estado;

    public EstadoCandidato(List<Integer> estados){
        this.estados = estados;
    }
   
    public void establecer_estados(List<Integer> estados){
        this.estados = estados;
    }   
    public void establecer_estado(int numero_estado){
        this.numero_estado = numero_estado;
    }
    public List<Integer> obtener_estados(){
        return estados;
    }
}
