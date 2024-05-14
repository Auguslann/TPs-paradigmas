package Axiom;

import java.util.List;

public class AxiomQuietoIzquierda extends AxiomQuieto{
    public AxiomQuietoIzquierda(Dron dron) {
        this.directions = List.of(dron.directions.get(3), dron.directions.get(0), dron.directions.get(1), dron.directions.get(2));
        this.sonda = dron.sonda;
        this.speed = dron.speed;
    }
}
