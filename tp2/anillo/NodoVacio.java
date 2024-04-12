package anillo;

class NodoVacio extends NodoPadre {
    Object valor;
    NodoPadre siguiente;

    NodoVacio() {
        this.valor = null;
        this.siguiente = this;
    }

    public NodoPadre next() {
         throw new RuntimeException();
    }
    public Object current() {
        throw new RuntimeException();
    }
    public NodoPadre add(Object cargo){
        return new NodoNoVacio( cargo );
    }
    public NodoPadre remove(){
        throw new RuntimeException();
    }

}