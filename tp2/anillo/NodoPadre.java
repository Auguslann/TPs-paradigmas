package anillo;
public abstract class NodoPadre {
    static NodoPadre arranca(){
        return new NodoVacio();
    }
    public abstract NodoPadre add(Object valor);
    public abstract NodoPadre next();
    public abstract Object current();
    public abstract NodoPadre remove();

}

