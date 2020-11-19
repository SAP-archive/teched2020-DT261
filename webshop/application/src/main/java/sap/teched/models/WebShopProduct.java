package sap.teched.models;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WebShopProduct {

	@JsonProperty
	private String id;

	@JsonProperty
	private String description;

	@JsonProperty
	private BigDecimal price;
	
	public WebShopProduct(String id, String description, BigDecimal price) {
		this.id = id;
		this.description = description;
		this.price = price;
	}
}
