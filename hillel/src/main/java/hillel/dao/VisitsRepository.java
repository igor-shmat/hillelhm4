package hillel.dao;

import hillel.enity.Person;
import hillel.enity.Visits;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitsRepository extends JpaRepository<Visits, Long> {
    Visits findByPerson(Person person);
}
