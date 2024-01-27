package domain;

import java.util.Arrays;

/**
 * HighGradeCard describes useful representation of cards that have face value as a letter.
 */
public enum HighGradeCard {

    DEFAULT("NONE", -1),
    TEN("T", 10),
    JACK("J", 11),
    QUEEN("Q", 12),
    KING("K", 13),
    ACE("A", 14);

    private final String id;

    private final int cost;

    HighGradeCard(String id, int cost) {
        this.id = id;
        this.cost = cost;
    }

    public String getId() {
        return id;
    }

    public int getCost() {
        return cost;
    }

    public static HighGradeCard getById(String cardFaceValue) {
        return Arrays.stream(values())
                .filter(highGradeCard -> highGradeCard.getId().equals(cardFaceValue))
                .findFirst()
                .orElse(HighGradeCard.DEFAULT);
    }
}
