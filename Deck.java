// Programmer: Zachariah Magee
// Class: CS145
// Lab: Deck of Cards
// Date: July 13, 2022

import java.util.*;

public class Deck {
    Stack<Card> cards = new Stack<>();

    public Deck() {
        fillDeck();
    }
    public void fillDeck() {
        for (int i = 0; i < Card.Suit.getSuitLength(); i++) {
            for (int j = 0; j < Card.Rank.getRankLength(); j++) {
                Card card = new Card(Card.Suit.getSuits(i), Card.Rank.getRank(j));
                cards.push(card);
            }
        }
    }

    public void resetDeck(Deck deck) {
        int deckSize = this.cards.size();

        for (int i = 0; i < deckSize; i++) {
            deck.cards.push(this.cards.get(i));
        }

        for (int i = 0; i < deckSize; i++) {
            this.removeCard(0);
        }
    }

    public void removeCard(int index) {
        cards.remove(index);
    }

    public void shuffle() {
        System.out.println();
        Collections.shuffle(cards);
        System.out.println("Shuffling...");
        System.out.println("........");
        System.out.println("...");
    }

    public void deal(Deck deck, Hand hand) {
        Card c = deck.cards.pop();
        hand.takeCard(c);
    }

    public int getDeckSize() {
        return cards.size();
    }


    public String toString() {
        String deck = "";
        for (Card card : cards) {
            deck += card.toString() + "%n";
        }
        return deck;
    }

}
