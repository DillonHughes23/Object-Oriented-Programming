//Authors: Dustin Bruce and Dillon Hughes

package shoppingCart;



import static org.junit.jupiter.api.Assertions.assertAll;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ItemToPurchaseHamcrestTest {
    //add tests here
	OutputStream os = new ByteArrayOutputStream();
	PrintStream ps = new PrintStream(os);
	ItemToPurchase tester = new ItemToPurchase();
	
	@BeforeEach
	public void output () {
	System.setOut(ps);
	ItemToPurchase tester = new ItemToPurchase();

	}
    @BeforeEach
    public void start() {
    	
    	ItemToPurchase tester = new ItemToPurchase();
    	
    }
    //setter/getter tests
    
    @Test
    public void setNameTest() {
    	tester.setName("TEST");
    	String test = tester.getName();
  
    	assertThat(test, is("TEST"));
    }
    @Test
    public void setPriceTest() {
    	tester.setPrice(100);
    	int testPrice = tester.getPrice();
    	assertThat(testPrice, is(100));
    }
    @Test
    public void setQuantTest() {
    	tester.setQuantity(500);
    	int testQuant = tester.getQuantity();
    	assertThat(testQuant, is(500));
    	
    }
    @Test
    public void setDescriptionTest() {
    	tester.setDescription("test");
    	String temp = tester.getDescription();
    	assertThat(temp, is("test"));
    }
    @Test
    public void constructorTest() {
    	ItemToPurchase localTemp = new ItemToPurchase("testName", "testDescription", 1, 1);
    	String testName = localTemp.getName();
    	String testDesc = localTemp.getDescription();
    	int testPrice = localTemp.getPrice();
    	int testQuant = localTemp.getQuantity();
    	assertAll(
    			() -> assertThat(testName, is("testName")),
    			() -> assertThat (testDesc, is("testDescription")),
    			() -> assertThat(testPrice, is(1)),
    			() -> assertThat (testQuant, is(1))
    			);
    }
    @Test
    public void defaultConstructorTest() {
    	ItemToPurchase localTemp = new ItemToPurchase();
    	String testName = localTemp.getName();
    	String testDesc = localTemp.getDescription();
    	int testPrice = localTemp.getPrice();
    	int testQuant = localTemp.getQuantity();
    	assertAll(
    				() -> assertThat(testName, is("none")),
    				()-> assertThat(testDesc, is("none")),
    				()-> assertThat(testPrice, is(0)),
    				()-> assertThat(testQuant, is(0))
    				);
    }
    
    @Test
    public void printCostTest() {
    	ItemToPurchase localTemp = new ItemToPurchase("testName", "testDescription", 1, 1);
    	String localName = "testName";
    	String localDesc = "testDescription";
    	int localCost = 1;
    	int localQuant = 1;
    	String tester = localName + " " + localQuant + " @ $" + localCost + " = $" + (localCost * localQuant + "\r\n");
    	localTemp.printItemCost();
    	assertThat(tester, is(os.toString()));
    	
    }
    
    			
    			
    	
    	
    	
    
}
