# Exercise 1 - Loading products from SAP S/4HANA Cloud

In this exercise, you will implement an API call to SAP S/4HANA Cloud using the SAP Cloud SDK toolkit. Also, you will configure a destination in your IDE and on SAP Cloud Platform. After completing the steps, your web shop application will load the available products from the SAP S/4HANA Cloud system.

## Implement the products controller

First you need to write the program code for the API call.

1. Open the class `ProductsController` in your IDE and locate the method `getProducts()`. You can find the Java classes in the `Explorer` view. Expand the apropriate directories like shown here.
<br>![](/exercises/ex0/images/IDE_ready.png)

2.	Insert the following lines of code as method implementation.
```java
		String productGroup = "A001"; // On Account Billed
		String productType = "HAWA"; // Trading Goods
		HttpDestination destination = DestinationAccessor.getDestination("S4HANA").asHttp();
		String shopLanguage = "EN";
		
		List<Product> s4Products = new DefaultProductMasterService().getAllProduct()
				.select(Product.PRODUCT,
						Product.TO_DESCRIPTION,
						Product.TO_VALUATION.select(ProductValuation.STANDARD_PRICE))
				.filter(Product.PRODUCT_GROUP.eq(productGroup)
						.and(Product.PRODUCT_TYPE.eq(productType)))
				.executeRequest(destination);

		List<WebShopProduct> products = transformProducts(s4Products, shopLanguage);

		return ResponseEntity.ok(products);
```

3. Make sure there are no compile errors and save the file by hitting `CTRL + S` on your keyboard.


## Configure the SAP S/4HANA Cloud destination in your IDE

After completing these steps you will be able to run the example application in your IDE and access the SAP S/4HANA Cloud system.

4. Run the following command in the terminal view.
```
export destinations='[{"type": "HTTP", "name": "S4HANA", "url": "https://odata-mock-server-hilarious-tiger.cfapps.sap.hana.ondemand.com/"}]'
```

5. Run the application in your IDE to see the changes.
```
cd ~/projects/teched2020-DT261/webshop/application
mvn spring-boot:run
```
<br>![](/exercises/ex1/images/product_list_page.png)


## Configure the SAP S/4HANA Cloud destination on SAP Cloud Platform

After completing these steps you will be able to run the example application on SAP Cloud Platform and access the SAP S/4HANA Cloud system.

6. Create a destination service instance by running the following command in the terminal. Let's call the service instance `destservice`.
```
cf create-service destination lite destservice
```

7. Bind the destination service instance `destservice` created in the above step to the `webshop` application. Note that this step will only work if you have already deployed the web shop like described in the [Getting Started](exercises/ex0/) exercise.
```
cf bind-service webshop destservice
```

8. Configure the SAP S/4HANA Cloud connection details. Open the SAP Cloud Platform Cockpit and navigate to the subaccount overview page, select `Connectivity > Destinations` from the menu on the left, then click `New Destination`. The destination configuration will appear. Enter the following details and click `Save`.
* Name: S4HANA
* URL: https://odata-mock-server-hilarious-tiger.cfapps.sap.hana.ondemand.com/
<br>![](/exercises/ex1/images/configure_destination.png)

9. Switch back to SAP Business Application Studio and deploy the application by running the following commands in the terminal.
```
cd ~/projects/teched2020-DT261/webshop
mvn package
cf push
```

If you open the deployed application in a new browser tab, you will see the products list, retrieved from SAP S/4HANA Cloud.

## Summary

You've now built an API call to load some data from SAP S/4HANA Cloud.

Continue to [Exercise 2 - Placing the order with SAP S/4HANA Cloud](../ex2/README.md).

