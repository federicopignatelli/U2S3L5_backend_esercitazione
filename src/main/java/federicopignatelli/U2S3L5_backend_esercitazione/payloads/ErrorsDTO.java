package federicopignatelli.U2S3L5_backend_esercitazione.payloads;

import java.time.LocalDateTime;

public record ErrorsDTO(String message, LocalDateTime timestamp) {
}
