package com.example.demo.dao;

import com.example.demo.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository("FakeDao")
public class FakePersonDao implements PersonDao {
    private static List<Person> DB = new ArrayList<>();

    @Override
    public int insertPerson(UUID id, Person person) {
        DB.add(new Person(id, person.getName()));
        return 1;
    }

    @Override
    public int deletePersonByID(UUID id) {
        Optional<Person> personMaybe = selectPersonByID(id);
        if (personMaybe.isEmpty()) {
            return -1;
        } else {
            DB.remove(personMaybe.get());
            return 1;
        }
    }

    @Override
    public int updatePersonByID(UUID id, Person personToBeUpdated) {
        return selectPersonByID(id).map(person -> {
            int index = DB.indexOf(person);
            if (index > -1) {
                DB.set(index, new Person(id, personToBeUpdated.getName()));
                return 1;
            } else {
                return -1;
            }
        }).orElse(-1);
    }

    @Override
    public Optional<Person> selectPersonByID(UUID uuid) {
        return DB.stream().filter(person -> person.getId().equals(uuid)).findFirst();
    }

    @Override
    public List<Person> selectAllPeople() {
        return DB;
    }
}
