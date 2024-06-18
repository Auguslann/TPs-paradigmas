package Uno;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import java.util.AbstractMap.SimpleEntry;




public class PartidaTest {
    List<Carta> deck = new ArrayList<>(Arrays.asList( new NumberCard(2, "Red"), new NumberCard(2, "Blue"), new NumberCard(2, "Green"), new NumberCard(2, "Yellow")));
    List<Carta> player1Cards = new ArrayList<>(Arrays.asList( new SkipCard("Blue"),new NumberCard(1, "Red"), new NumberCard(1, "Blue"),new Plus2Card("Blue")));
    List<Carta> player2Cards = new ArrayList<>(Arrays.asList( new SkipCard("Blue"),new NumberCard(1, "Red"), new NumberCard(1, "Blue"),new Plus2Card("Blue")));
    List<Carta> player3Cards = new ArrayList<>(Arrays.asList( new NumberCard(1, "Red"), new NumberCard(1, "Green"), new Plus2Card("Red")));
    List<Carta> list1 = new ArrayList<>(Arrays.asList(new NumberCard(1, "Blue"), new NumberCard(3, "Blue"), new SkipCard("Blue"), new Plus2Card("Red"),new WildCard( ""), new ReverseCard("Blue")));
    LinkedList<SimpleEntry<String, List<Carta>>> playerCards = new LinkedList<>(Arrays.asList(
            new SimpleEntry<>("Player1", player1Cards),
            new SimpleEntry<>("Player2", player2Cards)));
    LinkedList<SimpleEntry<String, List<Carta>>> playerCards3players = new LinkedList<>(Arrays.asList(
            new SimpleEntry<>("Player1", list1),
            new SimpleEntry<>("Player2", player2Cards),
            new SimpleEntry<>("Player3", player3Cards)));

    List<Carta> player1forUNO = new ArrayList<>(Arrays.asList( new NumberCard(1, "Red"), new NumberCard(1, "Yellow")));
    List<Carta> player2forUNO = new ArrayList<>(Arrays.asList( new NumberCard(1, "Red"),new NumberCard(1, "Red")));
    LinkedList<SimpleEntry<String, List<Carta>>> playerCardsUNO = new LinkedList<>(Arrays.asList(
            new SimpleEntry<>("Player1", player1forUNO),
            new SimpleEntry<>("Player2", player2forUNO)));


    @Test void testStartGame() {
        LinkedList<SimpleEntry<String, List<Carta>>> playerCards = new LinkedList<>();
        List<Carta> deck = new ArrayList<>();
        Partida partida = new Partida(playerCards, deck);
        assertEquals("Game has not started yet.", partida.getState().getMessage());
    }
    @Test void testSetHead() {
        List<Carta> deck = new ArrayList<>();
        Partida partida = new Partida(playerCards, deck);
        Carta head = new NumberCard(1, "Red");
        partida.startGame().setTopCard(head);
        assertEquals(head, partida.head);
    }
    @Test void getCurrentPlayerName() {
        List<Carta> deck = new ArrayList<>();
        Partida partida = new Partida(playerCards, deck);
        partida.startGame();
        assertEquals("Player1", partida.getCurrentPlayerName());
    }
    @Test void getNextPlayerName() {
        List<Carta> deck = new ArrayList<>();
        Partida partida = new Partida(playerCards, deck);
        Carta head = new NumberCard(1, "Red");
        partida.startGame().setTopCard(head);
        assertEquals("Player1", partida.getCurrentPlayerName());
        partida.nextTurn();
        assertEquals("Player2", partida.getCurrentPlayerName());
    }
    @Test void testPlayCard() {
        List<Carta> deck = new ArrayList<>();
        Partida partida = new Partida(playerCards, deck);
        Carta head = new NumberCard(1, "Red");
        Carta card = new NumberCard(1, "Blue");
        partida.startGame().setTopCard(head);
        partida.play(card, "Player1", "Blue");
        assertEquals("Blue", partida.head.color);
        assertEquals(1, partida.head.getValor());
        assertEquals(3, partida.getPlayerCards("Player1"));
    }
    @Test void testRemovingAfterPlaying() {
        List<Carta> deck = new ArrayList<>();
        Partida partida = new Partida(playerCards, deck);
        Carta head = new NumberCard(1, "Red");
        Carta card = new NumberCard(1, "Blue");
        partida.startGame().setTopCard(head);
        partida.play(card, "Player1", "Red");
        assertEquals(3, partida.getPlayerCards("Player1"));
    }
    @Test void testPlayCardInvalid() {
        List<Carta> deck = new ArrayList<>();
        Partida partida = new Partida(playerCards, deck);
        Carta head = new NumberCard(1, "Red");
        Carta card = new NumberCard(3, "Blue");
        partida.startGame().setTopCard(head);
        assertThrows(RuntimeException.class, () -> partida.play(card, "Player1", ""));
    }

