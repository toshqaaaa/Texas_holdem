package utils;

import domain.HighGradeCard;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * CardHelper class is the utility class that provides some useful methods to analyze player's hand
 */
public class CardHelper {

    private CardHelper() {
    }

    /**
     * Verifies that all cards belong to the same suit
     *
     * @param cards - player's hand
     */
    public static boolean isSameSuit(Map<String, List<String>> cards) {
        return cards.values().stream().mapToInt(Collection::size).sum() == 1;
    }

    /**
     * Get costs array of player's hand
     *
     * @param faceValues - face value (ru: номинал, достоинство карты. Перевод указан для уточнения двоякости понятия
     *                   номинала/достоинства карты в английском языке).
     */
    public static Integer[] getAscendingCostsListByFaceValues(Set<String> faceValues) {
        return faceValues.stream().map(faceValue -> {
                    HighGradeCard highGradeCard = HighGradeCard.getById(faceValue);
                    if (HighGradeCard.DEFAULT.equals(highGradeCard)) {
                        return Integer.parseInt(faceValue);
                    }
                    return highGradeCard.getCost();
                })
                .sorted().toArray(Integer[]::new);
    }

    /**
     * Verifies that cards order is sequential
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
    public static boolean isInSequentialOrder(Set<String> faceValues) {
        Integer[] costs = getAscendingCostsListByFaceValues(faceValues);
        return costs[costs.length - 1] - costs[0] == 4;
    }
}
