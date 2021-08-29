package hillel.enity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class Visits {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime enter;
    private LocalDateTime exit;
    private long total;
    @ManyToOne
    private Subscription subscription;
    @ManyToOne
    private Person person;
}
