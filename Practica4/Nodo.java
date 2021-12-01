public class Nodo {
    String caracter;
    Nodo hijo_izquierdo, hijo_derecho;

    void establecer_tipo(String caracter){
        this.caracter = caracter;
    }

    void establecer_hijo_izquierdo(Nodo hijo_izquierdo){
        this.hijo_izquierdo = hijo_izquierdo;
    }

    void establecer_hijo_derecho(Nodo hijo_derecho){
        this.hijo_derecho = hijo_derecho;
    }

    
}
