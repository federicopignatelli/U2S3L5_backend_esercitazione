package federicopignatelli.U2S3L5_backend_esercitazione.controllers;

import federicopignatelli.U2S3L5_backend_esercitazione.entities.User;
import federicopignatelli.U2S3L5_backend_esercitazione.exceptions.BadRequestException;
import federicopignatelli.U2S3L5_backend_esercitazione.payloads.NewUserDTO;
import federicopignatelli.U2S3L5_backend_esercitazione.payloads.NewUserResponseDTO;
import federicopignatelli.U2S3L5_backend_esercitazione.payloads.UserLoginDTO;
import federicopignatelli.U2S3L5_backend_esercitazione.payloads.UserLoginResponseDTO;
import federicopignatelli.U2S3L5_backend_esercitazione.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public UserLoginResponseDTO login(@RequestBody UserLoginDTO body) {
        String accessToken = authService.authenticateUser(body);
        return new UserLoginResponseDTO(accessToken);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public NewUserResponseDTO createUser(@RequestBody @Validated NewUserDTO newUserPayload, BindingResult validation) {
        System.out.println(validation);
        if (validation.hasErrors()) {
            System.out.println(validation.getAllErrors());
            throw new BadRequestException("Ci sono errori nel payload!");
        } else {
            User newUser = authService.save(newUserPayload);

            return new NewUserResponseDTO(newUser.getId(), newUser.getName(), newUser.getSurname());
        }
    }
}
