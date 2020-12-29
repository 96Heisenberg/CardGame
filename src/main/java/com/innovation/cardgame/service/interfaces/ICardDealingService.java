package com.innovation.cardgame.service.interfaces;

import com.innovation.cardgame.model.Card;

import java.util.List;

public interface ICardDealingService {
    public List<Card> createDeckOfShuffledCard();
}
