package edu.rrusso.advancedjava;

import java.util.Calendar;
import java.util.List;

public interface StockService {
    /**
     * Return the current price for a share of stock for the given symbol
     * @param symbol the stock symbol of the company you want a quote for.
     * e.g. APPL for APPLE
     * @return a <CODE>StockQuote </CODE> instance
     */
    StockQuote getQuote(String symbol, Calendar date);


    /**
     * Return a list of StockQuotes for a given symbol and date range
     * @param symbol the stock symbol of the company you want a quote for. e.g. APPL for APPLE
     * @param from the strarting date range to search for
     * @param until the ending date range to search for
     * @return a <CODE>StockQuote </CODE> instance
     */
    List<StockQuote> getQuote(String symbol, Calendar from, Calendar until);


    /**
     * Get a historical list of stock quotes for the provide symbol
     * This method will return one StockQuote per interval specified.
     *
     * @param symbol the stock symbol to search for
     * @param from the date of the first stock quote
     * @param until the date of the last stock quote
     * @param interval ­ the number of StockQuotes to get. E.g. if Interval.DAILY was
     * specified
     * one StockQuote per day will be returned.
     * @return a list of StockQuote instances. One for each day in the range specified.
     */
    List<StockQuote> getQuote(String symbol, Calendar from, Calendar until, Interval interval);
}