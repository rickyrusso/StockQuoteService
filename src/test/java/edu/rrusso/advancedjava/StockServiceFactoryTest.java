package edu.rrusso.advancedjava;

import org.junit.Test;
import java.math.BigDecimal;
import java.util.Calendar;

import static org.junit.Assert.*;
import org.mockito.Mockito;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

public class StockServiceFactoryTest {
    private StockServiceFactory stockTicker;

    //  test that no exception is thrown
    @Test
    public void getStockService() {
        StockServiceFactory stockServiceFactory = new StockServiceFactory(new BasicStockService());
        StockService stockService = stockServiceFactory.getStockService();
        if(stockService == null){
            throw new NullPointerException();
        }
    }


    //  test that a NullPointerException is thrown
    @Test(expected = NullPointerException.class)
    public void getNullStockService() {
        StockServiceFactory stockServiceFactory = new StockServiceFactory(null);
        StockService stockService = stockServiceFactory.getStockService();
        if(stockService == null){
            throw new NullPointerException();
        }
    }

}
