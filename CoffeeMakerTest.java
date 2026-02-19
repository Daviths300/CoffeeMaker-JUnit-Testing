package edu.ncsu.csc326.coffeemaker;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;

public class CoffeeMakerTest {

	private CoffeeMaker cm;
	private Recipe r1;
	private Recipe r2;
	private Recipe r3;
	private Recipe r4;

	@Before
	public void setUp() throws Exception {
		cm = new CoffeeMaker();

		// რეცეპტი 1: ჩვეულებრივი ყავა
		r1 = new Recipe();
		r1.setName("Coffee");
		r1.setAmtChocolate("0");
		r1.setAmtCoffee("3");
		r1.setAmtMilk("1");
		r1.setAmtSugar("1");
		r1.setPrice("50");

		// რეცეპტი 2: მოკა
		r2 = new Recipe();
		r2.setName("Mocha");
		r2.setAmtChocolate("20");
		r2.setAmtCoffee("3");
		r2.setAmtMilk("1");
		r2.setAmtSugar("1");
		r2.setPrice("75");

		// რეცეპტი 3: ლატე
		r3 = new Recipe();
		r3.setName("Latte");
		r3.setAmtChocolate("0");
		r3.setAmtCoffee("2");
		r3.setAmtMilk("3");
		r3.setAmtSugar("1");
		r3.setPrice("60");

		// რეცეპტი 4: კაპუჩინო (ლიმიტის შესამოწმებლად)
		r4 = new Recipe();
		r4.setName("Cappuccino");
		r4.setPrice("80");
	}

	// --- რეცეპტების ტესტები ---
	@Test
	public void testAddRecipe() {
		assertTrue(cm.addRecipe(r1));
	}

	@Test
	public void testAddDuplicateRecipe() {
		cm.addRecipe(r1);
		assertFalse("დუბლიკატი რეცეპტი არ უნდა დაემატოს", cm.addRecipe(r1));
	}

	@Test
	public void testAddRecipeLimit() {
		cm.addRecipe(r1);
		cm.addRecipe(r2);
		cm.addRecipe(r3);
		assertFalse("მე-4 რეცეპტი არ უნდა დაემატოს (ლიმიტია 3)", cm.addRecipe(r4));
	}

	@Test
	public void testDeleteRecipe() {
		cm.addRecipe(r1);
		assertEquals("Coffee", cm.deleteRecipe(0));
		// მეორედ წაშლისას ან ცარიელ სლოტზე უნდა დაბრუნდეს null
		assertNull(cm.deleteRecipe(0));
	}

	@Test
	public void testEditRecipe() {
		cm.addRecipe(r1);
		String oldName = cm.editRecipe(0, r2);
		assertEquals("Coffee", oldName);
	}

	// --- ინვენტარის ტესტები ---
	@Test(expected = InventoryException.class)
	public void testAddInventoryException() throws InventoryException {
		cm.addInventory("4", "არავალიდური", "3", "2");
	}

	@Test(expected = InventoryException.class)
	public void testAddNegativeInventory() throws InventoryException {
		cm.addInventory("4", "-1", "3", "2");
	}

	@Test
	public void testCheckInventory() {
		String inventory = cm.checkInventory();
		assertTrue(inventory.contains("Coffee: 15"));
	}

	// --- ყავის მომზადების ტესტები ---
	@Test
	public void testMakeCoffeeSuccess() {
		cm.addRecipe(r1);
		assertEquals(50, cm.makeCoffee(0, 100));
	}

	@Test
	public void testMakeCoffeeInsufficientFunds() {
		cm.addRecipe(r1);
		assertEquals(10, cm.makeCoffee(0, 10)); // თანხა უკან ბრუნდება
	}

	@Test
	public void testMakeCoffeeInsufficientInventory() {
		cm.addRecipe(r2); // მოკა ითხოვს 20 შოკოლადს (საწყისში გვაქვს 15)
		assertEquals(100, cm.makeCoffee(0, 100)); // თანხა უკან ბრუნდება
	}
}