package Uno;

import java.util.List;
import java.util.Optional;

public abstract class State {
    public abstract String getMessage();
    public abstract State startGame();
    public abstract State setTopCard(Carta carta);
    public abstract Partida playCard(Carta card, String desiredPlayer);
    public abstract Partida nextTurn();
    public abstract Partida swapDirection();
    public abstract void checkTurn(String desiredPlayer);
}

class EmptyGame extends State {
    String message = "Game has not started yet.";

    Partida partida;

    public EmptyGame(Partida partida) {
        this.partida = partida;
    }

    public String getMessage() {
        return message;
    }

    public State startGame() {
        return Optional.ofNullable(partida.checkMinPlayers() ? new NoHead(partida) : null)
                .orElseThrow(() -> new RuntimeException("Not enough players to start the game."));
    }
    public State setTopCard(Carta carta) {
        throw new RuntimeException("Game has not started yet.");
    }
    public Partida playCard(Carta card, String desiredPlayer) {
        throw new RuntimeException("Game has not started yet.");
    }
    public Partida nextTurn() {
        throw new RuntimeException("Game has not started yet.");
    }
    public Partida swapDirection() {
        throw new RuntimeException("Game has not started yet.");
    }
    public void checkTurn(String desiredPlayer) {
        throw new RuntimeException("Game has not started yet.");
    }
}

    class NoHead extends State {
        String message = "Game is being played.";

        Partida partida;

        public NoHead(Partida partida) {
            this.partida = partida;
        }

        public String getMessage() {
            return message;
        }

        public State startGame() {
            throw new RuntimeException("Game is already being played.");
        }
        public State setTopCard(Carta carta) {
            partida.head = carta;
            return new fowardGame(partida);
        }
        public Partida playCard(Carta card, String desiredPlayer) {
            throw new RuntimeException("There is no head card. Set one first.");
        }
        public Partida nextTurn() {
            throw new RuntimeException("There is no head card. Set one first.");
        }
        public Partida swapDirection() {
            throw new RuntimeException("There is no head card. Set one first.");
        }
        public void checkTurn(String desiredPlayer) {
            throw new RuntimeException("There is no head card. Set one first.");
        }
    }
    class fowardGame extends State {
        String message = "Game is being played.";

        Partida partida;

        public fowardGame(Partida partida) {
            this.partida = partida;
        }

        public String getMessage() {
            return message;
        }

        public State startGame() {
            throw new RuntimeException("Game is already being played.");
        }
        public State setTopCard(Carta carta) {
            throw new RuntimeException("Can't randomly set head. Let player play.");
        }
        public Partida playCard(Carta card, String desiredPlayer) {
            partida.checkTurn(desiredPlayer);

            return partida;
        }
        public Partida nextTurn() {
            partida.turns.nextTurn();
            return partida;
        }
        public Partida swapDirection() {
            partida.turns = partida.turns.swapDirection();
            return partida;
        }
        public void checkTurn(String desiredPlayer) {
            partida.turns.checkTurn(desiredPlayer);
        }
    }






