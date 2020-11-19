package sap.teched.controllers;

import java.math.BigDecimal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sap.teched.models.WebShopProduct;

@RestController
@RequestMapping("/api/products")
public class ProductsController {

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<WebShopProduct[]> getProducts() {

		WebShopProduct[] products = { new WebShopProduct("DUMMY", "Dummy Product", new BigDecimal(0)) };

		return ResponseEntity.ok(products);
	}

}
