package Axiom;

import java.util.List;

public class AxiomAvanzandoDerecha extends AxiomAvanzando{
    public AxiomAvanzandoDerecha(Dron dron) {
        super(dron);
        this.directions = List.of(dron.directions.get(1), dron.directions.get(2), dron.directions.get(3), dron.directions.get(0));
        this.sonda = dron.sonda;
        this.speed = dron.speed;
    }
}
