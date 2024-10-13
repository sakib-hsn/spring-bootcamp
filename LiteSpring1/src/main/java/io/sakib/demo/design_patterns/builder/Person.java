package io.sakib.demo.design_patterns.builder;

import lombok.Builder;
import lombok.Data;

@Data
public class Person {
    private String name;
    private int age;

    public static class PersonBuilder {
        private String name;
        private int age;

        public PersonBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public PersonBuilder setAge(int age) {
            this.age = age;
            return this;
        }

        public Person build() {
            Person person = new Person();
            person.name = name;
            person.age = age;
            return person;
        }
    }
}
