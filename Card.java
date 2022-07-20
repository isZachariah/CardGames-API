// Programmer: Zachariah Magee
// Class: CS145
// Lab: Deck of Cards
// Date: July 13, 2022
// Extra Credit: Enumeration, Switch/Case, Game User-Interface

public class Card {

    public enum Rank {
        ACE(11), TWO(2), THREE(3),
        FOUR(4), FIVE(5), SIX(6),
        SEVEN(7), EIGHT(8), NINE(9),
        TEN(10), JACK(10), QUEEN(10), KING(10);

        final int value;

        Rank(int value) {
            this.value = value;
        }

        public static Rank getRank(int index) {
            Rank[] ranks = Rank.values();
            return ranks[index];
        } // end get rank

        public static int getRankLength() {
            return Rank.values().length;
        }
    } // end of rank enum

    public enum Suit {
        HEART, DIAMOND, CLUB, SPADE;

        public static Suit getSuits(int index) {
            Suit[] suits = Suit.values();
            return suits[index];
        } // end get suit

        public static int getSuitLength() {
            return Suit.values().length;
        }
    } // end of suit enum

    // initializations
    public final Suit suit;
    public final Rank rank;
    public final int value;

    // constructor\
    public Card(Suit suit, Rank rank) {
        this.rank = rank;
        this.suit = suit;
        this.value = rank.value;
    } // end card constructor

    public Suit getSuit() {
        return this.suit;
    }

    public Rank getRank() { return this.rank; }


    // string representations of card attributes
    public String displaySuit() {
        return switch (this.suit) {
            case HEART -> "♥";
            case DIAMOND -> "♦";
            case CLUB -> "♣";
            case SPADE -> "♠";
        };
    } // end display suit

    public String displayRank() {
        return switch (this.rank) {
            case ACE -> "A";
            case TWO -> "2";
            case THREE -> "3";
            case FOUR -> "4";
            case FIVE -> "5";
            case SIX -> "6";
            case SEVEN -> "7";
            case EIGHT -> "8";
            case NINE -> "9";
            case TEN -> "10";
            case JACK -> "J";
            case QUEEN -> "Q";
            case KING -> "K";
        };
    } // end display rank

    // returns a string representation of a card
    @Override
    public String toString() {
        String value = displayRank();
        String symbol = displaySuit();
        return value + symbol;
    } // end card to string

} // end of card class