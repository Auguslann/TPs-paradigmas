package anillo;

public  class Ring {
    private NodoPadre nocoActual;

    public Ring() {
        nocoActual = NodoPadre.arranca();
    }

    public Ring next() {
        nocoActual = nocoActual.next();
        return this;
    }

    public Object current() {
        return nocoActual.current();
    }

    public Ring add(Object cargo) {
        nocoActual = nocoActual.add(cargo);
        return this;
    }

    public Ring remove() {
        nocoActual = nocoActual.remove();
        return this;
    }
}