    @Test void testSkipCard() {
        Partida partida = new Partida(playerCards, deck);
        Carta head = new NumberCard(1, "Blue");
        Carta card = new SkipCard( "Blue");
        partida.startGame().setTopCard(head);
        partida.play(card, "Player1", "Blue");
        assertEquals("Player1", partida.getCurrentPlayerName());
    }
    @Test void test3players(){
        Partida partida = new Partida(playerCards3players, deck);
        Carta head = new NumberCard(1, "Blue");
        Carta card = new NumberCard(1, "Blue");
        partida.startGame().setTopCard(head);
        assertEquals("Player1", partida.getCurrentPlayerName());
        partida.play(card, "Player1", "Blue");
        assertEquals("Player2", partida.getCurrentPlayerName());
        partida.nextTurn();
        assertEquals("Player3", partida.getCurrentPlayerName());
        partida.nextTurn();
        assertEquals("Player1", partida.getCurrentPlayerName());
    }
    @Test void testSkipCardWith3Players() {
        Partida partida = new Partida(playerCards3players, deck);
        Carta head = new NumberCard(1, "Blue");
        Carta card = new SkipCard( "Blue");
        partida.startGame().setTopCard(head);
        partida.play(card, "Player1", "Blue");
        assertEquals("Player3", partida.getCurrentPlayerName());
        partida.nextTurn();
        assertEquals("Player1", partida.getCurrentPlayerName());
        partida.nextTurn();
        assertEquals("Player2", partida.getCurrentPlayerName());
    }

    @Test void testReverseCardWith3Players() {
        Partida partida = new Partida(playerCards3players, deck);
        Carta head = new NumberCard(1, "Blue");
        Carta card = new ReverseCard( "Blue");
        partida.startGame().setTopCard(head);
        partida.play(card, "Player1", "Blue");
        assertEquals("Player3", partida.getCurrentPlayerName());
    }
    @Test void testWildCard(){
        Partida partida = new Partida(playerCards3players, deck);
        Carta head = new NumberCard(1, "Blue");
        Carta card = new WildCard("");
        partida.startGame().setTopCard(head);
        partida.play(card, "Player1", "Red");
        assertEquals("Red", partida.head.color);
    }
    @Test void testP2Card(){
        Partida partida = new Partida(playerCards3players, deck);
        Carta head = new NumberCard(1, "Red");
        Carta card = new Plus2Card( "Red");
        partida.startGame().setTopCard(head);
        partida.play(card, "Player1", "Red");
        assertEquals(6, partida.getPlayerCards("Player2"));
        Carta card2 = new Plus2Card("Blue");
        partida.play(card2, "Player2", "Blue");
        assertEquals(5, partida.getPlayerCards("Player3"));
    }
    @Test void testSayOneWrongly(){
        Partida partida = new Partida(playerCardsUNO, deck);
        Carta head = new NumberCard(1, "Red");
        partida.startGame().setTopCard(head);
        assertThrowsLike( () -> partida.sayUNO("Player2"),"Player not found/has more than 1 card.");
    }
    @Test void testSaidOne(){
        Partida partida = new Partida(playerCardsUNO, deck);
        Carta head = new NumberCard(1, "Red");
        Carta card = new NumberCard(1, "Red");
        partida.startGame().setTopCard(head);
        partida.play(card, "Player1", "Red");
        partida.sayUNO("Player1");

    }
    @Test void testNoSaidOne(){
        Partida partida = new Partida(playerCardsUNO, deck);
        Carta head = new NumberCard(1, "Red");
        Carta card = new NumberCard(1, "Red");
        partida.startGame().setTopCard(head);
        partida.play(card, "Player1", "Red");
        partida.sayUNOto("Player1");
    }
    @Test void testEndGame() {//like the avengers
        Partida partida = new Partida(playerCardsUNO, deck);
        Carta head = new NumberCard(1, "Red");
        Carta card = new NumberCard(1, "Red");
        Carta card2 = new NumberCard(1,"Yellow");
        partida.startGame().setTopCard(head);
        partida.play(card, "Player1", "Red");
        partida.play(card, "Player2", "Red");
        partida.play(card2, "Player1", "Yellow");
        assertThrowsLike( () -> partida.play(card, "Player2", "Red"),"Game has already ended.");
    }

    private void assertThrowsLike(Executable runnable, String errorMessage){
        assertEquals( assertThrows( Exception.class, runnable) .getMessage(), errorMessage);
    }
}