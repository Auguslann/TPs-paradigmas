package Axiom;

public class Moving extends Speed {
       private Integer speed;

       public Moving(int anSpeed) {
           speed = anSpeed + 1;
       }
       public int getSpeed() {
           return speed;
       }

       @Override
       public Speed addSpeed() {
           return new Moving(speed);
       }

       public Speed subSpeed() {
           return (speed - 1 > 0) ? new Moving(speed) : new Static();
       }

       public void candeploy() {
       }
       public Speed decSpeedWhileDeployed(int speed, Sonda sonda) {
           return (speed - 1 == 0 && sonda.estadoSonda()) ? canNotDoIt():this;
       }
       public Speed canNotDoIt() {
           throw new RuntimeException("Can't stop while it's deployed");
       }
   }
