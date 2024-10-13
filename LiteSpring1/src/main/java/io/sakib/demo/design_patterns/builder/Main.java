package io.sakib.demo.design_patterns.builder;

public class Main {
    public static void main(String[] args) {
        Person person = new Person.PersonBuilder()
                .setName("Sakib")
                .setAge(26)
                .build();

        System.out.println("person = " + person);
    }
}
