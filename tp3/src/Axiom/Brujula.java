package Axiom;

public abstract class Brujula {
    public abstract String getDirection();
    public abstract Brujula right();
    public abstract Brujula left();

    public static class Norte extends Brujula{
        public String getDirection(){
            return "Norte";
        }
        public Brujula right(){
            return new Este();
        }
        public Brujula left(){
            return new Oeste();
        }
    }

    public static class Este extends Brujula{
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

    public static class Sur extends Brujula{
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

    public static class Oeste extends Brujula{
        public String getDirection(){
            return "Oeste";
        }
        public Brujula right(){
            return new Norte();
        }
        public Brujula left(){
            return new Sur();
        }
    }
}
