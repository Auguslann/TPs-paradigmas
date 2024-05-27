package Axiom;

import java.util.List;

public abstract class Commands {
    public static Commands getCommand(char command) {
        return List.of(new turnRight(), new turnLeft(), new increaseSpeed(), new decreaseSpeed(), new desplegarSonda(), new recuperarSonda()).stream().filter((any) -> any.command(command)).findAny().orElseThrow(() -> new RuntimeException("Invalid command"));
    }
    public abstract char command();
    public abstract void executeCommand(Axiom drone);
    private boolean command(char command) {
        return command() == command;
    }
}
class turnRight extends Commands {
    public char command() {
        return 'r';
    }
    public void executeCommand(Axiom drone) {
        drone.right();
    }
}
class turnLeft extends Commands {
    public char command() {
        return 'l';
    }
    public void executeCommand(Axiom drone) {
        drone.left();
    }
}
class increaseSpeed extends Commands {
    public char command() {
        return 'i';
    }
    public void executeCommand(Axiom drone) {
        drone.increaseSpeed();
    }
}
class decreaseSpeed extends Commands {
    public char command() {
        return 's';
    }
    public void executeCommand(Axiom drone) {
        drone.decreaseSpeed();
    }
}
class desplegarSonda extends Commands {
    public char command() {
        return 'd';
    }
    public void executeCommand(Axiom drone) {
        drone.desplegarSonda();
    }
}
class recuperarSonda extends Commands {
    public char command() {
        return 'f';
    }
    public void executeCommand(Axiom drone) {
        drone.recuperarSonda();
    }
}
