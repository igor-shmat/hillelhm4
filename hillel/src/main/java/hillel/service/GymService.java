package hillel.service;

import hillel.dao.PersonRepository;
import hillel.dao.SubPersonRepository;
import hillel.dao.SubscriptionRepository;
import hillel.dao.VisitsRepository;
import hillel.dto.RegistrationRequest;
import hillel.dto.SubPersonRequest;
import hillel.enity.Person;
import hillel.enity.SubPerson;
import hillel.enity.Subscription;
import hillel.enity.Visits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class GymService {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private SubscriptionRepository subscriptionRepository;
    @Autowired
    private SubPersonRepository subPersonRepository;
    @Autowired
    private VisitsRepository visitsRepository;

    public void registerPerson(RegistrationRequest request) {
        LocalDateTime date = LocalDateTime.now();
        Person person = new Person();
        person.setDate(date);
        person.setFio(request.getFio());
        person.setPhone(request.getPhone());
        SubPerson subPerson = new SubPerson();
        subPerson.setStatus(false);
        subPerson.setPerson(person);
        subPerson.setBuyDate(null);
        subPerson.setSubscription(null);
        personRepository.save(person);
        subPersonRepository.save(subPerson);
    }

    public List<Subscription> getSubscriptions() {
        return subscriptionRepository.findAll();
    }

    public String buying(SubPersonRequest request) {
        Person person = personRepository.findByPhone(request.getPhone());
        SubPerson subPerson = subPersonRepository.findByPerson(person);
        if (subPerson.getStatus().equals(Boolean.TRUE) && subPerson.getTime() > 0) {
            return "У вас уже есть активный абонимент";
        }
        Subscription subscription = subscriptionRepository.getBySubType(request.getSubType());
        subPerson.setSubscription(subscription);
        subPerson.setStatus(true);
        subPerson.setTime(subscription.getSubTime());
        LocalDateTime date = LocalDateTime.now();
        subPerson.setBuyDate(date);
        subPersonRepository.save(subPerson);
        return "ОК";
    }

    public String gymEnter(SubPersonRequest subPersonRequest) {
        Person person = personRepository.findByPhone(subPersonRequest.getPhone());
        SubPerson subPerson = subPersonRepository.findByPerson(person);
        if (subPerson.getStatus().equals(Boolean.FALSE)) {
            return "Вы ещё не купили абонимент";
        }
        Visits visits = new Visits();
        visits.setPerson(personRepository.findByPhone(person.getPhone()));
        LocalDateTime date = LocalDateTime.now();
        visits.setEnter(date);
        visits.setSubscription(subPerson.getSubscription());
        visitsRepository.save(visits);
        return "Добро пожаловать";
    }

    public void gymExit(SubPersonRequest subPersonRequest) {
        Visits visits = visitsRepository.findByPerson(personRepository.findByPhone(subPersonRequest.getPhone()));
        LocalDateTime date = LocalDateTime.now();
        visits.setExit(date);
        visits.setTotal(ChronoUnit.MINUTES.between(visits.getEnter(), visits.getExit()));
        Person person = personRepository.findByPhone(subPersonRequest.getPhone());
        SubPerson subPerson = subPersonRepository.findByPerson(person);
        subPerson.setTime(subPerson.getTime() - visits.getTotal());
        visitsRepository.save(visits);
        subPersonRepository.save(subPerson);
    }

    public Object getTime(SubPersonRequest subPersonRequest) {
        SubPerson subPerson = subPersonRepository.findByPerson(personRepository.findByPhone(subPersonRequest.getPhone()));
        return subPerson.getTime();
    }
}



