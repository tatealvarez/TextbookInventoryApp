package alvarez;

import java.io.*;
import java.util.*;

public class Inventory {
	
	HashMap<Integer, Item> inventory = new HashMap<>();
	private static final String FILENAME = "inventory.dat";
	
	public void loadInventory() {
		try (ObjectInputStream OIS = new ObjectInputStream(new FileInputStream(FILENAME))) {
			inventory = (HashMap<Integer, Item>) OIS.readObject();
			//System.out.println("Inventory loaded successfully.");
		} catch (FileNotFoundException e) {
			//System.out.println("No previous inventory found, creating new inventory.");
		} catch (IOException | ClassNotFoundException e) {
			//System.out.println("Error loading inventory: " + e.getMessage());
		}
	}
	
	public void saveInventory() {
		try (ObjectOutputStream OOS = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
			OOS.writeObject(inventory);
			System.out.println("Inventory saved successfully.");
		} catch (IOException e){
			System.out.println("Error saving inventory: " + e.getMessage());
		}
	}
	
	public void addItem(int SKU, String title, double price, int quantity) {
		if (inventory.containsKey(SKU)) {
			throw new IllegalArgumentException("Textbook already exists.");
		}
		Item item = new Item(SKU, title, price, quantity);
		inventory.put(SKU, item);
		saveInventory();
	}
	
	public boolean removeItem(int SKU) {
		if (inventory.containsKey(SKU)) {
			inventory.remove(SKU);
			saveInventory();
			return true;
		}
		return false;
	}
	
	public Item getItem(int SKU) {
		return inventory.get(SKU);
	}
	
	public Collection<Item> getAllItems() {
		return inventory.values();
	}
}
