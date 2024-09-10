package com.example.demo.person;

import jakarta.persistence.EntityManager;
import jakarta.persistence.StoredProcedureQuery;
import org.aspectj.weaver.patterns.PerObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private EntityManager entityManager;

    public List<Person> getAllPersons() {return personRepository.findAll();}

    public void insertPerson(Person person) {
        StoredProcedureQuery query = entityManager.createNamedStoredProcedureQuery("new_person");
        query.setParameter("new_name", person.getPersonName());
        query.setParameter("new_first_name", person.getFirstName());
        query.setParameter("new_second_name", person.getSecondName());
        query.setParameter("new_user_name", person.getUserName());
        query.setParameter("new_birthdate", person.getBirthdate());
        query.setParameter("new_country", person.getCountry());
        query.setParameter("new_email", person.getEmail());
        query.setParameter("new_password", person.getPersonPassword());
        query.setParameter("new_image_link", person.getImageLink());
        query.execute();
    }
}
