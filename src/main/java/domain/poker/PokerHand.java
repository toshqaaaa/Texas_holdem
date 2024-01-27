package domain.poker;

import domain.AbstractHand;
import utils.CardHelper;
import domain.ComparableHand;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * PokerHand class describes player's hand. This class is also implements ComparableHand to "enable" sorting
 */
public final class PokerHand extends AbstractHand implements ComparableHand<PokerHand> {

    private final String cardsAsString;

    /**
     * The Map describes player's hand grouped by face value of each card
     */
    private final Map<String, List<String>> cards;

    public PokerHand(String cards) {
        this.cardsAsString = cards;
        String[] cardsArray = cards.split(" ");
        this.cards = Arrays.stream(cardsArray)
                .collect(Collectors.groupingBy(card -> card.substring(0, card.length() - 1),
                        Collectors.mapping(card -> card.substring(card.length() - 1), Collectors.toList())));
    }

    public Map<String, List<String>> getCards() {
        return cards;
    }

    /**
     * Get combination from Combination class and then compare their costs. If both of combinations are
     * Combination.HIGH_CARD, then we sort each hand in ascending order and get their representation. After that we
     * just take the last element of each hand and compare their cost
     *
     * @param another the another player's hand to be compared.
     */
    @Override
    public int compareTo(PokerHand another) {
        Combination combinationByHand = Combination.getCombinationByHand(this);
        Combination anotherCombinationByHand = Combination.getCombinationByHand(another);
        if (Combination.HIGH_CARD.equals(combinationByHand) && Combination.HIGH_CARD.equals(anotherCombinationByHand)) {
            Integer[] costsListByFaceValues = CardHelper.getAscendingCostsListByFaceValues(this.getCards().keySet());
            Integer[] anotherCostsListByFaceValues =
                    CardHelper.getAscendingCostsListByFaceValues(another.getCards().keySet());
            return anotherCostsListByFaceValues[anotherCostsListByFaceValues.length - 1]
                    - costsListByFaceValues[costsListByFaceValues.length - 1];
        }
        return anotherCombinationByHand.getCost() - combinationByHand.getCost();
    }

    @Override
    public String toString() {
        return cardsAsString;
    }
}
