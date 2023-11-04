package it.unibo.inner.impl;

import it.unibo.inner.api.IterableWithPolicy;

import java.util.function.Predicate;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T> {
    private final T[] elements;
    private Predicate<T> filter;

    /**
     * Iterates over all the elements.
     * The default policy is to take all the elements.
     * @param elements the elements to iterate over.
     */
    public IterableWithPolicyImpl(T[] elements) {
        this(elements, new Predicate<T>() {
            @Override
            public boolean test(T elem) {
                return true;
            }
        });
    }

    /**
     * Iterates over the elements that satisfy the given filter.
     * @param elements the elements to iterate over.
     * @param filter the filter to apply.
     */
    public IterableWithPolicyImpl(T[] elements, Predicate<T> filter) {
        this.elements = elements;
        this.filter = filter;
    }

    @Override
    public void setIterationPolicy(Predicate<T> filter) {
        this.filter = filter;
    }

    @Override
    public Iterator<T> iterator() {
        return new FilterIterator();
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append("[");
        for (var elem : this) {
            sb.append(elem + ", ");
        }
        sb.append("]");
        return sb.toString();
    }

    private class FilterIterator implements Iterator<T> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            while (currentIndex < elements.length) {
                T elem = elements[currentIndex];
                if (filter.test(elem)) {
                    return true;
                }
                currentIndex++;
            }
            return false;
        }

        @Override
        public T next() {
            if (hasNext()) {
                T element = elements[currentIndex];
                currentIndex++;
                return element;
            }
            throw new NoSuchElementException();
        }
    }
}
