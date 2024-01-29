package utils;

import domain.HighGradeCard;
import domain.Suit;

import java.util.*;
import java.util.stream.Collectors;

/**
 * CardHelper class is the utility class that provides some useful methods to analyze player's hand
 */
public class CardHelper {

    private CardHelper() {
    }

    /**
     * Verifies that card value can be parsed
     *
     * @param cardValue - separate card
     */
    public static boolean isCardValueValid(String cardValue) {
        if (!HighGradeCard.DEFAULT.equals(HighGradeCard.getByCardValue(cardValue))) {
            return true;
        }
        try {
            int cardValueAsInt = Integer.parseInt(cardValue);
            return cardValueAsInt >= 2 && cardValueAsInt <= 14;
        } catch (NumberFormatException numberFormatException) {
            System.err.println("Not valid card value: " + cardValue);
            return false;
        }
    }

    /**
     * Verifies suit (second char at each card)
     *
     * @param suit - string representation of card suit
     */
    public static boolean isSuitValid(String suit) {
        return !Suit.DEFAULT.equals(Suit.getBySuitValue(suit));
    }

    /**
     * Verifies face value and suit of the card
     *
     * @param card - separate card
     */
    private static boolean isCardValid(String card) {
        boolean isLengthValid = card.length() == 2;
        String cardValue = card.substring(0, card.length() - 1);
        String suit = card.substring(card.length() - 1);
        boolean cardValueValid = isCardValueValid(cardValue);
        boolean suitValid = isSuitValid(suit);
        return isLengthValid && cardValueValid && suitValid;
    }

    /**
     * Verifies all cards before PokerHand is instantiated
     *
     * @param hand - String representation of poker hand
     */
    public static boolean isHandValid(String hand) {
        Set<String> handAsSet = Arrays.stream(hand.split(" ")).collect(Collectors.toSet());
        if (handAsSet.size() != 5) {
            return false;
        }
        return handAsSet.stream().allMatch(CardHelper::isCardValid);
    }

    /**
     * Parse input string to Map<Integer, List<String>> where
     * key - is card value;
     * value - collection of suits
     *
     * @param cards - validated cards as string
     */
    public static Map<Integer, List<String>> handParser(String cards) {
        String[] cardsArray = cards.split(" ");
        Map<Integer, List<String>> cardsMap = Arrays.stream(cardsArray)
                .collect(Collectors.groupingBy(CardHelper::cardValueParser,
                        Collectors.mapping(card -> card.substring(card.length() - 1), Collectors.toList())));
        return new TreeMap<>(cardsMap);
    }

    /**
     * Parse input card by value (first char at string)
     *
     * @param card - a card from hand
     */
    private static Integer cardValueParser(String card) {
        String cardValue = card.substring(0, card.length() - 1);

        HighGradeCard highGradeCard = HighGradeCard.getByCardValue(cardValue);
        return HighGradeCard.DEFAULT.equals(highGradeCard) ? Integer.parseInt(cardValue)
                : highGradeCard.getCost();
    }

    /**
     * Verify that all suits from hand are the same
     *
     * @param suits - suits collection
     */

    public static boolean isSameSuit(Collection<List<String>> suits) {
        return suits.stream().flatMap(Collection::stream).collect(Collectors.toSet()).size() == 1;
    }

    /**
     * Verifies that cards order is sequential. This method is only used in case when there are 5 different keys
     * into card map (collection of faceValues is already ordered that is why difference between last and first element,
     * in case of sequential order, will be fixed and equals to 4)
     * Example 1:
     * input: 2, 3, 4, 5, 6
     * output: true
     * Example 2:
     * input: 7, 9, T, J, Q
     * output: false
     *
     * @param faceValues - face value (ru: номинал, достоинство карты. Перевод указан для уточнения двоякости понятия
     *                   номинала/достоинства карты в английском языке)
     */
    public static boolean isInSequentialOrder(Set<Integer> faceValues) {
        Integer[] costs = faceValues.toArray(Integer[]::new);
        return costs[costs.length - 1] - costs[0] == 4;
    }
}
