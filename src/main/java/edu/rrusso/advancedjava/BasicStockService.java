package edu.rrusso.advancedjava;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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

        for (int d = 0; d < 15; d++) {
            Calendar currentDayCal = (Calendar)startDate.clone();
            currentDayCal.add(Calendar.DAY_OF_MONTH, d);

            for(int m = 0; m < MINUTES_IN_DAY; m+=30) {
                BigDecimal bd = new BigDecimal(m/100);
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
    public StockQuote getQuote(String symbol, Calendar date) {
        return getQuote(symbol, date, date).get(0);
    }

    /**
     * Return a list of StockQuotes for a given symbol and date range
     * @param symbol the stock symbol of the company you want a quote for. e.g. APPL for APPLE
     * @param from the strarting date range to search for
     * @param until the ending date range to search for
     * @return a <CODE>StockQuote </CODE> instance
     */
    public List<StockQuote> getQuote(String symbol, Calendar from, Calendar until) {
        List<StockQuote> filteredStockQuotes = new ArrayList<StockQuote>();
        for(StockQuote stockQuote : stockQuotes){
            Calendar stockDate = stockQuote.getStockDate();
            if((stockDate.equals(from) || stockDate.after(from)) && (stockDate.equals(until) || stockDate.before(until))){
                filteredStockQuotes.add(stockQuote);
            }
        }

        return filteredStockQuotes;
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
    public List<StockQuote> getQuote(String symbol, Calendar from, Calendar until, Interval interval) {

        List<StockQuote> filteredStockQuotes = new ArrayList<StockQuote>();
        for(StockQuote stockQuote : stockQuotes){
            Calendar stockDate = stockQuote.getStockDate();
            if((stockDate.equals(from) || stockDate.after(from)) && (stockDate.equals(until) || stockDate.before(until))){
                filteredStockQuotes.add(stockQuote);
            }
        }

        return filteredStockQuotes;
    }
}
