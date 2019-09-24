package edu.rrusso.advancedjava;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class StockQuoteTest {
    private StockQuote stockQuote;

    @Before
    public void setUp(){
        Calendar cal = new GregorianCalendar(2019, 8, 15);
        stockQuote = new StockQuote("APPL", new BigDecimal(120), cal);
    }

    @Test
    public void getStockQuoteValues() {
        assertEquals("Test for correct symbol", "APPL", stockQuote.getStockSymbol());
        assertEquals("test for correct value", new BigDecimal(120), stockQuote.getStockPrice());

        Calendar date = new GregorianCalendar(2019, 8, 15);
        assertEquals("test for correct date", date, stockQuote.getStockDate());
    }
}