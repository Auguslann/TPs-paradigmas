package Axiom;

import java.util.List;

public class Axiom {
    Dron dron;
    public Axiom() {
        this.dron = new AxiomQuieto();
    }
    public Dron Orders(char orden) {
        dron = dron.Orders(orden);
        return dron;
    }
    public int Speed() {
        return dron.Speed();
    }
    public String getDirection() {
        return dron.getDirection();
    }

//    public Integer speed;
//    public String sonda;
//    public String sondaoff = "Sonda desplegada";
//    public String sondaon = "Sonda recuperada";
//    public List<String> directions = List.of("Norte", "Este", "Sur", "Oeste");
//    public Axiom(){
//        speed = 0;
//    }
//    public int Speed(){
//        return speed;
//    }
//
//    public Axiom Orders(char orden){
//        if(orden == 'i') {
//            speed++;
//            return this;
//        }
//        if(orden == 's' && speed > 0) {
//            speed--;
//            return this;
//        }
//        if(orden == 'l') {
//            directions = List.of(directions.get(3), directions.get(0), directions.get(1), directions.get(2));
//        }
//        if(orden == 'r') {
//            directions = List.of(directions.get(1), directions.get(2), directions.get(3), directions.get(0));
//        }
//        if(orden == 'd') {
//            if (speed == 0){
//                throw new RuntimeException("Too Slow");
//            }
//            sonda = sondaoff;
//        }
//        if(orden == 'f') {
//            sonda = sondaon;
//        }
//        return this;
//
//    }
//    public String getDirection(){
//        return directions.getFirst();
//    }
//
//    public String getSonda() {
//        return sonda;
//    }
}
