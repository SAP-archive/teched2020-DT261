package sap.teched.models;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WebShopOrder {

	public static class LineItem {
		@JsonProperty
		private String productId;

		@JsonProperty
		private BigDecimal quantity;
		
		public String getProductId() {
			return productId;
		}
		
		public BigDecimal getQuantity() {
			return quantity;
		}
	}

	@JsonProperty
	private LineItem[] lineItems;
	
	public LineItem[] getLineItems() {
		return lineItems;
	}
}
