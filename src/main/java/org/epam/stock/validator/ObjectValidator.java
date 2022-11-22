package org.epam.stock.validator;

public interface ObjectValidator<T> {
    boolean validate(T t);
}
