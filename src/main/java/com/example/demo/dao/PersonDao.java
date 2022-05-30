package com.example.demo.dao;

import com.example.demo.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonDao {
    int insertPerson(UUID id, Person person);
    List<Person> selectAllPeople();
    int deletePersonByID(UUID id);
    int updatePersonByID(UUID id, Person personToBeUpdated);
    Optional<Person> selectPersonByID(UUID uuid);

    default int insertPerson(Person person) {
        UUID id = UUID.randomUUID();
        return insertPerson(id, person);
    }
}
