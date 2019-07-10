package com.reserva.hotel.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "Hotel", description = "Objeto de retorno para a pesquisa de reserva por hotel")
@JsonInclude(JsonInclude.Include.NON_NULL)
public @Data class Reserva implements Serializable{

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "hotelID", example = "1")
	private Long hotelID;
    
    @ApiModelProperty(value = "cityCode", example = "9626")
	private String cityCode;
    
    @ApiModelProperty(value = "checkin", example = "2019-07-08")
	private Date checkin;
    
    @ApiModelProperty(value = "checkout", example = "2019-07-10")
	private Date checkout;
    
    @ApiModelProperty(value = "qtdAdultos" )
    private Integer qtdAdultos;
    
    @ApiModelProperty(value = "qtdCriancas" )
    private Integer qtdCriancas;
}