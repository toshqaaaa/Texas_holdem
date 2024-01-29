package org.example.domain.poker;

import org.example.domain.HighGradeCard;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class CombinationTest {

    private static final List<String> VALID_HANDS = List.of("KS 2H 5C JD TD", "2C 3C AC 4C 5C", "2C 3C 4D 5S 6D");

    @Test
    public void getCombinationWithCostByHand() {
        List<PokerHand> handsParsed = VALID_HANDS.stream().map(PokerHand::new).collect(Collectors.toList());
        Set<Map<Combination, Integer>> combinations = handsParsed.stream()
                .map(Combination::getCombinationWithCostByHand)
                .collect(Collectors.toSet());

        boolean correctCombinationWithCost = combinations.stream().anyMatch(map -> {
            if (map.containsKey(Combination.FLUSH) && map.containsValue(HighGradeCard.ACE.getCost())) {
                return true;
            }
            if (map.containsKey(Combination.HIGH_CARD) && map.containsValue(HighGradeCard.KING.getCost())) {
                return true;
            }
            return map.containsKey(Combination.STRAIGHT) && map.containsValue(6);
        });

        Assert.assertTrue(correctCombinationWithCost);
    }
}