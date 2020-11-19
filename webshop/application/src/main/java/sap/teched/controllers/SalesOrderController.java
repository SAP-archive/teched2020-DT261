package sap.teched.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sap.teched.models.WebShopOrder;

@RestController
@RequestMapping("/api/sales-order")
public class SalesOrderController {

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> placeOrder(@RequestBody WebShopOrder webShopOrder) {

		return ResponseEntity.created(null).build();
	}

}
