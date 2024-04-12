package anillo;
public abstract class Nodo{
    static Nodo arranca(){
        return new NodoToxi();
    }
    public abstract Nodo add(Object valor);
    public abstract Nodo next();
    public abstract Object current();
    public abstract Nodo remove();

}

