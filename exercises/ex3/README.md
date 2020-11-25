# Exercise 3 - Loading promotions from SAP Promotion Pricing

In this exercise, we will create...

## Generate the client library
## Implement the promotions controller

First you need to write the program code for the API call.

1. Open the class PromotionController in your IDE and locate the method `calculatePromotion()`.

2.	Insert this lines of code as method implementation.
```java
		HttpDestination destination = DestinationAccessor.getDestination("PROMOPRICING").asHttp();
		String tenantName = "techedtenant";

		CalculationApi calculationApi = new CalculationApi(destination);
		PriceCalculateResponse ppResponse = calculationApi.calculateViaRestWithTenant(tenantName, new PriceCalculate());
		PromotionResponse response = convertResponse(ppResponse);

		return ResponseEntity.ok(response);
```

3. Make sure there are no compile errors and save the file by hitting `CTRL + S` on your keyboard.


## Configure the SAP Promotion Pricing destination in your IDE

After completing these steps you will be able to run the example application in your IDE and access SAP Promotion Pricing.

1. Run the following command in the terminal view.
```
export destinations='[{"type": "HTTP", "name": "S4HANA", "url": "https://odata-mock-server-hilarious-tiger.cfapps.sap.hana.ondemand.com/"},{"type": "HTTP", "name": "PROMOPRICING", "url": "https://mock-server-cx-spontaneous-fossa.cfapps.sap.hana.ondemand.com/sapomnichannelpromotionpricing/calculation"}]'
```

3. Run the application in your IDE to see the changes.
```
cd ~/projects/teched2020-DT261/webshop
mvn spring-boot:run
```


## Configure the SAP Promotion Pricing destination on SAP Cloud Platform

After completing these steps you will be able to run the example application on SAP Cloud Platform and access SAP Promotion Pricing.

1. If you have completed [Exercise 1 - Loading products from SAP S/4HANA Cloud](exercises/ex1/) you will already have an instance of the destination service that is bound to your application. You can reuse this setup for the new destination.

4. Configure the SAP Promotion Pricing connection details. Open the SAP Cloud Platform Cockpit and navigate to the subaccount overview page, select `Destinations` from the menu on the left, then click `New Destination`. The destination configuration will appear. Enter the following details and click `Save`.
* Name: PROMOPRICING
* URL: https://mock-server-cx-spontaneous-fossa.cfapps.sap.hana.ondemand.com/sapomnichannelpromotionpricing/calculation
<br>![](/exercises/ex1/images/configure_destination.png)

5. Switch back to SAP Business Application Studio and deploy the application by running the following commands in the terminal.
```
cd ~/projects/teched2020-DT261/webshop
mvn clean package
cf push
```

Now you can open the deployed application in a new browser tab.


## Summary

You've now ...
