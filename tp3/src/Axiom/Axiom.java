package Axiom;

import java.util.List;

public class Axiom {

    public class Drone {
        public Speed speed = new Speed.Static();
        public Sonda sonda = new Sonda.SondaRecuperada();
        public Brujula direction = new Brujula.Norte();


    }
    public Drone drone = new Drone();
    public int Speed(){
        return drone.speed.getSpeed();
    }
    public Axiom Orders(char orden){
        if(orden == 'i') {
            drone.speed = drone.speed.addSpeed();
            return this;
        }
        if(orden == 's') {
            drone.speed = drone.speed.subSpeed();
            return this;
        }
        if(orden == 'l') {
            drone.sonda = drone.sonda.left();
            drone.direction = drone.direction.left();
            return this;
        }
        if(orden == 'r') {
            drone.sonda = drone.sonda.right();
            drone.direction = drone.direction.right();
            return this;
        }
        if(orden == 'd') {
            drone.speed.candeploy();
            drone.sonda = drone.sonda.desplegarSonda();
            return this;
        }
        if(orden == 'f') {
            drone.sonda = drone.sonda.recuperarSonda();
            return this;
        }
        return this;

    }
    public String getDirection(){
        return drone.direction.getDirection();
    }
    public String getSonda() {
        return drone.sonda.getSonda();
    }
}

