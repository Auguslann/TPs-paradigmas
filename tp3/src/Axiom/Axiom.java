package Axiom;

import java.util.Map;

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

    public interface OrderHandler {
        void handle(Drone drone);
    }

    public Axiom Orders(char orden) {
        OrderHandler handler = HANDLERS.get(orden);
        handler.handle(drone);
        return this;
    }

    public class IncreaseSpeedHandler implements OrderHandler {
        @Override
        public void handle(Drone drone) {
            drone.speed = drone.speed.addSpeed();
        }
    }

    public class DecreaseSpeedHandler implements OrderHandler {
        @Override
        public void handle(Drone drone) {
            drone.sonda.Speed();
            drone.speed = drone.speed.subSpeed();
        }
    }

    public class TurnLeftHandler implements OrderHandler {
        @Override
        public void handle(Drone drone) {
            drone.sonda = drone.sonda.left();
            drone.direction = drone.direction.left();
        }
    }

    public class TurnRightHandler implements OrderHandler {
        @Override
        public void handle(Drone drone) {
            drone.sonda = drone.sonda.right();
            drone.direction = drone.direction.right();
        }
    }

    public class DeployProbeHandler implements OrderHandler {
        @Override
        public void handle(Drone drone) {
            drone.speed.candeploy();
            drone.sonda = drone.sonda.desplegarSonda();
        }
    }

    public class RetrieveProbeHandler implements OrderHandler {
        @Override
        public void handle(Drone drone) {
            drone.sonda = drone.sonda.recuperarSonda();
        }
    }

    private final Map<Character, OrderHandler> HANDLERS = Map.of(
            'i', new IncreaseSpeedHandler(),
            's', new DecreaseSpeedHandler(),
            'l', new TurnLeftHandler(),
            'r', new TurnRightHandler(),
            'd', new DeployProbeHandler(),
            'f', new RetrieveProbeHandler()
            // Agrega los otros handlers aquí
    );
//    public Axiom Orders(char orden){
//        if(orden == 'i') {
//            drone.speed = drone.speed.addSpeed();
//            return this;
//        }
//        if(orden == 's') {
//            drone.speed = drone.speed.subSpeed();
//            return this;
//        }
//        if(orden == 'l') {
//            drone.sonda = drone.sonda.left();
//            drone.direction = drone.direction.left();
//            return this;
//        }
//        if(orden == 'r') {
//            drone.sonda = drone.sonda.right();
//            drone.direction = drone.direction.right();
//            return this;
//        }
//        if(orden == 'd') {
//            drone.speed.candeploy();
//            drone.sonda = drone.sonda.desplegarSonda();
//            return this;
//        }
//        if(orden == 'f') {
//            drone.sonda = drone.sonda.recuperarSonda();
//            return this;
//        }
//        return this;
//
//    }
    public String getDirection(){
        return drone.direction.getDirection();
    }

    public String getSonda() {
        return drone.sonda.getSonda();
    }
}

