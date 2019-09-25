package edu.rrusso.advancedjava;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class BasicStockServiceTest {

    @Test
    public void getQuote() {
        BasicStockService basicStockService = new BasicStockService();

        Calendar cal = new GregorianCalendar(2019, 8, 10);
        StockQuote stockQuote = basicStockService.getQuote("APPL", cal);
        BigDecimal stockValue = stockQuote.getStockPrice();
        stockValue.setScale(2, RoundingMode.HALF_EVEN);

        BigDecimal expectedValue = new BigDecimal(100.00);

        expectedValue.setScale(2, RoundingMode.HALF_EVEN);

        assertTrue("Test Stock Price Value", stockValue.compareTo(expectedValue) == 0);
    }
}