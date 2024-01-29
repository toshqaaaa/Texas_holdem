package domain;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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

    private static final Map<String, HighGradeCard> HIGH_GRADE_CARD_MAP;

    static {
        ConcurrentHashMap<String, HighGradeCard> map = new ConcurrentHashMap<>();
        for (HighGradeCard highGradeCard : values()) {
            map.put(highGradeCard.getId(), highGradeCard);
        }
        HIGH_GRADE_CARD_MAP = Collections.unmodifiableMap(map);
    }

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

    public static HighGradeCard getByCardValue(String cardValue) {
        return HIGH_GRADE_CARD_MAP.get(cardValue);
    }
}
