package com.ifpb.RedisContactBook.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ExistingEntityException extends RuntimeException{

    public ExistingEntityException(String message) {
		super(message);
	}
}