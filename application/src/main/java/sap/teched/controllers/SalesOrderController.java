package sap.teched.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sap.cloud.sdk.cloudplatform.connectivity.DestinationAccessor;
import com.sap.cloud.sdk.cloudplatform.connectivity.HttpDestination;
import com.sap.cloud.sdk.datamodel.odata.helper.ModificationResponse;
import com.sap.cloud.sdk.s4hana.datamodel.odata.namespaces.salesorder.SalesOrder;
import com.sap.cloud.sdk.s4hana.datamodel.odata.namespaces.salesorder.SalesOrderItem;
import com.sap.cloud.sdk.s4hana.datamodel.odata.services.DefaultSalesOrderService;

import sap.teched.models.WebShopOrder;

@RestController
@RequestMapping("/api/sales-order")
public class SalesOrderController {

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> placeOrder(@RequestBody WebShopOrder webShopOrder) {
        String salesOrderType = "OR"; // standard order
		String soldToParty = "17100001"; // domestic customer
		HttpDestination destination = DestinationAccessor.getDestination("S4HANA").asHttp();
		
		SalesOrder salesOrder = buildSalesOrder(webShopOrder, salesOrderType, soldToParty);
		ModificationResponse<SalesOrder> s4response = new DefaultSalesOrderService()
				.createSalesOrder(salesOrder)
				.executeRequest(destination);
				
		System.out.println("Received response from SAP S/4HANA Cloud: HTTP " + s4response.getResponseStatusCode());

		return ResponseEntity.status(s4response.getResponseStatusCode()).build();
	}

	private SalesOrder buildSalesOrder(WebShopOrder webShopOrder, String salesOrderType, String soldToParty) {
		List<SalesOrderItem> s4items = buildItems(webShopOrder);
		
		SalesOrder salesOrder = SalesOrder.builder()
				.salesOrderType(salesOrderType)
				.soldToParty(soldToParty)
				.item(s4items.toArray(new SalesOrderItem[s4items.size()]))
				.build();
		return salesOrder;
	}

	private List<SalesOrderItem> buildItems(WebShopOrder webShopOrder) {
		List<SalesOrderItem> s4items = new ArrayList<>();
		for (WebShopOrder.LineItem webShopItem : webShopOrder.getLineItems()) {
			SalesOrderItem s4Item = SalesOrderItem.builder()
					.material(webShopItem.getProductId())
					.requestedQuantity(webShopItem.getQuantity())
					.build();
			s4items.add(s4Item);
		}
		return s4items;
	}
}
