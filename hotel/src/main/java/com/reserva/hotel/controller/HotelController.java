package com.reserva.hotel.controller;

import java.util.Date;
import java.util.Optional;

import javax.servlet.ServletContext;

import com.reserva.hotel.model.Hotel;
import com.reserva.hotel.model.Reserva;
import com.reserva.hotel.service.HotelService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "callback", description = "Responsável por buscar disponibilidade e preço de hotel", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, basePath = "/api/callback")
@RestController
@RequestMapping("/api/v0/hotels")
public class HotelController {

	// private static final Logger logger =
	// LoggerFactory.getLogger(HotelController.class);

	@Autowired
	HotelService hotelService;

	@Autowired
	ServletContext context;

	@ApiOperation(value = "Pesquisa por cityCode", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, response = Hotel.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sucesso"), @ApiResponse(code = 404, message = "Erro ao comunicar com o servidor")})
	@GetMapping(path = "/avail/teste1", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Hotel[]> findByCidade(@RequestParam(required = true) String cityCode,
											  @RequestParam("checkin") @DateTimeFormat(pattern="yyyy-MM-dd") Date checkin, 
											  @RequestParam("checkOut") @DateTimeFormat(pattern="yyyy-MM-dd") Date checkout, 
											  @RequestParam int qtdAdultos, 
											  @RequestParam int qtdCriancas) {
															
		try {

			Reserva reserva = new Reserva();
			reserva.setCityCode(cityCode);
			reserva.setCheckin(checkin);	
			reserva.setCheckout(checkout);
			reserva.setQtdAdultos(qtdAdultos);
			reserva.setQtdCriancas(qtdCriancas);

			Optional<Hotel[]> hotel = hotelService.findByCity(reserva);
			if (cityCode != null && hotel.isPresent()) {	

				return new ResponseEntity<Hotel[]>(hotel.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<Hotel[]>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return new ResponseEntity<Hotel[]>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Pesquisa por hotelID", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, response = Hotel.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sucesso"), @ApiResponse(code = 404, message = "Erro ao comunicar com o servidor")})
	@GetMapping(path = "/avail/teste2", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Hotel[]> findByHotel(@RequestParam(required = true) String hotelID, 
											  @RequestParam("checkin") @DateTimeFormat(pattern="yyyy-MM-dd") Date checkin, 
											  @RequestParam("checkOut") @DateTimeFormat(pattern="yyyy-MM-dd") Date checkout, 
											  @RequestParam int qtdAdultos, 
											  @RequestParam int qtdCriancas) {
		
												  
		try {

			Reserva reserva = new Reserva();
			reserva.setHotelID(Long.parseLong(hotelID));
			reserva.setCheckin(checkin);	
			reserva.setCheckout(checkout);
			reserva.setQtdAdultos(qtdAdultos);
			reserva.setQtdCriancas(qtdCriancas);

			Optional<Hotel[]> hotel = hotelService.findByHotel(reserva);
			if (hotelID != null && hotel.isPresent()) {
				return new ResponseEntity<Hotel[]>(hotel.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<Hotel[]>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return new ResponseEntity<Hotel[]>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}	
}