package Uno;

public abstract class Turns {
    public abstract Turns nextTurn();
    public abstract Turns add(String player);
    public abstract String currentPlayer();
    public abstract Turns swapDirection();
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
        public Turns swapDirection() {
            throw new RuntimeException("Game has not started yet.");
        }
        public void checkTurn(String desiredPlayer) {
            throw new RuntimeException("Game has not started yet.");
        }
    }
    class forwardTurns extends Turns {
        Partida partida;
        String player;
        forwardTurns right;
        forwardTurns left;
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
        public State swapper() {
            return new NoHead(partida);
        }
        public Turns add(String player) {
            forwardTurns newPlayer = new forwardTurns(partida, player);
            newPlayer.right = this;
            newPlayer.left = this.left;
            this.left.right = newPlayer;
            this.left = newPlayer;
            return newPlayer;
        }
        public String currentPlayer() {
            return player;
        }
        public Turns swapDirection() {
            return new backwardTurns(partida);
        }
        public void checkTurn(String desiredPlayer) {
            if (!player.equals(desiredPlayer)) {
                throw new RuntimeException("It's not your turn.");
            }
        }
    }
    class backwardTurns extends Turns {
        Partida partida;
        String player;
        backwardTurns right;
        backwardTurns left;
        public backwardTurns(Partida partida, String player) {
            this.partida = partida;
            this.player = player;
            right = this;
            left = this;
        }
        public backwardTurns(Partida partida) {
            this.partida = partida;
        }
        public Turns nextTurn() {
            return left;
        }
        public Turns add(String player) {
            backwardTurns newPlayer = new backwardTurns(partida, player);
            newPlayer.right = this;
            newPlayer.left = this.left;
            this.left.right = newPlayer;
            this.left = newPlayer;
            return newPlayer;
        }
        public String currentPlayer() {
            return player;
        }
        public Turns swapDirection() {
            return new forwardTurns(partida);
        }
        public void checkTurn(String desiredPlayer) {
            if (!player.equals(desiredPlayer)) {
                throw new RuntimeException("It's not your turn.");
            }
        }
    }
