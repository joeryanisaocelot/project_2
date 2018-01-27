package com.example.josephryan.cardgame;

import com.example.josephryan.cardgame.Java.DeckOfCards.Card;
import com.example.josephryan.cardgame.Java.Dealer;
import com.example.josephryan.cardgame.Java.DeckOfCards.Deck;
import com.example.josephryan.cardgame.Java.Player;
import com.example.josephryan.cardgame.Java.DeckOfCards.Rank;
import com.example.josephryan.cardgame.Java.DeckOfCards.Suit;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DealerTest {

    Deck deck;
    Dealer dealer;
    Player player1;
    Player player2;
    Card card1;
    Card card2;
    Card card3;

    @Before
    public void before() {
        deck = new Deck();
        dealer = new Dealer(deck);
        player1 = new Player();
        player2 = new Player();
        card1 = new Card(Suit.CLUBS, Rank.FOUR);
        card2 = new Card(Suit.DIAMONDS, Rank.SEVEN);
        card3 = new Card(Suit.HEARTS, Rank.TWO);
    }

    @Test
    public void hasCards() {
        assertEquals(52, dealer.getDeck().getCardDeck().size());
    }

    @Test
    public void canAddPlayerToGame(){
        dealer.addPlayer(player1);
        dealer.addPlayer(player2);
        assertEquals(2, dealer.getPlayerCount());
    }

    @Test
    public void canDealCard() {
        dealer.dealCard(player1);
        assertEquals(1, player1.getHand().size());
        assertEquals(51, dealer.getDeck().getCardDeck().size());
    }

    @Test
    public void canDealCardToDealer() {
        dealer.dealToSelf();
        assertEquals(1, dealer.getHand().size());
    }

    @Test
    public void canDealARound(){
        dealer.addPlayer(player1);
        dealer.addPlayer(player2);
        dealer.dealForRound();
        dealer.dealForRound();
        assertEquals(2, dealer.getHand().size());
        assertEquals(2, player1.getHand().size());
        assertEquals(2, player2.getHand().size());
    }

    @Test
    public void canCheckPlayersCardValue(){
        player1.acceptCard(card1);
        assertEquals(4, dealer.checkCardValue(player1));
    }

   @Test
    public void canGetValueOfAHand__Player(){
        player1.acceptCard(card1);
        player1.acceptCard(card2);
        assertEquals(11, dealer.checkValueOfHand(player1));
   }

   @Test
    public void canCheckValueOfAHand__Dealer(){
        dealer.acceptCard(card1);
        dealer.acceptCard(card2);
        assertEquals(11, dealer.checkValueOfHand(dealer));
   }

   @Test
    public void checkForResultAgainstDealer(){
        dealer.addPlayer(player1);
        player1.acceptCard(card2); //7
        dealer.acceptCard(card1); //4
        assertEquals(true, dealer.resultAgainstDealer(player1));
   }
}