# Card Games API

This is my first attempt at building a Blackjack game.

Currently, there are still a handful of issues that need addressing - originally I didn't build it with the intention of implementing mutliple games; because of this, the entire menu is built within the Blackjack class but in a future release I intend to manoeuvre all of that functionality into its own Interface (or 'Casino') class that refills the deck and allows the user to choose what game they would like to play from a selection of pre-programmed games alongside Blackjack. Each within their own class that retains the games rules and scoring methods.

There are four usable classes as of right now. Below I will detail each and the methods that can be invoked upon them:

## Card
#### Card(Suit suit, Rank rank) -
Card Constructor
#### displaySuit() -
returns a String representation of the suit
#### displayRank() -
returns a String representation of the rank
#### toString() -
returns a String representation of a Card

- an example is: A♥ or K♦ or 2♣

---

### Suit enumeration:
- Spade: "♠"
- Diamond: "♦"
- Heart: "♥"
- Club: "♣"

#### getSuit(int index) - returns a suit
#### getSuitLength() - returns the length of suit [4]

---

### Rank enumeration:
each rank consists of a rank.value
- Ace [11]
- Two [2]
- Three [3]
- Four [4]
- Five [5]
- Six [6]
- Seven [7]
- Eight [8]
- Nine [9]
- Ten [10]
- Jack [10]
- Queen [10]
- King [10]

#### rank(int value) constructor - returns the rank
#### getRankLength() returns the length of rank [13]
#### getRank(int index) returns a rank 

---

## Deck

The data structure used to create the deck is a stack.

#### Deck() - 
constructor simply calls fillDeck()

#### fillDeck() - 
creates a brand new 52 card deck with four suits and 13 ranks.

#### resetDeck() - 
meant to reset  the deck to original status

#### removeCard(int index) - 
removes a card at the specified index

#### shuffle() - 
shuffles the deck and prints a few lines to the console to let user know the deck is being shuffled.

#### deal(Deck deck, Hand hand) - 
uses the removeCard method to transfer a card from the deck to a specified hand with the Hand class takeCard() method.

#### getDeckSize() - 
returns an int for the size of the deck <52

#### toString() - 
returns a String with each card represented on its own line

---

## Hand

Like the deck, each hand is created within its own stack.

#### Hand() - 
constructs the stack of cards

#### takeCard() - 
takes a card from another deck into the hand

#### getHandSize() - 
returns an int of the stack length (# of cards)

#### clearHand() - 
clears the hand empty of cards

#### toString() - 
creates a string with each card in the hand seperated by a single space for readability. When calling this method, because it simply returns a string and doesnt itself print anything to the console, it is easy to print something like "Player: " + player.toString()... which would appear something like:

Player: A♥ K♦

---

## Blackjack

The Blackjack class is a little more convoluted with many a methods but it does the job and will be modified in the future to better represent the now evolved goal of the project. The game is played by simply calling 

### Blackjack() 
after initializing the deck and each hand (one for the player and one for the dealer)

it consists of an:

#### introduction(),  menu() and play()

which further consists of game logic such as:

#### dealing(), hitOrStay(), dealerLogic(), hitDealer(), 

and to calculate and tell the player who won the game:

#### calculatePoints() and winner()

and finally the

#### scoreboard()

there is a hashmap that attempts to keep track of who wins how many games, how many ties and blackjacks there are, but it doesn't work exactly as it should  quite yet. 

---

## Test Class

everything is run out of the main method within the Test Class. You simply have to instantiate a new deck, and two new hands, and then a game of  blackjack as follows:

##### Deck deck = new Deck();
##### Hand dealer = new Hand();
##### Hand player = new Hand();
##### Blackjack blackjack = new Blackjack();

and youre off and rolling!


## Thoughts so far...

I really enjoyed making this so far. I do intend to add another class called "Casino" that will pull some of the methods from the Blackjack class and create a shared interface for the user to operate from within to pick and choose which game they'd like to play and see the scores from each session. I think it could be a really sweet learning opportunity for me- and isn't that what this is all about?

# -Z