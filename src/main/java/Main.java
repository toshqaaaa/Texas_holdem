import org.example.domain.poker.PokerHand;

import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        ArrayList<PokerHand> hands = new ArrayList<>();
        hands.add(new PokerHand("KS 2H 5C JD TD"));
        hands.add(new PokerHand("2C 3C AC 4C 5C"));

        System.out.println("Initial order: " + hands);

        Collections.sort(hands);

        System.out.println("Result order " + hands);
    }
}