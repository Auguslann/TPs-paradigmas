package Axiom;

public class AxiomAvanzando extends Dron{
    public AxiomAvanzando(Dron dron) {
        this.directions = dron.directions;
        this.sonda = dron.sonda;
        this.speed = dron.speed+1;
    }
    public Dron Orders(char orden) {
        if (orden == 'i') {
            this.speed++;
            return new AxiomAvanzando(this);
        }
        if (orden == 's'){
            this.speed--;
            return (speed > 0) ? new AxiomAvanzando(this) : new AxiomQuieto(this);
        }
        if (orden == 'd') {
            this.sonda = sondaoff;
            return new AxiomAvanzando(this);
        }
        if (orden == 'r') {
            return new AxiomAvanzandoDerecha(this);
        }
        if (orden == 'f') {
            return new AxiomAvanzando(this);
        }
        return new AxiomQuieto(this);
    }
    public int Speed() {
        return speed;
    }
    public String getDirection() {
        return directions.getFirst();
    }
    public String getSonda() {
        return this.sonda;
    }
}
