package com.reserva.hotel.model;

import java.util.List;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
public @Data class ErrorResponse implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private String message;
    private List<String> details;

    public ErrorResponse(String message, List<String> details){
        message = this.message;
        details = this.details;
    }
}
