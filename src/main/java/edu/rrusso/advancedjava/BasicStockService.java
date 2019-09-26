package edu.rrusso.advancedjava;

import com.sun.istack.internal.NotNull;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.InvalidParameterException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * This class provides a simple implementation of a StockService to aid in testing
 */
public class BasicStockService implements StockService {
    private List<StockQuote> stockQuotes;

    /**
     * Contruction to initialize test data
     */
    public BasicStockService(){
        final int MINUTES_IN_DAY = 1440;
        stockQuotes = new ArrayList<>();

        Calendar startDate = new GregorianCalendar(2019,8,1,0,0,0);
        double cents = 0.0;
        for (int d = 0; d < 15; d++) {
            Calendar currentDayCal = (Calendar)startDate.clone();
            currentDayCal.add(Calendar.DAY_OF_MONTH, d);

            for(int m = 0; m < MINUTES_IN_DAY; m+=30, cents+=.01) {
                BigDecimal bd = new BigDecimal(cents);
                bd = bd.setScale(2, RoundingMode.HALF_EVEN);

                Calendar currentMinuteCal = (Calendar)currentDayCal.clone();
                currentMinuteCal.add(Calendar.MINUTE, m);

                stockQuotes.add(new StockQuote("MSFT", bd.add(new BigDecimal(m+100)), currentMinuteCal));
                stockQuotes.add(new StockQuote("APPL", bd.add(new BigDecimal(m+120)), currentMinuteCal));
                stockQuotes.add(new StockQuote("IBM", bd.add(new BigDecimal(m+80)), currentMinuteCal));
            }
        }
    }

    /**
     * Return the current StockQuote for a share of stock for the given symbol
     * @param symbol the stock symbol of the company you want a quote for.
     * e.g. APPL for APPLE
     * @return a <CODE>StockQuote </CODE> instance
     */
    public StockQuote getQuote(@NotNull String symbol) {
        List<StockQuote> quotesForSymbol = stockQuotes.stream()
                .filter(x -> x.getStockSymbol().equals(symbol) )
                .collect(Collectors.toList());

        return quotesForSymbol.get(quotesForSymbol.size() - 1); // return last quot in list
    }

    /**
     * Return a list of StockQuotes for a given symbol and date range
     * @param symbol the stock symbol of the company you want a quote for. e.g. APPL for APPLE
     * @param from the strarting date range to search for
     * @param until the ending date range to search for
     * @return a <CODE>StockQuote </CODE> instance
     */
    public List<StockQuote> getQuote(@NotNull String symbol, @NotNull Calendar from, @NotNull Calendar until) {
        Calendar untilFixed = (Calendar)until.clone();
        untilFixed.set(Calendar.HOUR, 23);
        untilFixed.set(Calendar.MINUTE, 59);
        untilFixed.set(Calendar.SECOND, 59);
        untilFixed.set(Calendar.MILLISECOND, 999);

        List<StockQuote> quotesBySymbolAndRange = stockQuotes.stream()
                .filter(x ->
                        ((x.getStockDate().equals(from) || x.getStockDate().after(from)) && (x.getStockDate().equals(untilFixed) || x.getStockDate().before(untilFixed)))
                                && x.getStockSymbol().equals(symbol))
                .collect(Collectors.toList());

        return quotesBySymbolAndRange;
    }

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
    public List<StockQuote> getQuote(@NotNull String symbol, @NotNull Calendar from, @NotNull Calendar until, Interval interval) {

        if(interval == Interval.ALL) {
            return stockQuotes;
        }

        List<StockQuote> quotesBySymbolAndRange = getQuote(symbol, from, until);

        if(interval == Interval.HALF_HOUR){
            return quotesBySymbolAndRange;
        }

        if(interval == Interval.HOUR){
            List<StockQuote> hourlyList = quotesBySymbolAndRange.stream()
                    .filter(x -> x.getStockDate().get(Calendar.MINUTE) == 0)
                    .collect(Collectors.toList());
            return hourlyList;
        }

        if(interval == Interval.DAILY) {
            List<StockQuote> dailyList = quotesBySymbolAndRange.stream()
                    .filter(x -> (x.getStockDate().get(Calendar.HOUR_OF_DAY) == 23) && (x.getStockDate().get(Calendar.MINUTE) == 30))
                    .collect(Collectors.toList());
            return dailyList;
        }

        throw new InvalidParameterException();
    }
}
