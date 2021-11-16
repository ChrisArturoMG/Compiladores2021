public class Transicion {
    public int estadoSiguiente;
    public char simbolo;
    
    public Transicion(int estadoSiguiente, char  simbolo){
        this.estadoSiguiente = estadoSiguiente;
        this.simbolo = simbolo;
    }
    public void renumerar(int estadoSiguiente){
        this.estadoSiguiente = estadoSiguiente;
    }
}