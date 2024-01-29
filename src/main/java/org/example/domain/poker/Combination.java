package org.example.domain.poker;

import org.example.utils.CardHelper;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Combination enum is used for define the specific poker combination. All combinations are in ascending order according
 * to the info about <a href="https://en.wikipedia.org/wiki/Texas_hold_%27em">Texas hold'em</a>
 */
enum Combination {

    HIGH_CARD(0),
    PAIR(1),
    TWO_PAIRS(2),
    THREE_OF_A_KIND(3),
    STRAIGHT(4),
    FLUSH(5),
    FULL_HOUSE(6),
    FOUR_OF_A_KIND(7),
    STRAIGHT_FLUSH(8);

    private final int combinationCost;

    Combination(int combinationCost) {
        this.combinationCost = combinationCost;
    }

    public int getCombinationCost() {
        return combinationCost;
    }

    public static Map<Combination, Integer> getCombinationWithCostByHand(PokerHand pokerhand) {
        Map<Integer, List<String>> cards = pokerhand.getCards();

        int faceValuesSize = cards.size();
        int highCardCost = Collections.max(cards.keySet());

        switch (faceValuesSize) {
            case 2:
                return Collections.singletonMap(
                        cards.values().stream().map(List::size).anyMatch(size -> size == 4) ?
                                FOUR_OF_A_KIND : FULL_HOUSE,
                        highCardCost
                );
            case 3:
                return Collections.singletonMap(
                        cards.values().stream().map(List::size).anyMatch(size -> size == 3) ?
                                THREE_OF_A_KIND : TWO_PAIRS,
                        highCardCost
                );
            case 4:
                return Collections.singletonMap(PAIR, highCardCost);
            case 5:
                boolean sameSuit = CardHelper.isSameSuit(cards.values());
                boolean inSequentialOrder = CardHelper.isInSequentialOrder(cards.keySet());
                if (!sameSuit && inSequentialOrder) {
                    return Collections.singletonMap(STRAIGHT, highCardCost);
                }
                if (sameSuit && inSequentialOrder) {
                    return Collections.singletonMap(STRAIGHT_FLUSH, highCardCost);
                }
                if (sameSuit) {
                    return Collections.singletonMap(FLUSH, highCardCost);
                }
        }
        return Collections.singletonMap(HIGH_CARD, highCardCost);
    }
}
