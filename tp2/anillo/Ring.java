package anillo;

import jdk.javadoc.doclet.Doclet;

import javax.naming.CannotProceedException;
import javax.swing.undo.CannotRedoException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class Ring {
    private Nodo nodoActual;

    private static class Nodo {
        Object valor;
        Nodo siguiente;
        Nodo anterior;

        Nodo(Object valor) {
            this.valor = valor;
            this.siguiente = this;
            this.anterior = this;
        }
    }

    public Ring() {
        nodoActual = null;
    }

    public Ring next() {
        nodoActual = (nodoActual != null) ? nodoActual.siguiente : new RuntimeException("El anillo está vacío, no se puede avanzar.");
        return this;
    }

    public Object current() {
        if (nodoActual != null) {
            System.out.println("mostrat");
            System.out.println((nodoActual.valor));
            return nodoActual.valor;
        } else {
            throw new RuntimeException("El anillo está vacío, no hay nodo actual.");
        }
    }

    public Ring add(Object cargo) {
        Nodo nuevoNodo = new Nodo(cargo);
        if (nodoActual == null) {
            nuevoNodo.siguiente = nuevoNodo;
            nodoActual = nuevoNodo;
        } else {
            //mostrarnodo();
            Nodo anterior = nodoActual;
            while(anterior.siguiente!=nodoActual){
                anterior = anterior.siguiente;
            }
            nuevoNodo.siguiente=nodoActual;
            anterior.siguiente = nuevoNodo;
            nodoActual = nuevoNodo;
        }
        return this;
    }

    public Ring remove() {
        //System.out.println(nodoActual.valor);
        if (nodoActual != null) {
            if (nodoActual.siguiente == nodoActual) {
                System.out.println("No toma una linea");
                nodoActual = null; // Si solo hay un nodo en el anillo
            } else {
                Nodo nodoAnterior = nodoActual.anterior;
                Nodo nodoSiguiente = nodoActual.siguiente;
                nodoAnterior.siguiente = nodoSiguiente;
                nodoSiguiente.anterior= nodoAnterior;
                nodoActual = nodoSiguiente;

            }
        }
        return this;
    }
    private RuntimeException throwRuntimeException(String mensaje) {
        return new RuntimeException(mensaje);
    }
    }


