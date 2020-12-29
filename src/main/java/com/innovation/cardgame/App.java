package com.innovation.cardgame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.innovation.cardgame.model.Card;
import com.innovation.cardgame.model.Rank;
import com.innovation.cardgame.model.Suit;
import com.innovation.cardgame.model.User;
import com.innovation.cardgame.service.classes.CardDealingService;
import com.innovation.cardgame.service.interfaces.ICardDealingService;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        System.out.println("Prepare deck of cards");
		ICardDealingService cardDealingService = new CardDealingService();
        List<Card> deck = cardDealingService.createDeckOfShuffledCard();
        System.out.println("Deck of cards prepared successfully");

        System.out.println("Dealing into five packs");
        List<Card> packOne = deck.stream()
                .limit(8).collect(Collectors.toList());
        System.out.println(":::::::::::::::::: First Pack cards ::::::::::::::::::" + packOne.size());
        packOne.forEach(System.out::println);

        List<Card> packTwo = deck.stream()
                .skip(8)
                .limit(8).collect(Collectors.toList());
        System.out.println(":::::::::::::::::: Second Pack cards ::::::::::::::::::" + packTwo.size());
        packTwo.forEach(System.out::println);

        List<Card> packThree = deck.stream()
                .skip(16)
                .limit(8).collect(Collectors.toList());
        System.out.println(":::::::::::::::::: Third Pack cards ::::::::::::::::::" + packThree.size());
        packThree.forEach(System.out::println);

        List<Card> packFour = deck.stream()
                .skip(24)
                .limit(8).collect(Collectors.toList());
        System.out.println(":::::::::::::::::: Fourth Pack cards ::::::::::::::::::" + packFour.size());
        packFour.forEach(System.out::println);

        List<Card> packFive = deck.stream()
                .skip(32)
                .limit(20).collect(Collectors.toList());
        System.out.println(":::::::::::::::::: Fifth Pack cards ::::::::::::::::::" + packFive.size());
        packFive.forEach(System.out::println);

        System.out.println("Pack Dealing Done Successfully");

        System.out.println("Card Picking start");
        List<Card> pickingOrderedList = new ArrayList<Card>();
        while (packFour.size() > 1) {
            pickingOrderedList.add(packOne.get(0));
            packOne.remove(0);

            pickingOrderedList.add(packTwo.get(0));
            packTwo.remove(0);

            pickingOrderedList.add(packThree.get(0));
            packThree.remove(0);

            pickingOrderedList.add(packFour.get(0));
            packFour.remove(0);
        }

        System.out.println("Need to reset with the face card: " + packOne.get(0));
        System.out.println("Final Packing started...");
        List<Card> finalPack = new ArrayList<Card>();
        finalPack.add(packOne.get(0));

        List<Card> finalPackWithoutFace = new ArrayList<Card>();
        finalPackWithoutFace.add(packTwo.get(0));
        finalPackWithoutFace.add(packThree.get(0));
        finalPackWithoutFace.add(packFour.get(0));
        packFive.forEach(x -> finalPackWithoutFace.add(x));

        Collections.shuffle(finalPackWithoutFace);
        finalPackWithoutFace.forEach(x -> finalPack.add(x));
        System.out.println("Final Packing ended... : " + finalPack.size());
        finalPack.forEach(x -> pickingOrderedList.add(x));

        System.out.println("Picking from the remaining pack");
        while (finalPack.size() > 1) {
            System.out.println(finalPack.get(0));
            finalPack.remove(0);
        }

        Scanner sc = new Scanner(System.in);
        String color;
        String comp;
        User userOne = new User();
        User userTwo = new User();
        User userThree = new User();
        //TODO

        System.out.println("Final pick order is: ");
        pickingOrderedList.forEach(System.out::println);


        for (int i = 0; i < 51; i++) {
            if (i == 0 || i == 3 || i == 6 || i == 9 || i == 12 || i == 15
                    || i == 18 || i == 21 || i == 24 || i == 27 || i == 30 || i == 33 || i == 36
                    || i == 39 || i == 42 || i == 45 || i == 48) { //TODO for pack 5
                System.out.println("User 1 Attempt for card: " + pickingOrderedList.get(i));
                if (pickingOrderedList.get(i).getRank().name().equals("ACE") || pickingOrderedList.get(i).getRank().name().equals("KING")) {
                    color = sc.nextLine();
                    if (color.equalsIgnoreCase("SAME")) {
                        String deckSuit = pickingOrderedList.get(i).getSuit().name();
                        String nextDeckSuit;
                        if (i <= 27) {
                            nextDeckSuit = pickingOrderedList.get(i + 4).getSuit().name();
                            System.out.println("Next Suit is: " + pickingOrderedList.get(i + 4));
                        } else {
                            nextDeckSuit = pickingOrderedList.get(i + 1).getSuit().name();
                            System.out.println("Next Suit is: " + pickingOrderedList.get(i + 1));

                        }
                        if (deckSuit.equals(nextDeckSuit) || (deckSuit.equals("HEART") && nextDeckSuit.equals("DIAMOND"))
                                || (nextDeckSuit.equals("HEART") && deckSuit.equals("DIAMOND"))
                                || (deckSuit.equals("SPADE") && nextDeckSuit.equals("CLUB"))
                                || (nextDeckSuit.equals("SPADE") && deckSuit.equals("CLUB"))) {
                            System.out.println("Correct");
                            userOne.setCountOfNo(userOne.getCountOfNo() + 2);
                        } else {
                            userOne.setCountOfNo(userOne.getCountOfNo() - 1);
                            System.out.println("Incorrect");

                        }

                    } else {
                        String deckSuit = pickingOrderedList.get(i).getSuit().name();
                        String nextDeckSuit;
                        if (i <= 27) {
                            nextDeckSuit = pickingOrderedList.get(i + 4).getSuit().name();
                            System.out.println("Next Suit is: " + pickingOrderedList.get(i + 4));
                        } else {
                            nextDeckSuit = pickingOrderedList.get(i + 1).getSuit().name();
                            System.out.println("Next Suit is: " + pickingOrderedList.get(i + 1));
                        }
                        if (!deckSuit.equals(nextDeckSuit) || (deckSuit.equals("HEART") && !nextDeckSuit.equals("DIAMOND"))
                                || (!nextDeckSuit.equals("HEART") && deckSuit.equals("DIAMOND"))
                                || (deckSuit.equals("SPADE") && !nextDeckSuit.equals("CLUB"))
                                || (nextDeckSuit.equals("SPADE") && !deckSuit.equals("CLUB"))) {
                            System.out.println("Correct");
                            userOne.setCountOfNo(userOne.getCountOfNo() + 2);
                        } else {
                            System.out.println("Incorrect");
                            userOne.setCountOfNo(userOne.getCountOfNo() - 1);
                        }

                    }
                } else {
                    comp = sc.nextLine();
                    if (comp.equalsIgnoreCase("HIGH")) {
                        Integer deckRank = pickingOrderedList.get(i).getRank().ordinal();
                        Integer nextDeckRank;
                        if (i <= 27) {
                            nextDeckRank = pickingOrderedList.get(i + 4).getRank().ordinal();
                            System.out.println("Next Suit is: " + pickingOrderedList.get(i + 4));

                        } else {
                            nextDeckRank = pickingOrderedList.get(i + 1).getRank().ordinal();
                            System.out.println("Next Suit is: " + pickingOrderedList.get(i + 1));

                        }

                        if (deckRank < nextDeckRank) {
                            if (deckRank == 6) {
                                System.out.println("Correct");
                                userOne.setCountOfNo(userOne.getCountOfNo() + 2);
                            } else {
                                System.out.println("Correct");
                                userOne.setCountOfNo(userOne.getCountOfNo() + 1);
                            }
                        } else {
                            System.out.println("Incorrect");
                            userOne.setCountOfNo(userOne.getCountOfNo() - 1);
                        }

                    } else {
                        Integer deckRank = pickingOrderedList.get(i).getRank().ordinal();
                        Integer nextDeckRank;
                        if (i <= 27) {
                            nextDeckRank = pickingOrderedList.get(i + 4).getRank().ordinal();
                            System.out.println("Next Suit is: " + pickingOrderedList.get(i + 4));
                        } else {
                            nextDeckRank = pickingOrderedList.get(i + 1).getRank().ordinal();
                            System.out.println("Next Suit is: " + pickingOrderedList.get(i + 1));
                        }

                        if (deckRank > nextDeckRank) {
                            System.out.println("Correct");
                            userOne.setCountOfNo(userOne.getCountOfNo() + 1);
                        } else {
                            System.out.println("Incorrect");
                            userOne.setCountOfNo(userOne.getCountOfNo() - 1);
                        }
                    }
                }
            } else if (i == 1 || i == 4 || i == 7 || i == 10 || i == 13 || i == 16
                    || i == 19 || i == 22 || i == 25 || i == 28 || i == 31 || i == 34 || i == 37
                    || i == 40 || i == 43 || i == 46 || i == 49) { //TODO pack 5
                System.out.println("User 2 Attempt for card: " + pickingOrderedList.get(i));
                if (pickingOrderedList.get(i).getRank().name().equals("ACE") || pickingOrderedList.get(i).getRank().name().equals("KING")) {
                    color = sc.nextLine();
                    if (color.equalsIgnoreCase("SAME")) {
                        String deckSuit = pickingOrderedList.get(i).getSuit().name();
                        String nextDeckSuit;
                        if (i <= 27) {
                            nextDeckSuit = pickingOrderedList.get(i + 4).getSuit().name();
                            System.out.println("Next Suit is: " + pickingOrderedList.get(i + 4));
                        } else {
                            nextDeckSuit = pickingOrderedList.get(i + 1).getSuit().name();
                            System.out.println("Next Suit is: " + pickingOrderedList.get(i + 1));
                        }
                        if (deckSuit.equals(nextDeckSuit) || (deckSuit.equals("HEART") && nextDeckSuit.equals("DIAMOND"))
                                || (nextDeckSuit.equals("HEART") && deckSuit.equals("DIAMOND"))
                                || (deckSuit.equals("SPADE") && nextDeckSuit.equals("CLUB"))
                                || (nextDeckSuit.equals("SPADE") && deckSuit.equals("CLUB"))) {
                            System.out.println("Correct");
                            userTwo.setCountOfNo(userTwo.getCountOfNo() + 2);
                        } else {
                            System.out.println("Incorrect");
                            userTwo.setCountOfNo(userTwo.getCountOfNo() - 1);
                        }

                    } else {
                        String deckSuit = pickingOrderedList.get(i).getSuit().name();
                        String nextDeckSuit;
                        if (i <= 27) {
                            nextDeckSuit = pickingOrderedList.get(i + 4).getSuit().name();
                            System.out.println("Next Suit is: " + pickingOrderedList.get(i + 4));
                        } else {
                            nextDeckSuit = pickingOrderedList.get(i + 1).getSuit().name();
                            System.out.println("Next Suit is: " + pickingOrderedList.get(i + 1));
                        }
                        if (!deckSuit.equals(nextDeckSuit) || (deckSuit.equals("HEART") && !nextDeckSuit.equals("DIAMOND"))
                                || (!nextDeckSuit.equals("HEART") && deckSuit.equals("DIAMOND"))
                                || (deckSuit.equals("SPADE") && !nextDeckSuit.equals("CLUB"))
                                || (nextDeckSuit.equals("SPADE") && !deckSuit.equals("CLUB"))) {
                            System.out.println("Correct");
                            userTwo.setCountOfNo(userTwo.getCountOfNo() + 2);
                        } else {
                            System.out.println("Incorrect");
                            userTwo.setCountOfNo(userTwo.getCountOfNo() - 1);
                        }

                    }
                } else {
                    comp = sc.nextLine();
                    if (comp.equalsIgnoreCase("HIGH")) {
                        Integer deckRank = pickingOrderedList.get(i).getRank().ordinal();
                        Integer nextDeckRank;
                        if (i <= 27) {
                            nextDeckRank = pickingOrderedList.get(i + 4).getRank().ordinal();
                            System.out.println("Next Suit is: " + pickingOrderedList.get(i + 4));
                        } else {
                            nextDeckRank = pickingOrderedList.get(i + 1).getRank().ordinal();
                            System.out.println("Next Suit is: " + pickingOrderedList.get(i + 1));
                        }

                        if (deckRank < nextDeckRank) {
                            if (deckRank == 6) {
                                System.out.println("Correct");
                                userTwo.setCountOfNo(userTwo.getCountOfNo() + 2);
                            } else {
                                System.out.println("Correct");
                                userTwo.setCountOfNo(userTwo.getCountOfNo() + 1);
                            }
                        } else {
                            System.out.println("Incorrect");
                            userTwo.setCountOfNo(userTwo.getCountOfNo() - 1);
                        }

                    } else {
                        Integer deckRank = pickingOrderedList.get(i).getRank().ordinal();
                        Integer nextDeckRank;
                        if (i <= 27) {
                            nextDeckRank = pickingOrderedList.get(i + 4).getRank().ordinal();
                            System.out.println("Next Suit is: " + pickingOrderedList.get(i + 4));
                        } else {
                            nextDeckRank = pickingOrderedList.get(i + 1).getRank().ordinal();
                            System.out.println("Next Suit is: " + pickingOrderedList.get(i + 1));
                        }

                        if (deckRank > nextDeckRank) {
                            if (deckRank == 6) {
                                System.out.println("Correct");
                                userTwo.setCountOfNo(userTwo.getCountOfNo() + 2);
                            } else {
                                System.out.println("Correct");
                                userTwo.setCountOfNo(userTwo.getCountOfNo() + 1);
                            }
                        } else {
                            System.out.println("Incorrect");
                            userTwo.setCountOfNo(userTwo.getCountOfNo() - 1);
                        }
                    }
                }

            } else {
                System.out.println("User 3 Attempt for card: " + pickingOrderedList.get(i));
                if (pickingOrderedList.get(i).getRank().name().equals("ACE") || pickingOrderedList.get(i).getRank().name().equals("KING")) {
                    color = sc.nextLine();
                    if (color.equalsIgnoreCase("SAME")) {
                        String deckSuit = pickingOrderedList.get(i).getSuit().name();
                        String nextDeckSuit;
                        if (i <= 27) {
                            nextDeckSuit = pickingOrderedList.get(i + 4).getSuit().name();
                            System.out.println("Next Suit is: " + pickingOrderedList.get(i + 4));
                        } else {
                            nextDeckSuit = pickingOrderedList.get(i + 1).getSuit().name();
                            System.out.println("Next Suit is: " + pickingOrderedList.get(i + 1));
                        }
                        if (deckSuit.equals(nextDeckSuit) || (deckSuit.equals("HEART") && nextDeckSuit.equals("DIAMOND"))
                                || (nextDeckSuit.equals("HEART") && deckSuit.equals("DIAMOND"))
                                || (deckSuit.equals("SPADE") && nextDeckSuit.equals("CLUB"))
                                || (nextDeckSuit.equals("SPADE") && deckSuit.equals("CLUB"))) {
                            System.out.println("Correct");
                            userThree.setCountOfNo(userThree.getCountOfNo() + 2);
                        } else {
                            System.out.println("Incorrect");
                            userThree.setCountOfNo(userThree.getCountOfNo() - 1);
                        }

                    } else {
                        String deckSuit = pickingOrderedList.get(i).getSuit().name();
                        String nextDeckSuit;
                        if (i <= 27) {
                            nextDeckSuit = pickingOrderedList.get(i + 4).getSuit().name();
                            System.out.println("Next Suit is: " + pickingOrderedList.get(i + 4));

                        } else {
                            nextDeckSuit = pickingOrderedList.get(i + 1).getSuit().name();
                            System.out.println("Next Suit is: " + pickingOrderedList.get(i + 1));

                        }
                        if (!deckSuit.equals(nextDeckSuit) || (deckSuit.equals("HEART") && !nextDeckSuit.equals("DIAMOND"))
                                || (!nextDeckSuit.equals("HEART") && deckSuit.equals("DIAMOND"))
                                || (deckSuit.equals("SPADE") && !nextDeckSuit.equals("CLUB"))
                                || (nextDeckSuit.equals("SPADE") && !deckSuit.equals("CLUB"))) {
                            System.out.println("Correct");
                            userThree.setCountOfNo(userThree.getCountOfNo() + 2);
                        } else {
                            System.out.println("Incorrect");
                            userThree.setCountOfNo(userThree.getCountOfNo() - 1);
                        }

                    }
                } else {
                    comp = sc.nextLine();
                    if (comp.equalsIgnoreCase("HIGH")) {
                        Integer deckRank = pickingOrderedList.get(i).getRank().ordinal();
                        Integer nextDeckRank;
                        if (i <= 27) {
                            nextDeckRank = pickingOrderedList.get(i + 4).getRank().ordinal();
                            System.out.println("Next Suit is: " + pickingOrderedList.get(i + 4));
                        } else {
                            nextDeckRank = pickingOrderedList.get(i + 1).getRank().ordinal();
                            System.out.println("Next Suit is: " + pickingOrderedList.get(i + 1));
                        }

                        if (deckRank < nextDeckRank) {
                            if (deckRank == 6) {
                                System.out.println("Correct");
                                userThree.setCountOfNo(userThree.getCountOfNo() + 2);
                            } else {
                                System.out.println("Correct");
                                userThree.setCountOfNo(userThree.getCountOfNo() + 1);
                            }
                        } else {
                            System.out.println("Incorrect");
                            userThree.setCountOfNo(userThree.getCountOfNo() - 1);
                        }

                    } else {
                        Integer deckRank = pickingOrderedList.get(i).getRank().ordinal();
                        Integer nextDeckRank;
                        if (i <= 27) {
                            nextDeckRank = pickingOrderedList.get(i + 4).getRank().ordinal();
                            System.out.println("Next Suit is: " + pickingOrderedList.get(i + 4));
                        } else {
                            nextDeckRank = pickingOrderedList.get(i + 1).getRank().ordinal();
                            System.out.println("Next Suit is: " + pickingOrderedList.get(i + 1));
                        }

                        if (deckRank > nextDeckRank) {
                            System.out.println("Correct");
                            userThree.setCountOfNo(userThree.getCountOfNo() + 1);
                        } else {
                            System.out.println("Incorrect");
                            userThree.setCountOfNo(userThree.getCountOfNo() - 1);
                        }
                    }
                }

            }
        }

        System.out.println("Results :");
        System.out.println("Card count for u1: " + userOne.getCountOfNo());
        System.out.println("Card count for u2: " + userTwo.getCountOfNo());
        System.out.println("Card count for u3: " + userThree.getCountOfNo());


        sc.close();

    }
}
