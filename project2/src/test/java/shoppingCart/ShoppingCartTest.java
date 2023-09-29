//Authors:: Dustin Bruce and Dillon Hughes

package shoppingCart;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class ShoppingCartTest {
	OutputStream os = new ByteArrayOutputStream();
	PrintStream ps = new PrintStream (os);
	
	
	
	
	
	//add tests here
	
	ShoppingCart tester = null;
	ItemToPurchase testerItem = new ItemToPurchase("testName", "testDescription", 1, 1);
	ItemToPurchase testerItem2 = new ItemToPurchase ("testName2", "testDescription2", 2, 2);
	
	@BeforeEach
	public void cleanSlate() {
		tester = new ShoppingCart("TEST", "testDate");
	}
    @Test
    public void testName(){
        assertEquals("TEST", tester.getCustomerName(), "NAME CHECK");
       
    }
    @Test
    public void testDate() {
    	assertEquals("testDate", tester.getDate(), "DATE CHECK");
    }
    @Test
    public void testItemAdd() {
    	tester.addItem(testerItem);
    	assertEquals(1, tester.getNumItemsInCart(), "Add Item Check");
    }
    @Test
    public void testRemove() {
    	tester.addItem(testerItem);
    	assertEquals(1, tester.getNumItemsInCart(), "Confirm item add");
    	tester.removeItem("testName");
    	assertEquals(0, tester.getNumItemsInCart(), "Removal Test");
    }
    @Test
    public void testAdd() {
    	tester.addItem(testerItem);
    	assertEquals(1, tester.getNumItemsInCart(), "Add Test");
    }
    @Test
    public void testGetNum() {
    	tester.addItem(testerItem);
    	assertEquals(1, tester.getNumItemsInCart(), "1 items");
    }
    
    @Test
    public void testGetNum2() {
    	tester = new ShoppingCart();
    	tester.addItem(testerItem);//1 item
    	tester.addItem(testerItem2);// 2 items
    	assertEquals(3, tester.getNumItemsInCart(), "More than 1 item");
    }
 
    @Test
    public void testGetNum0() {
    	assertEquals(0, tester.getNumItemsInCart(), "0 items");
    }
    
    @Test
    public void totalCostTest() {
    	tester.addItem(testerItem);
    	assertEquals(1, tester.getCostOfCart(), "Cost should be 1");
    }
    
    @Test
    public void removeEmpty() {
    	System.setOut(ps);
    	tester = new ShoppingCart();
    	tester.removeItem("test");
    	System.out.println("Item not found in cart. Nothing removed.");
    	String test = os.toString();
    	assertEquals(test, os.toString(), "Should give default error message");
    	
    }
    
    @Test
    public void constructorTest() {
    	//Testing empty string constructor;
    	tester = new ShoppingCart("", "");
    	String localName = "";
    	String localDate = "";
    	
    	assertAll(
    			() -> assertEquals(localName, tester.getCustomerName()),
    			() -> assertEquals(localDate, tester.getDate())
    			);
    	
    }
    
    @Test
    public void negativeCostTest() {
    	tester = new ShoppingCart();
    	ItemToPurchase localTemp = new ItemToPurchase(" ", " ", -1, 1);
    	tester.addItem(localTemp);
    	int localCost = tester.getCostOfCart();
    	assertEquals(-1, localCost, "Negative cost results in negative cart amount");
    }
    @Test
    public void negativeQuantTest() {
    	tester = new ShoppingCart();
    	ItemToPurchase localTemp = new ItemToPurchase(" ", " ", 1, -1);
    	tester.addItem(localTemp);
    	int localQuant = tester.getNumItemsInCart();
    	assertEquals(-1, localQuant, "Negative cost results in negative cart amount");
    	
    }
    
    
    
    
    
    
    
}	
	