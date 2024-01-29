package org.example.utils;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class CardHelperTest {

    private static final List<String> VALID_CARD_VALUES = List.of("2", "9", "T", "J", "Q", "K", "A");
    private static final List<String> INVALID_CARD_VALUES = List.of("0", "X", "", " ", "AC");
    private static final List<String> VALID_SUITS = List.of("S", "C", "H", "D");
    private static final List<String> INVALID_SUITS = List.of("0", "Z", "", " ", "AC", "X");
    private static final List<String> VALID_HANDS = List.of("KS 2H 5C JD TD", "2C 3C AC 4C 5C", "2C 3C 4D 5S 6D");
    private static final List<String> INVALID_HANDS = List.of("2C 2C 2C 4C 5C", "2C 3C AC 4C 5C 7D", "2C3C AC 4C 5C",
            "0X 3C AC 4C 5C", "2C 3C -4C 4C 5C", "", " ");

    @Test
    public void isCardValueValid() {
        boolean allValid = VALID_CARD_VALUES.stream().allMatch(CardHelper::isCardValueValid);
        Assert.assertTrue(allValid);
        boolean allInvalid = INVALID_CARD_VALUES.stream().noneMatch(CardHelper::isCardValueValid);
        Assert.assertTrue(allInvalid);
    }

    @Test
    public void isSuitValid() {
        boolean allValid = VALID_SUITS.stream().allMatch(CardHelper::isSuitValid);
        Assert.assertTrue(allValid);
        boolean allInvalid = INVALID_SUITS.stream().noneMatch(CardHelper::isSuitValid);
        Assert.assertTrue(allInvalid);
    }

    @Test
    public void isHandValid() {
        boolean allValid = VALID_HANDS.stream().allMatch(CardHelper::isHandValid);
        Assert.assertTrue(allValid);
        boolean allInvalid = INVALID_HANDS.stream().noneMatch(CardHelper::isHandValid);
        Assert.assertTrue(allInvalid);
    }

    @Test
    public void handParser() {
        Set<Map<Integer, List<String>>> validHandsParsed = VALID_HANDS.stream()
                .map(CardHelper::handParser)
                .collect(Collectors.toSet());
        Assert.assertEquals(VALID_HANDS.size(), validHandsParsed.size());
    }

    @Test
    public void isSameSuit() {
        Set<Map<Integer, List<String>>> validHandsParsed = VALID_HANDS.stream()
                .map(CardHelper::handParser)
                .collect(Collectors.toSet());
        boolean isSameSuitHandExists = validHandsParsed.stream().anyMatch(map -> CardHelper.isSameSuit(map.values()));
        Assert.assertTrue(isSameSuitHandExists);
    }

    @Test
    public void isInSequentialOrder() {
        Set<Map<Integer, List<String>>> validHandsParsed = VALID_HANDS.stream()
                .map(CardHelper::handParser)
                .collect(Collectors.toSet());
        boolean inSequentialOrder = validHandsParsed.stream()
                .anyMatch(map -> CardHelper.isInSequentialOrder(map.keySet()));
        Assert.assertTrue(inSequentialOrder);
    }
}