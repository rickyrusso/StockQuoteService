package edu.rrusso.advancedjava;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.*;

public class BasicStockServiceTest {

    @Test
    public void getQuote() {
        BasicStockService basicStockService = new BasicStockService();

        StockQuote stockQuote = basicStockService.getQuote("APPL");
        BigDecimal stockValue = stockQuote.getStockPrice();
        BigDecimal expectedValue = new BigDecimal(1537.19).setScale(2, RoundingMode.HALF_EVEN);

        assertTrue("Test Stock Price Value", stockValue.compareTo(expectedValue) == 0);
    }

    @Test
    public void getQuoteWithDateRangeAndSymbol(){
        BasicStockService basicStockService = new BasicStockService();

        Calendar fromDate = new GregorianCalendar(2019, 8, 10);
        Calendar untilDate = new GregorianCalendar(2019, 8, 11);
        List<StockQuote> stockQuotes = basicStockService.getQuote("APPL", fromDate, untilDate);

        assertEquals("Test number of stock quotes returned are 96", 96, stockQuotes.size());

        BigDecimal expectedValue = new BigDecimal(424.42).setScale(2, RoundingMode.HALF_EVEN);
        assertEquals("test that the 10th element has a prices of 424.42", expectedValue, stockQuotes.get(10).getStockPrice());
    }
}