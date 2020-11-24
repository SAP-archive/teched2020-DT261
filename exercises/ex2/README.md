# Exercise 2 - Placing the order with SAP S/4HANA Cloud

In this exercise, you will implement an API call to SAP S/4HANA Cloud to post orders.

## Implement the orders controller

After completing these steps you will have the code using SAP Cloud SDK for the API call.

1. Open the class `SalesOrderController` in your IDE and locate the method `placeOrder()`.

2.	Insert this lines of code as method implementation.
```java
		String salesOrderType = "OR"; // standard order
		String soldToParty = "17100001"; // domestic customer
		HttpDestination destination = DestinationAccessor.getDestination("S4HANA").asHttp();
		
		SalesOrder salesOrder = buildSalesOrder(webShopOrder, salesOrderType, soldToParty);
		ModificationResponse<SalesOrder> s4response = new DefaultSalesOrderService()
				.createSalesOrder(salesOrder)
				.executeRequest(destination);

		return ResponseEntity.status(s4response.getResponseStatusCode()).build();
```

3. Make sure there are no compile errors and save the file by hitting `CTRL + S` on your keyboard.


## Configure the SAP S/4HANA Cloud destination in your IDE

If you have completed [Exercise 1 - Loading products from SAP S/4HANA Cloud](exercises/ex1/) there is no need to configure anything as the SAP S/4HANA Cloud destination is already configured.

1. Run the application in your IDE.
```
cd ~/projects/teched2020-DT261/webshop
mvn spring-boot:run
```

Now the placed orders will be posted to SAP S/4HANA Cloud.


## Configure the SAP S/4HANA Cloud destination on SAP Cloud Platform

If you have completed [Exercise 1 - Loading products from SAP S/4HANA Cloud](exercises/ex1/) there is no need to configure anything as the SAP S/4HANA Cloud destination is already configured.

1. Deploy the application by running the following commands in the terminal.
```
cd ~/projects/teched2020-DT261/webshop
mvn clean package
cf push
```

If you open the deployed application in a new browser tab and place an order, it will be posted to SAP S/4HANA Cloud.

## Summary

Your web shop is now posting new orders to SAP S/4HANA Cloud. You could reuse the destination configurations from the previous exercise as you are connecting to the same system.

Continue to [Exercise 3 - Loading promotions from SAP Promotion Pricing](../ex3/README.md)

