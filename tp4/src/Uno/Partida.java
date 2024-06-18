package Uno;
import java.util.*;
import java.util.AbstractMap.SimpleEntry;

public class Partida {

    private LinkedList<SimpleEntry<String, List<Carta>>> playerCards;
    ListIterator<SimpleEntry<String, List<Carta>>> currentPlayer;

    private List<Carta> mazo;
    public int plus2Counter = 0;
    Turns turns = new EmptyTurns(this);
    Carta head;

    State gameStatus;

    public Partida(LinkedList<SimpleEntry<String, List<Carta>>> playerCards, List<Carta> deck){
        this.playerCards = playerCards;
        this.mazo = deck;
        this.currentPlayer = playerCards.listIterator();
        this.gameStatus = new EmptyGame(this);
        playerCards.forEach(player -> {
            turns.add(player.getKey());
        });
    }
    public State getState(){
        return gameStatus;
    }
    public boolean checkMinPlayers() {
        return playerCards.size() >= 2;
    }
    public Partida startGame(){
        this.gameStatus = this.gameStatus.startGame();
        return this;
    }
    public Partida setTopCard(Carta carta){
        this.gameStatus = this.gameStatus.setTopCard(carta);
        return this;
    }
    public Carta head(){
        return head;
    }
    public String getCurrentPlayerName(){
        return this.turns.currentPlayer();
    }
    public Partida playCard(Carta card, String desiredPlayer) {
        Partida partida = this.gameStatus.playCard(card, desiredPlayer);
        return partida;
    }
    public Partida beginPut(Carta card, String desiredPlayer) {
        card = this.checkCardContentionAndPop(card, desiredPlayer);
        this.checkTurn(desiredPlayer);
        this.head = this.head.getComparison(card); // Compara si la carta se puede agregar
        head.comparePlus2(this);
        head.executeAction(this, card);
        return this;
    }
    public Partida drawCard(String desiredPlayer) {
        this.checkEnd();
        this.checkTurn(desiredPlayer);
        List<Carta> playerHand = playerCards.stream()
                .filter(entry -> entry.getKey().equals(desiredPlayer))
                .findFirst()
                .map(SimpleEntry::getValue)
                .orElseThrow(() -> new RuntimeException("Player not found."));

        playerHand.get(playerHand.size()-1).unoState = false;
        playerHand.add(mazo.get(0));
        mazo.remove(0);
        return this;
    }

    private Carta pretendPop(List<Carta> playerHand, Carta cardToRemove) {
        playerHand.remove(cardToRemove);
        return cardToRemove;
    }
    public void drawCardUNOCase(String desiredPlayer) {
        List<Carta> playerHand = playerCards.stream()
                .filter(entry -> entry.getKey().equals(desiredPlayer))
                .findFirst()
                .map(SimpleEntry::getValue)
                .orElseThrow(() -> new RuntimeException("Player not found."));

        playerHand.get(playerHand.size()-1).unoState = false;
        playerHand.add(deck.get(0));
        deck.remove(0);
    }
    public Carta checkCardContentionAndPop(Carta card, String desiredPlayer){
        // Get the desired player's hand
        List<Carta> playerHand = playerCards.stream()
                .filter(entry -> entry.getKey().equals(desiredPlayer))
                .findFirst()
                .map(SimpleEntry::getValue)
                .orElseThrow(() -> new RuntimeException("Player not found."));

        Carta cardToRemove = playerHand.stream()
                .filter(playerCard -> playerCard.getColor().equals(card.getColor()) && playerCard.getValor() == card.getValor())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Player does not have that card."));

        return pretendPop(playerHand, cardToRemove);

    }
    public void setUnoState(String desiredPlayer) {
        playerCards.stream()
                .filter(entry -> entry.getKey().equals(desiredPlayer) && entry.getValue().size() == 1)
                .forEach(entry -> entry.getValue().get(0).unoState = true);
    }

    public Partida personalUNO(String desiredPlayer) {
        this.checkEnd();
        playerCards.stream()
                .filter(entry -> entry.getKey().equals(desiredPlayer))
                .findFirst()
                .ifPresent(entry -> entry.getValue().get(0).unoState = false);
        return this;
    }

    public Partida shoutUNOTo(String targetPlayer) {
        this.checkEnd();
        // Find the target player in the playerCards list
        List<Carta> targetPlayerHand = playerCards.stream()
                .filter(entry -> entry.getKey().equals(targetPlayer))
                .findFirst()
                .map(AbstractMap.SimpleEntry::getValue)
                .orElseThrow(() -> new RuntimeException("Player not found."));

        // Check if the unoState of the first card is true
        if (!targetPlayerHand.isEmpty() && targetPlayerHand.get(0).unoState) {
            // If it is, make the player draw two cards
            drawCardUNOCase(targetPlayer);
            drawCardUNOCase(targetPlayer);
        }
        return this;
    }

    public Partida checkEnd() {
        // Use Stream API to check if any player has an empty hand
        playerCards.stream()
                .filter(entry -> entry.getValue().isEmpty())
                .findFirst()
                .map(entry -> {
                    this.state = new finishedGame(this);
                    return this;
                })
                .orElse(this);

        return this;
    }
    public Partida checkTurn(String desiredPlayer) {
        gameStatus.checkTurn(desiredPlayer);
        return this;
    }
    public void nextTurn() {
        gameStatus.nextTurn();
    }
    public Partida swapDirection() {
        gameStatus.swapDirection();
        return this;
    }

}
