package Uno;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class Carta {
    Object valor;
    Object color;
    public Boolean unoState;
    public abstract void executeAction(Partida partida, Carta carta);
    public abstract Carta getComparison(Carta incoming);
    public abstract Object getValor();
    public abstract Object getColor();
    public abstract Partida comparePlus2(Partida partida);
}
    class CartaNumerica extends Carta{
        public CartaNumerica(int valor, String color) {
            this.color = color;
            this.valor = valor;
            this.unoState = false;
        }
        public Object getValor() {
            return valor;
        }
        public Object getColor() {
            return color;
        }
        public Carta getComparison(Carta incoming) {
            return Optional.of(incoming)
                    .filter(card -> card.getColor().equals(this.getColor()) || card.getValor() == this.getValor() || card.getColor().isEmpty())
                    .orElseThrow(() -> new RuntimeException("Invalid card"));
        }

        public void executeAction(Partida partida, Carta card) {
            partida.nextTurn();
        }
        public Partida comparePlus2(Partida partida) {
            while (partida.plus2Counter > 0) {
                partida.drawCard(partida.getCurrentPlayerName());
                partida.plus2Counter -= 1;
            }
            return partida;
        }
    }
    class WildCard extends Carta{
        public WildCard(String color) {
            this.valor = "Wild";
            this.color = color;
            this.unoState = false;
        }
        public Object getValor() {
            return valor;
        }
        public Object getColor() {
            return color;
        }
        public WildCard red() {
            this.color = "Red";
            return this;
        }
        public WildCard blue() {
            this.color = "Blue";
            return this;
        }
        public WildCard green() {
            this.color = "Green";
            return this;
        }
        public WildCard yellow() {
            this.color = "Yellow";
            return this;
        }
        public Carta getComparison(Carta incoming) {
            return Optional.of(incoming)
                    .filter(card -> card.getColor().equals(this.getColor()) || card.getValor() == this.getValor() || card.getColor().isEmpty())
                    .orElseThrow(() -> new RuntimeException("Invalid card"));
        }
        public void executeAction(Partida partida, Carta carta) {
            partida.head.color = carta.color;
            partida.nextTurn();
        }
        public Partida comparePlus2(Partida partida) {
            while (partida.plus2Counter > 0) {
                partida.drawCard(partida.getCurrentPlayerName());
                partida.plus2Counter -= 1;
            }
            return partida;
        }
    }

    class plusTwo extends Carta{
        public plusTwo(String color) {
            this.valor = "+2";
            this.color = color;
            this.unoState = false;
        }
        public Object getValor() {
            return valor;
        }
        public Object getColor() {
            return color;
        }
        public void executeAction(Partida partida, Carta carta) {
            partida.plus2Counter += 2;
        }
        public Carta getComparison(Carta incoming) {
            return Optional.of(incoming)
                    .filter(card -> card.getColor().equals(this.getColor()) || card.getValor() == this.getValor() || card.getColor().isEmpty())
                    .orElseThrow(() -> new RuntimeException("Invalid card"));
        }
        public Partida comparePlus2(Partida partida) {
            while (partida.plus2Counter > 0) {
                partida.drawCard(partida.getCurrentPlayerName());
                partida.plus2Counter -= 1;
            }
            return partida;
        }
    }
    class reverse extends Carta{
        public reverse(String color) {
            this.valor = "Reverse";
            this.color = color;
            this.unoState = false;
        }
        public Object getValor() {
            return valor;
        }
        public Object getColor() {
            return color;
        }
        public void executeAction(Partida partida, Carta carta) {
            partida.swapDirection();
            partida.nextTurn();
        }
        public Carta getComparison(Carta incoming) {
            return Optional.of(incoming)
                    .filter(card -> card.getColor().equals(this.getColor()) || card.getValor() == this.getValor() || card.getColor().isEmpty())
                    .orElseThrow(() -> new RuntimeException("Invalid card"));
        }
        public Partida comparePlus2(Partida partida) {
            while (partida.plus2Counter > 0) {
                partida.drawCard(partida.getCurrentPlayerName());
                partida.plus2Counter -= 1;
            }
            return partida;
        }
    }
    class skip extends Carta{
        public skip(String color) {
            this.valor = "Skip";
            this.color = color;
            this.unoState = false;
        }
        public Object getValor() {
            return valor;
        }
        public Object getColor() {
            return color;
        }
        public Carta getComparison(Carta incoming) {
            return Optional.of(incoming)
                    .filter(card -> card.getColor().equals(this.getColor()) || card.getValor() == this.getValor() || card.getColor().isEmpty())
                    .orElseThrow(() -> new RuntimeException("Invalid card"));
        }
        public void executeAction(Partida partida, Carta carta) {
            partida.nextTurn();
            partida.nextTurn();
        }
        public Partida comparePlus2(Partida partida) {
            while (partida.plus2Counter > 0) {
                partida.drawCard(partida.getCurrentPlayerName());
                partida.plus2Counter -= 1;
            }
            return partida;
        }
    }