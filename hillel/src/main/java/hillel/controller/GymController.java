package hillel.controller;

import hillel.dto.RegistrationRequest;
import hillel.dto.SubPersonRequest;
import hillel.enity.Subscription;
import hillel.service.GymService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GymController {
    @Autowired
    private GymService gymService;

    @PostMapping("register")
    public void regPerson(@RequestBody RegistrationRequest registrationRequest) {
        gymService.registerPerson(registrationRequest);
    }

    @GetMapping("getPrice")
    public List<Subscription> SubscriptionBuy() {
        return gymService.getSubscriptions();
    }

    @PostMapping("buying")
    public String buying(@RequestBody SubPersonRequest subPersonRequest) {
        return gymService.buying(subPersonRequest);
    }

    @PostMapping("gymEnter")
    public String gymEnter(@RequestBody SubPersonRequest subPersonRequest) {
        return gymService.gymEnter(subPersonRequest);
    }

    @PostMapping("gymExit")
    public void gymExit(@RequestBody SubPersonRequest subPersonRequest) {
        gymService.gymExit(subPersonRequest);
    }

    @GetMapping("getTime")
    public Object getTime(@RequestBody SubPersonRequest subPersonRequest) {
        return gymService.getTime(subPersonRequest);
    }
}

