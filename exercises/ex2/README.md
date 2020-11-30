# Exercise 2 - Placing the order with SAP S/4HANA Cloud

In this exercise, you will implement an API call to SAP S/4HANA Cloud to post orders.

## Implement the orders controller

After completing these steps you will have the code using SAP Cloud SDK for the API call.

1. Open the class `SalesOrderController` in your IDE and locate the method `placeOrder()`.

2.	Insert the following lines of code as method implementation.
```java
		String salesOrderType = "OR"; // standard order
		String soldToParty = "17100001"; // domestic customer
		HttpDestination destination = DestinationAccessor.getDestination("S4HANA").asHttp();
		
		SalesOrder salesOrder = buildSalesOrder(webShopOrder, salesOrderType, soldToParty);
		ModificationResponse<SalesOrder> s4response = new DefaultSalesOrderService()
				.createSalesOrder(salesOrder)
				.executeRequest(destination);
				
		System.out.println("Received response from SAP S/4HANA Cloud: HTTP " + s4response.getResponseStatusCode());

		return ResponseEntity.status(s4response.getResponseStatusCode()).build();
```

3. Make sure there are no compile errors and save the file by hitting `CTRL + S` on your keyboard.


## Restart the local application

If you have completed [Exercise 1 - Loading products from SAP S/4HANA Cloud](../ex1/) there is no need to configure anything as the SAP S/4HANA Cloud destination is already configured.

4. Run the application in your IDE.
```
cd ~/projects/teched2020-DT261/webshop/application
mvn spring-boot:run
```

Do not forget to terminate the running program by switching to the `Terminal` view and hitting `CTRL + C` on your keyboard.

Now the placed orders will be posted to SAP S/4HANA Cloud.


## Restart the application on SAP Cloud Platform

If you have completed [Exercise 1 - Loading products from SAP S/4HANA Cloud](../ex1/) there is no need to configure anything as the SAP S/4HANA Cloud destination is already configured.

5. Deploy the application by running the following commands in the terminal.
```
cd ~/projects/teched2020-DT261/webshop
mvn package
cf push
```

If you open the deployed application in a new browser tab and place an order, it will be posted to SAP S/4HANA Cloud.

## Summary

Your web shop is now posting new orders to SAP S/4HANA Cloud. You could reuse the destination configurations from the previous exercise as you are connecting to the same system.

Continue to [Exercise 3 - Loading promotions from SAP Promotion Pricing](../ex3/README.md)

