package com.ifpb.RedisContactBook.exceptions;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import java.util.List;

@Getter
public class ExceptionResponse {

    private final Date timestamp;
	private final String message;
	private String details;
	@Setter
    private List<String> errors;

	public ExceptionResponse(Date timestamp, String message, String details) {
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}

	public ExceptionResponse(Date timestamp, String message) {
		this.timestamp = timestamp;
		this.message = message;
	}
}