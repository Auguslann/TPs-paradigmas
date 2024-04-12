package anillo;
import anillo.NodoAmigo;
class NodoToxi extends Nodo {
    Object valor;
    Nodo siguiente;

    NodoToxi() {
        this.valor = null;
        this.siguiente = this;
    }

    public Nodo next() {
         throw new RuntimeException();
    }
    public Object current() {
        throw new RuntimeException();
    }
    public Nodo add(Object cargo){
        return new NodoAmigo( cargo );
    }
    public Nodo remove(){
        throw new RuntimeException();
    }

}