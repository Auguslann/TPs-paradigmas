package anillo;

import jdk.javadoc.doclet.Doclet;

import javax.naming.CannotProceedException;
import javax.swing.undo.CannotRedoException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Ring {
    private List<Object> anillos;
    public Ring(){
        anillos = new ArrayList<>();
    }
    public Ring next() {
        Object primero = anillos.get(0);
        anillos.remove(0);
        anillos.add(primero);
        return this;
    }
    public Object current() {
        return anillos.getFirst();
    }
    public Ring add(Object cargo ) {
        reversed();
        anillos.add(cargo);
        reversed();
        return this;
    }

    private void reversed() {
        anillos = anillos.reversed();
    }

    public Ring remove() {
        anillos.removeFirst();
        return this;
    }
}
