package com.reserva.hotel.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
public @Data class Price {
    
    @ApiModelProperty(value = "adult", example = "5298.21")
    private Double adult;
    @ApiModelProperty(value = "child", example = "1625.07")
	private Double child;

}