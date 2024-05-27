package Axiom;

public class Axiom {

    public Speed speed = new Static();
    public Sonda sonda = new SondaNoDesplegada();
    public Brujula direction = new Norte();

    public int Speed(){
        return speed.getSpeed();
    }

    public void right() {
        sonda.right();
        direction = direction.right();
    }
    public void left() {
        sonda.left();
        direction = direction.left();
    }
    public void increaseSpeed() {
        speed = speed.addSpeed();
    }
    public void decreaseSpeed() {
        speed = speed.decSpeedWhileDeployed(speed.getSpeed(), sonda);
        speed = speed.subSpeed();
    }
    public void desplegarSonda() {
        speed.candeploy();
        sonda = sonda.desplegarSonda();
    }
    public void recuperarSonda() {
        sonda = sonda.recuperarSonda();
    }
    public Axiom Orders(char order){
        Commands.getCommand(order).executeCommand(this);
        return this;
    }
    public String getDirection(){
        return direction.getDirection();
    }

    public String getSonda() {
        return sonda.getSonda();
    }
}