package federicopignatelli.U2S3L5_backend_esercitazione.services;

import federicopignatelli.U2S3L5_backend_esercitazione.entities.Event;
import federicopignatelli.U2S3L5_backend_esercitazione.exceptions.NotFoundException;
import federicopignatelli.U2S3L5_backend_esercitazione.payloads.NewEventDTO;
import federicopignatelli.U2S3L5_backend_esercitazione.repositories.EventDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EventService {
    @Autowired
    private EventDAO eventdao;

    public Event save(NewEventDTO body){

        Event newEvent = new Event();
        newEvent.setTitle(body.title());
        newEvent.setDescription(body.description());
        newEvent.setDate(body.date());
        newEvent.setLocation(body.location());
        newEvent.setMaxavailability(body.maxavailability());

        return eventdao.save(newEvent);
    }

    public Page<Event> getEvent(int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return eventdao.findAll(pageable);
    }

    public Event findById(UUID id) {
        return eventdao.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Event findByIdAndUpdate(UUID id, Event body) {

        Event found = this.findById(id);

        found.setTitle(body.getTitle());
        found.setDescription(body.getDescription());
        found.setDate(body.getDate());
        found.setLocation(body.getLocation());
        found.setMaxavailability(body.getMaxavailability());

        return eventdao.save(found);
    }

    public void findByIdAndDelete(UUID id) {
        Event found = this.findById(id);
        eventdao.delete(found);
    }

}
