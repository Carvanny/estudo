package com.reserva.hotel.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.reserva.hotel.model.Hotel;
import com.reserva.hotel.model.Reserva;
import com.reserva.hotel.utils.Constantes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HotelServiceImpl implements HotelService {
	
	private static final Logger logger = LoggerFactory.getLogger(HotelServiceImpl.class);

	/**
	 * Método de busca de Hotel por ID de Cidades retonar uma lista de hoteis com informações
	 */
	@Override
	public Optional<Hotel[]> findByCity(Reserva reserva) {

		Hotel[] novoResult = null;
		try {

			final String uri = Constantes.URL_HOTEL_CITY + reserva.getCityCode();
			Map<String, String> params = new HashMap<String, String>();
			params.put("id", String.valueOf(reserva.getCityCode()));
	
			RestTemplate restTemplate = new RestTemplate();
			Hotel[] result = restTemplate.getForObject(uri, Hotel[].class, params);
			//Metodo para calcular regra
			novoResult = calcularRegraComissao(result, verificarDatasHospedagem(reserva.getCheckin(), reserva.getCheckout()));
			
		} catch (Exception e) {
			logger.error("Validação falhou ****** ", e);
			
		}
		return Optional.of(novoResult);

	}

	/**
	 * Método de busca de Hotel por ID e retonar informações sobre o hotel
	 */
	@Override
	public Optional<Hotel[]> findByHotel(Reserva reserva) {

		Hotel[] novoResult = null;

		try{
			final String uri = Constantes.URL_HOTEL_ID + reserva.getHotelID();
			Map<String, String> params = new HashMap<String, String>();
			params.put("id", String.valueOf(reserva.getHotelID()));

			RestTemplate restTemplate = new RestTemplate();
			Hotel[] result = restTemplate.getForObject(uri, Hotel[].class, params);
			
			novoResult = calcularRegraComissao(result, verificarDatasHospedagem(reserva.getCheckin(), reserva.getCheckout()));
		} catch (Exception e) {
			logger.error("Validação falhou ****** ", e);
			
		}
		return Optional.of(novoResult);
	}	

	private Hotel[] calcularRegraComissao(Hotel[] hoteis, int dias) {
		
		Arrays.asList(hoteis).forEach(hotel -> {
			hotel.getRooms().forEach(room -> {
				//Pegar a quantidade de dias e multiplicar pelo valor do Adulto
				room.getPrice().setAdult(room.getPrice().getAdult() * dias);

				//Pegar a quantidade de dias e multiplicar pelo valor do Criança
				room.getPrice().setChild(room.getPrice().getChild() * dias);

				// Atualiza os valores incluindo a comissão.
				room.getPrice().setAdult(calculaComissao(room.getPrice().getAdult()));
				room.getPrice().setChild(calculaComissao(room.getPrice().getChild()));

				//Somar tudo e você terá o totalPrice
				BigDecimal bd = new BigDecimal(room.getPrice().getAdult() + room.getPrice().getChild()).setScale(2, RoundingMode.DOWN);	
				room.setTotalPrice(bd.doubleValue());
			});
		});		

		return hoteis;
	}

	/**
	 * Calcula o valor da comissão ({valorViagemAdulto}/0.7)  ou ({valorViagemCrianca}/0.7) 
	 * @param valor
	 * @return
	 */
	private double calculaComissao(Double valor) {
		Double val = valor/Constantes.COMISSAO;
		BigDecimal bd = new BigDecimal(val).setScale(2, RoundingMode.DOWN);		
		return  bd.doubleValue();
	}

		/**
	 * Verifica data de checkin e checkout
	 * Calcular numero de dias entre datas
	 * @param checkin
	 * @param checkout
	 */
	private Integer verificarDatasHospedagem(Date checkin, Date checkout) {
		if(checkout.compareTo(checkin) < 0){
			throw new IllegalArgumentException("Checkin é maior que checkout");
		}else{
			int dias=(int) ((checkout.getTime()-checkin.getTime())/Constantes.DIA);
			return dias;
		}
	}

}
