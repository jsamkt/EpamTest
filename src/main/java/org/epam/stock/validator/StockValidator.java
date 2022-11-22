package org.epam.stock.validator;

import org.epam.stock.parser.Parser;

import java.util.List;

public class StockValidator implements ObjectValidator<String> {

    private final List<ObjectValidator<String>> dateValidators;
    private final List<ObjectValidator<String>> openPriceValidators;
    private final List<ObjectValidator<String>> closePriceValidators;
    private final List<ObjectValidator<String>> highPriceValidators;
    private final List<ObjectValidator<String>> lowPriceValidators;
    private final List<ObjectValidator<String>> adjPriceValidators;
    private final List<ObjectValidator<String>> volumeValidators;

    private final Parser parser;


    public StockValidator(List<ObjectValidator<String>> dateValidators,
                          List<ObjectValidator<String>> openPriceValidators,
                          List<ObjectValidator<String>> closePriceValidators,
                          List<ObjectValidator<String>> highPriceValidators,
                          List<ObjectValidator<String>> lowPriceValidators,
                          List<ObjectValidator<String>> adjPriceValidators,
                          List<ObjectValidator<String>> valumeValidators,
                          Parser parser) {
        this.dateValidators = dateValidators;
        this.openPriceValidators = openPriceValidators;
        this.closePriceValidators = closePriceValidators;
        this.highPriceValidators = highPriceValidators;
        this.lowPriceValidators = lowPriceValidators;
        this.adjPriceValidators = adjPriceValidators;
        this.volumeValidators = valumeValidators;
        this.parser = parser;
    }

    @Override
    public boolean validate(String stock) {
        String[] tokens = parser.tokens(stock);
        if (tokens.length != 7) {
            return false;
        }
        String date = tokens[0];
        String openPrice = tokens[1];
        String highPrice = tokens[2];
        String lowPrice = tokens[3];
        String closePrice = tokens[4];
        String adjPrice = tokens[5];
        String volume = tokens[6];

        boolean dateValidate = dateValidators.stream().allMatch(validator -> validator.validate(date));
        boolean openPriceValidate = openPriceValidators.stream().allMatch(validator -> validator.validate(openPrice));
        boolean highPriceValidate = highPriceValidators.stream().allMatch(validator -> validator.validate(highPrice));
        boolean lowPriceValidate = lowPriceValidators.stream().allMatch(validator -> validator.validate(lowPrice));
        boolean closePriceValidate = closePriceValidators.stream().allMatch(validator -> validator.validate(closePrice));
        boolean adjPriceValidate = adjPriceValidators.stream().allMatch(validator -> validator.validate(adjPrice));
        boolean volumeValidate = volumeValidators.stream().allMatch(validator -> validator.validate(volume));

        return dateValidate &&
                openPriceValidate &&
                highPriceValidate &&
                lowPriceValidate &&
                closePriceValidate &&
                adjPriceValidate &&
                volumeValidate;
    }
}