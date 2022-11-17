package br.com.locadora.exception;



import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.locadora.dto.ApiErrorDTO;

@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler({ LocadoraException.class })
	public ResponseEntity<ApiErrorDTO> handleMyApiException(LocadoraException ex, WebRequest request) {
		String error = "Erro no sistema";
		ApiErrorDTO apiErro = new ApiErrorDTO(ex.getMessage(),error,HttpStatus.INTERNAL_SERVER_ERROR);
		
		return new ResponseEntity<ApiErrorDTO>(apiErro, new HttpHeaders(), apiErro.getStatus());
	}
	
	@ExceptionHandler({ EntityNotFoundException.class })
	public ResponseEntity<ApiErrorDTO> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {
		String error = "Recurso não encontrado";
		ApiErrorDTO apiErro = new ApiErrorDTO(ex.getMessage(),error,HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<ApiErrorDTO>(apiErro, new HttpHeaders(), apiErro.getStatus());
	}
	
}
