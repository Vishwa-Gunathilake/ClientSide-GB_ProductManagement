<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="model.Product"%>

<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" href="Views/bootstrap.min.css">
		<script src="Components/jquery-3.6.0.min.js"></script>
		<script src="Components/products.js"></script>
		<meta charset="ISO-8859-1">
		<title>Product Management</title>
	</head>

	<body>
		<div class="container">
			<div class="row">
				<div class="col-6">
					<h1>Product Management</h1>
					<form id="formProduct" name="formProduct">
						<label>Product Name:</label>
						<input id="productName" name="productName" type="text" class="form-control form-control-sm"><br>
			 
						<label>Description:</label>
						<input id="description" name="description" type="text" class="form-control form-control-sm"><br>
			
						<label>Unit Price:</label>
						<input id="unitPrice" name="unitPrice" type="text" class="form-control form-control-sm"><br>
			
						<label>Category:</label>
						<input id="category" name="category" type="text" class="form-control form-control-sm"><br>
			
						<label>Status:</label>
						<input id="productStatus" name="productStatus" type="text" class="form-control form-control-sm"><br>
			
						<label>Seller ID:</label>
						<input id="sellerID" name="sellerID" type="text" class="form-control form-control-sm"><br>
		
						<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
						<input type="hidden" id="hidProductIDSave" name="hidProductIDSave" value="">
					</form>
	
					<div id="alertSuccess" class="alert alert-success"></div>
					<div id="alertError" class="alert alert-danger"></div>
					<br>
					<div id="divProductsGrid">
						<%
							Product productObj = new Product(); 
	 						out.print(productObj.getAllProducts()); 
						%>
					</div>
				</div> 
			</div> 
		</div> 
	</body>
</html>