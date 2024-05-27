package Axiom;

public class Norte extends Brujula{
    public String getDirection(){
        return "Norte";
    }
    public Brujula right(){
        return new Este();
    }
    public Brujula left(){
        return new Oeste();
    }
}
