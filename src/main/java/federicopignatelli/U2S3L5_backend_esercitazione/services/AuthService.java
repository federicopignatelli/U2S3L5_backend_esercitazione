package federicopignatelli.U2S3L5_backend_esercitazione.services;

import federicopignatelli.U2S3L5_backend_esercitazione.entities.Role;
import federicopignatelli.U2S3L5_backend_esercitazione.entities.User;
import federicopignatelli.U2S3L5_backend_esercitazione.exceptions.UnauthorizedException;
import federicopignatelli.U2S3L5_backend_esercitazione.exceptions.BadRequestException;
import federicopignatelli.U2S3L5_backend_esercitazione.payloads.NewUserDTO;
import federicopignatelli.U2S3L5_backend_esercitazione.payloads.UserLoginDTO;
import federicopignatelli.U2S3L5_backend_esercitazione.repositories.UsersDAO;
import federicopignatelli.U2S3L5_backend_esercitazione.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UsersService usersService;

    @Autowired
    private UsersDAO usersDAO;

    @Autowired
    private JWTTools jwtTools;

    @Autowired
    private PasswordEncoder bcrypt;

    public String authenticateUser(UserLoginDTO body) {

        User user = usersService.findByEmail(body.email());
        if (bcrypt.matches(body.password(), user.getPassword())) {
            return jwtTools.createToken(user);
        } else {
            throw new UnauthorizedException("Credenziali non valide!");
        }
    }

    public User save(NewUserDTO body) {
        usersDAO.findByEmail(body.email()).ifPresent(user -> {
            throw new BadRequestException("L'email " + user.getEmail() + " è già in uso!");
        });

        User newUser = new User();
        newUser.setSurname(body.surname());
        newUser.setName(body.name());
        newUser.setEmail(body.email());
        newUser.setPassword(bcrypt.encode(body.password()));
        newUser.setRole(Role.USER);
        return usersDAO.save(newUser);
    }
}
