package hillel.dao;

import hillel.enity.Person;
import hillel.enity.SubPerson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubPersonRepository extends JpaRepository<SubPerson, Long> {
    SubPerson findByPerson(Person person);
}

