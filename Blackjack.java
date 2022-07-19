// Programmer: Zachariah Magee
// Class: CS145
// Lab: Deck of Cards
// Date: July 13, 2022


import java.util.*;

public class Blackjack  {
    Card card;
    Deck deck = new Deck();
    Hand dealer = new Hand();
    Hand player = new Hand();

    Scanner input = new Scanner(System.in);
    boolean play = true;

    // Score keeping
    HashMap<String, Integer> score = new HashMap<>();
    int p=0;int d=0;int t=0;int l=0;int b=0;


    public Blackjack() {
        introduction();
        do {
            play = menu();
        } while (play);
    }

    public void play() {
        deck.shuffle();
        dealing();
        boolean hit = true;
        do {
            hit = hitOrStay();
        } while (hit);
        hitDealer();
        int pScore = calculations(player);
        int dScore = calculations(dealer);
        printScore(pScore, dScore);
        winner(pScore, dScore);
    }
    public void printScore(int pScore, int dScore) {
        System.out.println("Player: " + pScore);
        System.out.println("Dealer: " + dScore);
    }
    public void blackjack() {
        System.out.println("BLACKJACK!");
        score.put("Blackjack", b++);
    }
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
    }

    public int calculations(Hand hand) {
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
            }
        }
        for (int i = 0; i < aces; i++) {
            if (value <= 10) {
                value += 11;
            } else {
                value += 1;
            }
        } return value;
    }

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
    }
    public boolean hitOrStay() {
        System.out.println();
        System.out.print("(H)it or (S)tay? ");
        String hit = input.nextLine().toLowerCase();
        System.out.println();
        if (hit.charAt(0) == 'h') {
            deck.deal(deck, player);
            printPlayer();
            hitDealer();
            return true;
        } else if (hit.charAt(0) == 's'){
            return false;
        } else {
            System.out.println("Please input one of the provided commands (h/s): ");
            return true;
        }
    }

    public void hitDealer() {
        while (dealerLogic()) {
            deck.deal(deck, dealer);
            printDealer();
        }
    }

    public boolean dealerLogic() {
        int total = 0;
        boolean hitDealer = false;
        for (int i = 0; i < dealer.numberOfCards; i++) {
            Card c = dealer.hand.get(i);
            total += c.value;

            if (total < 17) {
                hitDealer = true;
            } else {
                hitDealer = false;
            }
        } return hitDealer;
    }

    public void printPlayer() {
        System.out.println("Player: " + player.toString());
    }
    public void printDealer() {
        System.out.println("Dealer: " + dealer.toString());
    }

    public void scoreBoard() {
        score.get("Blackjack");
            System.out.printf("%nScoreboard: %n%n");
            System.out.printf("BLACKJACK: %s%n", score.get("Blackjack"));
            System.out.printf("Player wins: %s%n", score.get("Player"));
            System.out.printf("Dealer wins: %s%n", score.get("Dealer"));
            System.out.printf("Ties: %s%n", score.get("Tie"));
            System.out.printf("Nobody wins: %s%n", score.get("Losers"));
    }

    public boolean menu() {
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
    }
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
    }
}
