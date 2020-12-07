package sap.teched.controllers;

import java.math.BigDecimal;

import com.sap.cloud.sdk.cloudplatform.connectivity.DestinationAccessor;
import com.sap.cloud.sdk.cloudplatform.connectivity.HttpDestination;

import com.sap.cloud.sdk.generated.promopricing.api.CalculationApi;
import com.sap.cloud.sdk.generated.promopricing.model.LineItemDomainSpecific;
import com.sap.cloud.sdk.generated.promopricing.model.Percent.ActionEnum;
import com.sap.cloud.sdk.generated.promopricing.model.PriceCalculate;
import com.sap.cloud.sdk.generated.promopricing.model.PriceCalculateResponse;
import com.sap.cloud.sdk.generated.promopricing.model.ShoppingBasketBase;

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

		HttpDestination destination = DestinationAccessor.getDestination("PROMOPRICING").asHttp();
		String tenantName = "techedtenant";

		CalculationApi calculationApi = new CalculationApi(destination);
		PriceCalculateResponse ppResponse = calculationApi.calculateViaRestWithTenant(tenantName, new PriceCalculate());
		PromotionResponse response = convertResponse(ppResponse);

		return ResponseEntity.ok(response);
    }
    
    private PromotionResponse convertResponse(PriceCalculateResponse ppResponse) {
		ShoppingBasketBase shoppingBasket = ppResponse.getPriceCalculateBody().get(0).getShoppingBasket();
		BigDecimal promoValue = new BigDecimal(0);
		for(LineItemDomainSpecific lineItem: shoppingBasket.getLineItem()) {
			promoValue = promoValue.add(lineItem.getSale().getRetailPriceModifier().get(0).getPercent().getValue());
		}
		promoValue = promoValue.divide(new BigDecimal(shoppingBasket.getLineItem().size()));

		return new PromotionResponse(ActionEnum.SUBTRACT.toString(), promoValue);
	}
}
