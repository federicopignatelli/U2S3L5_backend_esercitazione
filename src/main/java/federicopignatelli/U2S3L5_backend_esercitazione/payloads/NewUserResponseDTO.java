package federicopignatelli.U2S3L5_backend_esercitazione.payloads;

import java.util.UUID;

public record NewUserResponseDTO(UUID id, String name, String surname) {
}
