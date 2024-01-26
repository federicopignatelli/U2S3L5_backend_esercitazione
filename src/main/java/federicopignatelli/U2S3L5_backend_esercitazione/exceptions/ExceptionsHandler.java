package federicopignatelli.U2S3L5_backend_esercitazione.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import federicopignatelli.U2S3L5_backend_esercitazione.payloads.users.ErrorsDTO;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionsHandler {
	@ExceptionHandler(BadRequestException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorsDTO handleBadRequest(BadRequestException ex) {
		return new ErrorsDTO(ex.getMessage(), LocalDateTime.now());
	}

	@ExceptionHandler(UnauthorizedException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public ErrorsDTO handleUnauthorized(UnauthorizedException e) {
		return new ErrorsDTO(e.getMessage(), LocalDateTime.now());
	}

	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorsDTO handleNotFound(NotFoundException ex) {
		return new ErrorsDTO(ex.getMessage(), LocalDateTime.now());
	}


	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorsDTO handleGenericError(Exception ex) {
		ex.printStackTrace();
		return new ErrorsDTO("Problema lato server! Giuro che risolveremo presto!", LocalDateTime.now());
	}
}
