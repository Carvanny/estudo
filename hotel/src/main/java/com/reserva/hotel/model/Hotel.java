package com.reserva.hotel.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "Hotel", description = "Objeto de retorno para a pesquisa de reserva por hotel")
@JsonInclude(JsonInclude.Include.NON_NULL)
public @Data class Hotel implements Serializable{

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "id", example = "1")
	private Long id;
	@ApiModelProperty(value = "name", example = "Hotel Teste 2")
	private String name;
	@ApiModelProperty(value = "cityCode", example = "9626")
	private String cityCode;
	@ApiModelProperty(value = "cityName", example = "São Paulo")
	private String cityName;
	@ApiModelProperty(value = "rooms" )
	private List<Room> rooms;


}
