package Uno;
import java.util.Optional;


public abstract class State {

    protected Partida partida;
    protected String message;


    public abstract String getMessage();
    public abstract State startGame();
    public abstract State setHead(Carta head);
    public abstract Partida playCard(Carta card, String desiredPlayer);
    public abstract Partida nextTurn();
    public abstract void checkTurn(String desiredPlayer);
    public abstract String getCurrentPlayer();
    public abstract State swapTurns();


}
class NoPlayers extends State{
    String message = "Game has not started yet.";

    Partida partida;
    public NoPlayers(Partida partida) {
        this.partida = partida;
        partida.turnos = new EmptyTurns(partida);
        partida.turnos.left = partida.turnos;
        partida.turnos.right = partida.turnos;
    }

    public String getMessage() {
        return message;
    }

    public State startGame(){
        return Optional.ofNullable(partida.getMinNumberPLayers() ? new FirstCard(partida) : null)
                .orElseThrow(() -> new RuntimeException("Not enough players to start the game."));
    }

    public State setHead(Carta head) {
        throw new RuntimeException("Define Players Before");
    }

    public Partida playCard(Carta card, String desiredPlayer) {
        throw new RuntimeException("Game has not started yet.");
    }

    public Partida nextTurn() {
        throw new RuntimeException("Game has not started yet.");
    }

    public void checkTurn(String desiredPlayer) {}

    public String getCurrentPlayer() {
        throw new RuntimeException("Game has not started yet.");
    }
    public State swapTurns() {
        throw new RuntimeException("Game has not started yet.");
    }
}
class FirstCard extends State{
    String message = "Game has started, waiting for the first card to be played.";

    Partida partida;
    public FirstCard(Partida partida) {
        this.partida = partida;
    }

    public String getMessage() {
        return message;
    }

    public State startGame(){
        throw new RuntimeException("Game has already started.");
    }

    public State setHead(Carta head) {
        partida.head = head;

        return new Playing(partida);
    }

    public Partida playCard(Carta card, String desiredPlayer) {
        throw new RuntimeException("Game has not started yet.");
    }

    public Partida nextTurn() {
        partida.turnos = partida.turnos.nextTurn();
        return partida;
    }

    public void checkTurn(String desiredPlayer) {
        this.partida.turnos.checkTurn(desiredPlayer);
    }

    public String getCurrentPlayer() {
        return partida.turnos.currentPlayer();
    }
    public State swapTurns() {
        throw new RuntimeException("Game has not started yet.");
    }
}
class Playing extends State {
    String message = "Game is being played.";

    Partida partida;

    public Playing(Partida partida) {
        this.partida = partida;
    }

    public String getMessage() {
        return message;
    }

    public State startGame() {
        throw new RuntimeException("Game is already being played.");
    }

    public State setHead(Carta head) {
        throw new RuntimeException("Game is already being played.");
    }

    public Partida playCard(Carta card, String desiredPlayer) {
        Carta head = partida.head;
        this.checkTurn(desiredPlayer);
        partida.checkCardContentionAndPop(card, desiredPlayer);
        Carta comparison = head.getComparison(card);
        card.executeAction(partida, card);
        partida.head = card;
        return partida;
    }
    public void checkTurn(String desiredPlayer) {
        partida.turnos.checkTurn(desiredPlayer);
    }
    public Partida nextTurn() {
        partida.turnos = partida.turnos.nextTurn();
        return partida;
    }
    public String  getCurrentPlayer() {
        return partida.turnos.currentPlayer();
    }
    public State swapTurns() {
        System.out.println(partida.turnos);
        partida.turnos = partida.turnos.swapDirection(partida);
        return this;
    }
}
class EndGame extends State{
    public String message = "Game has ended.";

    public EndGame(Partida partida) {
        this.partida = partida;
    }
    public String getMessage() {
        return message;
    }
    public State startGame() {
        throw new RuntimeException("Game has already ended.");
    }
    public State setHead(Carta head) {
        throw new RuntimeException("Game has already ended.");
    }
    public Partida playCard(Carta card, String desiredPlayer) {
        throw new RuntimeException("Game has already ended.");
    }
    public Partida nextTurn() {
        throw new RuntimeException("Game has already ended.");
    }
    public void checkTurn(String desiredPlayer) {
        throw new RuntimeException("Game has already ended.");
    }
    public String getCurrentPlayer() {
        throw new RuntimeException("Game has already ended.");
    }
    public State swapTurns() {
        throw new RuntimeException("Game has already ended.");
    }

}
