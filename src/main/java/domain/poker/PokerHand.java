package domain.poker;

import domain.AbstractHand;
import domain.ComparableHand;
import utils.CardHelper;

import java.util.List;
import java.util.Map;

/**
 * PokerHand class describes player's hand. This class is also implements ComparableHand to "enable" sorting
 */
public final class PokerHand extends AbstractHand implements ComparableHand<PokerHand> {

    /**
     * Initial input string
     */
    private final String cardsAsString;

    /**
     * The Map describes player's hand grouped by face value of each card
     */
    private final Map<Integer, List<String>> cards;

    public PokerHand(String cards) {
        try {
            if (!CardHelper.isHandValid(cards)) {
                throw new IllegalStateException(String.format("Hand %s is not valid", cards));
            }
        } catch (IllegalStateException exception) {
            System.err.println(exception.getMessage());
            System.exit(1);
        } finally {
            this.cardsAsString = cards;
            this.cards = CardHelper.handParser(cards);
        }
    }

    public Map<Integer, List<String>> getCards() {
        return cards;
    }

    /**
     * Get combination with high card from Combination class and then compare their costs
     *
     * @param another the another player's hand to be compared.
     */
    @Override
    public int compareTo(PokerHand another) {
        Map.Entry<Combination, Integer> thisHandCombination = Combination.getCombinationWithCostByHand(this)
                .entrySet()
                .iterator()
                .next();

        Map.Entry<Combination, Integer> anotherHandCombination = Combination.getCombinationWithCostByHand(another)
                .entrySet()
                .iterator()
                .next();

        if (thisHandCombination.getKey().equals(anotherHandCombination.getKey())) {
            return anotherHandCombination.getValue() - thisHandCombination.getValue();
        }

        return anotherHandCombination.getKey().getCombinationCost() - thisHandCombination.getKey().getCombinationCost();

    }

    @Override
    public String toString() {
        return cardsAsString;
    }
}
