package org.example.domain.poker;

import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class PokerHandTest {

    private static final List<String> VALID_HANDS = List.of("KS 2H 5C JD TD", "2C 3C AC 4C 5C", "2C 3C 4D 5S 6D");
    @Test
    public void compareTo() {
        List<PokerHand> hands = VALID_HANDS.stream().map(PokerHand::new).collect(Collectors.toList());
        Collections.sort(hands);

        List<PokerHand> correctOrderHands = List.of(new PokerHand("2C 3C AC 4C 5C"),
                new PokerHand("2C 3C 4D 5S 6D"), new PokerHand("KS 2H 5C JD TD"));

        assertEquals(correctOrderHands.size(), hands.size());

        for (int i = 0; i < hands.size(); i++) {
            assertEquals(hands.get(i), correctOrderHands.get(i));
        }
    }
}