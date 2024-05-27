package Axiom;

public class SondaNoDesplegada extends Sonda {
    public Sonda desplegarSonda() {
        return new SondaDesplegada();
    }

    public Sonda recuperarSonda() {
        return new SondaNoDesplegada();
    }

    public String getSonda() {
        return "Probe not deployed";
    }

    public void right() {

    }

    public void left() {

    }

    public boolean estadoSonda() {
        return false;
    }
}
