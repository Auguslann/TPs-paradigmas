package Axiom;

public class Oeste extends Brujula{
    public String getDirection(){
        return "Oeste";
    }
    public Brujula right(){
        return new Norte();
    }
    public Brujula left(){
        return new Sur();
    }
}
