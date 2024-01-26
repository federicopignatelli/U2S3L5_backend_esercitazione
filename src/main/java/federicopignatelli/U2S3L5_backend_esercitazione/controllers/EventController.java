package federicopignatelli.U2S3L5_backend_esercitazione.controllers;

import federicopignatelli.U2S3L5_backend_esercitazione.entities.Event;
import federicopignatelli.U2S3L5_backend_esercitazione.exceptions.BadRequestException;
import federicopignatelli.U2S3L5_backend_esercitazione.payloads.NewEventDTO;
import federicopignatelli.U2S3L5_backend_esercitazione.payloads.NewEventResponseDTO;
import federicopignatelli.U2S3L5_backend_esercitazione.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/event")
public class EventController {
    @Autowired
    private EventService eventService;

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public NewEventResponseDTO save(@RequestBody @Validated NewEventDTO body, BindingResult validation) throws Exception {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors().toString());
        }
        Event newEvent = eventService.save(body);
        return new NewEventResponseDTO(newEvent.getId(), newEvent.getTitle(), newEvent.getDescription());
    }

    @GetMapping("")
    public Page<Event> getEvent(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "10") int size,
                                @RequestParam(defaultValue = "id") String sortBy) {
        return eventService.getEvent(page, size, sortBy);
    }

    @GetMapping("/{deviceId}")
    public Event findById(@PathVariable UUID deviceId) {
        return eventService.findById(deviceId);
    }

    @PutMapping("/{eventId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Event findByIdAndUpdate(@PathVariable UUID eventId, @RequestBody Event body) {
        return eventService.findByIdAndUpdate(eventId, body);
    }

    @DeleteMapping("/{eventId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable UUID eventId) {
        eventService.findByIdAndDelete(eventId);
    }
}
