package edu.rrusso.advancedjava;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class App {

    /**
     * program to retrieve the Stock Quotes in a given date range
     *
     * @param args command line parameters to receive a symbol, from date, until date
     */
    public static void main(String[] args) {
        String symbol = args[0];
        Calendar from = parseDate(args[1]);
        Calendar until = parseDate(args[2]);
        until.set(Calendar.HOUR, 23);
        until.set(Calendar.MINUTE, 59);
        until.set(Calendar.SECOND, 59);
        until.set(Calendar.MILLISECOND, 999);

        StockServiceFactory stockServiceFactory = new StockServiceFactory(new BasicStockService());
        StockService stockService = stockServiceFactory.getStockService();

        List<StockQuote> filteredStockQuotes = stockService.getQuote(symbol, from, until);
        for(StockQuote stockQuote : filteredStockQuotes){
            System.out.println(stockQuote);
        }
    }

    /**
     * Private helper method to parse a date from a string and returns a Calendar object
     *
     * @param strDate a String containing a date in MM/dd/yyyy format
     */
    private static Calendar parseDate(String strDate){
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Date date = null;
        try {
            date = sdf.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }
}
