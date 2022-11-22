package org.epam.stock.validator;

public class DateValidatorYYYY_MM_DD implements ObjectValidator<String>{
    private static final String pattern = "\\d{4}-\\d{2}-\\d{2}";
    @Override
    public boolean validate(String date) {
        return date != null && date.matches(pattern);
    }
}