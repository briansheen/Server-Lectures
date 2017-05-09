package com.example.service;

import com.example.domain.Person;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by bsheen on 5/9/17.
 */

public interface PersonService {

    void add(Person person);
    void add(List<Person> persons);
    List<Person> find();

}
