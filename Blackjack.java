// Programmer: Zachariah Magee
// Class: CS145
// Lab: Deck of Cards
// Date: July 13, 2022


import java.util.HashMap;
import java.util.Stack;
import java.util.Scanner;

public class Blackjack  {

    // initializations:
    Card card;
    Deck deck = new Deck();
    Hand dealer = new Hand();
    Hand player = new Hand();

    Scanner input = new Scanner(System.in);
    boolean play = true;

    // Score keeping
    HashMap<String, Integer> score = new HashMap<>();
    // int p=0;int d=0;int t=0;int l=0;int b=0;
    int p,d,t,l,b;


    public Blackjack() {
        scores();
        introduction();
        do {
            play = menu();
        } while (play);
    } // end of blackjack

    public void play() {
        deck.shuffle();
        dealing();
        boolean hit = true;
        do {
            hit = hitOrStay();
        } while (hit);
        hitDealer();
        int pScore = calculatePoints(player);
        int dScore = calculatePoints(dealer);
        printScore(pScore, dScore);
        winner(pScore, dScore);
    } // end of play

    public void printScore(int pScore, int dScore) {
        System.out.println("Player: " + pScore);
        System.out.println("Dealer: " + dScore);
    } // end of print score

    public void blackjack() {
        System.out.println("BLACKJACK!");
        score.put("Blackjack", b++);
    } // end of lil blackjack method

    public void winner(int pScore, int dScore) {
        if (pScore > dScore && pScore <= 21 || dScore > 21 && pScore <= 21) {
            System.out.println("Player Wins! ");
            score.put("Player", p++);
            if (pScore == 21) {
                blackjack();
            }
        } else if (dScore > pScore && dScore <= 21 || pScore > 21 && dScore <= 21) {
            System.out.println("Dealer Wins! ");
            score.put("Dealer", d++);
            if (dScore == 21) {
                blackjack();
            }
        } else if (dScore <= 21 && pScore <= 21 && dScore == pScore) {
            System.out.println("Dealer and Player Tie!");
            score.put("Tie", t++);
            if (dScore == 21 && pScore == 21) {
                blackjack();
            }
        } else if (dScore > 21 && pScore > 21) {
            System.out.println("Both Dealer and Player Lose!");
            score.put("Losers", l++);
        } else {
            System.out.println("A winner was unable to be determined.");
        }
    } // end of winner method

    public void scores() {
        score.put("Player", 0);
        score.put("Dealer", 0);
        score.put("Tie", 0);
        score.put("Losers", 0);
        score.put("Blackjack", 0);
    }
    public int calculatePoints(Hand hand) {
        int value = 0;
        int aces = 0;
        for (Card card: hand.hand) {
            switch(card.getRank()) {
                case ACE -> aces++;
                case TWO -> value += 2;
                case THREE -> value += 3;
                case FOUR -> value += 4;
                case FIVE -> value += 5;
                case SIX -> value += 6;
                case SEVEN -> value += 7;
                case EIGHT -> value += 8;
                case NINE -> value += 9;
                case TEN, JACK, QUEEN, KING -> value += 10;
            } // end of switch statement
        } // end of for each card in the hand loop
        for (int i = 0; i < aces; i++) {
            if (value <= 10) {
                value += 11;
            } else {
                value += 1;
            } // end of aces for loop
        } return value;
    } // end of calculate points

    public void dealing() {
        dealer.clearHand();
        player.clearHand();
        deck.deal(deck, player);
        printPlayer();
        deck.deal(deck, dealer);
        printDealer();
        System.out.println();
        deck.deal(deck, player);
        printPlayer();
        deck.deal(deck, dealer);
        printDealer();
    } // end of dealing

    public boolean hitOrStay() { try {
        System.out.println();
        System.out.print("(H)it or (S)tay? ");
        String hit = input.nextLine().toLowerCase();
        System.out.println();
        if (hit.charAt(0) == 'h') {
            deck.deal(deck, player);
            printPlayer();
            hitDealer();
            return true;
        } else if (hit.charAt(0) == 's') {
            return false;
        } else {
            System.out.println("Please input one of the provided commands (h/s): ");
            return true;
        }
    } catch (StringIndexOutOfBoundsException siobe) {
        System.out.println("Invalid input. Please make a selection.");
    } return true;
    } // end of hit or stay

    public void hitDealer() {
        while (dealerLogic()) {
            deck.deal(deck, dealer);
            printDealer();
        }
    } // end of hit dealer

    public boolean dealerLogic() {
        int total = 0;
        boolean hitDealer = false;
        for (int i = 0; i < dealer.getHandSize(); i++) {
            Card c = dealer.hand.get(i);
            total += c.value;

            if (total < 17) {
                hitDealer = true;
            } else {
                hitDealer = false;
            }
        } return hitDealer;
    } // end of dealer logic

    public void printPlayer() {
        System.out.println("Player: " + player.toString());
    } // end of print player
    public void printDealer() {
        System.out.println("Dealer: " + dealer.toString());
    } // end of print dealer

    public void scoreBoard() {
        score.get("Blackjack");
            System.out.printf("%nScoreboard: %n%n");
            System.out.printf("BLACKJACK: %s%n", score.get("Blackjack"));
            System.out.printf("Player wins: %s%n", score.get("Player"));
            System.out.printf("Dealer wins: %s%n", score.get("Dealer"));
            System.out.printf("Ties: %s%n", score.get("Tie"));
            System.out.printf("Nobody wins: %s%n", score.get("Losers"));
    } // end of scoreboard method

    public boolean menu() { try {
        System.out.printf("%n(P)lay (S)core (Q)uit: ");
        String response = input.nextLine().toLowerCase();
        if (response.charAt(0) == 'p') {
            play();
            return true;
        } else if (response.charAt(0) == 's') {
            scoreBoard();
            return true;
        } else if (response.charAt(0) == 'q') {
            System.out.print("Thank you for playing!");
            return false;
        } else {
            System.out.println("Please input one of the provided commands");
            return true;
        }
    } catch (StringIndexOutOfBoundsException siobe) {
        System.out.println("Invalid input. Please make a selection");
    } return true;
    } // end of menu method

    public void introduction() {
        System.out.println("""
                Welcome to the game  of Blackjack

                The dealer will shuffle the deck and
                You will both be dealt two cards.
                The goal is for the sum of the values
                of your cards to be as close to 21 as
                possible without going over.
                Faces value at ten, aces 1 or 11 depending
                on the sum of the rest of your hand.""");
    } // end of introduction method

} // end of blackjack class

