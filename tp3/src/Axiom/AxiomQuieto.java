package Axiom;

import java.util.List;
import java.util.Map;

public class AxiomQuieto extends Dron{

   public AxiomQuieto(){
        this.directions = List.of("Norte", "Este", "Sur", "Oeste");
        this.sonda = sondaon;
        this.speed = 0;
   }
    public AxiomQuieto(Dron dron) {
        this.directions = dron.directions;
        this.sonda = dron.sonda;
        this.speed = dron.speed;
    }

    @FunctionalInterface
    interface AxiomSupplier {
        Dron get(Dron dron);
    }


    private static final Map<Character, AxiomSupplier> ORDERS_MAP = Map.of(
            'i', AxiomAvanzando::new,
            's', AxiomQuieto::new,
            'r', AxiomQuietoDerecha::new,
            'l', AxiomQuietoIzquierda::new,
            'd', AxiomQuieto::throwException,
            'f', AxiomQuieto::throwException
    );

    private static Dron throwException(Dron dron) {
        throw new RuntimeException("Too Slow");
    }

    public Dron Orders(char orden) {
        AxiomSupplier supplier = ORDERS_MAP.getOrDefault(orden, AxiomQuieto::new);
        return supplier.get(this);
    }
    public int Speed() {
        return speed;
    }
    public String getDirection() {
        return this.directions.getFirst();
    }
    public String getSonda() {
        return this.sonda;
    }

}
