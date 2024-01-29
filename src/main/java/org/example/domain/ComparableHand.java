package org.example.domain;

/**
 * ComparableHand serves to increase abstraction in the application (kind of “padding” between the AbstractCard class
 * and the Comparable interface)
 *
 * @param <T> - inheritor of the AbstractHand class
 */
public interface ComparableHand<T extends AbstractHand> extends Comparable<T> {
}
