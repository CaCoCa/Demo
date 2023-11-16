package com.example.demo.reflection;

public class Tests {

    public static void main(String[] args) {
        Class<Animal> animalClass = Animal.class;
        Class<?>[] classes = animalClass.getClasses();



    }
}
