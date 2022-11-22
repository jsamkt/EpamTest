package org.epam.stock.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Stock {
    private LocalDate date;
    private BigDecimal openPrice;
    private BigDecimal highPrice;
    private BigDecimal lowPrice;
    private BigDecimal closePrice;
    private BigDecimal adjPrice;
    private Long volume;

    public Stock() {
    }

    public Stock(LocalDate date, BigDecimal openPrice, BigDecimal highPrice, BigDecimal lowPrice, BigDecimal closePrice, BigDecimal adjPrice, Long volume) {
        this.date = date;
        this.openPrice = openPrice;
        this.highPrice = highPrice;
        this.lowPrice = lowPrice;
        this.closePrice = closePrice;
        this.adjPrice = adjPrice;
        this.volume = volume;
    }

    public Stock date(LocalDate date) {
        setDate(date);
        return this;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Stock openPrice(BigDecimal openPrice) {
        setOpenPrice(openPrice);
        return this;
    }

    public BigDecimal getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(BigDecimal openPrice) {
        this.openPrice = openPrice;
    }

    public Stock highPrice(BigDecimal highPrice) {
        setHighPrice(highPrice);
        return this;
    }

    public BigDecimal getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(BigDecimal highPrice) {
        this.highPrice = highPrice;
    }

    public Stock lowPrice(BigDecimal lowPrice) {
        setLowPrice(lowPrice);
        return this;
    }

    public BigDecimal getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(BigDecimal lowPrice) {
        this.lowPrice = lowPrice;
    }

    public Stock closePrice(BigDecimal closePrice) {
        setClosePrice(closePrice);
        return this;
    }

    public BigDecimal getClosePrice() {
        return closePrice;
    }

    public void setClosePrice(BigDecimal closePrice) {
        this.closePrice = closePrice;
    }

    public Stock adjPrice(BigDecimal adjPrice) {
        setAdjPrice(adjPrice);
        return this;
    }

    public BigDecimal getAdjPrice() {
        return adjPrice;
    }

    public void setAdjPrice(BigDecimal adjPrice) {
        this.adjPrice = adjPrice;
    }

    public Stock volume(Long volume) {
        setVolume(volume);
        return this;
    }

    public Long getVolume() {
        return volume;
    }

    public void setVolume(Long volume) {
        this.volume = volume;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stock stock = (Stock) o;
        return Objects.equals(date, stock.date) && Objects.equals(openPrice, stock.openPrice) && Objects.equals(highPrice, stock.highPrice) && Objects.equals(lowPrice, stock.lowPrice) && Objects.equals(closePrice, stock.closePrice) && Objects.equals(adjPrice, stock.adjPrice) && Objects.equals(volume, stock.volume);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, openPrice, highPrice, lowPrice, closePrice, adjPrice, volume);
    }

    @Override
    public String toString() {
        return "Stock{" +
                "date=" + date +
                ", openPrice=" + openPrice +
                ", highPrice=" + highPrice +
                ", lowPrice=" + lowPrice +
                ", closePrice=" + closePrice +
                ", adjPrice=" + adjPrice +
                ", volume=" + volume +
                '}';
    }
}
