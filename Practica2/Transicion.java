public class Transicion {
    public int estadoInicial;
    public int siguienteEstado;
    public char simbolo;
    
    public Transicion(int estadoInicial, int siguienteEstado, char  simbolo){
        this.estadoInicial = estadoInicial;
        this.siguienteEstado = siguienteEstado;
        this.simbolo = simbolo;
    }
}
