package Axiom;

public abstract class Speed {
    public abstract int getSpeed();

    public abstract Speed addSpeed();

    public abstract Speed subSpeed();

    public abstract void candeploy();

    public abstract Speed decSpeedWhileDeployed(int speed, Sonda sonda);
}

