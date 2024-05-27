package Axiom;

public class Sur extends Brujula{
    public String getDirection(){
        return "Sur";
    }
    public Brujula right(){
        return new Oeste();
    }
    public Brujula left(){
        return new Este();
    }
}
