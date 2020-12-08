# Exercise 3 - Loading promotions from SAP Omnichannel Promotion Pricing

In this exercise, you will implement an API call to SAP Omnichannel Promotion Pricing by generating the client library from the API specification available on SAP API Business Hub.

Currently, buyers in the web shop can calculate a promotion discount by clicking the button in the shopping basket. The web shop always returns a discount ot 0%. We want to improve that and load promotions from SAP.

## Generate the client library

1. Open the [SAP Omnichannel Promotion Pricing page on SAP API Business Hub](https://api.sap.com/api/PriceCalculation/overview) and log on using your trial credentials. Click `Download Specification` and select `YAML` from the menu. Save the downloaded file as `PriceCalculation.yaml`.
<br>![](/exercises/ex3/images/download_api_spec.png)

2. Create a new subdirectory called `api-specs` within the `application` folder in your IDE (by right-clicking on the `application` folder and choosing "New Folder"). Right-click the new directory and select `Upload Files...`, then upload the file `PriceCalculation.yaml` from your computer.
<br>![](/exercises/ex3/images/upload_api_spec.png)

3. Open the `pom.xml` of your application. Note that there are two files named `pom.xml` - one for the parent, and one for the application itself. You need to open the latter one.

4. Add the following client library generator plugin configuration at the end of the `plugins` section of your `pom.xml`. Be careful to **not** add it within the `<pluginManagement>` section. Instead the snippet must be added in the `plugins` element which is under `<build>` directly.
```xml
			<plugin>
			    <groupId>com.sap.cloud.sdk.datamodel</groupId>
			    <artifactId>rest-generator-maven-plugin</artifactId>
			    <version>3.34.0</version>
			    <configuration>
			        <inputSpec>${project.basedir}/api-specs/PriceCalculation.yaml</inputSpec>
			        <apiPackage>com.sap.cloud.sdk.generated.promopricing.api</apiPackage>
			        <modelPackage>com.sap.cloud.sdk.generated.promopricing.model</modelPackage>
			        <outputDirectory>${project.basedir}</outputDirectory>
			        <apiMaturity>released</apiMaturity>
			    </configuration>
			    <executions>
			        <execution>
			            <id>generate-pp-client</id>
			            <goals>
			                <goal>generate</goal>
			            </goals>
			        </execution>
			    </executions>
			</plugin>
```
<br>![](/exercises/ex3/images/maven_plugin.png)


5. Run the client library generator by executing the following commands in your terminal.
```
cd ~/projects/teched2020-DT261/application
mvn rest-generator:generate@generate-pp-client
```

Now you have generated the client library for SAP Omnichannel Promotion Pricing.

## Implement the promotions controller

Next you need to write the program code for the API call.

1. Open the class PromotionController in your IDE and locate the method `calculatePromotion()`.

2.	Insert the following lines of code as method implementation.
```java
		HttpDestination destination = DestinationAccessor.getDestination("PROMOPRICING").asHttp();
		String tenantName = "techedtenant";

		CalculationApi calculationApi = new CalculationApi(destination);
		PriceCalculateResponse ppResponse = calculationApi.calculateViaRestWithTenant(tenantName, new PriceCalculate());
		PromotionResponse response = convertResponse(ppResponse);

		return ResponseEntity.ok(response);
```

3. Add the method `convertResponse()` with the following implementation.
```java
	private PromotionResponse convertResponse(PriceCalculateResponse ppResponse) {
		ShoppingBasketBase shoppingBasket = ppResponse.getPriceCalculateBody().get(0).getShoppingBasket();
		BigDecimal promoValue = new BigDecimal(0);
		for(LineItemDomainSpecific lineItem: shoppingBasket.getLineItem()) {
			promoValue = promoValue.add(lineItem.getSale().getRetailPriceModifier().get(0).getPercent().getValue());
		}
		promoValue = promoValue.divide(new BigDecimal(shoppingBasket.getLineItem().size()));

		return new PromotionResponse(ActionEnum.SUBTRACT.toString(), promoValue);
	}
```

4. Add the imports for the classes of the newly generated client library.
```java
import com.sap.cloud.sdk.cloudplatform.connectivity.DestinationAccessor;
import com.sap.cloud.sdk.cloudplatform.connectivity.HttpDestination;

import com.sap.cloud.sdk.generated.promopricing.api.CalculationApi;
import com.sap.cloud.sdk.generated.promopricing.model.LineItemDomainSpecific;
import com.sap.cloud.sdk.generated.promopricing.model.Percent.ActionEnum;
import com.sap.cloud.sdk.generated.promopricing.model.PriceCalculate;
import com.sap.cloud.sdk.generated.promopricing.model.PriceCalculateResponse;
import com.sap.cloud.sdk.generated.promopricing.model.ShoppingBasketBase;
```

4. Make sure there are no compile errors and save the file by hitting `CTRL + S` on your keyboard.


## Configure the SAP Promotion Pricing destination in your IDE

After completing these steps you will be able to run the example application in your IDE and access SAP Promotion Pricing.

1. Run the following command in the terminal view.
```
export destinations='[{"type": "HTTP", "name": "S4HANA", "url": "https://odata-mock-server-hilarious-tiger.cfapps.sap.hana.ondemand.com/"},{"type": "HTTP", "name": "PROMOPRICING", "url": "https://mock-server-cx-spontaneous-fossa.cfapps.sap.hana.ondemand.com/sapomnichannelpromotionpricing/calculation"}]'
```

2. Run the application in your IDE to see the changes.
```
cd ~/projects/teched2020-DT261/application
mvn spring-boot:run
```


## Configure the SAP Promotion Pricing destination on SAP Cloud Platform

After completing these steps you will be able to run the example application on SAP Cloud Platform and access SAP Promotion Pricing.

1. If you have completed [Exercise 1 - Loading products from SAP S/4HANA Cloud](exercises/ex1/) you will already have an instance of the destination service that is bound to your application. You can reuse this setup for the new destination.

2. Configure the SAP Promotion Pricing connection details. Open the SAP Cloud Platform Cockpit and navigate to the subaccount overview page, select `Destinations` from the menu on the left, then click `New Destination`. The destination configuration will appear. Enter the following details and click `Save`.
* Name: PROMOPRICING
* URL: https://mock-server-cx-spontaneous-fossa.cfapps.sap.hana.ondemand.com/sapomnichannelpromotionpricing/calculation
<br>![](/exercises/ex1/images/configure_destination.png)

3. Switch back to SAP Business Application Studio and deploy the application by running the following commands in the terminal.
```
cd ~/projects/teched2020-DT261
mvn package
cf push
```

Now you can open the deployed application in a new browser tab.

## Summary

You've now implemented the API call to SAP Omnichannel Promotion Pricing. To achieve this, you downloaded the API specification from SAP API Business Hub and used the SAP Cloud SDK client library generator to generate the classes for the API call. Finally you configured the destination in your IDE and on SAP Cloud Platform. 

As a bonus execercise continue to [Exercise 4 - Setup SAP Cloud Platform Continuous Integration and Delivery](../ex4/README.md)
