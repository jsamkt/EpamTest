package org.epam.stock.parser;

public class CSVParser implements Parser{
    @Override
    public String[] tokens(String line) {
        return line.split(",");
    }
}
