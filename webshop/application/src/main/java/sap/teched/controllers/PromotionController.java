package sap.teched.controllers;

import java.math.BigDecimal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sap.teched.models.PromotionResponse;

@RestController
@RequestMapping("/api/promotion")
public class PromotionController {

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<PromotionResponse> calculatePromotion() {

		PromotionResponse response = new PromotionResponse(new BigDecimal(0));

		return ResponseEntity.ok(response);
	}
}
