package domain.poker;

import utils.CardHelper;

import java.util.List;
import java.util.Map;
import java.util.Set;

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

    private final int cost;

    Combination(int cost) {
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }

    public static Combination getCombinationByHand(PokerHand pokerhand) {
        Map<String, List<String>> cards = pokerhand.getCards();
        Set<String> faceValues = cards.keySet();
        int faceValuesSize = faceValues.size();

        switch (faceValuesSize) {
            case 2:
                return cards.values().stream().map(List::size).anyMatch(size -> size == 4) ? FOUR_OF_A_KIND : FULL_HOUSE;
            case 3:
                return cards.values().stream().map(List::size).anyMatch(size -> size == 3) ? THREE_OF_A_KIND : TWO_PAIRS;
            case 4:
                return PAIR;
            case 5:
                boolean sameSuit = CardHelper.isSameSuit(cards);
                boolean inSequentialOrder = CardHelper.isInSequentialOrder(faceValues);
                if (!sameSuit && inSequentialOrder) {
                    return STRAIGHT;
                }
                if (sameSuit && inSequentialOrder) {
                    return STRAIGHT_FLUSH;
                }
                if (sameSuit) {
                    return FLUSH;
                }
        }
        return HIGH_CARD;
    }
}
