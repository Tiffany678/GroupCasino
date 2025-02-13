package com.github.zipcodewilmington.casino.games.War;

import com.github.zipcodewilmington.casino.GameInterface;
import com.github.zipcodewilmington.casino.PlayerInterface;
import com.github.zipcodewilmington.casino.games.Card.Card;
import com.github.zipcodewilmington.casino.games.Card.CardDeck;
import com.github.zipcodewilmington.casino.games.GameTypes.CardGame;
import java.util.Scanner;
import java.util.Stack;


public class WarGame extends CardGame implements GameInterface {
    private Stack<Card> playerDeck;
    private Stack<Card> dealerDeck;
    private int playerScore;
    private int dealerScore;

    public WarGame(CardDeck deck) {
        super(deck);
    }

    public Stack<Card> getPlayerDeck() {
        return playerDeck;
    }

    public void setPlayerDeck(Stack<Card> playerDeck) {
        this.playerDeck = playerDeck;
    }

    public Stack<Card> getDealerDeck() {
        return dealerDeck;
    }

    public void setDealerDeck(Stack<Card> dealerDeck) {
        this.dealerDeck = dealerDeck;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }

    public int getDealerScore() {
        return dealerScore;
    }

    public void setDealerScore(int dealerScore) {
        this.dealerScore = dealerScore;
    }

    public void splitDeck(){
        this.deck.shuffle();
        playerDeck = fillHalfDeck(this.deck);
        dealerDeck = fillSecondHalfDeck(this.deck);
    }
    public Stack<Card> fillHalfDeck(CardDeck deck){
        Stack<Card> halfDeck = new Stack<>();
        for(int i = 0; i < 26;i++){
            halfDeck.add(deck.drawNextCard());
        }
        return halfDeck;
    }
    public Stack<Card> fillSecondHalfDeck(CardDeck deck){
        Stack<Card> halfDeck = new Stack<>();
        for(int i = 26; i < 52;i++){
            halfDeck.add(deck.drawNextCard());
        }
        return halfDeck;
    }
    public boolean playerCardBetter(Card playerCard, Card dealerCard){ //return true for player
       return playerCard.getPrintedValues().compareTo(dealerCard.getPrintedValues()) > 0;
    }
    public boolean isTie(Card playerCard, Card dealerCard){
        return playerCard.getPrintedValues().compareTo(dealerCard.getPrintedValues()) == 0;
    }
    public void advanceTheGame(){
        Scanner in = new Scanner(System.in);
        System.out.println("Press any key and enter to advance");
        String input = in.nextLine();
    }
    public Card drawThreeThenFlipUpFourth(Stack<Card> deck){
        for(int i = 1; i <= 3; i++){
            deck.pop();
        }

        return deck.pop();
    }

    @Override
    public void add(PlayerInterface player) {

    }

    @Override
    public void remove(PlayerInterface player) {

    }

    @Override
    public Boolean checkWinner() {
        return null;
    }

    public void run() {
        Card playerCurrentCard;
        Card dealerCurrentCard;
        System.out.println("Welcome to war.\nDeck will be split good luck.");
        splitDeck();
        while(playerDeck.size() > 0){
            playerCurrentCard = playerDeck.pop();
            dealerCurrentCard = dealerDeck.pop();
            System.out.println("Your card is " + playerCurrentCard.toString() + " dealer card is " + dealerCurrentCard);
            advanceTheGame();
            while(isTie(playerCurrentCard, dealerCurrentCard)){
                System.out.println("WAR!!");
                playerCurrentCard = drawThreeThenFlipUpFourth(playerDeck);
                dealerCurrentCard = drawThreeThenFlipUpFourth(dealerDeck);
                System.out.println("Your card is " + playerCurrentCard.toString() + " dealer card is " + dealerCurrentCard);
                advanceTheGame();
            }
            if(playerCardBetter(playerCurrentCard,dealerCurrentCard)) playerScore++;
            else dealerScore++;
            System.out.println("Updated Scores: You: " + playerScore +" Dealer:" + dealerScore);
            advanceTheGame();
        }
        System.out.println("Final Score: " + playerScore + " "+ dealerScore);
    }
}

//fix WAR at the end of the deck
//Exception in thread "main" java.util.EmptyStackException
//        at java.base/java.util.Stack.peek(Stack.java:101)
//        at java.base/java.util.Stack.pop(Stack.java:83)
//        at com.github.zipcodewilmington.casino.games.War.WarGame.drawThreeThenFlipUpFourth(WarGame.java:86)
//        at com.github.zipcodewilmington.casino.games.War.WarGame.run(WarGame.java:119)
//        at com.github.zipcodewilmington.Casino.play(Casino.java:92)