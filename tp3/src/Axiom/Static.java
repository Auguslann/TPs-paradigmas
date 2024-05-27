package Axiom;

public class Static extends Speed {

        public int getSpeed() {
            return 0;
        }

        public Speed addSpeed() {
            return new Moving(0);
        }

        public Speed subSpeed() {
            return this;
        }

        public void candeploy() {
            throw new RuntimeException("Can't deploy it it's not moving");
        }
        public Speed decSpeedWhileDeployed(int speed, Sonda sonda) {
            return this;
        }
    }
