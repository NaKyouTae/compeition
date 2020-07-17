package com.mercury.common;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ControllerResponse<T> {
	private HttpStatus resultCode;
	private String message;
	private T result;
}
