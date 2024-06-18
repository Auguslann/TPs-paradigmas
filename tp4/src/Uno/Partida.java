package Uno;
import java.util.AbstractMap.SimpleEntry;
import java.util.LinkedList;
import java.util.List;

public class Partida {

    LinkedList<SimpleEntry<String, List<Carta>>> playerCards;

    List<Carta> deck;
    Turns turnos = new EmptyTurns(this);
    Carta head;

    State state;

    public Partida(LinkedList<SimpleEntry<String, List<Carta>>> playerCards, List<Carta> deck){
        this.playerCards = playerCards;
        this.deck = deck;
        this.state = new NoPlayers(this);
        playerCards.stream().forEach(player -> turnos = turnos.add(player.getKey()));
    }

    public State getState(){
        return state;
    }
    public Partida nextTurn(){
        this.state.nextTurn();
        return this;
    }
    public Boolean getMinNumberPLayers() {
        return playerCards.size()>=2;
    }
    public String getCurrentPlayerName() {
        return this.state.getCurrentPlayer();
    }
    public Partida startGame(){
        this.state = this.state.startGame();
        return this;
    }
    public Partida setTopCard(Carta head) {
        this.state = this.state.setHead(head);
        return this;
    }
    public Partida play(Carta card, String desiredPlayer, String possibleColor) {
        this.checkEndGame();
        Partida partida = this.state.playCard(card, desiredPlayer);
        partida.head.color = possibleColor;

        return partida;
    }
    public void checkTurn(String desiredPlayer) {
        this.state.checkTurn(desiredPlayer);
    }
    public Carta checkCardContentionAndPop(Carta card, String desiredPlayer){
        List<Carta> playerHand = playerCards.stream()
                .filter(entry -> entry.getKey().equals(desiredPlayer))
                .findFirst()
                .map(SimpleEntry::getValue)
                .orElseThrow(() -> new RuntimeException("Player not found."));

        Carta cardToRemove = playerHand.stream()
                .filter(playerCard -> playerCard.getColor().equals(card.getColor()) && playerCard.getValor().equals(card.getValor()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Player does not have that card."));

        return pretendPop(playerHand, cardToRemove);

    }
    public Partida drawCard(String desiredPlayer) {
        this.checkEndGame();
        this.checkTurn(desiredPlayer);
        List<Carta> playerHand = playerCards.stream()
                .filter(entry -> entry.getKey().equals(desiredPlayer))
                .findFirst()
                .map(SimpleEntry::getValue)
                .orElseThrow(() -> new RuntimeException("Player not found."));

        playerHand.get(playerHand.size()-1).unoState = false;
        playerHand.add(deck.get(0));
        deck.remove(0);
        return this;
    }
    public Partida drawCardForUNO(String desiredPlayer) {
        this.checkEndGame();
        List<Carta> playerHand = playerCards.stream()
                .filter(entry -> entry.getKey().equals(desiredPlayer))
                .findFirst()
                .map(SimpleEntry::getValue)
                .orElseThrow(() -> new RuntimeException("Player not found."));

        playerHand.get(playerHand.size()-1).unoState = false;
        playerHand.add(deck.get(0));
        deck.remove(0);
        return this;
    }
    private Carta pretendPop(List<Carta> playerHand, Carta cardToRemove) {
        playerHand.remove(cardToRemove);
        return cardToRemove;
    }
    public int getPlayerCards(String player) {
        return playerCards.stream().filter(p -> p.getKey().equals(player)).findFirst().get().getValue().size();
    }
    public Partida sayUNO(String player) {
        this.checkEndGame();
        List<Carta> playerHand = playerCards.stream()
                .filter(entry -> entry.getKey().equals(player) && entry.getValue().size() == 1)
                .findFirst()
                .map(SimpleEntry::getValue)
                .orElseThrow(() -> new RuntimeException("Player not found/has more than 1 card."));
        playerHand.get(0).unoState = true;
        return this;
    }
    public Partida sayUNOto(String player) {
        this.checkEndGame();
        List<Carta> playerHand = playerCards.stream()
                .filter(entry -> entry.getKey().equals(player) && entry.getValue().size() == 1 && !entry.getValue().get(0).unoState)
                .findFirst()
                .map(SimpleEntry::getValue)
                .orElseThrow(() -> new RuntimeException("Player not found/has already said one."));
        playerHand.get(0).unoState = false;
        drawCardForUNO(player);
        drawCardForUNO(player);
        return this;
    }
    public void checkEndGame() {
        playerCards.stream()
                .filter(player -> player.getValue().isEmpty())
                .findFirst().map(player -> {
            this.state = new EndGame(this);
            return this;
                }).orElse(this);
    }
}