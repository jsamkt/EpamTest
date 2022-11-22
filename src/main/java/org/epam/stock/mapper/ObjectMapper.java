package org.epam.stock.mapper;

public interface ObjectMapper<T> {
    T map(String str);
}