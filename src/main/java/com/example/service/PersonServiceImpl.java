package com.example.service;

import com.example.dao.PersonDao;
import com.example.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by bsheen on 5/9/17.
 */

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonDao personDao; //Dependency Injection

    @Override
    @Transactional
    public void add(Person person) {
        personDao.add(person);
    }

    @Override
    @Transactional
    public void add(List<Person> persons) {
        personDao.add(persons);
    }

    @Override
    public List<Person> find() {
        return personDao.find();
    }
}
