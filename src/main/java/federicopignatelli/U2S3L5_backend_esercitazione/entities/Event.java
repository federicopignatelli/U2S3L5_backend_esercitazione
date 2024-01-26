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
    public String title;
    public String description;
    public LocalDate date;
    public String location;
    public int maxavailability;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @JoinColumn(name = "userid")
    @JsonIgnore
    private List<User> users;
}
