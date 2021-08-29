package hillel.enity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class SubPerson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Subscription subscription;
    @OneToOne
    private Person person;
    private LocalDateTime buyDate;
    private Long time;
    private Boolean status;
}
