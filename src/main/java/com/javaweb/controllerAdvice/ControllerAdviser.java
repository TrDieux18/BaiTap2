package com.javaweb.controllerAdvice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.javaweb.customexception.FieldRequiredException;
import com.javaweb.model.ErrorResponseDTO;

//import java.time.LocalDate;
import java.util.List;
//import java.util.Map;
import java.util.ArrayList;
//import java.util.LinkedHashMap;
//import java.util.stream.Collectors;

//import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
//import org.springframework.validation.DefaultMessageSourceResolvable;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.FieldError;

@ControllerAdvice
public class ControllerAdviser extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ArithmeticException.class)
	public ResponseEntity<Object> handleArithmeticException(ArithmeticException ex, WebRequest request) {
		ErrorResponseDTO errorResponseDTO =  new ErrorResponseDTO();
		errorResponseDTO.setError(ex.getMessage());
		List<String> details = new ArrayList<String>();
		details.add("Số nguyên chia cho 0!!!");
		errorResponseDTO.setDetail(details);
		return new ResponseEntity<>(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(FieldRequiredException.class)
	public ResponseEntity<Object> handleFieldRequiredException(FieldRequiredException ex, WebRequest request) {
		ErrorResponseDTO errorDTO =  new ErrorResponseDTO();
		errorDTO.setError(ex.getMessage());
		List<String> details = new ArrayList<String>();
		details.add("Dữ liệu nào đó bị null!!!");
		errorDTO.setDetail(details);
		return new ResponseEntity<>(errorDTO, HttpStatus.BAD_GATEWAY);
	}
	
	

}