package com;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Product;

/**
 * Servlet implementation class ProductServiceAPI
 */
@WebServlet("/ProductServiceAPI")
public class ProductServiceAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	Product productObj = new Product();

//	------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductServiceAPI() {
        super();
        
    }

//	------------------------------------------------------------------------------------------------------------------------------------------------------------

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

//	------------------------------------------------------------------------------------------------------------------------------------------------------------

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Read the values from the request
		String productName = request.getParameter("productName");
		String description = request.getParameter("description");
		String unitPrice = request.getParameter("unitPrice");
		String category = request.getParameter("category");
		String productStatus = request.getParameter("productStatus");
		String sellerID = request.getParameter("sellerID");
		
		// Calling the POST method
		String output = productObj.createProduct(productName, description, unitPrice, category, productStatus, sellerID);
		
		// Write the output to the client
		response.getWriter().write(output);
		
	}

//	------------------------------------------------------------------------------------------------------------------------------------------------------------

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> parameters = getParametersMap(request);
		
		// Read the values from the parameters
		String productID = parameters.get("hidProductIDSave").toString();
		String productName = parameters.get("productName").toString();
		String description = parameters.get("description").toString();
		String unitPrice = parameters.get("unitPrice").toString();
		String category = parameters.get("category").toString();
		String productStatus = parameters.get("productStatus").toString();
		String sellerID = parameters.get("sellerID").toString();
		
		// Calling the PUT method
		String output = productObj.updateProductByID(productID, productName, description, unitPrice, category, productStatus, sellerID);
		
		// Write the output to the client
		response.getWriter().write(output);
		
	}

//	------------------------------------------------------------------------------------------------------------------------------------------------------------

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> parameters = getParametersMap(request);
		
		// Read the values from the parameters
		String productID = parameters.get("productID").toString();
		
		// Calling the DELETE method
		String output = productObj.removeProductByID(productID);
		
		// Write the output to the client
		response.getWriter().write(output);
		
	}

//	------------------------------------------------------------------------------------------------------------------------------------------------------------

	// Convert request parameters to a Map
	private static Map<String, String> getParametersMap(HttpServletRequest request) {
		
		Map<String, String> map = new HashMap<String, String>();
		
		try { 
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8"); 
			
			String queryString = scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
			
			scanner.close();
			
			String[] parameters = queryString.split("&");
			
			for (String parameter : parameters) {	 
				String[] p = parameter.split("=");
				map.put(p[0], p[1]);
				
			} 
			
		} catch (Exception e) {}
		
		return map;
		
	}

}
