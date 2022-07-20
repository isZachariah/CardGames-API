// Programmer: Zachariah Magee
// Class: CS145
// Lab: Deck of Cards
// Date: July 13, 2022

import java.util.Stack;


public class Hand {
    // initializations
    Stack<Card> hand = new Stack<>();

    // constructors
    public Hand(Stack<Card> hand) {
        this.hand = hand;
    }

    public Hand() { Stack<Card> h = new Stack<>(); }

    // methods
    public void takeCard(Card card) {
        hand.push(card);
    }

    public int getHandSize() {
        return hand.size();
    }

    public void clearHand() {
        hand.clear();
    }

    // Returns a string representation of a hand
    @Override
    public String toString() {
        String h = "";
        for (Card card : hand) {
            h += card.toString() + "  ";
        }
        return h;
    } // end Hand to string method

} // end of the Hand class
