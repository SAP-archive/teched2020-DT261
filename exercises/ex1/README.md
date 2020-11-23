# Exercise 1 - Loading products from SAP S/4HANA Cloud

In this exercise, we will create...

## Implement the products controller

After completing these steps you will have created...

1. Open the class ProductsController in your IDE and locate the method `getProducts()`.

2.	Insert this lines of code as method implementation.
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

		return ResponseEntity.ok(products.toArray(new WebShopProduct[products.size()]));
```

3. Make sure there are no compile errors and save the file by hitting `CTRL + S` on your keyboard.


## Configure the destination in your IDE

After completing these steps you will have...

1.	Enter this code.
```abap
DATA(lt_params) = request->get_form_fields(  ).
READ TABLE lt_params REFERENCE INTO DATA(lr_params) WITH KEY name = 'cmd'.
  IF sy-subrc <> 0.
    response->set_status( i_code = 400
                     i_reason = 'Bad request').
    RETURN.
  ENDIF.

```

2.	Click here.
<br>![](/exercises/ex1/images/01_02_0010.png)


## Summary

You've now ...

Continue to - [Exercise 2 - Exercise 2 Description](../ex2/README.md)

