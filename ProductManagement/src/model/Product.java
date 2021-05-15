/*
 * @author Vishwa Gunathilake J.D.B. - IT19110158
 * 
 * */

package model;

import java.sql.*;

import utility.DatabaseConnectivity;

public class Product {
//	The method to create a new Product record => for Researchers ------------------------------------------------------------------------------------------------
	public String createProduct(String productName, String description, String unitPrice, String category, String productStatus, String sellerID) {
		String output = "";
		
		try {
			Connection con = DatabaseConnectivity.connect();
			
			if (con == null) {
				return "An error has occurred while connecting to the database.";
				
			}
			
			// The query to insert a new record to the Product table & prepared statements
			String query = "INSERT INTO `product` (`product_name`, `description`, `unit_price`, `category`, `status`, `seller_id`) VALUES (?, ?, ?, ?, ?, ?)";
							
			PreparedStatement preparedStmt = con.prepareStatement(query);
							
			// binding values
			preparedStmt.setString(1, productName);
			preparedStmt.setString(2, description);
			preparedStmt.setDouble(3, Double.parseDouble(unitPrice));
			preparedStmt.setString(4, category);
			preparedStmt.setString(5, productStatus);
			preparedStmt.setInt(6, Integer.parseInt(sellerID));
							
			// execute the statement
			preparedStmt.execute();
				
			// Close the database connection
			con.close();
				
			// Success
			String products = getAllProducts();
			output = "{\"status\":\"success\", \"data\": \"" + products + "\"}";
			
		} catch (Exception e) {
			// Failure
			output = "{\"status\":\"error\", \"data\":\"An error occurred while creating a new product in the database.\"}"; 
			System.err.println(e.getMessage());
			
		}
		
		return output;
		
	}
//	-------------------------------------------------------------------------------------------------------------------------------------------------------------

//  The method to read all the products in a category -----------------------------------------------------------------------------------------------------------
	public String getAllProducts() {
		String output = "";
		
		try {
			Connection con = DatabaseConnectivity.connect();
			
			if (con == null) {
				return "An error has occurred while connecting to the database.";
				
			}
			
			// Prepare a HTML table to display the Products ordered by a Category
			output = "<table border='1'>" + "<tr>" + "<th>Product Name</th>" + "<th>Description</th>" + "<th>Unit Price</th>"
					+ "<th>Category</th>" + "<th>Product Status</th>" + "<th>Seller ID</th>" + "<th>Created At</th>" + "<th>Updated At</th>" 
					+ "<th>Update</th>" + "<th>Remove</th>" + "</tr>";

			// The query to select all Product records ordered by a Category
			String query = "SELECT * FROM `product`";
			
			Statement stmt = con.createStatement();
			ResultSet set = stmt.executeQuery(query);
			
			// Iterate through all the records in the result set
			while (set.next()) {
				// Reading values from the Result Set - set
				String productID = Integer.toString(set.getInt("product_id"));
				String productName = set.getString("product_name");
				String description = set.getString("description");
				String unitPrice = Double.toString(set.getDouble("unit_price"));
				String category = set.getString("category");
				String productStatus = set.getString("status");
				String sellerID = Integer.toString(set.getInt("seller_id"));
				String createdAt = set.getTimestamp("created_at").toString();
				String updatedAt = set.getTimestamp("updated_at").toString();
				
				// Add the record in to the HTML table
				output += "<tr><td>" + productName + "</td>";
				output += "<td>" + description + "</td>";
				output += "<td>" + unitPrice + "</td>";
				output += "<td>" + category + "</td>";
				output += "<td>" + productStatus + "</td>";
				output += "<td>" + sellerID + "</td>";
				output += "<td>" + createdAt + "</td>";
				output += "<td>" + updatedAt + "</td>";
				
				// Update & Remove buttons
				 output += "<td><input name='btnUpdate' type='button' value='Update' "
							+ "class='btnUpdate btn btn-secondary' data-productid='" + productID + "'></td>"
							+ "<td><input name='btnRemove' type='button' value='Remove' "
							+ "class='btnRemove btn btn-danger' data-productid='" + productID + "'></td></tr>"; 
				
			}
			
			// Close the database connection
			con.close();
			
			// End of the HTML table
			output += "</table>";
			
		} catch (Exception e) {
			// Failure
			output = "An error has occurred while reading the Product records.";
			System.err.println(e.getMessage());
			
		}
		
		return output;
		
	}
	
//	-------------------------------------------------------------------------------------------------------------------------------------------------------------

//  The method to update a product record by product ID ---------------------------------------------------------------------------------------------------------
	public String updateProductByID( String productID, String productName, String description, String unitPrice, String category, String productStatus, String sellerID) {
		String output = "";
		
		try {
			Connection con = DatabaseConnectivity.connect();
			
			if (con == null) {
				return "An error has occurred while connecting to the database.";
				
			}
			
			// The query to update the product record
			String query = "UPDATE `product` SET `product_name`=?, `description`=?, `unit_price`=?, `category`=?, `status`=?, `seller_id`=? WHERE `product_id`=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setString(1, productName);
			preparedStmt.setString(2, description);
			preparedStmt.setDouble(3, Double.parseDouble(unitPrice));
			preparedStmt.setString(4, category);
			preparedStmt.setString(5, productStatus);
			preparedStmt.setInt(6, Integer.parseInt(sellerID));
			preparedStmt.setInt(7, Integer.parseInt(productID));
			
			// execute the statement
			preparedStmt.execute();
			
			// Close the database connection
			con.close();
			
			// Success
			String products = getAllProducts();
			output = "{\"status\":\"success\", \"data\": \"" + products + "\"}";
			
		} catch (Exception e) {
			// Failure
			output = "{\"status\":\"error\", \"data\": \"An error occurred while updating the product.\"}";
			System.err.println(e.getMessage());
			
		}
		
		return output;
		
	}
	
//	-------------------------------------------------------------------------------------------------------------------------------------------------------------

//  The method to remove a product using product ID  ------------------------------------------------------------------------------------------------------------
	public String removeProductByID( String productID ) {
		String output = "";
		
		try {
			Connection con = DatabaseConnectivity.connect();
			
			if (con == null) {
				return "An error has occurred while connecting to the database.";
				
			}
			
			// The query to delete the product record
			String query = "DELETE FROM `product` WHERE `product_id`=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(productID));
			
			// execute the statement
			preparedStmt.execute();
			
			// Close the database connection
			con.close();
			
			// Success
			String products = getAllProducts();
			output = "{\"status\":\"success\", \"data\": \"" + products + "\"}";
			
		} catch (Exception e) {
			// Failure
			output = "{\"status\":\"error\", \"data\": \"An error occurred while removing a product record.\"}"; 
			System.err.println(e.getMessage());
			
		}
		
		return output;

	}
	
}
	
//	-------------------------------------------------------------------------------------------------------------------------------------------------------------
