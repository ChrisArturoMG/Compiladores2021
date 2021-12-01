import java.util.ArrayList;
import java.util.List;

public class EstadoCandidato {
   List <Integer> estados;
   protected int numero_estado;
   List <Integer> nucleo;

    public EstadoCandidato(List<Integer> estados){
        this.estados =  new ArrayList<>(estados);;
    }

    public void establecer_nucleo(List<Integer> nucleo){
        this.nucleo =  new ArrayList<>(nucleo);
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
    public int obtener_estado() {
        return numero_estado;
    }
    public List<Integer> obtener_nucleo(){
        return nucleo;
    }
}
