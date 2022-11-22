package org.epam.stock.command;

import org.apache.commons.collections4.CollectionUtils;
import org.epam.stock.dto.Stock;
import reactor.core.publisher.Flux;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SearchHolidays implements Searcher<LocalDate[], Flux<Stock>> {

    @Override
    public LocalDate[] invoke(Flux<Stock> stocks) {
        AtomicReference<LocalDate> expectedDate = new AtomicReference<>(null);

        Function<LocalDate, List<LocalDate>> calculator = date -> {
            try {
                if (expectedDate.get() == null) {
                    return Collections.emptyList();
                }
                if (!date.equals(expectedDate.get())) {
                    return Flux.<LocalDate, LocalDate>generate(
                            expectedDate::get,
                            (value, sink) -> {
                                if (value.isBefore(date)) {
                                    sink.next(value);
                                } else {
                                    sink.complete();
                                }
                                return value.plusDays(1);
                            })
                            .filter(d -> d.getDayOfWeek() != DayOfWeek.SATURDAY)
                            .filter(d -> d.getDayOfWeek() != DayOfWeek.SUNDAY)
                            .collectList().block();
                }
                return Collections.emptyList();
            } finally {
                expectedDate.set(getNextExpectedWorkDay(date));
            }
        };

        return Objects.requireNonNull(stocks
                        .map(Stock::getDate)
                        .map(calculator)
                        .filter(CollectionUtils::isNotEmpty)
                        .flatMap(Flux::fromIterable)
                        .collectList()
                        .block())
                .toArray(new LocalDate[0]);
    }

    private LocalDate getNextExpectedWorkDay(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return switch (dayOfWeek) {
            case FRIDAY -> date.plusDays(3);
            case SATURDAY -> date.plusDays(2);
            default -> date.plusDays(1);
        };
    }
}
