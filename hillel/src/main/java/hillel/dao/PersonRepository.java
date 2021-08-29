package hillel.dao;

import hillel.enity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Person findByPhone(String phone);
    Person findByFio(String phone);
}
