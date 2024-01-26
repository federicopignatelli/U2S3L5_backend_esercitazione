package federicopignatelli.U2S3L5_backend_esercitazione.controllers;

import federicopignatelli.U2S3L5_backend_esercitazione.entities.User;
import federicopignatelli.U2S3L5_backend_esercitazione.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UsersService usersService;

    @GetMapping
    public Page<User> getUsers(@RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "10") int size,
                               @RequestParam(defaultValue = "id") String orderBy) {
        return usersService.getUsers(page, size, orderBy);
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable UUID userId) {
        return usersService.findById(userId);
    }


    @PutMapping("/{userId}")
    public User getUserByIdAndUpdate(@PathVariable UUID userId, @RequestBody User modifiedUserPayload) {
        return usersService.findByIdAndUpdate(userId, modifiedUserPayload);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void getUserByIdAndDelete(@PathVariable UUID userId) {
        usersService.findByIdAndDelete(userId);
    }
}
