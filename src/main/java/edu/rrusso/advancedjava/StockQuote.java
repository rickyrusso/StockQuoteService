package edu.rrusso.advancedjava;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class StockQuote {
    private String symbol;
    private BigDecimal price;
    private Calendar date;
    private SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm");

    /**
     *  Create a new  StockQuote instance
     * @param symbol the stock symbol
     * @param price the price
     * @param date the date
     */
    public StockQuote(String symbol, BigDecimal price, Calendar date){
        this.symbol = symbol;
        this.price = price;
        this.date = date;
    }

    /**
     * @return the stock symbol
     */
    public String getStockSymbol() {
        return symbol;
    }

    /**
     * @return The price of the stock.
     */
    public BigDecimal getStockPrice() {
        return price;
    }

    /**
     * @return The date of the stock.
     */
    public Calendar getStockDate() {
        return date;
    }

    @Override
    public String toString() {
        return String.format("Symbol = %s, Price = %s, Date = %s", this.symbol, this.price, sdf.format((this.date.getTime())));
    }
}
