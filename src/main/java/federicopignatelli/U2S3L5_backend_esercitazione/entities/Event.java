package federicopignatelli.U2S3L5_backend_esercitazione.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue
    private UUID id;
    public String eventTitle;
    public String eventDescription;
    public LocalDate eventDate;
    public String location;
    public int availablePlaces;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user-id")
    @JsonIgnore
    private List<User> users;

}