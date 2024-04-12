package anillo;

import jdk.javadoc.doclet.Doclet;

import javax.naming.CannotProceedException;
import javax.swing.undo.CannotRedoException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public  class Ring {
    private Nodo nodoActual;

    public Ring() {
        nodoActual = Nodo.arranca();
    }

    public Ring next() {
        nodoActual = nodoActual.next();
        return this;
    }

    public Object current() {
        return nodoActual.current();
    }

    public Ring add(Object cargo) {
        nodoActual = nodoActual.add(cargo);
        return this;
    }

    public Ring remove() {
        nodoActual = nodoActual.remove();
        return this;
    }
}


