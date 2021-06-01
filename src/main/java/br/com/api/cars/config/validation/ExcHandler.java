package br.com.api.cars.config.validation;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExcHandler {
	
	@Autowired
	private MessageSource messageSource;
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<FormDTOExceptionResponse> erroForm(MethodArgumentNotValidException exc) {
		List<FormDTOExceptionResponse> dto = new ArrayList<>();
		List<FieldError> fe = exc.getBindingResult().getFieldErrors();
		fe.forEach(e -> {
			String msg = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			FormDTOExceptionResponse erro = new FormDTOExceptionResponse(e.getField(), msg);
			dto.add(erro);
		});
		return dto;
	}
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public DTOExceptionResponse erroSQL(SQLIntegrityConstraintViolationException exc){
		return new DTOExceptionResponse("Este chassi já foi inserido.");
	}
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(DTOException.class)
	public DTOExceptionResponse erroSQL(DTOException exc){
		return new DTOExceptionResponse(exc.getMessage());
	}
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public DTOExceptionResponse erroSQL(HttpMessageNotReadableException exc){
		return new DTOExceptionResponse(exc.getMessage());
	}
}
