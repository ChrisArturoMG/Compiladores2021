public class Transicion {
    public int estadoinicial;
    public int siguienteEstado;
    public char simbolo;
    
    public Transicion(int estadoinicial, int siguienteEstado, char  simbolo){
        this.estadoinicial = estadoinicial;
        this.siguienteEstado = siguienteEstado;
        this.simbolo = simbolo;
    }
}
