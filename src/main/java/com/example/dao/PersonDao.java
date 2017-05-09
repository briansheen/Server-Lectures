package com.example.dao;

import java.util.List;

import com.example.domain.Person;

public interface PersonDao {
    void add(Person person);
    List<Person> find();
}
