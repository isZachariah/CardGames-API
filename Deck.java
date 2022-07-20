// Programmer: Zachariah Magee
// Class: CS145
// Lab: Deck of Cards
// Date: July 13, 2022

import java.util.Collections;
import java.util.Stack;

public class Deck {
    // initializations:
    Stack<Card> cards = new Stack<>();

    // constructors:
    public Deck() {
        fillDeck();
    } // end of deck constructor

    public void fillDeck() {
        for (int i = 0; i < Card.Suit.getSuitLength(); i++) {
            for (int j = 0; j < Card.Rank.getRankLength(); j++) {
                Card card = new Card(Card.Suit.getSuits(i), Card.Rank.getRank(j));
                cards.push(card);
            }
        }
    } // end of fill deck method

    // methods:
    public void resetDeck(Deck deck) {
        int deckSize = this.cards.size();

        for (int i = 0; i < deckSize; i++) {
            deck.cards.push(this.cards.get(i));
        }

        for (int i = 0; i < deckSize; i++) {
            this.removeCard(0);
        }
    } // end of reset deck method

    public void removeCard(int index) {
        cards.remove(index);
    }

    public void shuffle() {
        System.out.println();
        Collections.shuffle(cards);
        System.out.println("Shuffling...");
        System.out.println("........");
        System.out.println("...");
    } // end of shuffle method

    public void deal(Deck deck, Hand hand) {
        Card c = deck.cards.pop();
        hand.takeCard(c);
    } // end of deal method

    public int getDeckSize() {
        return cards.size();
    }

    @Override
    public String toString() {
        String deck = "";
        for (Card card : cards) {
            deck += card.toString() + "%n";
        }
        return deck;
    } // end of to string method

} // end of the deck class
