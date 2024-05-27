package Axiom;

public class SondaDesplegada extends Sonda{
    private String errMessage = "Can't turn with the probe deployed";
    public Sonda desplegarSonda(){
        return new SondaDesplegada();
    }
    public Sonda recuperarSonda(){
        return new SondaNoDesplegada();
    }
    public String getSonda(){
        return "Probe deployed";
    }
    public void right(){
        throw new RuntimeException(errMessage);
    }
    public void left(){
        throw new RuntimeException(errMessage);
    }
    public boolean estadoSonda(){
        return true;
    }
}
