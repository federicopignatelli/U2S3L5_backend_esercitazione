package federicopignatelli.U2S3L5_backend_esercitazione.services;

import federicopignatelli.U2S3L5_backend_esercitazione.entities.User;
import federicopignatelli.U2S3L5_backend_esercitazione.exceptions.UnauthorizedException;
import federicopignatelli.U2S3L5_backend_esercitazione.payloads.users.UserLoginDTO;
import federicopignatelli.U2S3L5_backend_esercitazione.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UsersService usersService;

    @Autowired
    private JWTTools jwtTools;

    public String authenticateUser(UserLoginDTO body) {

        User user = usersService.findByEmail(body.email());
        if (body.password().equals(user.getPassword())) {
            return jwtTools.createToken(user);
        } else {
            throw new UnauthorizedException("Credenziali non valide!");
        }
    }
}
