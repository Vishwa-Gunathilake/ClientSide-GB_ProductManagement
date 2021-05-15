$(document).on("click", "#btnSave", function(event) { 
	// Clear alerts -----------------------------------------------------------------------------------
	$("#alertSuccess").text(""); 
	$("#alertSuccess").hide(); 
	$("#alertError").text(""); 
	$("#alertError").hide(); 
 
	// Form validation --------------------------------------------------------------------------------
	var status = validateProductItemForm(); 
	if (status != true) { 
		$("#alertError").text(status); 
		$("#alertError").show(); 
	 
		return;
	} 

	// If valid ---------------------------------------------------------------------------------------
	var type = ($("#hidProductIDSave").val() == "") ? "POST" : "PUT"; 
	
	$.ajax({ 
		url : "ProductServiceAPI", 
		type : type, 
		data : $("#formProduct").serialize(), 
		dataType : "text", 
		complete : function(response, status) { 
			onProductSaveComplete(response.responseText, status); 
		}
			
	});
		
});

// -----------------------------------------------------------------------------------------------------------------------------------------------------------

function onProductSaveComplete(response, status) { 
	if (status == "success") {
		var resultSet = JSON.parse(response);
		
		if (resultSet.status.trim() == "success") { 	 
			$("#alertSuccess").text("Successfully saved."); 
			$("#alertSuccess").show();
			 
			$("#divProductsGrid").html(resultSet.data);
			 
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data); 
			$("#alertError").show();
			 
		}
		
	} else if (status == "error") {
		 $("#alertError").text("Error while saving."); 
		 $("#alertError").show();
		 
	} else {
		 $("#alertError").text("Unknown error while saving.."); 
		 $("#alertError").show();
		 
	}
	
	$("#hidProductIDSave").val("");
	$("#formProduct")[0].reset();
	
}

//------------------------------------------------------------------------------------------------------------------------------------------------------------

// ON UPDATE -------------------------------------------------------------------------------------------------------------------------------------------------
$(document).on("click", ".btnUpdate", function(event){ 
	$("#hidProductIDSave").val($(this).data("productid"));
	$("#productName").val($(this).closest("tr").find('td:eq(0)').text());
	$("#description").val($(this).closest("tr").find('td:eq(1)').text());
	$("#unitPrice").val($(this).closest("tr").find('td:eq(2)').text());
	$("#category").val($(this).closest("tr").find('td:eq(3)').text());
	$("#productStatus").val($(this).closest("tr").find('td:eq(4)').text());
	$("#sellerID").val($(this).closest("tr").find('td:eq(5)').text());
		
});

//------------------------------------------------------------------------------------------------------------------------------------------------------------

//ON DELETE --------------------------------------------------------------------------------------------------------------------------------------------------
$(document).on("click", ".btnRemove", function(event) { 
	$.ajax({ 
		url : "ProductServiceAPI", 
	 	type : "DELETE", 
	 	data : "productID=" + $(this).data("productid"),
	 	dataType : "text", 
	 	complete : function(response, status) { 
	 		onProductDeleteComplete(response.responseText, status); 
	 		
	 	} 
	
	});
	
})

//	------------------------------------------------------------------------------------------------------------------------------------------------------------
	
function onProductDeleteComplete(response, status){
	if (status == "success") {
		var resultSet = JSON.parse(response);
		
		if (resultSet.status.trim() == "success"){
			$("#alertSuccess").text("Successfully removed."); 
			$("#alertSuccess").show(); 
			$("#divProductsGrid").html(resultSet.data); 
				
		} else if (resultSet.status.trim() == "error") { 	
			$("#alertError").text(resultSet.data); 
			$("#alertError").show(); 
			
		} 
		
	} else if (status == "error") {
		$("#alertError").text("Error while removing.");
		$("#alertError").show();
		
	} else { 
		$("#alertError").text("Unknown error while removing.."); 
		$("#alertError").show();
		
	} 
	
}

//------------------------------------------------------------------------------------------------------------------------------------------------------------

// CLIENT-MODEL ----------------------------------------------------------------------------------------------------------------------------------------------
function validateProductItemForm(){
	// Product Name --------------------------------------------------------------
	if ($("#productName").val().trim() == "") {
		return "Insert Product Name.";
		
	}
	
	// Description ---------------------------------------------------------------
	if ($("#description").val().trim() == "") {
		return "Insert Description.";
		
	}
	
	// Unit Price ----------------------------------------------------------------
	if ($("#unitPrice").val().trim() == "") {
		return "Insert Unit Price.";
		
	}
	
	// is Unit Price numerical ---------------------------
	var tmpUnitPrice = $("#unitPrice").val().trim();
	
	if (!$.isNumeric(tmpUnitPrice)) {
		return "Insert a numerical value as Unit Price.";
		
	}
	
	// Category ------------------------------------------------------------------
	if ($("#category").val().trim() == "") {
		return "Insert Category.";
		
	}
	
	// Product Status ------------------------------------------------------------
	if ($("#productStatus").val().trim() == "") {
		return "Insert Product Status.";
		
	}
	
	// seller ID -----------------------------------------------------------------
	if ($("#sellerID").val().trim() == "") {
		return "Insert Seller ID.";
		
	}
	
	// is Seller ID numerical ----------------------------
	var tmpSellerID = $("#sellerID").val().trim();
	
	if (!$.isNumeric(tmpSellerID)) {
		return "Insert a valid, numerical value as Seller ID.";
		
	}
	
	return true;
	
}