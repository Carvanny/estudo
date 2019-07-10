package com.reserva.hotel.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
public @Data class Room {

	@ApiModelProperty(value = "roomID", example = "0")
	private Long roomID;
	@ApiModelProperty(value = "categoryName", example = "Standard")
	private String categoryName;
	@ApiModelProperty(value = "price")
	private Price price;
	@ApiModelProperty(value = "totalPrice", example = "6923.27")
	private Double totalPrice;
	

}