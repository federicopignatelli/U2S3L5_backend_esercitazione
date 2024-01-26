package federicopignatelli.U2S3L5_backend_esercitazione.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import federicopignatelli.U2S3L5_backend_esercitazione.entities.User;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsersDAO extends JpaRepository<User, UUID> {
	Optional<User> findByEmail(String email);
}
