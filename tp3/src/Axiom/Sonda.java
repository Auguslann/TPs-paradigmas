package Axiom;

public abstract class Sonda {
    public abstract Sonda desplegarSonda();
    public abstract Sonda recuperarSonda();
    public abstract String getSonda();
    public abstract Sonda right();
    public abstract Sonda left();
    public abstract void Speed();

    public static class SondaDesplegada extends Sonda{
        String errMessage = "Can't turn with the probe deployed";
        public Sonda desplegarSonda(){
            return new SondaDesplegada();
        }
        public Sonda recuperarSonda(){
            return new SondaRecuperada();
        }
        public String getSonda(){
            return "Sonda desplegada";
        }
        public Sonda right(){
            throw new RuntimeException(errMessage);
        }
        public Sonda left(){
            throw new RuntimeException(errMessage);
        }
        public void Speed(){
            throw new RuntimeException("Can't decrease speed with the probe deployed");
        }
    }

    public static class SondaRecuperada extends Sonda{
        public Sonda desplegarSonda(){
            return new SondaDesplegada();
        }
        public Sonda recuperarSonda(){
            return new SondaRecuperada();
        }
        public String getSonda(){
            return "Sonda recuperada";
        }
        public Sonda right(){
            return this;
        }
        public Sonda left(){
            return this;
        }
        public void Speed(){
        }
    }
}
