package com.example.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domain.Person;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonServiceTest {

    Random random = new Random();

    @Autowired
    PersonService personService;

    @Test
    public void testCreate() {
        Person person = this.createRandomPerson();
        personService.add(person);
        List<Person> persons = personService.find();
        Assert.assertNotNull(persons);
        Assert.assertTrue(persons.size() > 0);
        boolean found = false;
        for (Person p : persons) {
            if (p.equals(person)) {
                found = true;
                break;
            }
        }

        Assert.assertTrue("Could not find " + person, found);
    }

    @Test
    public void testCreateMultiple() {
        Person person = this.createRandomPerson();
        Person person2 = this.createRandomPerson();
        List<Person> people = new ArrayList<>();
        people.add(person);
        people.add(person2);
        personService.add(people);
        List<Person> persons = personService.find();
        Assert.assertNotNull(persons);
        Assert.assertTrue(persons.size() > 0);
        boolean found = false;
        for (Person p : persons) {
            if (p.equals(person)) {
                found = true;
                break;
            }
        }

        boolean found2 = false;
        for (Person p : persons) {
            if (p.equals(person2)) {
                found2 = true;
                break;
            }
        }

        Assert.assertTrue("Could not find " + person, found);
        Assert.assertTrue("Could not find second " + person2, found2);
    }

    @Test
    public void testCannotCreate() {
        Person person = this.createRandomPerson();
        // Too big
        person.setGender("MF");
        try {
            personService.add(person);
            Assert.fail("should not be able to add a person with gender=MF");
        } catch (DataIntegrityViolationException e) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testCannotCreateMultiple() {
        Person person = this.createRandomPerson();
        Person person2 = this.createRandomPerson();
        // Too big
        person2.setGender("MF");
        List<Person> people = new ArrayList<>();
        people.add(person);
        people.add(person2);
        try {
            personService.add(people);
            Assert.fail("should not be able to add a person with gender=MF");
        } catch (DataIntegrityViolationException e) {
            Assert.assertTrue(true);
        }

        List<Person> persons = personService.find();
        boolean found = false;
        for (Person p : persons) {
            if (p.equals(person)) {
                found = true;
                break;
            }
        }

        boolean found2 = false;
        for (Person p : persons) {
            if (p.equals(person2)) {
                found2 = true;
                break;
            }
        }

        Assert.assertTrue("Should not find " + person, !found);
        Assert.assertTrue("Should not find second " + person2, !found2);

    }

    private Person createRandomPerson() {
        Person person = new Person();
        person.setName(Integer.toString(random.nextInt()));
        person.setDob(LocalDate.now());
        person.setGender(random.nextBoolean() ? "F" : "M");
        return person;
    }

}

