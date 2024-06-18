package Uno;

import java.util.TreeMap;

public abstract class Turns {
    public Turns left;
    public Turns right;
    public abstract Turns nextTurn();
    public abstract Turns add(String player);
    public abstract String currentPlayer();
    public abstract Turns swapDirection(Partida partida);
    public abstract void checkTurn(String desiredPlayer);
}
class EmptyTurns extends Turns {
    Partida partida;
    public EmptyTurns(Partida partida) {
        this.partida = partida;
    }
    public Turns nextTurn() {
        throw new RuntimeException("Game has not started yet.");
    }
    public Turns add(String player) {
        return new forwardTurns(partida, player);
    }
    public String currentPlayer() {
        throw new RuntimeException("Game has not started yet.");
    }
    public Turns swapDirection(Partida partida){
        throw new RuntimeException("Game has not started yet.");
    }
    public void checkTurn(String desiredPlayer) {
        throw new RuntimeException("Game has not started yet.");
    }
}
class forwardTurns extends Turns {
    Partida partida;
    String player;
    Turns right;
    Turns left;
    public forwardTurns(Partida partida, String player) {
        this.partida = partida;
        this.player = player;
        right = this;
        left = this;
    }
    public forwardTurns(Partida partida) {
        this.partida = partida;
    }
    public Turns nextTurn() {
        return right;
    }
    public Turns add(String player) {
        forwardTurns newPlayer = new forwardTurns(partida, player);
        String current = this.currentPlayer();
        this.player = player;
        newPlayer.right = this.right;
        newPlayer.left = this;
        this.right = newPlayer;
        newPlayer.player = current;
        return newPlayer;
    }
    public String currentPlayer() {
        return player;
    }
    public Turns swapDirection(Partida partida) {
        backwardTurns bt = new backwardTurns(partida);
        bt.left = this.left;
        bt.right = this.right;
        return bt;
    }
    public void checkTurn(String desiredPlayer) {
        if (!partida.getCurrentPlayerName().equals(desiredPlayer)) {
            throw new RuntimeException("It's not your turn.");
        }
    }
}
class backwardTurns extends Turns {
    Partida partida;
    String player;
    public backwardTurns(Partida partida) {
        this.partida = partida;
        this.partida.turnos = partida.turnos;
        this.partida.turnos.left = partida.turnos.left;
        this.player = partida.turnos.currentPlayer();
    }
    public Turns nextTurn() {
        return partida.turnos.left;
    }
    public Turns add(String player) {
        return this;
    }
    public String currentPlayer() {
        return player;
    }
    public Turns swapDirection(Partida partida){
        forwardTurns ft = new forwardTurns(partida);
        ft.left = this.left;
        ft.right = this.right;
        return ft;
    }
    public void checkTurn(String desiredPlayer) {
        if (!player.equals(desiredPlayer)) {
            throw new RuntimeException("It's not your turn.");
        }
    }
}
