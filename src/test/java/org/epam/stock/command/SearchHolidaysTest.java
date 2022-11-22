package org.epam.stock.command;

import org.epam.stock.dto.Stock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.TreeSet;

public class SearchHolidaysTest {

//   2022-07-01, 296.65, 303.53, 294.46, 302.12, 302.12, 414000
//   2022-07-05, 299.4,  310.64, 296.87, 310.64, 310.64, 377600
//   2022-07-06, 309.86, 318.99, 308.61, 311.94, 311.94, 415400
//   2022-07-11, 314.43, 318.08, 309.47, 317.14, 317.14, 400000
//   2022-07-12, 319.17, 329.67, 302.05, 304.75, 304.75, 507600
//   2022-07-13, 299,    311.32, 297.26, 302.39, 302.39, 306900
//   2022-07-14, 298.44, 299.77, 291.43, 298.03, 298.03, 303300
//   2022-07-15, 305.77, 308.38, 299.44, 302.56, 302.56, 321400

    private LocalDate getDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(date, formatter);
    }

    private Set<LocalDate> asSet(String... dates) {
        Set<LocalDate> set = new TreeSet<>();
        for (String date : dates) {
            set.add(getDate(date));
        }
        return set;
    }

    private Set<LocalDate> asSet(LocalDate[] dates) {
        Set<LocalDate> set = new TreeSet<>();
        for (LocalDate date : dates) {
            set.add(date);
        }
        return set;
    }

    @Test
    public void testSearchHolidays() {
        Flux<Stock> stocks = Flux.just(
                new Stock().date(getDate("2022-07-01")),
                new Stock().date(getDate("2022-07-05")),
                new Stock().date(getDate("2022-07-06")),
                new Stock().date(getDate("2022-07-11")),
                new Stock().date(getDate("2022-07-12")),
                new Stock().date(getDate("2022-07-13")),
                new Stock().date(getDate("2022-07-14")),
                new Stock().date(getDate("2022-07-14"))
        );

        SearchHolidays searchHolidays = new SearchHolidays();
        LocalDate[] searched = searchHolidays.invoke(stocks);

        Assertions.assertEquals(
                asSet("2022-07-04", "2022-07-07", "2022-07-08"),
                asSet(searched)
        );
    }

    @Test
    public void testSearchHolidays_Zerro() {
        Flux<Stock> stocks = Flux.just(
                new Stock().date(getDate("2022-07-01")),
                new Stock().date(getDate("2022-07-02")),
                new Stock().date(getDate("2022-07-03")),
                new Stock().date(getDate("2022-07-04")),
                new Stock().date(getDate("2022-07-05")),
                new Stock().date(getDate("2022-07-06"))
        );

        SearchHolidays searchHolidays = new SearchHolidays();
        LocalDate[] searched = searchHolidays.invoke(stocks);

        Assertions.assertEquals(
                asSet(),
                asSet(searched)
        );
    }

    @Test
    public void testSearchHolidays_Many() {
        Flux<Stock> stocks = Flux.just(
                new Stock().date(getDate("2022-07-01")),
                new Stock().date(getDate("2022-07-10"))
        );

        SearchHolidays searchHolidays = new SearchHolidays();
        LocalDate[] searched = searchHolidays.invoke(stocks);

        Assertions.assertEquals(
                asSet("2022-07-04","2022-07-05","2022-07-06","2022-07-07","2022-07-08"),
                asSet(searched)
        );
    }
}
