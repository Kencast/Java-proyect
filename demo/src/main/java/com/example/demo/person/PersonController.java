package com.example.demo.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping("/get")
    public List<Person> getAllPersons() {
        return personService.getAllPersons();
    }

    @PostMapping("/create")
    public ResponseEntity<String> createPerson(@RequestBody Person person) {
        try{
            System.out.println(person.getEmail());
            System.out.println(person.getBirthdate());
            personService.insertPerson(person);
            return ResponseEntity.ok("ok");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating user."+e.getMessage());
        }
    }

}
