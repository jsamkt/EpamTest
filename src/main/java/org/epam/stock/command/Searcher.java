package org.epam.stock.command;

public interface Searcher<T, V> {

    T invoke(V obj);
}
