package domain;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Suit enum serves to check hand input suits.
 */
public enum Suit {

    DEFAULT("NONE"),
    SPADES("S"),
    HEARTS("H"),
    DIAMONDS("D"),
    CLUBS("C");

    private static final Map<String, Suit> SUIT_MAP;

    static {
        ConcurrentHashMap<String, Suit> map = new ConcurrentHashMap<>();
        for (Suit suit : values()) {
            map.put(suit.getType(), suit);
        }
        SUIT_MAP = Collections.unmodifiableMap(map);
    }

    private final String type;

    Suit(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static Suit getBySuitValue(String suit) {
        return SUIT_MAP.getOrDefault(suit, DEFAULT);
    }
}
