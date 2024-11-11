package alvarez;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class InventoryInterface {
	
	private Inventory inventory;

	private JFrame frmTextbookInventoryApp;
	private JTextField skuText;
	private JTextField titleText;
	private JTextField priceText;
	private JTextField quantityText;
	private JTextArea inventoryText;
	private JButton addItemBtn;
	private JButton removeItemBtn;
	private JButton displayItemBtn;
	private JButton displayAllBtn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InventoryInterface window = new InventoryInterface();
					window.frmTextbookInventoryApp.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InventoryInterface() {
		inventory = new Inventory();
		inventory.loadInventory();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTextbookInventoryApp = new JFrame();
		frmTextbookInventoryApp.setTitle("Textbook Inventory App");
		frmTextbookInventoryApp.setBounds(100, 100, 600, 400);
		frmTextbookInventoryApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTextbookInventoryApp.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("TEXTBOOK INVENTORY");
		lblNewLabel.setBounds(0, 5, 600, 27);
		lblNewLabel.setFont(new Font("Futura", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frmTextbookInventoryApp.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("SKU: ");
		lblNewLabel_1.setFont(new Font("Futura", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(25, 40, 60, 40);
		frmTextbookInventoryApp.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Title:");
		lblNewLabel_2.setFont(new Font("Futura", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(25, 90, 60, 40);
		frmTextbookInventoryApp.getContentPane().add(lblNewLabel_2);
		
		skuText = new JTextField();
		skuText.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		skuText.setBounds(75, 47, 200, 30);
		frmTextbookInventoryApp.getContentPane().add(skuText);
		skuText.setColumns(10);
		
		titleText = new JTextField();
		titleText.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		titleText.setColumns(10);
		titleText.setBounds(75, 97, 200, 30);
		frmTextbookInventoryApp.getContentPane().add(titleText);
		
		JLabel lblNewLabel_3 = new JLabel("Price: $");
		lblNewLabel_3.setFont(new Font("Futura", Font.PLAIN, 13));
		lblNewLabel_3.setBounds(315, 40, 60, 40);
		frmTextbookInventoryApp.getContentPane().add(lblNewLabel_3);
		
		priceText = new JTextField();
		priceText.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		priceText.setColumns(10);
		priceText.setBounds(375, 45, 200, 30);
		frmTextbookInventoryApp.getContentPane().add(priceText);
		
		JLabel lblNewLabel_4 = new JLabel("Quantity: ");
		lblNewLabel_4.setFont(new Font("Futura", Font.PLAIN, 13));
		lblNewLabel_4.setBounds(315, 90, 70, 40);
		frmTextbookInventoryApp.getContentPane().add(lblNewLabel_4);
		
		quantityText = new JTextField();
		quantityText.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		quantityText.setColumns(10);
		quantityText.setBounds(375, 97, 200, 30);
		frmTextbookInventoryApp.getContentPane().add(quantityText);
		
		addItemBtn = new JButton("Add Item");
		addItemBtn.setFont(new Font("Futura", Font.PLAIN, 13));
		addItemBtn.setBounds(180, 145, 120, 30);
		frmTextbookInventoryApp.getContentPane().add(addItemBtn);
		
		removeItemBtn = new JButton("Remove Item");
		removeItemBtn.setFont(new Font("Futura", Font.PLAIN, 13));
		removeItemBtn.setBounds(320, 145, 120, 30);
		frmTextbookInventoryApp.getContentPane().add(removeItemBtn);
		
		displayItemBtn = new JButton("Display Item");
		displayItemBtn.setFont(new Font("Futura", Font.PLAIN, 13));
		displayItemBtn.setBounds(180, 195, 120, 30);
		frmTextbookInventoryApp.getContentPane().add(displayItemBtn);
		
		displayAllBtn = new JButton("Display All");
		displayAllBtn.setFont(new Font("Futura", Font.PLAIN, 13));
		displayAllBtn.setBounds(320, 195, 120, 30);
		frmTextbookInventoryApp.getContentPane().add(displayAllBtn);
		
		inventoryText = new JTextArea();
		inventoryText.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		inventoryText.setEditable(false);
		inventoryText.setBounds(25, 240, 550, 115);
		frmTextbookInventoryApp.getContentPane().add(inventoryText);
		
		addEventListener();
	}
	
	private void addEventListener() {
		addItemBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addTextbook();
			}
		});
		
		removeItemBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeTextbook();
			}
		});
		
		displayItemBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayTextbook();
			}
		});
		
		displayAllBtn.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				displayAll();
			}
		});
	}
	
	private void addTextbook() {
		try {
			int SKU = Integer.parseInt(skuText.getText());
			String title = titleText.getText();
			double price = Double.parseDouble(priceText.getText());
			int quantity = Integer.parseInt(quantityText.getText());
			
			if (price <= 0 || quantity <= 0) {
				inventoryText.setText("Error: Invalid price or quantity");
				return;
			}
			inventory.addItem(SKU, title, price, quantity);
			clearFields();
		} catch (NumberFormatException e) {
			inventoryText.setText("Error: Invalid input. Please check data.");
		}
	}
	
	private void removeTextbook() {
		try {
			int SKU = Integer.parseInt(skuText.getText());
			boolean success = inventory.removeItem(SKU);
			
			if (success) {
				inventoryText.setText("Textbook removed successfully.");
			} else {
				inventoryText.setText("Error: Textbook not found.");
			}
			clearFields();
		} catch (NumberFormatException e) {
			inventoryText.setText("Error: Please enter a valid SKU.");
		}
	}
	
	private void displayTextbook() {
		try {
			int SKU = Integer.parseInt(skuText.getText());
			Item item = inventory.getItem(SKU);
			if (item != null) {
				inventoryText.setText(item.toString());
			} else {
				inventoryText.setText("Error: Textbook not found.");
			}
			clearFields();
		} catch (NumberFormatException e) {
			inventoryText.setText("Error: Invalid SKU format.");
		}
	}
	
	private void displayAll() {
		StringBuilder sb = new StringBuilder();
		for (Item item: inventory.getAllItems()) {
			sb.append(item.toString()).append("\n");
		}
		if (sb.length() == 0) {
			inventoryText.setText("Inventory is empty.");
		}
		inventoryText.setText(sb.toString());
	}
	
	private void clearFields() {
		skuText.setText("");
		priceText.setText("");
		titleText.setText("");
		quantityText.setText("");
	}
}
