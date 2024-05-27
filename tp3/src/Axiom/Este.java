package Axiom;

public class Este extends Brujula{
    public String getDirection(){
        return "Este";
    }
    public Brujula right(){
        return new Sur();
    }
    public Brujula left(){
        return new Norte();
    }
}
