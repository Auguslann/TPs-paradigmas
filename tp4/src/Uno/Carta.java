package Uno;

import java.util.Optional;

public abstract class Carta {
    protected Object valor;
    protected String color;
    protected boolean unoState;

    public abstract Object getValor();
    public abstract String getColor();
    public abstract Carta getComparison(Carta incoming);

    public abstract void executeAction(Partida partida, Carta card);

}



class NumberCard extends Carta {
    public NumberCard(int valor, String color) {
        this.valor = valor;
        this.color = color;
        this.unoState = false;
    }

    public Object getValor() {
        return valor;
    }

    public String getColor() {
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
}

class SkipCard extends Carta {
    public SkipCard(String color) {
        this.color = color;
        this.valor = -1;
        this.unoState = false;
    }

    public Object getValor() {
        return valor;
    }


    public String getColor() {
        return color;
    }

    public void executeAction(Partida partida, Carta card) {
        partida.nextTurn();
        partida.nextTurn();
    }

    public Carta getComparison(Carta incoming) {
        return Optional.of(incoming)
                .filter(card -> card.getColor().equals(this.getColor()) || card.getValor() == this.getValor() || card.getColor().isEmpty())
                .orElseThrow(() -> new RuntimeException("Invalid card"));
    }
}

class WildCard extends Carta {
    public WildCard(String color) {
        this.color = color;
        this.valor = -2;
        this.unoState = false;
    }

    public Object getValor() {
        return valor;
    }

    public String getColor() {
        return color;
    }

    public void executeAction(Partida partida, Carta card) {
        partida.nextTurn();
    }

    public Carta getComparison(Carta incoming) {
        return Optional.of(incoming)
                .filter(card -> card.getColor().equals(this.getColor()) || card.getValor() == this.getValor())
                .orElseThrow(() -> new RuntimeException("Invalid card"));
    }
}



class Plus2Card extends Carta {
    public Plus2Card(String color) {
        this.color = color;
        this.valor = -3;
        this.unoState = false;
    }

    public Object getValor() {
        return valor;
    }

    public String getColor() {
        return color;
    }


    public void executeAction(Partida partida, Carta card) {
        String nextPlayer = partida.nextTurn().getCurrentPlayerName();
        partida.drawCard(nextPlayer);
        partida.drawCard(nextPlayer);
    }

    public Carta getComparison(Carta incoming) {
        return Optional.of(incoming)
                .filter(card -> card.getColor().equals(this.getColor()) || card.getValor() == this.getValor() || card.getColor().isEmpty())
                .orElseThrow(() -> new RuntimeException("Invalid card"));
    }

}


class ReverseCard extends Carta {
    public ReverseCard(String color) {
        this.color = color;
        this.valor = -4;
        this.unoState = false;
    }


    public Object getValor() {
        return valor;
    }

    public String getColor() {
        return color;
    }


    public void executeAction(Partida partida, Carta card) {
        partida.state = partida.state.swapTurns();
        partida.nextTurn();
    }

    public Carta getComparison(Carta incoming) {
        return Optional.of(incoming)
                .filter(card -> card.getColor().equals(this.getColor()) || card.getValor() == this.getValor() || card.getColor().isEmpty())
                .orElseThrow(() -> new RuntimeException("Invalid card"));
    }

}