package com.example.josephryan.beatthedealer;

import com.example.josephryan.beatthedealer.DeckOfCards.Card;
import com.example.josephryan.beatthedealer.DeckOfCards.Deck;
import com.example.josephryan.beatthedealer.DeckOfCards.Rank;
import com.example.josephryan.beatthedealer.DeckOfCards.Suit;
import com.example.josephryan.beatthedealer.Persons.Dealer;
import com.example.josephryan.beatthedealer.Persons.Player;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by josephryan on 27/01/2018.
 */

public class PersonTest {

    Deck deck;
    Dealer dealer;
    Player player;
    Card card1;
    Card card2;

    @Before
    public void before(){
        deck = new Deck();
        dealer = new Dealer(1, true, deck);
        player = new Player(1, true);
        card1 = new Card(Suit.SPADES, Rank.ACE, true);
        card2 = new Card(Suit.SPADES, Rank.EIGHT, true);
    }

    @Test
    public void canGetScore(){
        assertEquals(1, dealer.getScore());
        assertEquals(1, player.getScore());
    }

    @Test
    public void canGetHand(){
        assertEquals(0, player.getHand().size());
        assertEquals(0, dealer.getHand().size());
    }

    @Test
    public void personCanAcceptCard(){
        player.acceptCard(card1);
        dealer.acceptCard(card1);
        dealer.acceptCard(card1);
        assertEquals(1, player.getHand().size());
        assertEquals(2, dealer.getHand().size());
    }

    @Test
    public void canEmptyHand(){
        player.acceptCard(card1);
        player.acceptCard(card1);
        dealer.acceptCard(card1);
        dealer.acceptCard(card1);
        player.emptyHand();
        dealer.emptyHand();
        assertEquals(0, player.getHand().size());
        assertEquals(0, dealer.getHand().size());
    }

    @Test
    public void canCheckCardValue(){
        player.acceptCard(card1);
        assertEquals(11, player.checkCardValue(0));
    }

    @Test
    public void canGetValueOfAHand__Player(){
        player.acceptCard(card1);
        player.acceptCard(card2);
        assertEquals(19, player.checkValueOfHand());
    }

    @Test
    public void canCheckValueOfAHand__Dealer(){
        dealer.acceptCard(card1);
        dealer.acceptCard(card2);
        assertEquals(19, dealer.checkValueOfHand());
    }
}
