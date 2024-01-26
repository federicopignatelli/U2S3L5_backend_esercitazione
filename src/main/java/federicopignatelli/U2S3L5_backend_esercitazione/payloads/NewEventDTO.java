package federicopignatelli.U2S3L5_backend_esercitazione.payloads;

import java.time.LocalDate;
import java.util.UUID;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record NewEventDTO(
        UUID id,
        @NotEmpty(message = "titolo evento obbligatorio")
        String title,
        @NotEmpty(message = "descrizione evento obbligatorio")
        String description,
        @NotNull(message = "data obbligatoria")
        LocalDate date,
        @NotEmpty(message = "luogo evento obbligatorio")
        String location,
        int maxavailability
) {
}
