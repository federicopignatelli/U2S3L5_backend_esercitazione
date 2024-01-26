package federicopignatelli.U2S3L5_backend_esercitazione.repositories;

import federicopignatelli.U2S3L5_backend_esercitazione.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EventDAO extends JpaRepository<Event, UUID> {
}
