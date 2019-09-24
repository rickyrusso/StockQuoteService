package edu.rrusso.advancedjava;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class BasicStockServiceTest {

    @Test
    public void getQuote() {
        BasicStockService basicStockService = new BasicStockService();

        Calendar cal = new GregorianCalendar(2019, 8, 20);
        StockQuote stockQuote = basicStockService.getQuote("APPL", cal);

        assertEquals("Test Stock Price Value", stockQuote.getStockPrice(), new BigDecimal(105));
    }
}