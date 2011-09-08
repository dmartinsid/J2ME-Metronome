/*
 * NewEmptyJMUnitTest.java
 * JMUnit based test
 *
 * Created on 07/09/2011, 23:55:01
 */

package com.j2memetronome.test;


import jmunit.framework.cldc10.*;

/**
 * @author Deivid Martins
 */
public class NewEmptyJMUnitTest extends TestCase {
    
    public NewEmptyJMUnitTest() {
        //The first parameter of inherited constructor is the number of test cases
        super(1,"NewEmptyJMUnitTest");
    }            

    public void test(int testNumber) throws Throwable {

                testOne();
   
       
    }

    private void testOne() throws Exception{
        // here we run our test, use assertXXX methods to check results
        String tmp = "Hello World";
        assertEquals(tmp, "Hello World!");
    }
}

