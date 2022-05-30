package com.example.demo.api;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/person")
@RestController
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(@Qualifier("DefaultPersonService") PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public void addPerson(@RequestBody @Valid @NotNull Person person) {
        personService.insertPerson(person);
    }

    @GetMapping()
    public List<Person> getAllPerson() {
        return personService.getAllPeople();
    }

    @GetMapping(path = "{id}")
    public Person getPersonByID(@PathVariable("id") UUID uuid) {
        return personService.getPersonByID(uuid).orElse(null);
    }

    @PutMapping(path = "{id}")
    public void updatePerson(@PathVariable("id") UUID uuid, @Valid @NotNull @RequestBody Person person) {
        personService.updatePersonByID(uuid, person);
    }

    @DeleteMapping(path = "{id}")
    public void deletePerson(@PathVariable("id") UUID uuid) {
        personService.deletePersonByID(uuid);
    }

}
