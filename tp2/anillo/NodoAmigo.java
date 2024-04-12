package anillo;
class NodoAmigo extends Nodo{
    private Object valor;
    private NodoAmigo siguiente;
    NodoAmigo(Object valor) {
        this.valor = valor;
        siguiente = this;
    }
    public Nodo add(Object cargo) {
        NodoAmigo nuevo = new NodoAmigo(cargo);
        Object current = this.current();
        this.valor = cargo;
        nuevo.siguiente = this.siguiente;
        this.siguiente = nuevo;
        nuevo.valor = current;
        return this;
    }
    public Nodo next(){
        return this.siguiente;
    }
    public Nodo remove(){
        return (this.siguiente == this) ? new NodoToxi() : this.siguiente;
    }
    public Object current(){
        return this.valor;
    }

}