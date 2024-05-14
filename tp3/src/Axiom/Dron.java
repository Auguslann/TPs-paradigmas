package Axiom;

import java.util.List;

public abstract class Dron{
    public Integer speed;
    public String sonda;
    public String sondaoff = "Sonda desplegada";
    public String sondaon = "Sonda recuperada";
    public List<String> directions = List.of("Norte", "Este", "Sur", "Oeste");
    public abstract Dron Orders(char orden);
    public abstract int Speed();
    public abstract String getDirection();
    public abstract String getSonda();

}
