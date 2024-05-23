package Axiom;

public abstract class Speed {
    public abstract int getSpeed();


    public abstract Speed addSpeed();

    public abstract Speed subSpeed();
    public abstract void candeploy();

    public static class Moving extends Speed {
        Integer speed;

        public Moving(int speed) {
            this.speed = speed + 1;
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
        public void candeploy(){;
        }
    }

    public static class Static extends Speed{
            Integer speed = 0;
            public int getSpeed(){
                return speed;
            }
            public Speed addSpeed(){
                return new Moving(speed);
            }
            public Speed subSpeed(){
                return this;
            }
            public void candeploy(){
                throw new RuntimeException("Can't deploy it it's not moving");
            }
        }
}
