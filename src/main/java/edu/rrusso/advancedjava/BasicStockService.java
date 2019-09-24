package edu.rrusso.advancedjava;

import java.math.BigDecimal;
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
        stockQuotes = new ArrayList<>();

        Calendar startDate = new GregorianCalendar(2019,8,15,0,0,0);

        for (int i = 0; i < 15; i++) {
            Calendar currentCal = (Calendar)startDate.clone();
            currentCal.add(Calendar.DAY_OF_MONTH, i);

            stockQuotes.add(new StockQuote("MSFT", 100 + i, currentCal));
            stockQuotes.add(new StockQuote("APPL", 120 + i, currentCal));
            stockQuotes.add(new StockQuote("IBM", 80 + i, currentCal));
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
}
