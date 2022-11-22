package org.epam.stock;

import org.epam.stock.command.SearchHolidays;
import org.epam.stock.command.Searcher;
import org.epam.stock.dto.Stock;
import org.epam.stock.mapper.ObjectMapper;
import org.epam.stock.mapper.StockCSVMapper;
import org.epam.stock.parser.CSVParser;
import org.epam.stock.parser.Parser;
import org.epam.stock.source.StockSource;
import org.epam.stock.source.StocksFromFile;
import org.epam.stock.validator.DateValidatorYYYY_MM_DD;
import org.epam.stock.validator.ObjectValidator;
import org.epam.stock.validator.StockValidator;
import reactor.core.publisher.Flux;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        StockSource<String, String> source = getSource();
        Searcher<LocalDate[], Flux<Stock>> searcher = getSearcher();
        ObjectMapper<Stock> mapper = getStockMapper();
        ObjectValidator<String> validator = getStockValidator();

        Flux<Stock> stocks = source.getStocks("EPAM.csv")
                .filter(validator::validate)
                .map(mapper::map);

        LocalDate[] days = searcher.invoke(stocks);

        for (LocalDate day : days) {
            System.out.println(day);
        }

    }

    private static ObjectValidator<String> getStockValidator() {
        List<ObjectValidator<String>> dateValidator = Arrays.asList(new DateValidatorYYYY_MM_DD());
        List<ObjectValidator<String>> openPriceValidators = Collections.emptyList();
        List<ObjectValidator<String>> closePriceValidators = Collections.emptyList();
        List<ObjectValidator<String>> highPriceValidators = Collections.emptyList();
        List<ObjectValidator<String>> lowPriceValidators = Collections.emptyList();
        List<ObjectValidator<String>> adjPriceValidators = Collections.emptyList();
        List<ObjectValidator<String>> volumeValidators = Collections.emptyList();
        return new StockValidator(dateValidator,
                openPriceValidators,
                closePriceValidators,
                highPriceValidators,
                lowPriceValidators,
                adjPriceValidators,
                volumeValidators,
                getParser());
    }

    private static Parser getParser() {
        return new CSVParser();
    }

    private static StockSource<String, String> getSource() {
        return new StocksFromFile();
    }

    private static ObjectMapper<Stock> getStockMapper() {
        return new StockCSVMapper("yyyy-MM-dd", getParser());
    }

    private static Searcher<LocalDate[], Flux<Stock>> getSearcher() {
        return new SearchHolidays();
    }

}