package com.sqli.challenge.model;

public class Person {
    private String name;
    public Person(String name){
        this.name = name;
    }
    @Override
    public boolean equals(Object person){
        if(!(person instanceof  Person)) return false;
        return name.equals(((Person)person).name);
    }
}
