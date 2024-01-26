package federicopignatelli.U2S3L5_backend_esercitazione.payloads.users;

import java.util.Date;
import java.util.List;

public record ErrorsResponseWithListDTO(String message,
                                        Date timestamp,
                                        List<String> errorsList) {
}
