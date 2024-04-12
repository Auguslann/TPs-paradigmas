package anillo;
class NodoNoVacio extends NodoPadre {
    private Object valor;
    private NodoNoVacio siguiente;
    NodoNoVacio(Object valor) {
        this.valor = valor;
        siguiente = this;
    }
    public NodoPadre add(Object cargo) {
        NodoNoVacio nuevo = new NodoNoVacio(cargo);
        Object current = this.current();
        this.valor = cargo;
        nuevo.siguiente = this.siguiente;
        this.siguiente = nuevo;
        nuevo.valor = current;
        return this;
    }
    public NodoPadre next(){
        return this.siguiente;
    }
    public NodoPadre remove(){
        return (this.siguiente == this) ? new NodoVacio() : this.siguiente;
    }
    public Object current(){
        return this.valor;
    }

}