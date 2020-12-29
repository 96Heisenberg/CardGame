package com.innovation.cardgame.service.classes;

import com.innovation.cardgame.model.Card;
import com.innovation.cardgame.model.Rank;
import com.innovation.cardgame.model.Suit;
import com.innovation.cardgame.service.interfaces.ICardDealingService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CardDealingService implements ICardDealingService {

    private static  List<Rank> rankList = Arrays.asList(Rank.values());
    private static  List<Suit> suitList = Arrays.asList(Suit.values());
    private static  List<Card> cardList = new ArrayList<>();

    @Override
    public List<Card> createDeckOfShuffledCard() {

        suitList.forEach(s -> {
            rankList.forEach(r -> {
                cardList.add(new Card(s, r));
            });
        });

        Collections.shuffle(cardList);
        return cardList;
    }
}
