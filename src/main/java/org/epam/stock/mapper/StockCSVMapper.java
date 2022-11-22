package org.epam.stock.mapper;

import org.epam.stock.dto.Stock;
import org.epam.stock.parser.Parser;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class StockCSVMapper implements ObjectMapper<Stock> {
    private final DateTimeFormatter dateFormat;
    private final Parser parser;

    public StockCSVMapper(String dateFormatPattern, Parser parser) {
        this.dateFormat = DateTimeFormatter.ofPattern(dateFormatPattern);
        this.parser = parser;
    }

    @Override
    public Stock map(String str) {
        String[] tokens = parser.tokens(str);
        String date = tokens[0];
        String openPrice = tokens[1];
        String highPrice = tokens[2];
        String lowPrice = tokens[3];
        String closePrice = tokens[4];
        String adjPrice = tokens[5];
        String volume = tokens[6];

        return new Stock()
                .date(LocalDate.parse(date, dateFormat))
                .openPrice(new BigDecimal(openPrice))
                .highPrice(new BigDecimal(highPrice))
                .lowPrice(new BigDecimal(lowPrice))
                .closePrice(new BigDecimal(closePrice))
                .adjPrice(new BigDecimal(adjPrice))
                .volume(Long.parseLong(volume));
    }

}