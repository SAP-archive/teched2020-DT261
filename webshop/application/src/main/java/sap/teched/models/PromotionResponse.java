package sap.teched.models;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PromotionResponse {

	@JsonProperty
	private BigDecimal value;

	@JsonProperty("Action")
	private String action = "Subtract";

	public PromotionResponse(BigDecimal value) {
		this.value = value;
	}

	public PromotionResponse(String action, BigDecimal value) {
		this.action = action;
		this.value = value;
	}
}
