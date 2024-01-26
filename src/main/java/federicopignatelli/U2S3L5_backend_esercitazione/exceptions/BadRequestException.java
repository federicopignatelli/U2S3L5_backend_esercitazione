package federicopignatelli.U2S3L5_backend_esercitazione.exceptions;

import lombok.Getter;
import org.springframework.validation.ObjectError;

import java.util.List;

@Getter
public class BadRequestException extends RuntimeException {
	private List<ObjectError> errorsList;

	public BadRequestException(String message) {
		super(message);
	}

	public BadRequestException(List<ObjectError> errors) {
		this.errorsList = errors;
	}
}
