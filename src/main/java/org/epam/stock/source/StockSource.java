package org.epam.stock.source;

import org.epam.stock.dto.Stock;
import reactor.core.publisher.Flux;

public interface StockSource<T, V> {
    Flux<T> getStocks(V source);
}
