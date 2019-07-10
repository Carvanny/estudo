package com.reserva.hotel.service;

import java.util.Optional;

import com.reserva.hotel.model.Hotel;
import com.reserva.hotel.model.Reserva;


public interface HotelService {

	public Optional<Hotel[]> findByCity(Reserva reserva);

	public Optional<Hotel[]> findByHotel(Reserva reserva);

}
