package edu.rrusso.advancedjava;

import org.junit.Test;

public class AppTest {


    //  test that no exception is thrown
    @Test
    public void testMain(){
        String[] params = new String[]{"MSFT", "9/18/2019", "9/20/2019"};
        App.main(params);
    }

    //  test that a NullPointerException is thrown
    @Test(expected = NullPointerException.class)
    public void testNullMain(){
        App.main(null);
    }
}
